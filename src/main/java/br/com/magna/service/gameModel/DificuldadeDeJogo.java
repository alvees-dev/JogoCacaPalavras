package br.com.magna.service.gameModel;

public enum DificuldadeDeJogo {
	
    FACIL(10),
    MEDIO(15),
    DIFICIL(20);

    private final int TAMANHO;

    DificuldadeDeJogo(int tamanho) {
        this.TAMANHO = tamanho;
    }

    public int getTamanho() {
        return TAMANHO;
    }
}