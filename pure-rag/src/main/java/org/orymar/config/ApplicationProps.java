package org.orymar.config;

/// it's all local , so it's !matter
/// don't make this in prod :)
public class ApplicationProps {
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    public static final String DB_USER = "postgres";
    public static final String DB_PASSWORD = "12345";
    public static final String LLM_MODEL = "mistral";
    public static final String EMBEDDING_MODEL = "nomic-embed-text";
    public static final String GENERATE_URL = "http://localhost:11434/api/generate";
    public static final String EMBEDDING_URL = "http://localhost:11434/api/embeddings";
}
