import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

/**
 * IFPB - TSI - POO 
 * @author Prof. Fausto Ayres
 *
 */
public class Tabuleiro  {

	private JFrame frame;
	JLabel[][] labels = new JLabel[3][3];


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tabuleiro window = new Tabuleiro();
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
	public Tabuleiro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Matriz de Labels");
		frame.setBounds(100, 100, 417, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		//inicializar a matriz de labels
		for(int i=0; i < 3; i++){
			for(int j=0; j < 3; j++){
				labels[i][j]=new JLabel(i+","+j);
				frame.getContentPane().add(labels[i][j]);
				labels[i][j].setBounds(i*40, j*40, 40, 40);	//x,y, width, height - 40x40
				labels[i][j].setBackground(Color.YELLOW);
				labels[i][j].setBorder(new LineBorder(new Color(0, 0, 0)));
				labels[i][j].setOpaque(true);
				labels[i][j].addMouseListener(new  MouseAdapter(){
					public void mouseClicked(MouseEvent e){		//click
						JLabel b = (JLabel)e.getSource();
						int indicex = b.getX()/40;
						int indicey = b.getY()/40;
						labels[indicex][indicey].setBackground(Color.BLUE);
						System.out.println("clicou na celula:"+  indicex + "-" + indicey);
					}
				});
			}
		}

	}

}
