function login(){
    const email = document.getElementById("emaillogin").value
    const senha = document.getElementById("senhalogin").value



    if(email && senha){

        const loginUser = {
            email:email,
            senha:senha,

        };

        fetch("http://localhost:8080/cadastro/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(loginUser),

        })
        .then(response => {
            if(response.ok){
                alert("Login feito")
                window.location.href = 'bemVindo.html';
            }else{
                alert("Usuário não encontrado")
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