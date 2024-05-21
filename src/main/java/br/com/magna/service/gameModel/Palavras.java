package br.com.magna.service.gameModel;

public class Palavras {
	
    public static String[] getPalavras(DificuldadeDeJogo dificuldade) {
    	
        String[] palavras;
        
        switch (dificuldade) {
        
            case FACIL:
                palavras = new String[]{"THEBEATLES", "PINKFLOYD", "NIRVANA", "GREENDAY", "IRONMAIDEN"};
                break;
                
            case MEDIO:
                palavras = new String[]{"METALLICA", "FOOFIGHTERS", "GUNSNROSES", "LINKINPARK", "SLIPKNOT"};
                break;
                
            case DIFICIL:
                palavras = new String[]{"QUEEN", "KISS", "ACDC", "PEARLJAM", "BONJOVI"};
                break;
                
            default:
                palavras = new String[0];
        }
        return palavras;
    }
}
