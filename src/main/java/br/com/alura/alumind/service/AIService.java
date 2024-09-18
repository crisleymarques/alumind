package br.com.alura.alumind.service;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Service;

/**
 * @author Crisley Marques
 */
@Service
public class AIService {

    OpenAiApi openAiApi;
    OpenAiChatOptions openAiChatOptions;

    public AIService() {
        this.openAiApi = new OpenAiApi("https://api.groq.com/openai", System.getenv("GROQ_API_KEY"));
        this.openAiChatOptions = OpenAiChatOptions.builder()
                .withModel("llama3-70b-8192")
                .withTemperature(0.4)
                .withMaxTokens(200)
                .build();
    }

    public String getClassificationCompletion(String feedback) {
        OpenAiChatModel chatModel = new OpenAiChatModel(openAiApi, openAiChatOptions);
        ChatResponse response = chatModel.call(createClassificationPrompt(feedback));

        return response.toString();
    }

    public Prompt createClassificationPrompt(String feedback) {
        String instructions = "Qual sentimento está sendo expressado pelo seguinte feedback: ";
        return new Prompt(instructions + feedback);
    }
}
