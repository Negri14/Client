package br.usp.nidaba.window;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.ScrollPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Button;

public class Editor extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editor frame = new Editor();
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
	public Editor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 480);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(Color.WHITE);
		textPane.setBounds(10, 22, 515, 340);
		contentPane.add(textPane);
		
		JButton Upload = new JButton("Upload File");
		Upload.setBackground(SystemColor.activeCaption);
		Upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(Upload);
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				}

			}
		});
		Upload.setBounds(553, 22, 161, 35);
		contentPane.add(Upload);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setBackground(SystemColor.activeCaption);
		btnDeletar.setBounds(553, 180, 161, 35);
		contentPane.add(btnDeletar);
		
		JButton Criar = new JButton("Criar");
		Criar.setBackground(SystemColor.activeCaption);
		Criar.setBounds(553, 63, 161, 35);
		contentPane.add(Criar);
		
		JButton Compartilhar = new JButton("Compartilhar");
		Compartilhar.setBackground(SystemColor.activeCaption);
		Compartilhar.setBounds(553, 98, 161, 35);
		contentPane.add(Compartilhar);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(526, 22, 17, 340);
		contentPane.add(scrollBar);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBackground(Color.WHITE);
		textPane_1.setBounds(553, 262, 161, 132);
		contentPane.add(textPane_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 373, 533, 57);
		contentPane.add(panel);
		
		JButton btnExportar = new JButton("Exportar");
		btnExportar.setBackground(SystemColor.activeCaption);
		btnExportar.setBounds(553, 140, 161, 35);
		contentPane.add(btnExportar);
		
		JLabel lblNewLabel = new JLabel("USUÁRIOS EDITANDO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(553, 226, 132, 14);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(553, 250, 132, 2);
		contentPane.add(separator);
		
		Button Voltar = new Button("VOLTAR");
		Voltar.setBounds(602, 400, 70, 30);
		contentPane.add(Voltar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(0, 0, 724, 21);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

	}
}
