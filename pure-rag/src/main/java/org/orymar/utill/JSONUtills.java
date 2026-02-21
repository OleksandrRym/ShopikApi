package org.orymar.utill;

public class JSONUtills {
    public static String escape(String text) {
        return text.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n");
    }

    public static String extractResponse(String rawResponse) {
        int idx = rawResponse.indexOf("\"response\":\"");
        if (idx < 0) return rawResponse;

        int start = idx + 12;
        int end = rawResponse.indexOf("\"", start);
        return rawResponse.substring(start, end);
    }
}
