# Projeto de Cadastro e Login

Oi, pessoal! 👋

🛠️ Tenho novidades incríveis no meu projeto de Cadastro e Login, onde busquei aprimorar a segurança, experiência do usuário e boas práticas de desenvolvimento. Aqui estão as atualizações que implementei:

## Funcionalidades
- **Cadastro de Usuários**: Permite criar contas com validação de dados e proteção de credenciais usando BCrypt.
- **Login de Usuários**: Valida as credenciais de e-mail e senha.
- **Exceções Personalizadas**: Mensagens amigáveis para erros, como "Usuário já existe" ou "Credenciais inválidas".
- **Integração Front-End/Back-End**: Envio e recebimento de dados entre as interfaces.
- **Banco de Dados PostgreSQL**: Gerenciamento de dados de usuários utilizando Spring Data JPA.
- **Interface Responsiva**: Layout minimalista e adaptável para diferentes dispositivos.
- **Testes de Integração**: Desenvolvi dois testes para validar o funcionamento do cadastro e login.

## Tecnologias Utilizadas

### Back-End
- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- PostgreSQL
- Docker (para gerenciamento do banco de dados)
- BCrypt (criptografia de senhas)

### Front-End
- HTML5
- CSS3
- JavaScript - `fetch()` para chamadas à API

## Como Executar o Projeto

### Requisitos
- Java 17 ou superior
- Maven instalado
- Docker e Docker Compose configurados

### Passos

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/Julia61/Projeto_cadastro_login.git
   ```

2. **Navegue até a pasta do projeto:**
   ```bash
   cd projeto-cadastro-login
   ```

3. **Suba o banco de dados PostgreSQL com Docker:**
   ```bash
   docker-compose up -d
   ```

4. **Execute o projeto com Maven:**
   ```bash
   mvn spring-boot:run
   ```

5. **Acesse a aplicação:**
   - **Endpoints da API:** [http://localhost:8080](http://localhost:8080)
   - **Interface front-end:** [http://localhost:63342](http://localhost:63342) (ajuste conforme o ambiente local)

## Estrutura do Projeto
```plaintext
projeto-cadastro-login
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── br.com.juliasilva.main
│   │   │   │   ├── config
│   │   │   │   ├── controller
│   │   │   │   ├── cripto
│   │   │   │   ├── dto
│   │   │   │   ├── exception
│   │   │   │   ├── provider
│   │   │   │   ├── repository
│   │   │   │   ├── useCase
│   │   │   │   └── user
│   │   ├── resources
│   │   │   ├── application.properties
│   │   │   ├── static
│   │   │   │   ├── index.html
│   │   │   │   ├── style.css
│   │   │   │   └── script.js
└── docker-compose.yml

```

## Endpoints Disponíveis

### Cadastro de Usuário
**POST /cadastro/usuario**

**Body (JSON):**
```json
{
  "usuario": "Julia",
  "email": "ju@gmail.com",
  "senha": "1234567890"
}
```

**Respostas:**
- `200 OK`: Usuário cadastrado com sucesso.
- `400 Bad Request`: Usuário ou e-mail já existentes.

### Login de Usuário
**POST /cadastro/login**

**Body (JSON):**
```json
{
  "email": "ju@gmail.com",
  "senha": "1234567890"
}
```

**Respostas:**
- `200 OK`: Login realizado com sucesso.
- `401 Unauthorized`: Credenciais inválidas.
- `500 Internal Server Error`: Erro interno do servidor.

---

Esse projeto tem sido um excelente laboratório para consolidar conceitos fundamentais, como autenticação JWT, integração com front-end e back-end, e melhores práticas de segurança. 🚀

Além disso, você pode estar checando a aplicação que está disponível, fazendo o cadastro e login para testar as funcionalidades. Confira mais detalhes no [Aplicação](https://cadastro-login.onrender.com/pagina_cadastro.html). 😉
E no [swagger](https://cadastro-login.onrender.com/swagger-ui/index.html#/). 🛠️

---

<p>Desenvolvido com dedicação por <a href="https://github.com/Julia61" target="_blank"><strong>Julia</strong></a>. 🚀</p>

Espero que gostem! 💻✨

---
