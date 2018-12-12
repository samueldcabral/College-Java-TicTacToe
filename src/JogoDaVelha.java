import java.io.FileWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.util.Random;

import javax.swing.JLabel;

public class JogoDaVelha {
	private String nome1;
	private String nome2;
	private String[][] matriz = new String[3][3];
	private boolean fim=false;
	private int contjogada;
	private String historico="";
	private String linha;
	private int resultado;
	private int ultimoJogador;
	private int ultimaLinha;
	private int ultimaColuna;
	private int vencedor;
	
	public FileWriter fileW() {
		try{
			FileWriter arquivo;
			arquivo = new FileWriter ( new File("Jogo.txt") );
			return arquivo;
			}
			catch(IOException e) {
			System.out.println("arquivo não pode ser criado");
			System.exit(0);
			}
		return null;
	}
	
	public JogoDaVelha(String nome1, String nome2) {
		this.nome1 = nome1;
		this.nome2 = nome2;
		historico += "================= JOGO DA VELHA =================\r\n";
		historico += "Dois Jogadores\r\n";
		historico += "Jogadores: " + nome1 + " (X) contra " +nome2 + " (O)";
	
		for(int i=0; i < 3; i++)
			for(int j=0; j < 3; j++)
				matriz[i][j] = "-";
	}
	public JogoDaVelha(String nome1) {
		this.nome1 = nome1;
		this.nome2 = "Maquina";
		historico += "================= JOGO DA VELHA =================\r\n";
		historico += "1 Jogador vs Maquina\r\n";
		historico += "Jogadores: " + nome1 + " (X) contra " + nome2 + " (O)";
		
		for(int i=0; i < 3; i++)
			for(int j=0; j < 3; j++)
				matriz[i][j] = "-";
	}
	
	public boolean jogarJogador(int numj, int lin, int col) {
		if(matriz[lin][col].equals("-") || matriz[lin][col].equals(" ")) {
			if(numj == 1) 
				matriz[lin][col] = "X";
			else
				matriz[lin][col] = "O";
			
			this.setUltimoJogador(numj);
			this.setUltimaLinha(lin);
			this.setUltimaColuna(col);
			
			historico += "\r\nJogador: " + this.getNomeJogador(numj) + " jogou na Linha: " + lin + " e Coluna: " + col;
			
			return true;
		}
		return false;
	}
	
	public void jogarMaquina() {
		Random gerador = new Random();
		int lin = gerador.nextInt(3);
		int col = gerador.nextInt(3);
//		System.out.println("Jogada Maquina linha: " + lin + " Coluna: " + col);
		while(!matriz[lin][col].equals("-") || matriz[lin][col].equals(" ")) {
			lin = gerador.nextInt(3);
			col = gerador.nextInt(3);
		}
		matriz[lin][col] = "O";

		this.setUltimoJogador(2);
		this.setUltimaLinha(lin);
		this.setUltimaColuna(col);
		
		historico += "\r\nJogador: Maquina Jogou na Linha: " + lin + " e Coluna: " + col;
//		boolean jogou = false;
//		for(int i=0; i < 3; i++)
//			for(int j=0; j < 3; j++)
//				if(matriz[i][j].equals("-")) {
//					if(!jogou) {
//						
//						jogou = true;
//					}
//				}
	}
	
	public void printMatriz() {
		
		historico += "\r\n------------------------------\r\n";
		
		for(int i=0; i < 3; i++) {
			for(int j=0; j < 3; j++) {
				historico += matriz[i][j] + " | ";
				System.out.print(matriz[i][j] + " ");
			}
			historico += "\r\n";
			System.out.print("\r");
		}
	
	}
	
	public boolean terminou() throws IOException {
		String p1 = "X";
		String p2 = "O";
		
		FileWriter arquivoJogo = fileW();
		
		if( (matriz[0][0] == p1 && matriz[1][1] == p1 && matriz[2][2] == p1) ||
			(matriz[0][2] == p1 && matriz[1][1] == p1 && matriz[2][0] == p1) ||	
			(matriz[0][0] == p1 && matriz[0][1] == p1 && matriz[0][2] == p1) ||
			(matriz[1][0] == p1 && matriz[1][1] == p1 && matriz[1][2] == p1) ||
			(matriz[2][0] == p1 && matriz[2][1] == p1 && matriz[2][2] == p1) ||
			(matriz[0][0] == p1 && matriz[1][0] == p1 && matriz[2][0] == p1) ||
			(matriz[0][1] == p1 && matriz[1][1] == p1 && matriz[2][1] == p1) ||
			(matriz[0][2] == p1 && matriz[1][2] == p1 && matriz[2][2] == p1)) {
			this.vencedor = 1;
			
			historico += "\r\n================= JOGO TERMINOU =================\r\n";
			historico += "O VENCEDOR É (X): " + this.getNomeJogador(this.vencedor) +"!!";
			System.out.println("Vencedor e: " + this.getNomeJogador(this.vencedor) + " jogo terminou !!!");
			
			arquivoJogo.write(historico);
			arquivoJogo.close();
			
			return true;
		}
		else if((matriz[0][0] == p2 && matriz[1][1] == p2 && matriz[2][2] == p2) ||
				(matriz[0][2] == p2 && matriz[1][1] == p2 && matriz[2][0] == p2) ||	
				(matriz[0][0] == p2 && matriz[0][1] == p2 && matriz[0][2] == p2) ||
				(matriz[1][0] == p2 && matriz[1][1] == p2 && matriz[1][2] == p2) ||
				(matriz[2][0] == p2 && matriz[2][1] == p2 && matriz[2][2] == p2) ||
				(matriz[0][0] == p2 && matriz[1][0] == p2 && matriz[2][0] == p2) ||
				(matriz[0][1] == p2 && matriz[1][1] == p2 && matriz[2][1] == p2) ||
				(matriz[0][2] == p2 && matriz[1][2] == p2 && matriz[2][2] == p2)) {
				this.vencedor = 2;
				
				historico += "\r\n================= JOGO TERMINOU =================\r\n";
				historico += "O VENCEDOR É (O): " + this.getNomeJogador(this.vencedor) +"!!";
				System.out.println(this.vencedor + " jogo terminou !!!");
				
				arquivoJogo.write(historico);
				arquivoJogo.close();

				return true;
				}
		else {
			int jogadaRestante = 9;
			for(int i=0; i < 3; i++)
				for(int j=0; j < 3; j++)
					if(!matriz[i][j].equals("-"))
						jogadaRestante--;
			
			if(jogadaRestante == 0) {
				this.vencedor = 3;
				historico += "\r\n================= JOGO TERMINOU =================\r\n";
				historico += "DEU EMPATE!";
				
				arquivoJogo.write(historico);
				arquivoJogo.close();

				return true;
			}
			
			return false;
		}
	}
	
	public int getResultado() {
		return this.vencedor;
	}
	
	public String getNomeJogador(int num) {
		if(num == 1) 
			return this.nome1;
		else
			return this.nome2;
	}
	
	//Getters and Setters
	
	public int getUltimoJogador() {
		return ultimoJogador;
	}
	public void setUltimoJogador(int ultimoJogador) {
		this.ultimoJogador = ultimoJogador;
	}
	public int getUltimaLinha() {
		return ultimaLinha;
	}
	public void setUltimaLinha(int ultimaLinha) {
		this.ultimaLinha = ultimaLinha;
	}
	public int getUltimaColuna() {
		return ultimaColuna;
	}
	public void setUltimaColuna(int ultimaColuna) {
		this.ultimaColuna = ultimaColuna;
	}

}
