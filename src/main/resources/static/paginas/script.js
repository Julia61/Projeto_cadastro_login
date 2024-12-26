function cadastro(){
    const usuario = document.getElementById("usuarioCadastro").value
    const email = document.getElementById("emailCadastro").value
    const senha = document.getElementById("senhaCadastro").value
    const btnCadastro = document.getElementById("cadastroBtn")
    const btnLogin = document.getElementById('btnLogin');

    if (!email.includes("@")) {
      alert("E-mail inválido!");
      document.getElementById("emailCadastro").value = "";
      return;
    }


    if(usuario && email && senha){

        const user = {
            usuario: usuario,
            email:email,
            senha:senha,
        };

        fetch("http://localhost:8080/cadastro/usuario", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(user),
        })
        .then(response => {
            if(response.ok) {
                alert("Usuário cadastrado com sucesso!");

                window.location.href = 'pagina_login.html';

            }else {
                alert("Usuário já existe! Erro ao cadastrar usuário.");
            }
        })
        .catch(error => {
            console.error("Erro na requisição:", error);
            alert("Não foi possível enviar os dados.");
        });
    }
    else{
        alert("Por favor, preencha todos os dados!!")
    }

    //btnLogin.addEventListener('click', () => {
        // Simula o redirecionamento para outra página
        //window.location.href = 'pagina_login.html'; // Altere para o nome da página de login
    //});

}