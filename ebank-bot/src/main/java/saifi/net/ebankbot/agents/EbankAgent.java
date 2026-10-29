package saifi.net.ebankbot.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Service
public class EbankAgent {
    private ChatClient chatClient;

    public EbankAgent(ChatClient.Builder chatClient,
                      ToolCallbackProvider tools,
                      ChatMemory chatMemory){
        this.chatClient = chatClient
                .defaultSystem("""
                        Vous etes un assistant qui se charge de repondre aux question 
                        de l'utilisateur a propos des clients et des comptes bancaires, en fonction du contexte fournie
                        Si aucun contexte n'est fourni, repond avec JE NE SAIS PAS
                        """)
                .defaultOptions(OpenAiChatOptions.builder()
                        .extraBody(Map.of("include_reasoning", false)))   // no .build() here
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultTools(tools)
                .build();                                                 // this .build() is the ChatClient's
    }

    public String chat(String query, String conversationId) {
        return chatClient.prompt()
                .user(query)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call()
                .content();
    }
}
