const bemVindo = document.getElementById("bemVindo").textContent = `Bem-vindo!`
async function verificarToken() {
    try {
        const response = await fetch('/autor/validate-token', {
            method: 'GET',
            credentials: 'include' // Inclui os cookies nas requisições
        });

        if (response.status === 200) {
            console.log('Token válido. Usuário autenticado.');
        } else {
            alert('Token expirado ou inválido. Redirecionando...');
            window.location.href = 'pagina_login.html'; // Redirecionar para a página de login
        }
    } catch (error) {
        console.error('Erro ao verificar o token:', error);
        window.location.href = '/login.html'; // Redirecionar em caso de erro
    }
}


setInterval(verificarToken, 5 * 60 * 1000);