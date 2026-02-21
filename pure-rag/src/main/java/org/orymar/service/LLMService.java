package org.orymar.service;

import org.orymar.repository.VectorRepository;
import org.orymar.utill.JSONUtills;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;

import static org.orymar.config.ApplicationProps.*;
import static org.orymar.utill.JSONUtills.escape;
import static org.orymar.utill.VectorUtills.parseEmbedding;

public class LLMService {

    private final VectorRepository vectorRepository = new VectorRepository();
    private final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public String ask(String question) {
        try {
            float[] embedding = getEmbedding(question);
            List<String> relevantDocs = vectorRepository.findRelevantDocuments(embedding);

            String contextText = buildContext(relevantDocs);
            String prompt = buildPrompt(contextText, question);

            String rawResponse = callLLM(prompt);

            return JSONUtills.extractResponse(rawResponse);

        } catch (Exception e) {
            e.printStackTrace();
            return "no content";
        }
    }



    public void save(String content) throws Exception {
        float[] embedding = getEmbedding(content);
        vectorRepository.saveDocument(embedding, content);
    }

    public float[] getEmbedding(String text) throws Exception {
        String payload = """
                {
                  "model": "%s",
                  "prompt": "%s"
                }
                """.formatted(EMBEDDING_MODEL, escape(text));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(EMBEDDING_URL))
                .timeout(Duration.ofSeconds(30))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("Embedding API error: " + response.body());
        }

        return parseEmbedding(response.body());
    }

    private String buildContext(List<String> docs) {
        return String.join("\n---\n", docs);
    }

    private String buildPrompt(String context, String question) {
        return """
                You are an assistant. Use the context below to answer the question.

                Context:
                %s

                Question:
                %s
                """.formatted(context, question);
    }

    private String callLLM(String prompt) throws Exception {
        String payload = """
                {
                  "model": "%s",
                  "prompt": "%s",
                  "stream": false
                }
                """.formatted(LLM_MODEL, escape(prompt));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GENERATE_URL))
                .timeout(Duration.ofSeconds(60))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("LLM API error: " + response.body());
        }

        return response.body();
    }


}