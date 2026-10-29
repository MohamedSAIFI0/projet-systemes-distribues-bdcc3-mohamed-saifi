package saifi.net.ebankbot.controllers;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import saifi.net.ebankbot.agents.EbankAgent;

@RestController
public class EbankChatbotController {
    private EbankAgent ebankAgent;

    public EbankChatbotController(EbankAgent ebankAgent){
        this.ebankAgent = ebankAgent;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam(name = "query", defaultValue = "Bonjour") String query,
                       @RequestParam(name = "conversationId", defaultValue = "default") String conversationId) {
        return ebankAgent.chat(query, conversationId);
    }

}
