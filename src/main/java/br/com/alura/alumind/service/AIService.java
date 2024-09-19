package br.com.alura.alumind.service;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

/**
 * @author Crisley Marques
 */
@Service
public class AIService {
    OpenAiChatModel chatModel;

    public AIService(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String getClassificationCompletion(String feedback) {
        Prompt prompt = createClassificationPrompt(feedback);
        ChatResponse response = chatModel.call(prompt);

        return response.getResult().getOutput().getContent();
    }

    public Prompt createClassificationPrompt(String feedback) {
        String instructions = "Qual sentimento está sendo expressado pelo seguinte feedback: ";
        return new Prompt(instructions + feedback);
    }
}
