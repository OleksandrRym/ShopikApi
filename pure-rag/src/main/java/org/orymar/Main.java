package org.orymar;

import org.orymar.service.LLMService;

public class Main {
    public static void main(String[] args) throws Exception {
            LLMService service = new LLMService();
          var s =  service.ask("яка остання версія джави");
        System.out.println(s);

    }
}
