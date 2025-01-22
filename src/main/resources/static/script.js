function cadastro(){
    const usuario = document.getElementById("usuarioCadastro").value
    const email = document.getElementById("emailCadastro").value
    const senha = document.getElementById("senhaCadastro").value
    const btnCadastro = document.getElementById("cadastroBtn")
    const btnLogin = document.getElementById('btnLogin');


    if(usuario && email && senha){

        const user = {
            usuario: usuario,
            email:email,
            senha:senha,
        };

        if (!email.includes("@")) {
              alert("[ERRO] O e-mail deve conter '@'");
              document.getElementById("emailCadastro").value = "";
              return;
        }

        if(senha.length  < 10){
             alert("[ERRO] A senha deve ter no mínimo 10 caracteres")
             const senha = document.getElementById("senhaCadastro").value = "";
             return;
        }

        if(usuario.includes(" ")){
            alert("[ERRO] O nome de usuário não deve conter espaços!!")
            const usuario = document.getElementById("usuarioCadastro").value = "";
            return;
        }



        fetch("https://cadastro-login.onrender.com/cadastro/usuario", {
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
                const usuario = document.getElementById("usuarioCadastro").value = "";
                    const email = document.getElementById("emailCadastro").value = "";
                    const senha = document.getElementById("senhaCadastro").value = "";

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



}