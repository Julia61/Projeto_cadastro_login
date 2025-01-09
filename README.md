<section>
  <h1>Bem-vindo ao Projeto de Cadastro e Login</h1>
  <p>
    Uma aplicação desenvolvida para praticar conceitos fundamentais de <strong>Java</strong>, 
    <strong>Spring Boot</strong> e integração com <strong>front-end</strong>. Este repositório contém o código 
    completo da aplicação, desde a estrutura back-end até o front-end responsivo.
  </p>
  <div style="text-align: center; margin: 20px 0;">
    <img 
      src="https://github.com/user-attachments/assets/db881a14-0d8d-4434-800c-ac7e83fb8597" 
      alt="Demonstração do projeto" 
      style="max-width: 100%; height: auto; border: 1px solid #ccc; border-radius: 8px;"
    />
  </div>
</section>

<h2>Funcionalidades</h2>
    <ul>
        <li><strong>Cadastro de Usuários</strong>: Permite criar contas com validação de dados e proteção de credenciais usando BCrypt.</li>
        <li><strong>Login de Usuários</strong>: Valida as credenciais de e-mail e senha.</li>
        <li><strong>Exceções Personalizadas</strong>: Mensagens amigáveis para erros, como "Usuário já existe" ou "Credenciais inválidas".</li>
        <li><strong>Integração Front-End/Back-End</strong>: Envio e recebimento de dados entre as interfaces.</li>
        <li><strong>Banco de Dados PostgreSQL</strong>: Gerenciamento de dados de usuários utilizando Spring Data JPA.</li>
        <li><strong>Interface Responsiva</strong>: Layout minimalista e adaptável para diferentes dispositivos.</li>
    </ul>

<h2>Tecnologias Utilizadas</h2>
    <h3>Back-End</h3>
    <ul>
        <li><strong>Java 17</strong></li>
        <li><strong>Spring Boot</strong></li>
        <ul>
            <li>Spring Data JPA</li>
            <li>Spring Security</li>
        </ul>
        <li><strong>PostgreSQL</strong></li>
        <li><strong>Docker</strong> (para gerenciamento do banco de dados)</li>
        <li><strong>BCrypt</strong> (criptografia de senhas)</li>
    </ul>
    <h3>Front-End</h3>
    <ul>
        <li><strong>HTML5</strong></li>
        <li><strong>CSS3</strong></li>
        <li><strong>JavaScript</strong> - <code>fetch()</code> para chamadas à API</li>
    </ul>

<h2>Como Executar o Projeto</h2>
    <h3>Requisitos</h3>
    <ul>
        <li><strong>Java 17</strong> ou superior</li>
        <li><strong>Maven</strong> instalado</li>
        <li><strong>Docker</strong> e <strong>Docker Compose</strong> configurados</li>
    </ul>

<h3>Passos</h3>
<ol>
    <li>
        <strong>Clone o repositório:</strong>
        <pre>
            <code>
https://github.com/Julia61/Projeto_cadastro_login.git
            </code>
        </pre>
    </li>
    <li>
        <strong>Navegue até a pasta do projeto:</strong>
        <pre>
            <code>
cd projeto-cadastro-login
            </code>
        </pre>
    </li>
    <li>
        <strong>Suba o banco de dados PostgreSQL com Docker:</strong>
        <pre>
            <code>
docker-compose up -d
            </code>
        </pre>
    </li>
    <li>
        <strong>Execute o projeto com Maven:</strong>
        <pre>
            <code>
mvn spring-boot:run
            </code>
        </pre>
    </li>
    <li>
        <strong>Acesse a aplicação:</strong>
        <ul>
            <li>Endpoints da API: <code>http://localhost:8080</code></li>
            <li>Interface front-end: <code>http://localhost:63342</code> (ajuste conforme o ambiente local)</li>
        </ul>
    </li>
</ol>

<section>
  <h2>Estrutura do Projeto</h2>
  <pre style="background-color: #f4f4f4; padding: 15px; border-radius: 8px; border: 1px solid #ddd; overflow-x: auto;">
projeto-cadastro-login
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── br.com.juliasilva.main
│   │   │   │   ├── casoDeUso
│   │   │   │   ├── controlador
│   │   │   │   ├── cripto
│   │   │   │   ├── dto
│   │   │   │   ├── excecao
│   │   │   │   ├── repositorio
│   │   │   │   └── usuario
│   │   ├── resources
│   │       ├── application.properties
├── static
│   ├── index.html
│   ├── style.css
│   └── script.js
└── docker-compose.yml
  </pre>
</section>


<h2>Endpoints Disponíveis</h2>
    <h3>Cadastro de Usuário</h3>
    <div class="highlight">
        <strong>POST</strong> <code>/cadastro/usuario</code>
        <br>
        <strong>Body (JSON):</strong>
        <pre>
            <code>
{
  "usuario": "Julia",
  "email": "ju@gmail.com",
  "senha": "1234567890"
}
            </code>
        </pre>
        <strong>Respostas:</strong>
        <ul>
            <li><strong>200 OK</strong>: Usuário cadastrado com sucesso.</li>
            <li><strong>400 Bad Request</strong>: Usuário ou e-mail já existentes.</li>
        </ul>
    </div>

<h3>Login de Usuário</h3>
    <div class="highlight">
        <strong>POST</strong> <code>/cadastro/login</code>
        <br>
        <strong>Body (JSON):</strong>
        <pre>
<code>
{
  "email": "ju@gmail.com",
  "senha": "1234567890"
}
 </code>
        </pre>
        <strong>Respostas:</strong>
        <ul>
            <li><strong>200 OK</strong>: Login realizado com sucesso.</li>
            <li><strong>401 Unauthorized</strong>: Credenciais inválidas.</li>
            <li><strong>500 Internal Server Error</strong>: Erro interno do servidor.</li>
        </ul>
    </div>

<h2>Melhorias Futuras</h2>
    <ul>
        <li>Criação de testes unitários e de integração.</li>
        <li>Integração com bibliotecas de front-end modernas como React ou Vue.js.</li>
    </ul>

<p>Desenvolvido com dedicação por <a href="https://github.com/Julia61" target="_blank"><strong>Julia</strong></a>. 🚀</p>


    

    

