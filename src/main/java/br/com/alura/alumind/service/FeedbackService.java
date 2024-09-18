package br.com.alura.alumind.service;

import br.com.alura.alumind.dto.FeedbackDTO;
import org.springframework.stereotype.Service;

/**
 * @author Crisley Marques
 */
@Service
public class FeedbackService {

    AIService aiService;

    public FeedbackService(AIService aiService) {
        this.aiService = aiService;
    }

    public String classifyFeedback(FeedbackDTO feedbackDTO) {
        String responseLLM = aiService.getClassificationCompletion(feedbackDTO.descricao());

        return responseLLM;
    }


}
