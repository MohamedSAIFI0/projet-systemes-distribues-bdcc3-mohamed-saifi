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
