package br.com.alura.alumind.dto;

import br.com.alura.alumind.enumeration.Sentiment;

import java.util.List;

/**
 * @author Crisley Marques
 */
public record LlmResponseDTO(
        Sentiment sentiment,
        List<RequestedFeaturesDTO> requested_features
) {}
