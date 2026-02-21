package org.orymar.utill;



public class VectorUtills {
    public static float[] parseEmbedding(String jsonResponse) {
        int idx = jsonResponse.indexOf("\"embedding\":[");
        if (idx < 0) throw new RuntimeException("Embedding not found");

        int start = idx + 13;
        int end = jsonResponse.indexOf("]", start);
        String[] parts = jsonResponse.substring(start, end).split(",");

        float[] embedding = new float[parts.length];
        for (int i = 0; i < parts.length; i++) {
            embedding[i] = Float.parseFloat(parts[i]);
        }
        return embedding;
    }
}
