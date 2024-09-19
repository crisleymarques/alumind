package br.com.alura.alumind.service;

import br.com.alura.alumind.domain.entity.Feedback;
import br.com.alura.alumind.domain.entity.RequestedFeatures;
import br.com.alura.alumind.domain.repository.FeedbackRepository;
import br.com.alura.alumind.domain.repository.RequestedFeaturesRepository;
import br.com.alura.alumind.dto.FeedbackDTO;
import br.com.alura.alumind.dto.LlmResponseDTO;
import br.com.alura.alumind.dto.RequestedFeaturesDTO;
import br.com.alura.alumind.exception.AlumindException;
import br.com.alura.alumind.validator.LlmValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Crisley Marques
 */
@Service
public class FeedbackService {

    private final LlmService llmService;
    private final LlmValidator llmValidator;
    private final FeedbackRepository feedbackRepository;
    private final RequestedFeaturesRepository requestedFeaturesRepository;

    public FeedbackService(LlmService llmService, LlmValidator llmValidator, FeedbackRepository feedbackRepository, RequestedFeaturesRepository requestedFeaturesRepository) {
        this.llmService = llmService;
        this.llmValidator = llmValidator;
        this.feedbackRepository = feedbackRepository;
        this.requestedFeaturesRepository = requestedFeaturesRepository;
    }

    public String classifyFeedback(FeedbackDTO feedbackDTO) {
        String responseSPAM = llmService.getSpamValidation(feedbackDTO.description());
        llmValidator.validateSpamResponse(responseSPAM);

        String responseLLM = llmService.getClassificationCompletion(feedbackDTO.description());
        llmValidator.validateJsonResponse(responseLLM);

        ObjectMapper objectMapper = new ObjectMapper();
        LlmResponseDTO llmResponseDTO;
        try {
            llmResponseDTO = objectMapper.readValue(responseLLM, LlmResponseDTO.class);
        } catch (Exception e) {
            throw new AlumindException("Erro ao converter a resposta da LLM para DTO.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        saveResponseToDatabase(llmResponseDTO, feedbackDTO);

        return responseLLM;
    }

    @Transactional
    public void saveResponseToDatabase(LlmResponseDTO llmResponseDTO, FeedbackDTO feedbackDTO) {
        Feedback feedback = feedbackRepository.findByDescription(feedbackDTO.description());

        if (feedback == null) {
            feedback = new Feedback();
            feedback.setDescription(feedbackDTO.description());
            feedback.setSentiment(llmResponseDTO.sentiment());
        } else {
            feedback.setSentiment(llmResponseDTO.sentiment());
        }

        List<RequestedFeatures> requestedFeaturesList = new ArrayList<>();

        if (llmResponseDTO.requested_features() != null) {
            for (RequestedFeaturesDTO featureDTO : llmResponseDTO.requested_features()) {
                RequestedFeatures existingFeature = requestedFeaturesRepository.findByCode(featureDTO.code());

                RequestedFeatures requestedFeature;
                if (existingFeature != null) {
                    requestedFeature = existingFeature;
                } else {
                    requestedFeature = new RequestedFeatures();
                    requestedFeature.setCode(featureDTO.code());
                    requestedFeature.setReason(featureDTO.reason());

                    requestedFeaturesRepository.save(requestedFeature);
                }
                requestedFeaturesList.add(requestedFeature);
            }
        }
        feedback.setRequestedFeatures(requestedFeaturesList);
        feedbackRepository.save(feedback);
    }
}
