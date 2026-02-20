package org.orymar;

import org.orymar.service.LLMService;

public class Main {
    public static void main(String[] args) {
        try {
            LLMService service = new LLMService();
            String sss =  service.askLLM("шо скажеш про * (все що знаєш");
            System.out.println(sss);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
