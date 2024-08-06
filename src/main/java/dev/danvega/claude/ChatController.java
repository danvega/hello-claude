package dev.danvega.claude;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatClient chatClient;
    private final GeneratedCodeService codeService;

    public ChatController(ChatClient.Builder builder, GeneratedCodeService codeService) {
        this.chatClient = builder
                .defaultSystem("""
                You are helpful AI assistant for writing code. Each class or method you are
                asked to generate should have a supporting test class to cover that method or
                methods. Please include each test in the result.
                Please generate concise and readable code geared towards beginners.
                """)
                .build();
        this.codeService = codeService;
    }

    @GetMapping("/")
    public Code chat() {
        Code code = chatClient.prompt()
                .user("""
                        Generate a Java class that contains math operations.
                        Please contain more than just the basic 4 arithmetic operations.
                        """)
                .call()
                .entity(Code.class);


        System.out.println(code.code());
        System.out.println(code.test());

        codeService.writeToFile(code.code());
        codeService.writeToFile(code.test());

        return code;
    }

}
