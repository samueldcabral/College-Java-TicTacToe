
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.HeadlessException;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;

/**
 * IFPB - TSI - POO 
 * @author Prof. Fausto Ayres
 *
 */
public class TabuleiroTrabalho{

	private JFrame frame;
	private String[][] matriz = new String[3][3];
	private JLabel[][] TicTacToeTiles = new JLabel[3][3];
	private JogoDaVelha jogo;
	private int numeroJogador=1;
	private JButton btnReiniciar;
	private JTextField jogador1Text;
	private JTextField jogador2Text;
	private String jogador1Nome;
	private String jogador2Nome;
	private int linha;
	private int coluna;
	private int escolha;
	private int indicex;
	private int indicey;
	private int comecou = 0;
	private int placar1;
	private int placar2;
	
	/* CORES DO APPLET */
	private String botao = "#7FB685";
	private String text = "#FFFFFF";
	private String accent = "#4D4955";
	
	/* IMAGENS X E O */
	private String[] nomes = {"5.png", "4.png"};
	
	private final JPanel panel = new JPanel();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabuleiroTrabalho window = new TabuleiroTrabalho();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TabuleiroTrabalho() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		for(int i= 0; i < 3; i++)
			for(int j= 0; j < 3; j++)
				matriz[i][j] = "-";
		
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.decode(text));
		frame.getContentPane().setForeground(Color.decode(text));
		frame.setTitle("JOGO DA VELHA | BY SAMUEL CABRAL");
		frame.setBounds(500, 40, 800, 946);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
			
		JLabel acaoJogador_1 = new JLabel("A\u00E7\u00E3o Jogador 1: ");
		acaoJogador_1.setForeground(Color.decode(text));
		acaoJogador_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		acaoJogador_1.setBounds(39, 316, 713, 28);
		frame.getContentPane().add(acaoJogador_1);
		
		JLabel acaoJogador_2 = new JLabel("A\u00E7\u00E3o Jogador 2: ");
		acaoJogador_2.setForeground(Color.decode(text));
		acaoJogador_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		acaoJogador_2.setBounds(39, 361, 713, 28);
		frame.getContentPane().add(acaoJogador_2);
			
		JLabel placar_1 = new JLabel("Placar:");
		placar_1.setForeground(Color.decode(text));
		placar_1.setFont(new Font("Tahoma", Font.BOLD, 23));
		placar_1.setBounds(83, 266, 254, 28);
		frame.getContentPane().add(placar_1);
		placar_1.setText("Placar: " + placar1);
		
		JLabel placar_2 = new JLabel("Placar:");
		placar_2.setForeground(Color.decode(text));
		placar_2.setFont(new Font("Tahoma", Font.BOLD, 23));
		placar_2.setBounds(420, 266, 254, 22);
		frame.getContentPane().add(placar_2);
		placar_2.setText("Placar: " + placar2);
		
		JLabel label_X = new JLabel("X:");
		label_X.setBounds(83, 225, 254, 28);
		frame.getContentPane().add(label_X);
		label_X.setFont(new Font("Tahoma", Font.BOLD, 23));
		label_X.setForeground(Color.decode(text));
		
		JLabel label_O = new JLabel("O:");
		label_O.setBounds(420, 231, 254, 22);
		frame.getContentPane().add(label_O);
		label_O.setFont(new Font("Tahoma", Font.BOLD, 23));
		label_O.setForeground(Color.decode(text));
				
		JLabel digiteJogador_1 = new JLabel("Digite o Nome do Jogador 1:");
		digiteJogador_1.setForeground(Color.decode(text));
		digiteJogador_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		digiteJogador_1.setBounds(83, 89, 254, 30);
		frame.getContentPane().add(digiteJogador_1);
		
		JLabel digiteJogador_2 = new JLabel("Digite o Nome do Jogador 2:");
		digiteJogador_2.setForeground(Color.decode(text));
		digiteJogador_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		digiteJogador_2.setBounds(420, 89, 254, 30);
		frame.getContentPane().add(digiteJogador_2);
		
		
		jogador1Text = new JTextField();
		jogador1Text.setHorizontalAlignment(SwingConstants.CENTER);
		jogador1Text.setFont(new Font("Tahoma", Font.BOLD, 22));
		jogador1Text.setForeground(Color.decode(accent));
		jogador1Text.setBounds(83, 119, 254, 40);
		frame.getContentPane().add(jogador1Text);
		jogador1Text.setColumns(10);
		/* Se apertar Enter no campo jogador 1, ele inicia o jogo */
		jogador1Text.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					jogador1Nome = jogador1Text.getText();
					jogador2Nome = jogador2Text.getText();
					escolha = resultadoEscolha(jogador1Nome, jogador2Nome);
					label_X.setText("X: " + jogador1Nome);
					
					if(jogador2Nome.equals("(opcional)"))
						label_O.setText("O: Máquina");
					else
						label_O.setText("O: " + jogador2Nome);
					
					placar1 = 0;
					placar2 = 0;
					placar_1.setText("Placar: " + placar1);
					placar_2.setText("Placar: " + placar2);
					
					switchEscolha(escolha);
					jogo.printMatriz();
					
				}
			}
		});
		
		jogador2Text = new JTextField();
		jogador2Text.setText("(opcional)");
		jogador2Text.setHorizontalAlignment(SwingConstants.CENTER);
		jogador2Text.setToolTipText("");
		jogador2Text.setFont(new Font("Tahoma", Font.BOLD, 22));
		jogador2Text.setForeground(Color.decode(accent));
		jogador2Text.setColumns(10);
		jogador2Text.setBounds(420, 119, 254, 40);
		frame.getContentPane().add(jogador2Text);
		/* Se apertar Enter no campo jogador 2, ele inicia o jogo */
		jogador2Text.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					jogador1Nome = jogador1Text.getText();
					jogador2Nome = jogador2Text.getText();
					escolha = resultadoEscolha(jogador1Nome, jogador2Nome);
					label_X.setText("X: " + jogador1Nome);
					
					
					if(jogador2Nome.equals("(opcional)"))
						label_O.setText("O: Máquina");
					else
						label_O.setText("O: " + jogador2Nome);
					
					placar1 = 0;
					placar2 = 0;
					placar_1.setText("Placar: " + placar1);
					placar_2.setText("Placar: " + placar2);
					
					switchEscolha(escolha);
					jogo.printMatriz();
				}
			}
		});
		
		/* Botoes Iniciar e Reiniciar */
		
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.setBackground(Color.decode(botao));
		btnIniciar.setForeground(Color.white);
		btnIniciar.setFont(new Font("Tahoma", Font.BOLD, 28));
		btnIniciar.setBounds(83, 172, 254, 40);
		frame.getContentPane().add(btnIniciar);
		/* Se apertar no botao Iniciar, ele inicia o jogo do zero, placar zerado */
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jogador1Nome = jogador1Text.getText();
				jogador2Nome = jogador2Text.getText();
				escolha = resultadoEscolha(jogador1Nome, jogador2Nome);
				label_X.setText("X: " + jogador1Nome);
				
				if(jogador2Nome.equals("(opcional)"))
					label_O.setText("O: Máquina");
				else
					label_O.setText("O: " + jogador2Nome);
			
				placar1 = 0;
				placar2 = 0;
				placar_1.setText("Placar: " + placar1);
				placar_2.setText("Placar: " + placar2);
				
				switchEscolha(escolha);
				jogo.printMatriz();
			}
		});
		
		btnIniciar.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					jogador1Nome = jogador1Text.getText();
					jogador2Nome = jogador2Text.getText();
					escolha = resultadoEscolha(jogador1Nome, jogador2Nome);
					label_X.setText("X: " + jogador1Nome);
					
					System.out.println("Jogador 2" + jogador2Nome);
					if(jogador2Nome.equals("(opcional)"))
						label_O.setText("O: Máquina");
					else
						label_O.setText("O: " + jogador2Nome);
					
					placar1 = 0;
					placar2 = 0;
					placar_1.setText("Placar: " + placar1);
					placar_2.setText("Placar: " + placar2);
					
					switchEscolha(escolha);
					jogo.printMatriz();
				}
			}
		});
			
		btnReiniciar = new JButton("Reiniciar");
		btnReiniciar.setBounds(420, 172, 254, 40);
		frame.getContentPane().add(btnReiniciar);
		btnReiniciar.setBackground(Color.decode(botao));
		btnReiniciar.setForeground(Color.white);
		btnReiniciar.setFont(new Font("Tahoma", Font.BOLD, 28));
		/* Se apertar no botao Reiniciar, ele reinicia o jogo mantendo nomes e placar vai auemntando */
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ticTacToeTilesReset();
				jogador1Nome = jogador1Text.getText();
				jogador2Nome = jogador2Text.getText();
				escolha = resultadoEscolha(jogador1Nome, jogador2Nome);
				System.out.println("ESCOLHA ESCOLHA " + jogador1Nome +"2: " + jogador2Nome);
				label_X.setText("X: " + jogador1Nome);
							
				if(jogador2Nome.equals("(opcional)"))
					label_O.setText("O: Máquina");
				else
					label_O.setText("O: " + jogador2Nome);
				
				switchEscolha(escolha);
				jogo.printMatriz();

			}
		});
	
		panel.setBounds(0, 0, 782, 402);
		frame.getContentPane().add(panel);
		panel.setBackground(Color.decode(accent));
		
		JLabel jogoDaVelha = new JLabel("JOGO DA VELHA");
		panel.add(jogoDaVelha);
		jogoDaVelha.setFont(new Font("Lemon", Font.BOLD, 49));
		jogoDaVelha.setForeground(Color.decode(text));
		
		//inicializar a matriz de labels
		for(int i=0; i < 3; i++){
			for(int j=0; j < 3; j++){
			
				int offset_x = 150;
				int offset_y = 425;
				int tileSize = 150;
				TicTacToeTiles[i][j]=new JLabel(" ");
				frame.getContentPane().add(TicTacToeTiles[i][j]);
				TicTacToeTiles[i][j].setBounds(i*tileSize + offset_x, j*tileSize + offset_y, tileSize, tileSize);	//x,y, width, height - 40x40
				TicTacToeTiles[i][j].setBackground(Color.WHITE);
				
				/* Criando o shape do jogo da velha usando border */
				int borderSize = 7;
				if(i == 0 && j == 0 || i == 0 && j == 1 || i == 1 && j == 0 || i == 1 && j == 1)
					TicTacToeTiles[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, borderSize, borderSize, Color.decode(accent)));
				
				if(i == 0 && j == 2 || i == 1 && j == 2)
					TicTacToeTiles[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 0, borderSize, Color.decode(accent)));
			
				if(i == 2 && j == 0 || i == 2 && j == 1)
					TicTacToeTiles[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, borderSize, 0, Color.decode(accent)));
				
				if(i == 2 && j == 2)
					TicTacToeTiles[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.decode(accent)));
				
				TicTacToeTiles[i][j].setOpaque(true);
				TicTacToeTiles[i][j].addMouseListener(new  MouseAdapter(){
					public void mouseClicked(MouseEvent e){		//click
						JLabel b = (JLabel)e.getSource();
						int clickOffset_x = -1;
						int clickOffset_y = -3;
						indicex = b.getX()/140 + clickOffset_x;
						indicey = b.getY()/140 + clickOffset_y;
						System.out.println("x: " +indicex + "y: " + indicey);
						
						if(comecou != 0) {
							boolean jogadaValida = jogo.jogarJogador(numeroJogador, indicey, indicex);
							jogo.printMatriz();

							if(jogadaValida) {
								String valorR = null;

								if(numeroJogador == 1) {
									acaoJogador_1.setText("Ação Jogador 1: " + jogo.getNomeJogador(1) + " jogou na posicao "+ linha + "-" + coluna);
									System.out.println("Jogador " + jogo.getNomeJogador(1) + " jogou na posicao "+ linha + "-" + coluna);
									valorR = "X";
									
									ImageIcon iconX = new ImageIcon(Tabuleiro.class.getResource("/imagens/"+ nomes[0]));
									iconX.setImage(iconX.getImage().getScaledInstance(120, 120, 2) );
											
									matriz[indicex][indicey] = "X";
									TicTacToeTiles[indicex][indicey].setBackground(Color.WHITE);
//									 
									if(TicTacToeTiles[indicex][indicey].getIcon() == null)
										TicTacToeTiles[indicex][indicey].setIcon(iconX);
									
									TicTacToeTiles[indicex][indicey].setHorizontalTextPosition(0);
									TicTacToeTiles[indicex][indicey].setHorizontalAlignment(JLabel.CENTER);
									TicTacToeTiles[indicex][indicey].setFont(new Font("Tahoma", Font.PLAIN, 20));
									
								}
								else {
									acaoJogador_2.setText("Ação Jogador 2: " + jogo.getNomeJogador(2) + " jogou na posicao "+ linha + "-" + coluna);
									System.out.println("Jogador " + jogo.getNomeJogador(2) + " jogou na posicao "+linha + "-" + coluna);
									valorR = "O";
									
									ImageIcon icon =new ImageIcon(Tabuleiro.class.getResource("/imagens/"+ nomes[1]));
									icon.setImage(icon.getImage().getScaledInstance(120, 120, 1) );
									matriz[indicex][indicey] = "O";
									TicTacToeTiles[indicex][indicey].setBackground(Color.WHITE);									 
									
									if(TicTacToeTiles[indicex][indicey].getIcon() == null)
										TicTacToeTiles[indicex][indicey].setIcon(icon);
									
									TicTacToeTiles[indicex][indicey].setHorizontalTextPosition(0);
									TicTacToeTiles[indicex][indicey].setHorizontalAlignment(JLabel.CENTER);
									TicTacToeTiles[indicex][indicey].setFont(new Font("Tahoma", Font.PLAIN, 20));
								}
								
								if(numeroJogador==1) 
									numeroJogador=2; 
								else numeroJogador=1;
								
								/* Jogadas da Maquina */
								if(escolha == 1) { //Escolha 1 significa 1 jogador
									numeroJogador=1;
									try {
										if(!jogo.terminou()) {
											ImageIcon iconO =new ImageIcon(Tabuleiro.class.getResource("/imagens/"+ nomes[1]));
											iconO.setImage(iconO.getImage().getScaledInstance(120, 120, 1) );
											jogo.jogarMaquina();
											coluna = jogo.getUltimaLinha();
											linha = jogo.getUltimaColuna();
											matriz[linha][coluna] = "O";
											TicTacToeTiles[linha][coluna].setIcon(iconO);
											TicTacToeTiles[linha][coluna].setHorizontalTextPosition(0);
											TicTacToeTiles[linha][coluna].setHorizontalAlignment(JLabel.CENTER);
											TicTacToeTiles[linha][coluna].setFont(new Font("Tahoma", Font.PLAIN, 20));
											
											acaoJogador_2.setText("Ação Jogador 2: A Maquina jogou na posicao "+ linha + "-" + coluna);
											System.out.println("A Maquina jogou na posicao "+linha + "-" + coluna);
											
											jogo.printMatriz();
										}
									} catch (IOException e1) {
										e1.printStackTrace();
									}
								}
								
								linha = jogo.getUltimaLinha();
								coluna = jogo.getUltimaColuna();
								matriz[linha][coluna] = valorR;
								
							}
							
							System.out.println("clicou na celula:"+  indicex + "-" + indicey);
						
							try {
								if(jogo.terminou()) {
									switch(jogo.getResultado()) {
										case 1: 
											JOptionPane.showMessageDialog(null, "Resultado: " + jogo.getNomeJogador(1) + " venceu"); 
											acaoJogador_1.setText("Resultado: " + jogo.getNomeJogador(1) + " venceu");
											acaoJogador_2.setText("");
											ticTacToeTilesReset();
											placar1++;
											placar_1.setText("Placar: " + placar1);
											comecou=0;
											break;
										case 2: 
											JOptionPane.showMessageDialog(null, "Resultado: " + jogo.getNomeJogador(2) + " venceu"); 
											acaoJogador_1.setText("Resultado: " + jogo.getNomeJogador(2) + " venceu");
											acaoJogador_2.setText("");
											ticTacToeTilesReset();
											comecou=0;
											placar2++;
											placar_2.setText("Placar: " + placar2);
											break;
										case 3: 
											JOptionPane.showMessageDialog(null, "Resultado: Ninguem venceu");
											acaoJogador_1.setText("Resultado: Ninguem venceu");
											acaoJogador_2.setText("");
											ticTacToeTilesReset();
											comecou=0;
											break;
									}
								}
							} catch (HeadlessException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
				});
			}
		}
	}
	
	public void ticTacToeTilesReset() {
		for(int i=0; i < 3; i++) {
			for(int j=0; j < 3; j++) {
				TicTacToeTiles[i][j].setText(" ");	
				TicTacToeTiles[i][j].setBackground(Color.WHITE);
				TicTacToeTiles[i][j].setIcon(null);
				matriz[indicex][indicey] = "-";
			}					
		}
	}
	
	public int resultadoEscolha(String jogador1Nome, String jogador2Nome){
		if(!jogador1Nome.equals("") && jogador2Nome.equals("(opcional)")) {
			return 1;
		}
		else if(!jogador1Nome.equals("") && !jogador2Nome.equals("(opcional)")) {
			return 2;
		}
		else {
			return -1;
		}
	}
	
	public void iniciar1Jogador() throws IOException {
		ticTacToeTilesReset();
		numeroJogador=1;
		comecou=1;		
		jogo = new JogoDaVelha(jogador1Nome);
	}

	public void iniciar2Jogadores() throws IOException {
		ticTacToeTilesReset();
		comecou=2;
		numeroJogador=1;
		jogo = new JogoDaVelha(jogador1Nome, jogador2Nome);
	}

	public void switchEscolha (int escolha) {
		switch(escolha) {
			case 1: System.out.println("Escolheu 1 jogador apenas"); try {
				iniciar1Jogador();
			} catch (IOException e2) {
				e2.printStackTrace();
			} break;
			case 2: System.out.println("Escolheu 2 jogadores"); try {
				iniciar2Jogadores();
			} catch (IOException e1) {
				e1.printStackTrace();
			} break;
		}
	}
}


