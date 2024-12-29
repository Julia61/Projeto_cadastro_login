function login(){
    const email = document.getElementById("emaillogin").value
    const senha = document.getElementById("senhalogin").value



    if(email && senha){

        const loginUser = {
            email:email,
            senha:senha,

        };

        if (!email.includes("@")) {
             alert("[ERRO] O e-mail deve conter '@'");
             document.getElementById("emailCadastro").value = "";
             return;
        }

        if(senha.length < 10){
            alert("[ERRO] A senha deve ter no mínimo 10 caracteres");
            const senha = document.getElementById("senhalogin").value = "";
            return;
        }

        fetch("http://localhost:8080/cadastro/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(loginUser),

        })
        .then(response => {
            if(response.ok){
                alert("Login realizado com sucesso! Bem-vindo(a)!")
                window.location.href = 'bemVindo.html';
            }else{
                alert("[ERRO] Usuário não encontrado. Verifique os dados ou faça o cadastro.")
            }
        })
        .catch(error => {
            console.error("Erro na requisição:", error);
            alert("Não foi possível enviar os dados.");
        });
    }else{
        alert("Por favor, preencha todos os dados!!")
    }

}