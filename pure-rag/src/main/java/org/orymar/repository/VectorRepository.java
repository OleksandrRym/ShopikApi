package org.orymar.repository;

import org.postgresql.util.PGobject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import static org.orymar.config.ApplicationProps.*;

public class VectorRepository {
    private final String INSERT_QUERY = "INSERT INTO documents(content, embedding) VALUES (?, ?)";
    private final String SELECT_SQL = "SELECT content FROM documents ORDER BY embedding <-> ?::vector LIMIT 5";

    public void saveDocument( float[] embedding , String content ){
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            try (PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY)) {

                StringBuilder sb = new StringBuilder("[");
                for (int i = 0; i < embedding.length; i++) {
                    sb.append(embedding[i]);
                    if (i < embedding.length - 1) sb.append(",");
                }
                sb.append("]");

                PGobject vectorObj = new PGobject();
                vectorObj.setType("vector");
                vectorObj.setValue(sb.toString());

                stmt.setString(1, content);
                stmt.setObject(2, vectorObj);

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<String> findRelevantDocuments(float[] embedding) throws Exception {
        List<String> docs = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

            try (PreparedStatement stmt = conn.prepareStatement(SELECT_SQL)) {

                stmt.setObject(1, createVectorObject(embedding));

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    docs.add(rs.getString("content"));
                }
            }
        }
        return docs;
    }

    private PGobject createVectorObject(float[] embedding) throws Exception {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < embedding.length; i++) {
            sb.append(embedding[i]);
            if (i < embedding.length - 1) sb.append(",");
        }
        sb.append("]");

        PGobject vectorObj = new PGobject();
        vectorObj.setType("vector");
        vectorObj.setValue(sb.toString());

        return vectorObj;
    }
}
