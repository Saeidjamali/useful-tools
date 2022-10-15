package uk.ac.mmu.cnt2;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

public class GuessNumberGameGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7640111014477115116L;
	private JPanel contentPane;
	private JTextField textGuess;

	int tries=0;
	int random;
	private JButton btnNewButton;
	private JButton btnReplay;
	private JLabel lblTries;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuessNumberGameGUI frame = new GuessNumberGameGUI();
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
	public GuessNumberGameGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//lets call the method here to generate the random number, so random number is available for first play.
		random = randomGenerator();//will now have 0-100 randomly
		
		
		JLabel lblNewLabel = new JLabel("I have a number between 1 and 100\u2014 Can you guess my number?");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblNewLabel.setBounds(26, 25, 439, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblPleaseEnterYour = new JLabel("Please enter your first guess.");
		lblPleaseEnterYour.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		lblPleaseEnterYour.setBounds(143, 72, 276, 14);
		contentPane.add(lblPleaseEnterYour);
		
		btnNewButton = new JButton("Guess");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//get the value user inputted
				int userInput;
				userInput = Integer.parseInt(textGuess.getText());
				
				if(userInput > random)
				{
					JOptionPane.showMessageDialog(GuessNumberGameGUI.this, "Your guess is higher...");
					++tries;
					lblTries.setText(tries+"");
					textGuess.setBackground(Color.RED);
				}
				else if(userInput < random)
				{
					JOptionPane.showMessageDialog(GuessNumberGameGUI.this, "Your guess is lower...");
					++tries;
					lblTries.setText(tries+"");
					textGuess.setBackground(Color.ORANGE);
				}
				else
				{
					++tries;
					//this means its equal hence guess right...
					JOptionPane.showMessageDialog(GuessNumberGameGUI.this, "Guessed Correctly\n\nTotal Tries "+tries);
					btnReplay.setEnabled(true);
					textGuess.setText("");
					btnNewButton.setEnabled(false);
					textGuess.setBackground(Color.GREEN);
					
				}
			}
		});
		btnNewButton.setBounds(210, 185, 89, 23);
		contentPane.add(btnNewButton);
		
		textGuess = new JTextField();
		textGuess.setBounds(210, 135, 86, 20);
		contentPane.add(textGuess);
		textGuess.setColumns(10);
		
		btnReplay = new JButton("Replay");
		btnReplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tries=0;//resets the tries
				random = randomGenerator();
				btnReplay.setEnabled(false);
				btnNewButton.setEnabled(true);	
				textGuess.setBackground(Color.WHITE);
			}
		});
		btnReplay.setEnabled(false);
		btnReplay.setBounds(210, 230, 89, 23);
		contentPane.add(btnReplay);
		
		lblTries = new JLabel("Tries...");
		lblTries.setEnabled(false);
		lblTries.setBounds(386, 157, 64, 14);
		contentPane.add(lblTries);
	}
	
	public int randomGenerator()
	{
		int random;
		
	    Random randomGenerator = new Random();
        random = randomGenerator.nextInt(101);		
        System.out.println(random+"");
		return random;
	}

}
