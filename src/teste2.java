import java.io.IOException;
import java.util.Scanner;

public class teste2 {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		JogoDaVelha jogo = new JogoDaVelha("joao");
		int linha, coluna;
		boolean jogadavalida;
		int numeroJogador=1;
		try {
			do {
			
				do {
					jogo.printMatriz();
					System.out.println("jogador 1");
					System.out.println("digite a linha");
					linha = teclado.nextInt();
					System.out.println("digite a coluna");
					coluna = teclado.nextInt();
					jogadavalida = jogo.jogarJogador(numeroJogador, linha, coluna);
					linha = jogo.getUltimaLinha();
					coluna = jogo.getUltimaColuna();
					System.out.println("o jogador jogou na posicao "+linha + "-" + coluna);
					jogo.printMatriz(); 
				}while(!jogadavalida);

				try {
					if(!jogo.terminou()) {
						jogo.jogarMaquina();
						linha = jogo.getUltimaLinha();
						coluna = jogo.getUltimaColuna();
						System.out.println("a maquina jogou na posicao "+linha + "-" + coluna);
						
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
			}while(!jogo.terminou());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		switch(jogo.getResultado()) {
		case 1: System.out.println("Resultado: " + jogo.getNomeJogador(1) + " venceu");  break;
		case 2: jogo.printMatriz(); System.out.println("Resultado: A " + jogo.getNomeJogador(2) + " venceu"); break;
		case 3: jogo.printMatriz(); System.out.println("Resultado: Ninguem venceu"); break;
		}
		
		//	gravar no arquivo jogo.txt  o histórico do jogo até o resultado

	}
}
