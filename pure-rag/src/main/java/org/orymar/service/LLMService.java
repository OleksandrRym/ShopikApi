package org.orymar.service;


import org.orymar.repository.VectorRepository;
import org.postgresql.util.PGobject;

import java.net.*;
import java.io.*;
import java.sql.*;
import java.util.*;

import static org.orymar.config.ApplicationProps.*;
import static org.orymar.utill.JSONUtills.escapeJson;

public class LLMService {


    public String askLLM(String question) throws Exception {
        float[] questionEmbedding = getEmbedding(question);

        List<String> relevantDocs = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT content FROM documents ORDER BY embedding <-> ?::vector LIMIT 5";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                StringBuilder sb = new StringBuilder("[");
                for (int i = 0; i < questionEmbedding.length; i++) {
                    sb.append(questionEmbedding[i]);
                    if (i < questionEmbedding.length - 1) sb.append(",");
                }
                sb.append("]");

                PGobject vectorObj = new PGobject();
                vectorObj.setType("vector");
                vectorObj.setValue(sb.toString());

                stmt.setObject(1, vectorObj);

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    relevantDocs.add(rs.getString("content"));
                }
            }
        }

        StringBuilder context = new StringBuilder();
        for (String doc : relevantDocs) {
            context.append(doc).append("\n---\n");
        }

        String combinedPrompt = "You are an assistant. Use the context below to answer the question.\n\nContext:\n"
                + context.toString() + "\nQuestion:\n" + question;

        String payload = "{\"model\":\"" + LLM_MODEL + "\",\"prompt\":\"" + escapeJson(combinedPrompt) + "\",\"stream\":false}";

        HttpURLConnection conn = (HttpURLConnection) new URL(GENERATE_URL).openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");

        try (OutputStream os = conn.getOutputStream()) {
            os.write(payload.getBytes());
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
        }

        String resp = response.toString();
        int idx = resp.indexOf("\"response\":\"");
        if (idx < 0) return resp;

        int start = idx + 12;
        int end = resp.indexOf("\"", start);
        return resp.substring(start, end);
    }



    public static float[] getEmbedding(String text) throws Exception {
        String payload = "{\"model\":\"" + EMBEDDING_MODEL + "\",\"prompt\":\"" + escapeJson(text) + "\"}";

        HttpURLConnection conn = (HttpURLConnection) new URL(EMBEDDING_URL).openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");

        try (OutputStream os = conn.getOutputStream()) {
            os.write(payload.getBytes());
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
        }

        String resp = response.toString();
        int idx = resp.indexOf("\"embedding\":[");
        if (idx < 0) throw new RuntimeException("Embedding not found");

        int start = idx + 13;
        int end = resp.indexOf("]", start);
        String[] parts = resp.substring(start, end).split(",");
        float[] embedding = new float[parts.length];
        for (int i = 0; i < parts.length; i++) {
            embedding[i] = Float.parseFloat(parts[i]);
        }
        return embedding;
    }

    public static void saveDocument(String content) throws Exception {
        float[] embedding = getEmbedding(content);
        VectorRepository vectorRepository = new VectorRepository();
        vectorRepository.saveDocument(embedding ,content);
    }
}
