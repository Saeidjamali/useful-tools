package ac.uk.muu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIbmi extends JFrame {

	private JPanel contentPane;
	private JButton btn1;
	private JLabel lbl;
	private JTextField text1;
	private JTextField text2;
	static String gender;
	
	private final ButtonGroup rdgender = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIbmi frame = new GUIbmi();
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
	public GUIbmi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btn1 = new JButton("click");
		btn1.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
		
				double weight, height;
				weight = Double.parseDouble(text1.getText());
				height= Double.parseDouble(text2.getText());
			String bmi=	BMILogic.calculate(weight, height, gender);
				lbl.setText(bmi);
			}
		});
		btn1.setBounds(55, 106, 89, 23);
		contentPane.add(btn1);
		
		lbl = new JLabel("TextArea");
		lbl.setBounds(76, 190, 208, 33);
		contentPane.add(lbl);
		
		text1 = new JTextField();
		text1.setText("weight");
		text1.setBounds(55, 36, 86, 20);
		contentPane.add(text1);
		text1.setColumns(10);
		
		text2 = new JTextField();
		text2.setText("height");
		text2.setBounds(55, 67, 86, 20);
		contentPane.add(text2);
		text2.setColumns(10);
		
		JRadioButton rdm = new JRadioButton("Male");
		rdm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender = "Male";
			}
		});
		rdgender.add(rdm);
		rdm.setBounds(227, 35, 109, 23);
		contentPane.add(rdm);
		
		JRadioButton rdfm = new JRadioButton("Female");
		rdfm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender = "Female";
			}
		});
		rdgender.add(rdfm);
		rdfm.setBounds(227, 61, 109, 23);
		contentPane.add(rdfm);
	}
}
