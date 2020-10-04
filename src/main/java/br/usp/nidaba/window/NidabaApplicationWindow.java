package br.usp.nidaba.window;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.apache.commons.io.FileUtils;

import br.usp.nidaba.event.Event;
import br.usp.nidaba.event.EventType;
import br.usp.nidaba.event.File;
import br.usp.nidaba.event.HomeRequest;
import br.usp.nidaba.event.HomeResponse;
import br.usp.nidaba.event.LoginRequest;
import br.usp.nidaba.event.LoginResponse;
import br.usp.nidaba.event.LoginStatus;
import br.usp.nidaba.event.NewFileRequest;
import br.usp.nidaba.event.NewFileResponse;
import br.usp.nidaba.socket.Client;

public class NidabaApplicationWindow {

	private JFrame frame;
	private JLabel editorDescricaoCriacaoLabel;
	
	SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	//LOGIN COMPONENTS
	private JPanel LoginPanel;  
	private JPanel loginLogoPanel;
	private JTextField loginUsuarioTextField;
	private JPasswordField loginSenhaTextField;
	private JLabel loginSenhaIncorretaLabel;
	
	//SIGN UP COMPONENTES
	private JPanel SignUpPanel;
	private JTextField signUpUsuarioTextField;
	private JTextField signUpEmailTextField;
	private JPasswordField signUpPasswordField;
	
	//EDITOR
	private JPanel EditorPanel;
	private JPanel editorDescricaoPanel;
	private JTextPane editorTextPane;
	private JLabel editorDescricaoArquivoLabel;
	private JLabel editorDescricaoCriadorLabel;
	private JLabel editorDescricaoModificacaoLabel;
	private JList editorUsuariosList;
	
	
	//HOME
	JPanel HomePanel;
	JList list;
	private Client clientSocket;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NidabaApplicationWindow window = new NidabaApplicationWindow();
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
	public NidabaApplicationWindow() {
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
		
		
		/************************************ LOGIN *****************************************/
		
		LoginPanel = new JPanel();
		LoginPanel.setBackground(SystemColor.textHighlightText);
		frame.getContentPane().add(LoginPanel, "name_75585548540600");
		LoginPanel.setLayout(null);
		LoginPanel.setVisible(true);
		
		JLabel loginUsuarioLabel = new JLabel("USUÁRIO");
		loginUsuarioLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
		loginUsuarioLabel.setBounds(296, 145, 73, 14);
		LoginPanel.add(loginUsuarioLabel);
		
		loginUsuarioTextField = new JTextField();
		loginUsuarioTextField.setColumns(10);
		loginUsuarioTextField.setBounds(298, 165, 351, 38);
		LoginPanel.add(loginUsuarioTextField);
		
		JLabel loginSenhaLabel = new JLabel("SENHA");
		loginSenhaLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
		loginSenhaLabel.setBounds(299, 210, 73, 14);
		LoginPanel.add(loginSenhaLabel);
		
		loginSenhaTextField = new JPasswordField();
		loginSenhaTextField.setBounds(300, 229, 351, 38);
		LoginPanel.add(loginSenhaTextField);
		
		loginSenhaIncorretaLabel = new JLabel("");
		loginSenhaIncorretaLabel.setBounds(296, 96, 353, 38);
		LoginPanel.add(loginSenhaIncorretaLabel);
		
		
		/**************************************************************************************/

		
		/************************************ LOGIN LOGO *************************************/
		
		loginLogoPanel = new JPanel();
		loginLogoPanel.setBackground(Color.BLACK);
		loginLogoPanel.setBounds(0, 0, 228, 563);
		LoginPanel.add(loginLogoPanel);
		loginLogoPanel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\leoco\\Desktop\\Nidaba - Resources\\Nidaba.jpg"));
		lblNewLabel_3.setBounds(-52, 152, 318, 463);
		loginLogoPanel.add(lblNewLabel_3);
		
		JLabel label_7 = new JLabel("NIDABA");
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("Arial", Font.BOLD, 47));
		label_7.setBounds(24, 50, 224, 125);
		loginLogoPanel.add(label_7);
		
		/**************************************************************************************/
		
		/************************************ SIGN UP *****************************************/

		SignUpPanel = new JPanel();
		SignUpPanel.setBackground(SystemColor.textHighlightText);
		frame.getContentPane().add(SignUpPanel, "name_75590307492500");
		SignUpPanel.setLayout(null);
		SignUpPanel.setVisible(false);
		
