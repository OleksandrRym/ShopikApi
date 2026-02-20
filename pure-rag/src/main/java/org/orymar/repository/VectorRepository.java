package org.orymar.repository;

import org.postgresql.util.PGobject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.orymar.config.ApplicationProps.*;

public class VectorRepository {

    public void saveDocument( float[] embedding , String content ){
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO documents(content, embedding) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

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
}
