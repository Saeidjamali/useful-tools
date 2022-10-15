

import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.util.List;

@SuppressWarnings("serial")
public class TestXMLRead extends JFrame {

	private JPanel contentPane;
	private JTextField textFirst;
	private TextArea textOutput;
	final JFileChooser fc = new JFileChooser();	
	String content="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestXMLRead frame = new TestXMLRead();
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
	public TestXMLRead() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 889, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("Open File");
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int returnVal = fc.showOpenDialog(contentPane);//show the dialog to choose a file by user
				 
				 if (returnVal == JFileChooser.APPROVE_OPTION) {//this means user have selected a file
			            try 
			            {
				            File file = fc.getSelectedFile();
				            textFirst.setText(file.getAbsolutePath());			            
				            
				            XmlParser read = new XmlParser(); //create object out of our class...
				            List<Student> readStudents = read.readstudents(file.getAbsolutePath());
				            for (Student student : readStudents) {
				              textOutput.append(student+"\n\n");
				            }
				            
						} 
			            catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							textOutput.append("\nError.. Please try again...");
						}
			            
			        } else { //this means user  cancelled the file selection process...
			        	textOutput.setText("User cancelled the open command");			            
			        }
			}
		});
		button.setBounds(379, 11, 89, 23);
		contentPane.add(button);
		
		textFirst = new JTextField();
		textFirst.setColumns(10);
		textFirst.setBounds(94, 12, 275, 20);
		contentPane.add(textFirst);
		
		JLabel label = new JLabel("File");
		label.setBounds(43, 15, 55, 14);
		contentPane.add(label);
		
		textOutput = new TextArea();
		textOutput.setBounds(25, 62, 816, 356);
		contentPane.add(textOutput);
	}
}
