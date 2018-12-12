import java.io.IOException;
import java.util.Scanner;

public class teste1 {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner teclado = new Scanner(System.in);
		JogoDaVelha jogo = new JogoDaVelha("joao", "maria");
		int linha, coluna;
		boolean jogadavalida;
		int numeroJogador=1;
		try {
			do {

				do {
					System.out.println("\njogador:" + numeroJogador);
					System.out.println("digite a linha");
					linha = teclado.nextInt();
					System.out.println("digite a coluna");
					coluna = teclado.nextInt();
					jogadavalida = jogo.jogarJogador(numeroJogador, linha, coluna);
					linha = jogo.getUltimaLinha();
					coluna = jogo.getUltimaColuna();
					System.out.println("o jogador " + jogo.getUltimoJogador() + " jogou na posicao "+linha + "-" + coluna);
					jogo.printMatriz();
				}while(!jogadavalida);

				if(numeroJogador==1) 
					numeroJogador=2; 
				else numeroJogador=1;

			}while( !jogo.terminou());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		switch(jogo.getResultado()) {
			case 1: System.out.println("Resultado: " + jogo.getNomeJogador(1) + " venceu"); break;
			case 2: System.out.println("Resultado: " + jogo.getNomeJogador(2) + " venceu"); break;
			case 3: System.out.println("Resultado: Ninguem venceu"); break;
		}
		
		//	gravar no arquivo jogo.txt  o histórico do jogo até o resultado

	}
}
