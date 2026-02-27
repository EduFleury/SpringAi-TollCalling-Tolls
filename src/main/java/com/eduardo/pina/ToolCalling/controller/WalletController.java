package com.eduardo.pina.ToolCalling.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.model.tool.ToolCallingChatOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class WalletController {

    private final ChatClient chatClient;


    public WalletController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.defaultAdvisors(new SimpleLoggerAdvisor()).build();
    }

    @GetMapping("/wallet")
    String calculateWalletValue(){
        PromptTemplate template = new PromptTemplate("""
            What’s the current value in dollars of my wallet based on the latest stock daily prices?
            To improve readability, add tables and line breaks when deemed necessary.
            """);

        return this.chatClient.prompt(template.create(
                        ToolCallingChatOptions.builder()
                                .toolNames("numberOffShares", "latestStockPrice")
                                .build()))
                .call().content();
    }
}
