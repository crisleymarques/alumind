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
        String instructions = """
                Sua tarefa é avaliar um feedback recebido de um cliente sobre um aplicativo de saúde mental.
                
                O feedback está delimitado com XML tags.
                Você deve classificar o feedback como "POSITIVO", "NEGATIVO" ou "INCONCLUSIVO".
                Além disso, você deve extrair possíveis funcionalidades do sistema sugeridas no feedback.
                
                A sua resposta deve conter apenas um JSON com os campos "sentiment", "requested_features" com uma lista de possíveis "code" (código que identifica a funcionalidade unicamente) e "reason" (descrição da importância da funcionalidade).
                É possível que não haja sugestões no feedback, quando isso ocorrer, "requested_features" deve ser null.
                
                Exemplos de Feedback e sua resposta:
                Feedback: Gosto muito de usar o Alumind! Está me ajudando bastante em relação a alguns problemas que tenho. Só queria que houvesse uma forma mais fácil de eu mesmo realizar a edição do meu perfil dentro da minha conta
                Resposta:
                {
                  "sentiment": "POSITIVO",
                  "requested_features": [
                    {
                      "code": "EDITAR_PERFIL",
                      "reason": "O usuário gostaria de realizar a edição do próprio perfil"
                    } ]
                }
                
                Feedback: Não gostei do app. Poderia ter a opção de um diário emocional facilitaria bastante o monitoramento durante o mês, por exemplo.
                Resposta:
                {
                  "sentiment": "NEGATIVO",
                  "requested_features": [
                    {
                      "code": "CRIAR_DIARIO_EMOCIONAL",
                      "reason": "O usuário gostaria de monitorar suas emoções durante o mês."
                    } ]
                }
                
                Feedback: Ainda não tenho uma opinião.
                Resposta:
                {
                  "sentiment": "INCONCLUSIVO",
                  "requested_features": []
                }
                """;
        return new Prompt(instructions + "<feedback>" + feedback + "</feedback>");
    }
}
