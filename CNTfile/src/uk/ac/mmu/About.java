package uk.ac.mmu;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class About extends JFrame {
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public About() {
		setTitle("About This Programme");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(150, 150, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(UIManager.getColor("Button.background"));
		textArea.setForeground(Color.BLACK);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setBounds(90, 90, 300, 300);
		contentPane.add(textArea);
		textArea.append("This programme is a file reader in three different\nformats including "
				+ "XML, JSON, and CSV.\n"
				+ "First select the data using Open button and load the\nfile. When file is loaded the data can be summarised\n"
				      + "Choose the Sensor with radio button and click\nsummary again for each individual sensor to see the\nsummarised data."
				      + " After pressing summary each\nindividual sensor can be visualised in a line chart\nby pressing Vis summary."
					  + " Visualise button will create a\nline chart showing the the whole file");
		textArea.append("\n\n\n");
		textArea.append("Created by Saeid Jamali ");
					  
	}
	
}
