package br.com.alura.alumind.validator;

import br.com.alura.alumind.exception.AlumindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Crisley Marques
 */
@Component
public class LlmValidator {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public void validateSpamResponse(String llmResponse) {
        if (llmResponse.equals("SIM")) {
            throw new AlumindException("SPAM detectado!", HttpStatus.BAD_REQUEST);
        } else if (!(llmResponse.equals("NÃO") || llmResponse.equals("NAO") )) {
            throw new AlumindException("Erro na verificação de SPAM.", HttpStatus.BAD_REQUEST);
        }
    }

    public void validateJsonResponse(String jsonResponse) {
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            if (!rootNode.has("sentiment") || !rootNode.get("sentiment").isTextual()) {
                throw new AlumindException("Campo 'sentiment' ausente ou inválido!", HttpStatus.BAD_REQUEST);
            }

            if (rootNode.has("requested_features") && !rootNode.get("requested_features").isNull()) {
                if (!rootNode.get("requested_features").isArray()) {
                    throw new AlumindException("Campo 'requested_features' deve ser um array!", HttpStatus.BAD_REQUEST);
                }

                for (JsonNode feature : rootNode.get("requested_features")) {
                    if (!feature.has("code") || !feature.get("code").isTextual()) {
                        throw new AlumindException("Campo 'code' ausente ou inválido em requested_features!", HttpStatus.BAD_REQUEST);
                    }
                    if (!feature.has("reason") || !feature.get("reason").isTextual()) {
                        throw new AlumindException("Campo 'reason' ausente ou inválido em requested_features!", HttpStatus.BAD_REQUEST);
                    }
                }
            }

        } catch (Exception e) {
            throw new AlumindException("Erro ao processar resposta do moodelo (JSON).", HttpStatus.BAD_REQUEST);
        }
    }
}
