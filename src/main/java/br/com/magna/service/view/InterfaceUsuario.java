package br.com.magna.service.view;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.magna.service.boardModel.Tabuleiro;
import br.com.magna.service.gameModel.DificuldadeDeJogo;

public class InterfaceUsuario {

	private static final Logger logger = LoggerFactory.getLogger(InterfaceUsuario.class);
	
	Scanner scan = new Scanner(System.in);
	Jogo jogar = new Jogo();
	DificuldadeDeJogo dificuldade = null;
	
	private static Integer opcoes = 0;


	private void menuInicial() {

		logger.info("Seja Bem-vindo ao Caça Palavras");
		logger.info("===============================\n");

		while (opcoes != 2) {

			logger.info("Digite o número da opção Desejada\n");

			logger.info("1. Jogar");
			logger.info("2. Sair");

			try {
				opcoes = scan.nextInt();

				if (opcoes == 1) {
					break;
				} else if (opcoes == 2) {
					logger.info("\nAté mais!");
					System.exit(0);
				} else {
					logger.error("\nOpcão digitada diferente das opções listadas.\n");
				}

			} catch (InputMismatchException e) {
				logger.error("\nOpção digitada é inválida\n");
				scan.nextLine();
			}

		}
	}

	private void tutorial() {

		Map<Integer, Map<Integer, Character>> matriz = Tabuleiro.criarMatriz(10);

		logger.info("\n------ Tutorial ------");
		logger.info("======================");
		logger.info("\nAo iniciar o jogo, será apresentado um Tabuleiro com as palavra e as coordenadas\n");

		Tabuleiro.exibirMatriz(matriz);

		logger.info("\nA primeira coluna de letras da esquerda e os numeros acima são as coordenadas de cada carácter do tabuleiro");
		logger.info("Para encontrar uma palavra, basta digitar uma as coordenadas de início e fim da palavra");
		logger.info("\nPor exemplo, Digite a primeira coordenada: a1 (aperte Enter para confirmar a entrada)");
		logger.info("Digite a segunda coordenada: a7");
		logger.info("\nEntão o Sistema irá verificar se tem uma palavra nessas coordenadas ou não.");

		while (opcoes != 2) {

			logger.info("Digite o número da opção Desejada\n");

			logger.info("1. Jogar");
			logger.info("2. Sair");

			try {
				opcoes = scan.nextInt();

				if (opcoes == 1) {
					break;
				} else if (opcoes == 2) {
					logger.info("\nAté mais!");
					System.exit(0);
				} else {
					logger.error("\nOpcão digitada diferente das opções listadas.\n");
				}

			} catch (InputMismatchException e) {
				logger.error("\nOpção digitada é inválida\n");
				scan.nextLine();
			}

		}
	}

	private DificuldadeDeJogo escolhendoDificuldade() {
		
		
		
	    logger.info("\nSelecione a Dificuldade do jogo\n");

	    logger.info("1. Fácil");
	    logger.info("2. Intermediário");
	    logger.info("3. Difícil");
	    logger.info("4. Sair");

	    while (opcoes != 4) {
	        try {
	            opcoes = scan.nextInt();

	            switch (opcoes) {
	                case 1:
	                    logger.info("\nDificuldade Fácil: ");
	                    dificuldade = DificuldadeDeJogo.FACIL;
	                    return dificuldade;

	                case 2:
	                    logger.info("\nDificuldade Médio: ");
	                    dificuldade = DificuldadeDeJogo.MEDIO;
	                    return dificuldade;

	                case 3:
	                    logger.info("\nDificuldade Difícil: ");
	                    dificuldade = DificuldadeDeJogo.DIFICIL;
	                    return dificuldade;

	                case 4:
	                    logger.info("\nAté mais!");
	                    System.exit(opcoes);

	                default:
	                    logger.error("\nOpção digitada diferente das opções listadas.\n");
	                    break;
	            }

	        } catch (InputMismatchException e) {
	            logger.error("\nOpção digitada é inválida\n");
	            scan.nextLine();
	        }
	    }
	    return dificuldade;
	}


	public void start() {

		menuInicial();
		tutorial();
		DificuldadeDeJogo dificuldadeEscolhida = escolhendoDificuldade();
		jogar.jogo(dificuldadeEscolhida);

	}
}
