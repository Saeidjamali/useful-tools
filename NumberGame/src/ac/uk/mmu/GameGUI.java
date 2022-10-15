package ac.uk.mmu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GameGUI extends JFrame {

	private JPanel contentPane;
	private JTextField text1;
	private JLabel lbl1;
	static Random random= new Random();
	static int rand = random.nextInt(100);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.out.println(rand);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameGUI frame = new GameGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GameGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		text1 = new JTextField();
		text1.setBounds(148, 67, 109, 20);
		contentPane.add(text1);
		text1.setColumns(10);
		
		JButton btnGuess = new JButton("Guess");
		btnGuess.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int num;
				String result;
				num = Integer.parseInt(text1.getText());
				result = Gamelogic.calculate(num);
				lbl1.setText(result);
			}
		});
		btnGuess.setBounds(157, 180, 89, 23);
		contentPane.add(btnGuess);
		
		lbl1 = new JLabel("");
		lbl1.setBounds(10, 98, 414, 14);
		contentPane.add(lbl1);
		
		JLabel lbl2 = new JLabel("");
		lbl2.setBounds(96, 123, 232, 14);
		contentPane.add(lbl2);
	}

}
