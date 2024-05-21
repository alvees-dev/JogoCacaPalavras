package br.com.magna.service.boardModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.magna.service.view.InterfaceUsuario;

public class ValidaTabuleiro {

	private static final Logger logger = LoggerFactory.getLogger(InterfaceUsuario.class);
	static StringBuilder palavra = new StringBuilder();

	public static boolean validarCoordenadas(String coordenadas) {
		return coordenadas.matches("[A-Z]\\d+");
	}

	public static String encontrarPalavra(Map<Integer, Map<Integer, Character>> matriz, String inicio, String fim,
			String[] palavras) {
		
		Character inicioLetra = inicio.charAt(0);
		Integer inicioNumero = Integer.parseInt(inicio.substring(1));
		
		Character fimLetra = fim.charAt(0);
		Integer fimNumero = Integer.parseInt(fim.substring(1));

		Integer tamanho = matriz.size();

		if (inicioLetra < 'A' || inicioLetra > 'A' + tamanho || fimLetra < 'A' || fimLetra > 'A' + tamanho
				|| inicioNumero < 1 || inicioNumero > tamanho || fimNumero < 1 || fimNumero > tamanho) {
			logger.error("Coordenadas estão fora dos limites da matriz.");
			return null;
		}

		if (inicioLetra == fimLetra) {
			int linha = inicioLetra - 'A' + 1; // Ajuste para começar de 1
			int inicioColuna = Math.min(inicioNumero, fimNumero);
			int fimColuna = Math.max(inicioNumero, fimNumero);

			for (int coluna = inicioColuna; coluna <= fimColuna; coluna++) {
				if (!matriz.get(linha).containsKey(coluna)) {
					return null;
				}
				palavra.append(matriz.get(linha).get(coluna));
			}
			for (String outraPalavra : palavras) {
				if (palavra.toString().contains(outraPalavra)) {
					return outraPalavra;
				}
			}
		} else if (inicioNumero == fimNumero) {
			
			int coluna = inicioNumero;
			int inicioLinha = Math.min(inicioLetra - 'A' + 1, fimLetra - 'A' + 1);
			int fimLinha = Math.max(inicioLetra - 'A' + 1, fimLetra - 'A' + 1);
			
			for (int linha = inicioLinha; linha <= fimLinha; linha++) {
				if (!matriz.get(linha).containsKey(coluna)) {
					return null;
				}
				palavra.append(matriz.get(linha).get(coluna));
			}
			for (String outraPalavra : palavras) {
				if (palavra.toString().contains(outraPalavra)) {
					return outraPalavra;
				}
			}
		}
		return null;
	}

	public static String[] palavrasEncontradas(String[] palavras, String palavra) {
		
		List<String> listaPalavras = new ArrayList<>(Arrays.asList(palavras));
		listaPalavras.remove(palavra);
		return listaPalavras.toArray(new String[0]);
	}

	public static void substituirPalavra(Map<Integer, Map<Integer, Character>> matriz, String palavra) {
		int tamanho = matriz.size();

		for (int linha = 1; linha <= tamanho; linha++) {
			for (int coluna = 1; coluna <= tamanho; coluna++) {

				if (matriz.get(linha).containsKey(coluna) && matriz.get(linha).get(coluna) == palavra.charAt(0)) {

					Boolean encontrouPalavraHorizontal = true;
					Boolean encontrouPalavraVertical = true;

					for (int letra = 0; letra < palavra.length(); letra++) {
						if (coluna + letra > tamanho
								|| matriz.get(linha).get(coluna + letra) != palavra.charAt(letra)) {
							encontrouPalavraHorizontal = false;
							break;
						}
					}

					for (int letra = 0; letra < palavra.length(); letra++) {
						if (linha + letra > tamanho || matriz.get(linha + letra).get(coluna) != palavra.charAt(letra)) {
							encontrouPalavraVertical = false;
							break;
						}
					}
					if (encontrouPalavraHorizontal) {
						for (int letra = 0; letra < palavra.length(); letra++) {
							matriz.get(linha).put(coluna + letra, '-');
						}
					}
					if (encontrouPalavraVertical) {
						for (int letra = 0; letra < palavra.length(); letra++) {
							matriz.get(linha + letra).put(coluna, '-');
						}
					}
				}
			}
		}
	}

}