		JLabel signUpUsuarioLabel = new JLabel("USUÁRIO");
		signUpUsuarioLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
		signUpUsuarioLabel.setBounds(300, 112, 73, 14);
		SignUpPanel.add(signUpUsuarioLabel);

		signUpUsuarioTextField = new JTextField();
		signUpUsuarioTextField.setColumns(10);
		signUpUsuarioTextField.setBounds(300, 137, 351, 38);
		SignUpPanel.add(signUpUsuarioTextField);
				
		JLabel signUpEmailLabel = new JLabel("EMAIL");
		signUpEmailLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
		signUpEmailLabel.setBounds(300, 186, 73, 14);
		SignUpPanel.add(signUpEmailLabel);
		
		signUpEmailTextField = new JTextField();
		signUpEmailTextField.setColumns(10);
		signUpEmailTextField.setBounds(300, 211, 351, 38);
		SignUpPanel.add(signUpEmailTextField);
		
		JLabel signUpSenhaLabel = new JLabel("SENHA");
		signUpSenhaLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
		signUpSenhaLabel.setBounds(300, 260, 73, 14);
		SignUpPanel.add(signUpSenhaLabel);
		
		signUpPasswordField = new JPasswordField();
		signUpPasswordField.setBounds(300, 282, 351, 38);
		SignUpPanel.add(signUpPasswordField);
		
		/**************************************************************************************/

		/************************************ SIGN UP LOGO *************************************/

		JPanel signUpLogoPanel = new JPanel();
		signUpLogoPanel.setBackground(Color.BLACK);
		signUpLogoPanel.setBounds(0, 0, 228, 563);
		SignUpPanel.add(signUpLogoPanel);

		/**************************************************************************************/

		
		/************************************ EDITOR ******************************************/

		EditorPanel = new JPanel();
		EditorPanel.setBackground(SystemColor.controlHighlight);
		frame.getContentPane().add(EditorPanel, "name_87723977858900");
		EditorPanel.setLayout(null);
		
		editorTextPane = new JTextPane();
		editorTextPane.setBounds(10, 11, 511, 541);
		EditorPanel.add(editorTextPane);
		
		editorDescricaoPanel = new JPanel();
		editorDescricaoPanel.setBorder(new LineBorder(Color.GRAY));
		editorDescricaoPanel.setBounds(531, 11, 179, 111);
		EditorPanel.add(editorDescricaoPanel);
		editorDescricaoPanel.setLayout(null);
		
		editorDescricaoArquivoLabel = new JLabel("ARQUIVO:");
		editorDescricaoArquivoLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		editorDescricaoArquivoLabel.setBounds(10, 11, 169, 14);
		editorDescricaoPanel.add(editorDescricaoArquivoLabel);
		
		editorDescricaoCriadorLabel = new JLabel("CRIADOR:");
		editorDescricaoCriadorLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		editorDescricaoCriadorLabel.setBounds(10, 36, 169, 14);
		editorDescricaoPanel.add(editorDescricaoCriadorLabel);
		
		editorDescricaoCriacaoLabel = new JLabel("CRIAÇÃO");
		editorDescricaoCriacaoLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		editorDescricaoCriacaoLabel.setBounds(10, 61, 169, 14);
		editorDescricaoPanel.add(editorDescricaoCriacaoLabel);
		
		editorDescricaoModificacaoLabel = new JLabel("MODIFICAÇÃO:");
		editorDescricaoModificacaoLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		editorDescricaoModificacaoLabel.setBounds(10, 86, 169, 14);
		editorDescricaoPanel.add(editorDescricaoModificacaoLabel);

		editorUsuariosList = new JList();
		editorUsuariosList.setBorder(new LineBorder(Color.GRAY));
		editorUsuariosList.setBounds(531, 423, 179, 79);
		EditorPanel.add(editorUsuariosList);
		
		JLabel editorUsuarioEditandoLabel = new JLabel("USUÁRIOS EDITANDO");
		editorUsuarioEditandoLabel.setForeground(SystemColor.textHighlight);
		editorUsuarioEditandoLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		editorUsuarioEditandoLabel.setBounds(531, 403, 135, 14);
		EditorPanel.add(editorUsuarioEditandoLabel);
		
		/**************************************************************************************/

		/* BOTAO SIGN UP CADASTRAR  */

		Button signUpCadastrarButton = new Button("CADASTRAR");
		signUpCadastrarButton.setForeground(Color.WHITE);
		signUpCadastrarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		signUpCadastrarButton.setBackground(new Color(241, 57, 83));
		signUpCadastrarButton.setBounds(300, 336, 351, 45);
		SignUpPanel.add(signUpCadastrarButton);

