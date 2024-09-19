<h1 align="center"> 
	🧘 AluMind | Feedbacks ✅
</h1>

<h4 align="center"> 
	👍 Avaliação de Feedbacks com LLM 🤖
</h4>

<p align="center">
 <a href="#descricao">Descrição</a> •
 <a href="#tecnologias">Tecnologias</a> •
 <a href="#como-rodar">Como rodar</a> • 
 <a href="#desenvolvido-por">Sobre</a>
</p>

## Descrição 📋
A AluMind é uma startup que oferece um aplicativo focado em bem-estar e saúde mental, proporcionando aos usuários acesso a meditações guiadas, sessões de terapia, e conteúdos educativos sobre saúde mental. 
Os Feedbacks vindo dos usuários em diferentes plataformas (canais de atendimento ao cliente; comunidades no Discord; redes sociais) são analisados, classificados por sentimentos e extraídas as possíveis melhorias contidas neles.

## Tecnologias 🛠
[![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/pt-BR/)
[![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io)
[![Llama 3](https://img.shields.io/badge/Llama_3-%230467DF.svg?style=for-the-badge&logo=Meta&logoColor=white)](https://www.llama.com)
[![Groq](https://img.shields.io/badge/groq_api-F54A2A?style=for-the-badge&logo=groq&logoColor=white)](https://groq.com)

## Como rodar 💻
### - Pré-requisitos
Você precisa ter instalado na sua máquina [Git](https://git-scm.com), [Java 17](https://www.java.com/pt-BR/).

### Clone o repositório
```bash
git clone https://github.com/crisleymarques/alumind.git
```

### Adicionando a Key da Groq API ao seu ambiente
1. Crie uma conta gratuita no [Groq](https://groq.com).
2. Crie uma API Key e copie.
3. No arquivo `src/resources/application.properties`, procure a linha que declara a API Key do GROQ e insira a sua que foi gerada:
```bash
spring.ai.openai.api-key=your_api_key
```
### Executando
1. Execute o projeto na IDE de sua preferência

Acesse os endpoints no Swagger em: http://localhost:8080/swagger-ui/index.html

Aproveite! 🎉

## Desenvolvido por 🧑‍💻

| [<img src="https://avatars.githubusercontent.com/u/44072771?s=400&amp;u=b17d945fa43dec67a69d1cb11e2f23a7b2e0ad95&amp;v=4" width="120px;"/><br /><sub><b>Crisley Marques</b></sub>](https://github.com/crisleymarques) <br/> | 
| :---: | 
