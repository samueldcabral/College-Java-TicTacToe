import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
/**
 * IFPB - TSI - POO 
 * @author Prof. Fausto Ayres
 *
 */
public class Sequencia  {
	private JFrame frame;
	JLabel[] labels ;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sequencia window = new Sequencia();
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
	public Sequencia() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Array de Labels");
		frame.setBounds(100, 100, 417, 219);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		//criar a sequencia de labels
		
		int largura = 60, altura = 60;	//dimensoes do  label = 40x40
		int n=10;
		labels= new JLabel[n];
		for(int i=0; i<n; i++){
			labels[i] = new JLabel(i+"");
			frame.getContentPane().add(labels[i]);			
			int posicaoX = i * (largura+5);		//posicao na tela
			int posicaoY = 100;  			//arbitraria
			labels[i].setBounds(posicaoX, posicaoY, largura, altura);		
			labels[i].setBorder(new LineBorder(new Color(0, 0, 0))); //Color.BLACK
			labels[i].setHorizontalAlignment(SwingConstants.CENTER);
			labels[i].setVerticalAlignment(SwingConstants.CENTER);
			labels[i].setBackground(Color.YELLOW);
			labels[i].setOpaque(true);
			labels[i].addMouseListener(new  MouseAdapter(){
				public void mouseClicked(MouseEvent e){		//click
					JLabel b = (JLabel)e.getSource();
					int posicao = b.getX()/(largura+5);
					labels[posicao].setBackground(Color.BLUE);
					System.out.println("x:"+  b.getX());
					System.out.println("clicou na celula:"+  posicao );
				}
			});
		}
	}
}