		/* BOTAO LOGIN CADASTRAR */

		Button loginCadastrarButton = new Button("CADASTRAR");
		loginCadastrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpPanel.setVisible(true);
				LoginPanel.setVisible(false);
			}
		});
		loginCadastrarButton.setForeground(Color.WHITE);
		loginCadastrarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		loginCadastrarButton.setBackground(SystemColor.windowBorder);
		loginCadastrarButton.setBounds(297, 332, 168, 45);
		LoginPanel.add(loginCadastrarButton);
		
		
		
		
		JButton editorCriarButton = new JButton("CRIAR");
		editorCriarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		          String name = JOptionPane.showInputDialog(EditorPanel,
	                        "Insira o nome do arquivo", null);
		          if(name != null) {
			          try {
							criarNovoArquivoUpload(editorTextPane.getText(), name);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

		          }
		});
		editorCriarButton.setBackground(new Color(160, 160, 160));
		editorCriarButton.setBounds(531, 133, 179, 34);
		EditorPanel.add(editorCriarButton);
		
		Button loginEsqueciSenhaButton = new Button("ESQUECI A SENHA");
		loginEsqueciSenhaButton.setForeground(Color.WHITE);
		loginEsqueciSenhaButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		loginEsqueciSenhaButton.setBackground(SystemColor.windowBorder);
		loginEsqueciSenhaButton.setBounds(478, 332, 168, 45);
		LoginPanel.add(loginEsqueciSenhaButton);
		
		
		
		JButton editorUnshareButton = new JButton("PARAR COMPARTILHAMENTO");
		editorUnshareButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		editorUnshareButton.setBackground(SystemColor.window);
		editorUnshareButton.setBounds(531, 313, 179, 34);
		EditorPanel.add(editorUnshareButton);
		
		JButton editorExportarButton = new JButton("EXPORTAR");
		editorExportarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exportarArquivo();
			}
		});
		editorExportarButton.setBackground(SystemColor.window);
		editorExportarButton.setBounds(531, 223, 179, 34);
		EditorPanel.add(editorExportarButton);
		
		JButton editorUploadButton = new JButton("UPLOAD");
		editorUploadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final JFileChooser fc = new JFileChooser();
		        int returnValue = fc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					java.io.File selectedFile = fc.getSelectedFile();
					try {
						String contextAsString = FileUtils.readFileToString(selectedFile, StandardCharsets.UTF_8);
						criarNovoArquivoUpload(contextAsString, selectedFile.getName());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}

		});
		editorUploadButton.setBackground(SystemColor.window);
		editorUploadButton.setBounds(531, 178, 179, 34);
		EditorPanel.add(editorUploadButton);
		
		JButton editorCompartilharButton = new JButton("COMPARTILHAR");
		editorCompartilharButton.setBackground(SystemColor.window);
		editorCompartilharButton.setBounds(531, 268, 179, 34);
		EditorPanel.add(editorCompartilharButton);
		
		
		Button loginEntrarButton = new Button("LOGIN");
		loginEntrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					realizarLogin();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//senhaIncorreta();
//				EditorPanel.setVisible(true);
//				LoginPanel.setVisible(false);
			}
		});
		loginEntrarButton.setForeground(Color.WHITE);
		loginEntrarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		loginEntrarButton.setBackground(new Color(241, 57, 83));
		loginEntrarButton.setBounds(296, 280, 351, 45);
		LoginPanel.add(loginEntrarButton);
				
		
		JButton editorExcluitButton = new JButton("EXCLUIR");
		editorExcluitButton.setBackground(Color.WHITE);
		editorExcluitButton.setBounds(531, 358, 179, 34);
		EditorPanel.add(editorExcluitButton);
		
		JButton editorVoltarButton = new JButton("VOLTAR");
		editorVoltarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		editorVoltarButton.setBackground(Color.WHITE);
		editorVoltarButton.setBounds(585, 513, 77, 34);
		EditorPanel.add(editorVoltarButton);
		
		
		HomePanel = new JPanel();
		frame.getContentPane().add(HomePanel, "name_148599297405900");
		HomePanel.setLayout(null);
		
		String week[]= { "Monday","Tuesday","Wednesday", 
                "Thursday","Friday","Saturday","Sunday"}; 
		
		list = new JList(week);
		list.setBounds(398, 28, 297, 484);
		HomePanel.add(list);
		
		JButton homeNovoArquivoButton = new JButton("NOVO ARQUIVO");
		homeNovoArquivoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditorPanel.setVisible(true);
				HomePanel.setVisible(false);
			}
		});
		homeNovoArquivoButton.setBounds(548, 523, 147, 29);
		HomePanel.add(homeNovoArquivoButton);
		
		JButton homeAbrirButton = new JButton("ABRIR");
		homeAbrirButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedFile = list.getSelectedValue().toString();
				System.out.println(selectedFile);
			}
		});
		homeAbrirButton.setBounds(398, 523, 147, 29);
		HomePanel.add(homeAbrirButton);
		
		loginEsqueciSenhaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				PasswordRecovery passwordRecovery = new PasswordRecovery();
