package br.com.alura.alumind.controller;

import br.com.alura.alumind.dto.FeedbackDTO;
import br.com.alura.alumind.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

/**
 * @author Crisley Marques
 */
@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping()
    public ResponseEntity<String> classifyFeedback(@Valid @RequestBody FeedbackDTO feedbackDTO) {
        String responseLLM = feedbackService.classifyFeedback(feedbackDTO);
        return ResponseEntity.status(OK).body(responseLLM);
    }
}