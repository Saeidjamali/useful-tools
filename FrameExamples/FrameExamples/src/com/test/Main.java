package com.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 408, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnFirst = new JButton("First");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				First first = new First();//just create a new object of the Jframe class and set visible to true
				first.setVisible(true);
			}
		});
		btnFirst.setBounds(39, 44, 89, 23);
		contentPane.add(btnFirst);
		
		JButton btnSecond = new JButton("Second");
		btnSecond.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Second second = new Second();
				second.setVisible(true);
			}
		});
		btnSecond.setBounds(156, 44, 89, 23);
		contentPane.add(btnSecond);
		
		JButton btnThird = new JButton("Third");
		btnThird.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Third third = new Third();
				third.setVisible(true);
			}
		});
		btnThird.setBounds(267, 44, 89, 23);
		contentPane.add(btnThird);
	}
}
