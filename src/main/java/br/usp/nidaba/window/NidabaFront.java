package br.usp.nidaba.window;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Button;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.ScrollPane;
import java.awt.List;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.border.LineBorder;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.swing.UIManager;
import javax.swing.ImageIcon;

public class NidabaFront {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JTextField textField_3;
	private JPasswordField passwordField_1;
	private JLabel label;
	private JLabel label_4;
	private JLabel lblNewLabel_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NidabaFront window = new NidabaFront();
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
	public NidabaFront() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBackground(SystemColor.textHighlightText);
		frame.setBounds(100, 100, 736, 602);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		
		//									JPANEL						\\
		JPanel LoginPanel = new JPanel();
		LoginPanel.setBackground(SystemColor.textHighlightText);
		frame.getContentPane().add(LoginPanel, "name_75585548540600");
		LoginPanel.setLayout(null);
		LoginPanel.setVisible(true);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 228, 563);
		LoginPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\leoco\\Desktop\\Nidaba - Resources\\Nidaba.jpg"));
		lblNewLabel_3.setBounds(-52, 152, 318, 463);
		panel.add(lblNewLabel_3);
		
		JLabel label_7 = new JLabel("NIDABA");
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("Arial", Font.BOLD, 47));
		label_7.setBounds(24, 50, 224, 125);
		panel.add(label_7);

		
		JLabel label_3 = new JLabel("USUÁRIO");
		label_3.setFont(new Font("Segoe UI", Font.BOLD, 11));
		label_3.setBounds(296, 145, 73, 14);
		LoginPanel.add(label_3);
		
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(298, 165, 351, 38);
		LoginPanel.add(textField_3);
		
		JLabel label_5 = new JLabel("SENHA");
		label_5.setFont(new Font("Segoe UI", Font.BOLD, 11));
		label_5.setBounds(299, 210, 73, 14);
		LoginPanel.add(label_5);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				senhaIncorreta();
			}
		});
		passwordField_1.setBounds(300, 229, 351, 38);
		LoginPanel.add(passwordField_1);
	
		
		Button button_3 = new Button("ESQUECI A SENHA");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PasswordRecovery passwordRecovery = new PasswordRecovery();
				passwordRecovery.setLocationRelativeTo(LoginPanel);
				passwordRecovery.setVisible(true);
				
			}
		});
		button_3.setForeground(Color.WHITE);
		button_3.setFont(new Font("Segoe UI", Font.BOLD, 14));
		button_3.setBackground(SystemColor.windowBorder);
		button_3.setBounds(478, 332, 168, 45);
		LoginPanel.add(button_3);
		
		JPanel SignUpPanel = new JPanel();
		SignUpPanel.setBackground(SystemColor.textHighlightText);
		frame.getContentPane().add(SignUpPanel, "name_75590307492500");
		SignUpPanel.setLayout(null);
		SignUpPanel.setVisible(false);

		JPanel LogoPanelSignUp = new JPanel();
		LogoPanelSignUp.setBackground(Color.BLACK);
		LogoPanelSignUp.setBounds(0, 0, 228, 563);
		SignUpPanel.add(LogoPanelSignUp);
		
		Button button = new Button("CADASTRAR");
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Segoe UI", Font.BOLD, 14));
		button.setBackground(new Color(241, 57, 83));
		button.setBounds(300, 336, 351, 45);
		SignUpPanel.add(button);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(300, 137, 351, 38);
		SignUpPanel.add(textField);
		
		this.label = new JLabel("USUÁRIO");
		label.setFont(new Font("Segoe UI", Font.BOLD, 11));
		label.setBounds(300, 112, 73, 14);
		SignUpPanel.add(label);
		
		JLabel label_1 = new JLabel("EMAIL");
		label_1.setFont(new Font("Segoe UI", Font.BOLD, 11));
		label_1.setBounds(300, 186, 73, 14);
		SignUpPanel.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(300, 211, 351, 38);
		SignUpPanel.add(textField_1);
		
		JLabel label_2 = new JLabel("SENHA");
		label_2.setFont(new Font("Segoe UI", Font.BOLD, 11));
		label_2.setBounds(300, 260, 73, 14);
		SignUpPanel.add(label_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(300, 282, 351, 38);
		SignUpPanel.add(passwordField);
		
		Button button_2 = new Button("CADASTRAR");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpPanel.setVisible(true);
				LoginPanel.setVisible(false);
			}
		});
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		button_2.setBackground(SystemColor.windowBorder);
		button_2.setBounds(297, 332, 168, 45);
		LoginPanel.add(button_2);
		
		JPanel EditorPanel = new JPanel();
		EditorPanel.setBackground(SystemColor.controlHighlight);
		frame.getContentPane().add(EditorPanel, "name_87723977858900");
		EditorPanel.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 11, 511, 541);
		EditorPanel.add(textPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.GRAY));
		panel_2.setBounds(531, 11, 179, 111);
		EditorPanel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ARQUIVO");
		lblNewLabel.setBounds(10, 11, 73, 14);
		panel_2.add(lblNewLabel);
		
		JLabel lblCriador = new JLabel("CRIADOR");
		lblCriador.setBounds(10, 36, 73, 14);
		panel_2.add(lblCriador);
		
		label_4 = new JLabel("CRIADOR");
		label_4.setBounds(10, 61, 73, 14);
		panel_2.add(label_4);
		
		JLabel label_6 = new JLabel("CRIADOR");
		label_6.setBounds(10, 86, 73, 14);
		panel_2.add(label_6);
		
		JButton btnNewButton = new JButton("CRIAR");
		btnNewButton.setBackground(SystemColor.window);
		btnNewButton.setBounds(531, 133, 179, 34);
		EditorPanel.add(btnNewButton);
		
		JButton btnUpload = new JButton("UPLOAD");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpload.setBackground(SystemColor.window);
		btnUpload.setBounds(531, 313, 179, 34);
		EditorPanel.add(btnUpload);
		
		JButton btnExportar = new JButton("EXPORTAR");
		btnExportar.setBackground(SystemColor.window);
		btnExportar.setBounds(531, 223, 179, 34);
		EditorPanel.add(btnExportar);
		
		JButton btnCompartilhar = new JButton("COMPARTILHAR");
		btnCompartilhar.setBackground(SystemColor.window);
		btnCompartilhar.setBounds(531, 178, 179, 34);
		EditorPanel.add(btnCompartilhar);
		
		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.setBackground(SystemColor.window);
		btnExcluir.setBounds(531, 268, 179, 34);
		EditorPanel.add(btnExcluir);
		
		JButton button_7 = new JButton("New button");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				senhaIncorreta();
			}
		});
		button_7.setBackground(SystemColor.window);
		button_7.setBounds(531, 358, 179, 34);
		EditorPanel.add(button_7);
		
		
		Button LOGIN = new Button("LOGIN");
		LOGIN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				senhaIncorreta();
//				EditorPanel.setVisible(true);
//				LoginPanel.setVisible(false);
			}
		});
		LOGIN.setForeground(Color.WHITE);
		LOGIN.setFont(new Font("Segoe UI", Font.BOLD, 14));
		LOGIN.setBackground(new Color(241, 57, 83));
		LOGIN.setBounds(296, 280, 351, 45);
		LoginPanel.add(LOGIN);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(296, 96, 353, 38);
		LoginPanel.add(lblNewLabel_2);
		
		
		JList list = new JList();
		list.setBorder(new LineBorder(Color.GRAY));
		list.setBounds(531, 423, 179, 129);
		EditorPanel.add(list);
		
		JLabel lblNewLabel_1 = new JLabel("USUÁRIOS EDITANDO");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(531, 403, 135, 14);
		EditorPanel.add(lblNewLabel_1);
		
		
	}
	
	public void senhaIncorreta () {
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setText("SENHA INCORRETA!");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setVerticalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBackground(Color.RED);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 14));

	}
}
