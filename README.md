<h1 align="center"> 
	🧘 AluMind | Feedbacks ✅
</h1>

<h4 align="center"> 
	👍 Análise de Feedbacks com LLM 🤖
</h4>

<p align="center">
 <a href="#descricao">Descrição</a> •
 <a href="#tecnologias">Tecnologias</a> •
 <a href="#como-rodar">Como rodar</a> • 
 <a href="#decisoes">Decisões Técnicas</a> • 
 <a href="#desenvolvido-por">Sobre</a>
</p>

## Descrição 📋
A AluMind é uma startup que oferece um aplicativo focado em bem-estar e saúde mental, proporcionando aos usuários acesso a meditações guiadas, sessões de terapia, e conteúdos educativos sobre saúde mental. 
Os Feedbacks vindo dos usuários em diferentes plataformas (canais de atendimento ao cliente; comunidades no Discord; redes sociais) são analisados, classificados por sentimentos e extraídas as possíveis melhorias contidas neles.

## Tecnologias 🛠
[![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/pt-BR/)
[![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io)
[![Flyway](https://img.shields.io/badge/flyway-AC2020?style=for-the-badge&logo=flyway&logoColor=white)](https://www.red-gate.com/products/flyway/community/)
[![Llama 3](https://img.shields.io/badge/Llama_3-%230467DF.svg?style=for-the-badge&logo=Meta&logoColor=white)](https://www.llama.com)
[![Groq](https://img.shields.io/badge/groq_api-F54A2A?style=for-the-badge&logo=groq&logoColor=white)](https://groq.com)

## Como rodar 💻
### Pré-requisitos
Você precisa ter instalado na sua máquina [Git](https://git-scm.com), [Java 17](https://www.java.com/pt-BR/) e o [MySQL 8.1 ou mais recente](https://www.mysql.com).

### Clone o repositório
```bash
git clone https://github.com/crisleymarques/alumind.git
```

### Adicionando a Key da Groq API ao seu ambiente 🔑
1. Crie uma conta gratuita no [Groq](https://groq.com).
2. Crie uma API Key e copie.
3. No arquivo `src/resources/application.properties`, procure a linha que declara a API Key do GROQ e insira a sua que foi gerada:
```bash
spring.ai.openai.api-key=your_api_key
```

### Adicionando sua senha do MySQL 🤫
No arquivo `src/resources/application.properties`, procure a linha que declara a senha do database e insira a sua:
```bash
spring.datasource.password=your-password-here
```
- Não é necessário executar scripts SQL, pois o Flyway faz isso ao executar o projeto.

### Executando ▶️
- Execute o projeto na IDE de sua preferência

Acesse os endpoints no Swagger: http://localhost:8080/swagger-ui/index.html

Aproveite! 🎉

## Decisões Técnicas 📑

### 1. Classificação de Feedbacks 
#### Modelagem do Banco de Dados 🎲
- Os feedbacks são armazenados em uma tabela junto com seu ID e a sua classificação de sentimento.
- As Requested Features são armazenadas em uma tabela com seu ID, Code e Reason.
- Uma tabela auxiliar conecta Feedback e Requested Features através de suas PKs.
- O relacionamento escolhido foi N:N pois foi considerado que:
  - Um feedback pode ter 0 ou mais requested_features associadas
  - Uma requested_feature pode estar em 1 ou mais feedbacks

<img width="1111" alt="Modelagem do BD" src="https://github.com/user-attachments/assets/d4dc46bc-062d-4edf-af3a-79a072d20c77">

#### Implementação
- Foi utilizado o modelo para analisar o sentimento do feedback e estrturar a informação em formato JSON para ser posteriormente salva no banco.
- Técnicas de **Engenharia de Prompt** foram aplicadas para conseguir o resultado esperado
  - Uma das técnicas utilizadas foi o **Few-Shot Prompting**, que consiste em passar exemplos para o modelo no prompt para que ele consiga raciocinar com base neles e fazer analogias.

### 2. Marcação de SPAM ❌
A implementação foi análoga a de classificação dos feedbacks, mas foi feita em uma etapa anterior.
- A classificação só executada quando o sistema identifica que o feedback não é SPAM.
  - Note que os LLMs não são determinísticos, então é possível que hajam erros na identificação do SPAM.

### 3. Nova Feature: Diário Emocional Inteligente 📝
Minha sugestão de funcionalidade para a AluMind é a criação de um **Diário Emocional Inteligente** usando LLMs. 
Esse diário teria como objetivo ajudar os usuários a monitorar seu bem-estar emocional ao longo do tempo, proporcionando uma plataforma para que eles possam registrar suas emoções, reflexões e experiências diárias.

A funcionalidade seria útil porque incentivaria os usuários a refletirem sobre suas emoções, promovendo o autoconhecimento e ajudando-os a compreender melhor seus sentimentos. 
Com a identificação dos padrões de sentimentos e comportamentos, pode atuar como uma ferramenta preventiva ao sugerir ações quando detectar sinais de alerta. 
Além disso, o sistema ofereceria recomendações personalizadas, como meditações ou conteúdos educativos, aumentando a relevância do aplicativo e proporcionando suporte emocional imediato.

A implementação do consistiria em uma interface em que os usuários podem registrar suas reflexões diárias, com o LLM processando esses textos em segundo plano. 
O sistema usaria o modelo de análise de sentimentos para categorizar as emoções e fornecer respostas e recomendações personalizadas. 
Além disso, seriam gerados relatórios de bem-estar com gráficos baseados nas emoções registradas, oferecendo uma visão geral do estado emocional ao longo do tempo.

### 4. Geração de respostas personalizadas (BÔNUS) 📨
- Utilizei o mesmo endpoint para retornar as respostas personalizadas, pois acredito que faz sentido ter a resposta para o feedback inserido no mesmo lugar.
Dado que todas as informações extraídas pela LLM já estão sendo salvas no BD e podem ser acessíveis por ele.
- Para a implementação da funcionalidade utilizei uma abordagem similar as demais fazendo requisições ao modelo e usando técnicas de **Engenharia de Prompt**.



## Desenvolvido por 🧑‍💻

<div align="center">

<a href="https://github.com/crisleymarques">
 <img style="border-radius: 50%" src="https://avatars.githubusercontent.com/u/44072771?v=4" width="100px;" alt="">
 <br>
 <sub><b>Crisley Marques ✨</b></sub></a> 

[![Linkedin Badge](https://img.shields.io/badge/-Crisley_Marques-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/crisley-marques/)](https://www.linkedin.com/in/crisley-marques/)
[![Gmail Badge](https://img.shields.io/badge/-crisleyvmarques@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:crisleyvmarques@gmail.com)](mailto:crisleyvmarques@gmail.com)

</div>
