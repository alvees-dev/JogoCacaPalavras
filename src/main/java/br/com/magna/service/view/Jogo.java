package br.com.magna.service.view;

import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.magna.service.boardModel.Tabuleiro;
import br.com.magna.service.boardModel.ValidaTabuleiro;
import br.com.magna.service.gameModel.DificuldadeDeJogo;
import br.com.magna.service.gameModel.Palavras;

public class Jogo {

	private static final Logger logger = LoggerFactory.getLogger(InterfaceUsuario.class);
	Scanner scan = new Scanner(System.in);
	DificuldadeDeJogo dificuldade;

	public void jogo(DificuldadeDeJogo dificuldade) {
		
		this.dificuldade = dificuldade;

		Map<Integer, Map<Integer, Character>> matriz = Tabuleiro.criarMatriz(dificuldade.getTamanho());

		String[] palavras = Palavras.getPalavras(dificuldade);

		Tabuleiro.colocarPalavras(matriz, palavras);
		Tabuleiro.preencherEspacosVazios(matriz);
		Tabuleiro.exibirMatriz(matriz);



		while (palavras.length != 0) {
			
			logger.info("\nPalavras a serem encontradas:");
			
			for (String palavra : palavras) {
				logger.info(palavra);
			}
			
			logger.info("\nEncontre as palavras. Digite as coordenadas de início e fim (por exemplo: A1 A6): \n");

			logger.info("Digite a primeira coordenada");
			String inicioPalavra = scan.next().toUpperCase();
			
			logger.info("Digite a segunda coordenada");
			String fimPalavra = scan.next().toUpperCase();

			if (ValidaTabuleiro.validarCoordenadas(inicioPalavra) && ValidaTabuleiro.validarCoordenadas(fimPalavra)) {
				String palavraEncontrada = ValidaTabuleiro.encontrarPalavra(matriz, inicioPalavra, fimPalavra, palavras);

				if (palavraEncontrada != null) {

					logger.info("\nPalavra encontrada: " + palavraEncontrada);
					
					palavras = ValidaTabuleiro.palavrasEncontradas(palavras, palavraEncontrada);
					ValidaTabuleiro.substituirPalavra(matriz, palavraEncontrada);
					Tabuleiro.exibirMatriz(matriz);
					
					if (palavras.length == 0) {
						logger.info("\nParabéns! Você encontrou todas as palavras.");
						logger.info("O jogo será encerrado.");
						break;
					}
				} else {
					logger.error("Palavra não encontrada.");
				}
			} else {
				logger.error("Coordenadas inválidas. Digite novamente.");
			}
		}
	}
}
