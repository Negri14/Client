package br.usp.nidaba.window;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Button;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	
	
	private String user;
	private String password;
	private String email;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
					//frame.setUndecorated(true);
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
	public SignUp() {
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 701, 520);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 243, 481);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(-26, 60, 300, 492);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\leoco\\Desktop\\Nidaba - Resources\\Nidaba.jpg"));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("NIDABA");
		lblNewLabel_2.setFont(new Font("Monospaced", Font.BOLD, 40));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(44, 31, 158, 53);
		panel.add(lblNewLabel_2);
		
		Button button = new Button("CADASTRAR");
		button.setFont(new Font("Segoe UI", Font.BOLD, 14));
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		//		contentPane.getComponent(n)
			}
		});
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(241, 57, 83));
		button.setBounds(286, 348, 351, 45);
		contentPane.add(button);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = textField.getText();
			}
		});
		textField.setBounds(286, 120, 351, 38);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("USUÁRIO");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblNewLabel.setBounds(286, 95, 73, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblEmail.setBounds(286, 169, 73, 14);
		contentPane.add(lblEmail);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(286, 194, 351, 38);
		contentPane.add(textField_1);
		
		JLabel lblPassword = new JLabel("SENHA");
		lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblPassword.setBounds(286, 243, 73, 14);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(286, 266, 351, 38);
		contentPane.add(passwordField);
	}
}
