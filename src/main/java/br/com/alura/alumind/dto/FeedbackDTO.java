package br.com.alura.alumind.dto;

import jakarta.validation.constraints.NotBlank;

/**c
 * @author Crisley Marques
 */
public record FeedbackDTO(
        @NotBlank(message = "Descrição é obrigatória.")
        String descricao) {
}
