function cadastro(){
    const usuario = document.getElementById("usuarioCadastro").value
    const email = document.getElementById("emailCadastro").value
    const senha = document.getElementById("senhaCadastro").value

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