//				passwordRecovery.setLocationRelativeTo(LoginPanel);
//				passwordRecovery.setVisible(true);
				LoginPanel.setVisible(false);
				HomePanel.setVisible(true);
				
			}
		});
		
	}
	
	public void realizarLogin() throws IOException { 
		
		Event event = new Event(EventType.LOGIN, new LoginRequest(loginUsuarioTextField.getText(), new String(loginSenhaTextField.getPassword())));
		clientSocket = new Client(this);
		clientSocket.initializeSocket();
		clientSocket.sendEvent(event);
		
	}
	

	private void criarNovoArquivoUpload(String contentAsString, String fileName) throws IOException {
		NewFileRequest newFileRequest = new NewFileRequest(fileName, loginUsuarioTextField.getText(), contentAsString);
		Event event = new Event(EventType.NEWFILE, newFileRequest);
		clientSocket.sendEvent(event);
	}
	
	public void realizarCadastro() { 
		Event event = new Event(EventType.LOGIN, new LoginRequest(loginUsuarioTextField.getText(), new String(loginSenhaTextField.getPassword())));

	}
	
	public void senhaIncorreta () {
		
		loginSenhaIncorretaLabel.setOpaque(true);
		loginSenhaIncorretaLabel.setText("SENHA INCORRETA!");
		loginSenhaIncorretaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginSenhaIncorretaLabel.setVerticalAlignment(SwingConstants.CENTER);
		loginSenhaIncorretaLabel.setBackground(Color.RED);
		loginSenhaIncorretaLabel.setForeground(Color.WHITE);
		loginSenhaIncorretaLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

	}
	
	public void recebeEventos(Event eventoRecebido) throws IOException {
		
		EventType eventType = eventoRecebido.getEventType();
		
		switch (eventType) {
		
		case LOGIN :
			
			LoginResponse loginResponse = (LoginResponse) eventoRecebido.getContent();
			
			if (loginResponse.getLoginStatus() == LoginStatus.NOTALLOWED) {
				
//				clientSocket.closeSocketConnection();
				senhaIncorreta();
				
			} else {
				
				
				obterListaArquivos();
				HomePanel.setVisible(true);
				LoginPanel.setVisible(false);
				
			}
			break;
		case NEWFILE:
				NewFileResponse newFileResponse = (NewFileResponse) eventoRecebido.getContent();
				editorTextPane.setText(newFileResponse.getFile().getContent());
				editorDescricaoArquivoLabel.setText("ARQUIVO: "+newFileResponse.getFile().getName());
				editorDescricaoCriadorLabel.setText("CRIADOR: "+newFileResponse.getFile().getOwner());
				editorDescricaoCriacaoLabel.setText("CRIAÇÃO: "+newFileResponse.getFile().getCreationDate());
				editorDescricaoModificacaoLabel.setText("MODIFICAÇÃO: "+newFileResponse.getFile().getCreationDate());

				break;
		
		case HOME:
			HomeResponse homeResponse = (HomeResponse) eventoRecebido.getContent();
			Map<String, Integer> map = new HashMap<>();
			for (File file : homeResponse.getListOfFiles()) {
				map.put(String.format("%s - %s - %s", file.getName(), file.getOwner(), formatador.format(file.getCreationDate())), file.getId());
			}			
			Object[] files =  map.keySet().toArray();
			Arrays.sort(files);
			list.setListData(files);
		}
		
	}
	
	public void exportarArquivo() {
		String desktopPath = System.getProperty("user.home") + "\\Desktop\\"+editorDescricaoArquivoLabel.getText().substring(editorDescricaoArquivoLabel.getText().indexOf(" ")+1, editorDescricaoArquivoLabel.getText().length());
		System.out.println(desktopPath);
	    try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(desktopPath));
			writer.write(editorTextPane.getText());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void obterListaArquivos() throws IOException {
		HomeRequest homeRequest = new HomeRequest(loginUsuarioTextField.getText());
		Event event = new Event(EventType.HOME, homeRequest);
		clientSocket.sendEvent(event);
	}
}
