package br.usp.nidaba.window;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PasswordRecovery extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordRecovery frame = new PasswordRecovery();
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
	public PasswordRecovery() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 223);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlightText);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.window);
		contentPane.add(panel, "name_79846051300500");
		panel.setLayout(null);
		
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(37, 69, 351, 38);
		panel.add(textField);
		
		JLabel lblInsiraOSeu = new JLabel("INSIRA O SEU EMAIL");
		lblInsiraOSeu.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblInsiraOSeu.setBounds(35, 45, 149, 14);
		panel.add(lblInsiraOSeu);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textHighlightText);
		contentPane.add(panel_1, "name_79846067590000");
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CASO O SEU E-MAIL ESTEJA CADASTRADO, VOCÊ RECEBERÁ A SUA SENHA!");
		lblNewLabel.setBounds(20, 34, 377, 96);
		panel_1.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("OK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(165, 117, 89, 23);
		btnNewButton_1.setBackground((new Color(241, 57, 83)));
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("ENVIAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(true);
				panel.setVisible(false);
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground((new Color(241, 57, 83)));
		btnNewButton.setBounds(169, 126, 99, 30);
		panel.add(btnNewButton);
	}
}
