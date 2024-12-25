package br.com.juliasilva.main.excecao;

public class EncontradaExcecao extends RuntimeException{

    public EncontradaExcecao() {
        super("Usuário já exite");
    }
}
