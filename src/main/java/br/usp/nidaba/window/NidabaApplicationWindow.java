package br.usp.nidaba.window;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.apache.commons.io.FileUtils;

import br.usp.nidaba.event.Modificacao;
import br.usp.nidaba.event.DeleteRequest;
import br.usp.nidaba.event.DeleteResponse;
import br.usp.nidaba.event.EditingResponse;
import br.usp.nidaba.event.EditorResponse;
import br.usp.nidaba.event.Event;
import br.usp.nidaba.event.EventType;
import br.usp.nidaba.event.File;
import br.usp.nidaba.event.HomeResponse;
import br.usp.nidaba.event.LoginResponse;
import br.usp.nidaba.event.LoginStatus;
import br.usp.nidaba.event.NewFileResponse;
import br.usp.nidaba.event.RequestEditionResponse;
import br.usp.nidaba.event.SignUpResponse;
import br.usp.nidaba.event.SignUpStatus;
import br.usp.nidaba.event.UnshareResponse;
import br.usp.nidaba.event.UpdateFileRequest;
import br.usp.nidaba.service.EventService;
import br.usp.nidaba.service.Session;
import br.usp.nidaba.socket.Client;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class NidabaApplicationWindow {

	private JFrame frame;
	
	private SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			
	private static List<File> listOfFiles = new LinkedList<>();

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
	private JLabel signUpUsuarioLabel;
	
	//EDITOR
	private JPanel EditorPanel;
	private JPanel editorDescricaoPanel;
	private JTextArea editorTextPane;
	private JLabel editorDescricaoArquivoLabel2;
	private JLabel editorDescricaoCriadorLabel2;
	private JLabel editorDescricaoCriacaoLabel2;
	private JLabel editorDescricaoModificacaoLabel2;
	private JButton editorCompartilharButton;
	private JLabel editorUsuarioEditandoLabel2;
	private JButton editorEditarButton;
	
	//HOME
	JPanel HomePanel;
	private Client clientSocket;
    JTable logTabelaAlteracoes; 
    JLabel lblNewLabel_2;
    EventService service; 
    JPanel LogPanel;
    
    private List<Modificacao> modificacoes;
    
	private Session session;
	JButton editorExcluirButton; 
	JTable homeTabelaArquivos; 
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
	 * @throws IOException 
	 */
	public NidabaApplicationWindow() {
		initialize();
		createSocketConnection();
		this.service = new EventService(clientSocket);
	}
	

	public void createSocketConnection()  {
		
		this.session = new Session();
		this.clientSocket = new Client(this);
		
		try {
			
			this.clientSocket.initializeSocket();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
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
		LoginPanel.setBackground(SystemColor.menu);
		frame.getContentPane().add(LoginPanel, "name_75585548540600");
		LoginPanel.setLayout(null);
		frame.setTitle("Login");
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
		
		Button loginEsqueciSenhaButton = new Button("ESQUECI A SENHA");
		loginEsqueciSenhaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		          String email = JOptionPane.showInputDialog(LoginPanel,
	                        "Insira o seu e-mail", null);
		          if(email != null) {
		        	  JOptionPane.showMessageDialog(LoginPanel, "Você receberá a senha no seu e-mail caso ele esteja cadastrado");
		        	  service.enviarEmailSenha(email);
				}
			}
		});
		loginEsqueciSenhaButton.setForeground(Color.WHITE);
		loginEsqueciSenhaButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		loginEsqueciSenhaButton.setBackground(SystemColor.windowBorder);
		loginEsqueciSenhaButton.setBounds(478, 332, 168, 45);
		LoginPanel.add(loginEsqueciSenhaButton);

		Button loginCadastrarButton = new Button("CADASTRAR");
		loginCadastrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setTitle("Cadastro");
				signUpUsuarioTextField.setText("");
				signUpPasswordField.setText("");
				signUpEmailTextField.setText("");
				SignUpPanel.setVisible(true);
				LoginPanel.setVisible(false);
			}
		});
		loginCadastrarButton.setForeground(Color.WHITE);
		loginCadastrarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		loginCadastrarButton.setBackground(SystemColor.windowBorder);
		loginCadastrarButton.setBounds(297, 332, 168, 45);
		LoginPanel.add(loginCadastrarButton);
		
		
		
		Button loginEntrarButton = new Button("ENTRAR");
		loginEntrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				service.realizarLogin(loginUsuarioTextField.getText(), new String(loginSenhaTextField.getPassword()));
			}
		});
		loginEntrarButton.setForeground(Color.WHITE);
		loginEntrarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		loginEntrarButton.setBackground(new Color(241, 57, 83));
		loginEntrarButton.setBounds(296, 280, 351, 45);
		LoginPanel.add(loginEntrarButton);

		
		
		loginLogoPanel = new JPanel();
		loginLogoPanel.setBackground(Color.BLACK);
		loginLogoPanel.setBounds(0, 0, 228, 563);
		LoginPanel.add(loginLogoPanel);
		loginLogoPanel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("New label");

		try {
			lblNewLabel_3.setIcon(new ImageIcon(ImageIO.read((getClass().getClassLoader().getResourceAsStream("Nidaba.jpg")))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		JLabel label_7 = new JLabel("NIDABA");
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("MS Gothic", Font.BOLD, 50));
		label_7.setBounds(32, 10, 224, 125);
		loginLogoPanel.add(label_7);
		
		JLabel lblEditor = new JLabel("EDITOR");
		lblEditor.setForeground(Color.WHITE);
		lblEditor.setFont(new Font("MS Gothic", Font.BOLD, 47));
		lblEditor.setBounds(32, 67, 224, 125);
		loginLogoPanel.add(lblEditor);

		lblNewLabel_3.setBounds(-37, 146, 265, 463);
		loginLogoPanel.add(lblNewLabel_3);
		
		
		
		/**************************************************************************************/
		/************************************ SIGN UP *****************************************/

		SignUpPanel = new JPanel();
		SignUpPanel.setBackground(SystemColor.menu);
		frame.getContentPane().add(SignUpPanel, "name_75590307492500");
		SignUpPanel.setLayout(null);
		SignUpPanel.setVisible(false);
		
		signUpUsuarioLabel = new JLabel("USUÁRIO");
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
		
		
		Button signUpCadastrarButton = new Button("CADASTRAR");
		signUpCadastrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(validarCadastro())
					service.realizarCadastro(signUpUsuarioTextField.getText(), signUpEmailTextField.getText(), new String(signUpPasswordField.getPassword()));
			}
		});
		signUpCadastrarButton.setForeground(Color.WHITE);
		signUpCadastrarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		signUpCadastrarButton.setBackground(new Color(241, 57, 83));
		signUpCadastrarButton.setBounds(300, 336, 351, 45);
		SignUpPanel.add(signUpCadastrarButton);
				
		Button signUpVoltarButton = new Button("VOLTAR");
		signUpVoltarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpPanel.setVisible(false);
				frame.setTitle("Login");
				LoginPanel.setVisible(true);
			}
		});
		signUpVoltarButton.setForeground(Color.WHITE);
		signUpVoltarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		signUpVoltarButton.setBackground(SystemColor.windowBorder);
		signUpVoltarButton.setBounds(408, 403, 122, 38);
		SignUpPanel.add(signUpVoltarButton);

		

		JPanel signUpLogoPanel = new JPanel();
		signUpLogoPanel.setBackground(Color.BLACK);
		signUpLogoPanel.setBounds(0, 0, 228, 563);
		SignUpPanel.add(signUpLogoPanel);
		signUpLogoPanel.setLayout(null);

		JLabel labelLogoSignUp = new JLabel("New label");
		try {
			labelLogoSignUp.setIcon(new ImageIcon(ImageIO.read((getClass().getClassLoader().getResourceAsStream("Nidaba.jpg")))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		labelLogoSignUp.setBounds(-37, 146, 265, 463);

		JLabel labelTextLogo = new JLabel("NIDABA");
		labelTextLogo.setForeground(Color.WHITE);
		labelTextLogo.setFont(new Font("MS Gothic", Font.BOLD, 50));
		labelTextLogo.setBounds(32, 10, 224, 125);
		
		JLabel lblEditor2 = new JLabel("EDITOR");
		lblEditor2.setForeground(Color.WHITE);
		lblEditor2.setFont(new Font("MS Gothic", Font.BOLD, 47));
		lblEditor2.setBounds(32, 67, 224, 125);
		
		signUpLogoPanel.add(lblEditor2);
	
		signUpLogoPanel.add(labelLogoSignUp);
		signUpLogoPanel.add(labelTextLogo);
		/**************************************************************************************/
		/************************************ EDITOR ******************************************/

		EditorPanel = new JPanel();
		EditorPanel.setBackground(SystemColor.menu);
		frame.getContentPane().add(EditorPanel, "name_87723977858900");
		EditorPanel.setLayout(null);
		

		editorTextPane = new JTextArea();
		editorTextPane.setBounds(10, 11, 511, 541);
		editorTextPane.setLineWrap(true);
		editorTextPane.setWrapStyleWord(true);
		editorTextPane.setEditable(false);
		JScrollPane scrollPane2 = new JScrollPane(editorTextPane);
		scrollPane2.setBounds(10, 11, 511, 544);
		EditorPanel.add(scrollPane2);
		
		editorDescricaoPanel = new JPanel();
		editorDescricaoPanel.setBackground(SystemColor.info);
		editorDescricaoPanel.setBorder(null);
		editorDescricaoPanel.setBounds(531, 11, 179, 111);
		EditorPanel.add(editorDescricaoPanel);
		editorDescricaoPanel.setLayout(null);
		
		JLabel editorDescricaoArquivoLabel = new JLabel("  ARQUIVO ");
		editorDescricaoArquivoLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		editorDescricaoArquivoLabel.setBounds(32, 11, 61, 14);
		editorDescricaoPanel.add(editorDescricaoArquivoLabel);
		
		JLabel editorDescricaoCriadorLabel = new JLabel("  CRIADOR ");
		editorDescricaoCriadorLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		editorDescricaoCriadorLabel.setBounds(32, 36, 61, 14);
		editorDescricaoPanel.add(editorDescricaoCriadorLabel);
		
		JLabel editorDescricaoCriacaoLabel = new JLabel("  CRIAÇÃO");
		editorDescricaoCriacaoLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		editorDescricaoCriacaoLabel.setBounds(32, 61, 61, 14);
		editorDescricaoPanel.add(editorDescricaoCriacaoLabel);
		
		JLabel editorDescricaoModificacaoLabel = new JLabel("MODIFICAÇÃO");
		editorDescricaoModificacaoLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		editorDescricaoModificacaoLabel.setBounds(10, 86, 87, 14);
		editorDescricaoPanel.add(editorDescricaoModificacaoLabel);
		
		editorDescricaoModificacaoLabel2 = new JLabel("");
		editorDescricaoModificacaoLabel2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		editorDescricaoModificacaoLabel2.setBounds(92, 86, 80, 14);
		editorDescricaoPanel.add(editorDescricaoModificacaoLabel2);
		
		editorDescricaoCriacaoLabel2 = new JLabel("");
		editorDescricaoCriacaoLabel2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		editorDescricaoCriacaoLabel2.setBounds(92, 61, 87, 14);
		editorDescricaoPanel.add(editorDescricaoCriacaoLabel2);
		
		editorDescricaoCriadorLabel2 = new JLabel("");
		editorDescricaoCriadorLabel2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		editorDescricaoCriadorLabel2.setBounds(92, 36, 87, 14);
		editorDescricaoPanel.add(editorDescricaoCriadorLabel2);
		
		editorDescricaoArquivoLabel2 = new JLabel("");
		editorDescricaoArquivoLabel2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		editorDescricaoArquivoLabel2.setBounds(92, 11, 87, 14);
		editorDescricaoPanel.add(editorDescricaoArquivoLabel2);
		
		JLabel editorUsuarioEditandoLabel = new JLabel("USUÁRIO EDITANDO");
		editorUsuarioEditandoLabel.setForeground(Color.DARK_GRAY);
		editorUsuarioEditandoLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		editorUsuarioEditandoLabel.setBounds(565, 440, 145, 14);
		EditorPanel.add(editorUsuarioEditandoLabel);
		
		JButton editorSalvarButton = new JButton("SALVAR");
		editorSalvarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				service.enviarUpdate(new UpdateFileRequest(session.getDocumentID(), editorTextPane.getText()));
			}
		});
		editorSalvarButton.setBackground(Color.WHITE);
		editorSalvarButton.setBounds(531, 133, 179, 34);
		EditorPanel.add(editorSalvarButton);

		JButton editorCriarButton = new JButton("SALVAR COMO");
		editorCriarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		          String name = JOptionPane.showInputDialog(EditorPanel,
	                        "Insira o nome do arquivo", null);
		          name = name.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");

		          if(name != null) {
			          if(!(name.contains(".TXT") || name.contains(".txt"))) {
						  name = name.concat(".txt");
					  }
						editorSalvarButton.setEnabled(false);
						editorTextPane.setEditable(false);
						editorEditarButton.setBackground(new Color(46, 139, 87));
						editorEditarButton.setText("EDITAR");
						service.enviarEventoEdicao(false);
						
						if (editorEditarButton.getText().equals("LIBERAR EDIÇÃO")) {
							service.enviarEventoEdicao(false);
						}
						
						service.criarNovoArquivoUpload(editorTextPane.getText(), name, session.getClientUsername());
					}

		          }
		});
		editorCriarButton.setBackground(Color.WHITE);
		editorCriarButton.setBounds(531, 171, 179, 34);
		EditorPanel.add(editorCriarButton);
		
		JButton editorExportarButton = new JButton("EXPORTAR");
		editorExportarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exportarArquivo();
			}
		});
		editorExportarButton.setBackground(SystemColor.window);
		editorExportarButton.setBounds(531, 248, 179, 34);
		EditorPanel.add(editorExportarButton);
		
		JButton editorUploadButton = new JButton("UPLOAD NOVO ARQUIVO");
		editorUploadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final JFileChooser fc = new JFileChooser();
		        int returnValue = fc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					java.io.File selectedFile = fc.getSelectedFile();
					String contextAsString;
					try {
						contextAsString = FileUtils.readFileToString(selectedFile, StandardCharsets.UTF_8);
						if (editorEditarButton.getText().equals("LIBERAR EDIÇÃO")) {
							service.enviarEventoEdicao(false);
						}
						service.criarNovoArquivoUpload(contextAsString, selectedFile.getName(), session.getClientUsername());

					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}

		});
		editorUploadButton.setBackground(SystemColor.window);
		editorUploadButton.setBounds(531, 209, 179, 34);
		EditorPanel.add(editorUploadButton);
		
		
		
		editorCompartilharButton = new JButton("COMPARTILHAR");
		editorCompartilharButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String username = JOptionPane.showInputDialog(EditorPanel,
                        "Insira o usuário com o qual você deseja compartilhar o arquivo", null); 
				service.compartilharArquivo(username, session.getDocumentID());
				
			}
		});
		editorCompartilharButton.setBackground(SystemColor.window);
		editorCompartilharButton.setBounds(531, 287, 179, 34);
		EditorPanel.add(editorCompartilharButton);
		
		
		JButton editorUnshareButton = new JButton("REMOVER ACESSO");
		editorUnshareButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		editorUnshareButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = JOptionPane.showInputDialog(EditorPanel,
                        "Insira o usuário que você deseja remover o acesso", null); 
				service.pararCompartilharArquivo(username, session.getDocumentID());
				
			}
		});
		editorUnshareButton.setBackground(SystemColor.window);
		editorUnshareButton.setBounds(531, 325, 179, 34);
		EditorPanel.add(editorUnshareButton);

		editorExcluirButton = new JButton("EXCLUIR");
		editorExcluirButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (editorDescricaoCriadorLabel2.getText().equals(session.getClientUsername())) {
				     int opcao = JOptionPane.showConfirmDialog(EditorPanel, "Tem certeza que deseja excluir o arquivo?", null, JOptionPane.YES_NO_OPTION);
				     if(opcao == JOptionPane.YES_OPTION) {
				    	 service.removerArquivo(new DeleteRequest(session.getDocumentID()));
				     }
				}
				
			}
		});
		editorExcluirButton.setBackground(Color.WHITE);
		editorExcluirButton.setBounds(531, 365, 179, 34);
		EditorPanel.add(editorExcluirButton);
		
		editorUsuarioEditandoLabel2 = new JLabel("");
		editorUsuarioEditandoLabel2.setOpaque(true);
		editorUsuarioEditandoLabel2.setBackground(SystemColor.info);
		editorUsuarioEditandoLabel2.setBounds(531, 455, 179, 23);
		EditorPanel.add(editorUsuarioEditandoLabel2);

		editorEditarButton = new JButton("EDITAR");
		editorEditarButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		editorEditarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (editorEditarButton.getText().equalsIgnoreCase("EDITAR")) {
					editorSalvarButton.setEnabled(true);
					editorTextPane.setEditable(true);
					editorEditarButton.setBackground(Color.ORANGE);
					editorEditarButton.setText("LIBERAR EDIÇÃO");
					service.enviarEventoEdicao(true);
					editorUsuarioEditandoLabel2.setText(session.getClientUsername());
				} else if(editorEditarButton.getText().equalsIgnoreCase("LIBERAR EDIÇÃO")) {
					editorSalvarButton.setEnabled(false);
					editorTextPane.setEditable(false);
					editorEditarButton.setBackground(new Color(46, 139, 87));
					editorEditarButton.setText("EDITAR");
					service.enviarEventoEdicao(false);
					editorUsuarioEditandoLabel2.setText("");

				} else {	
				     int opcao = JOptionPane.showConfirmDialog(EditorPanel, "Deseja enviar uma solicitação para o usuário ".concat(editorUsuarioEditandoLabel2.getText()).concat(" ?"), null, JOptionPane.YES_NO_OPTION);
				     if(opcao == JOptionPane.YES_OPTION) {
				    	 service.enviarEventoSolicitacaoEdicao(session.getDocumentID(), session.getClientUsername());
				     }
				}
			}


		});
		editorEditarButton.setForeground(Color.WHITE);
		editorEditarButton.setBackground(new Color(46, 139, 87));
		editorEditarButton.setBounds(531, 403, 179, 34);
		EditorPanel.add(editorEditarButton);

		
		JButton editorLogButton = new JButton("LOG");
		editorLogButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setTitle("Modificações - "+editorDescricaoArquivoLabel2.getText());
				LogPanel.setVisible(true);
				EditorPanel.setVisible(false);
			}
		});
		editorLogButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		editorLogButton.setBackground(Color.WHITE);
		editorLogButton.setBounds(530, 480, 180, 27);
		EditorPanel.add(editorLogButton);

		
		
		JButton editorVoltarButton = new JButton("VOLTAR");
		editorVoltarButton.setFont(new Font("Tahoma", Font.BOLD, 9));
		editorVoltarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditorPanel.setVisible(false);
				frame.setTitle("Home");
				HomePanel.setVisible(true);
				if (editorEditarButton.getText().equalsIgnoreCase("LIBERAR EDIÇÃO")) {
					service.enviarEventoEdicao(false);
					editorEditarButton.setText("EDITAR");
					editorEditarButton.setBackground(new Color(46, 139, 87));
					editorUsuarioEditandoLabel2.setText("");
				}
				service.obterListaArquivos(session.getClientUsername());
			}
		});
		editorVoltarButton.setBackground(Color.WHITE);
		editorVoltarButton.setBounds(562, 521, 121, 34);
		EditorPanel.add(editorVoltarButton);

		/**************************************************************************************/
		/************************************ LOG ******************************************/

		LogPanel = new JPanel();
		LogPanel.setBackground(SystemColor.menu);
		frame.getContentPane().add(LogPanel, "name_259201955106400");
		LogPanel.setLayout(null);

		String[][] data = {};
	    String[] columnNames = { "Data", "Reponsável"}; 
	    logTabelaAlteracoes = new JTable(data, columnNames); 
	    logTabelaAlteracoes.setBackground(SystemColor.text);

	    logTabelaAlteracoes.setBounds(405, 72, 270, 409);

	    JScrollPane logTabelaScrollPane = new JScrollPane(logTabelaAlteracoes); 
	    logTabelaScrollPane.setBounds(405, 11, 294, 455);
	    LogPanel.add(logTabelaScrollPane);
        JTableHeader header2 = logTabelaAlteracoes.getTableHeader();
        header2.setBackground(Color.WHITE); 
	    logTabelaScrollPane.getViewport().setBackground(Color.WHITE);

	    
	    JTextArea logTextPane = new JTextArea();
	    logTextPane.setBounds(38, 72, 344, 409);
	    logTextPane.setEditable(false);
	    logTextPane.setLineWrap(true);
	    logTextPane.setWrapStyleWord(true);

	    JScrollPane logTextPaneScrollPane = new JScrollPane(logTextPane); 
	    logTextPaneScrollPane.setBounds(10, 11, 385, 541);
	    LogPanel.add(logTextPaneScrollPane);
		
		JButton logVisualizarTextButton = new JButton("VISUALIZAR TEXTO");
		logVisualizarTextButton.setBackground(SystemColor.window);
		logVisualizarTextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int valor = logTabelaAlteracoes.getSelectedRow();
				String data = (String) logTabelaAlteracoes.getValueAt(valor , 0);
				String autor = (String) logTabelaAlteracoes.getValueAt(valor , 1);
				String conteudo ="";
				for (Modificacao m : modificacoes) {
					if (formatador.format(m.getDataMoficacao()).equals(data) && m.getUsuario().equalsIgnoreCase(autor)) {
						conteudo = m.getSnapShot();
					}
				}
				logTextPane.setText(conteudo);

			}
		});
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	
		    	if (editorEditarButton.getText().equals("LIBERAR EDIÇÃO")) {
		    		
		    		service.enviarEventoEdicao(false);
		    		
		    	}
		  
		    }
		});
		logVisualizarTextButton.setBounds(405, 477, 294, 32);
		LogPanel.add(logVisualizarTextButton);
		
		JButton logVoltarButton = new JButton("VOLTAR");
		logVoltarButton.setBackground(SystemColor.window);
		logVoltarButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				logTextPane.setText("");
				frame.setTitle("Editor - "+editorDescricaoArquivoLabel2.getText());
				EditorPanel.setVisible(true);
				if (!editorDescricaoCriadorLabel2.getText().equals(session.getClientUsername())) {
					editorExcluirButton.setEnabled(false);
				} else {
					editorExcluirButton.setEnabled(true);
				}
				LogPanel.setVisible(false);
			}
		});
		logVoltarButton.setBounds(559, 520, 140, 32);
		LogPanel.add(logVoltarButton);
		
		JButton logExportarButton = new JButton("EXPORTAR");
		logExportarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					
			        JFileChooser fileChooser = new JFileChooser();
		            int r = fileChooser.showSaveDialog(null); 
		            if (r == JFileChooser.APPROVE_OPTION)  {
		            	String path = fileChooser.getSelectedFile().getAbsolutePath();
		            	path = path.contains(".TXT") || path.contains(".txt") ? path : path.concat(".txt");
				    	BufferedWriter writer = new BufferedWriter(new FileWriter(path));
				    	
						writer.write(logTextPane.getText());
						writer.close();
						
						JOptionPane.showMessageDialog(LogPanel, "O arquivo foi exportado!");

		            }


				} catch (IOException e) {
					JOptionPane.showMessageDialog(LogPanel, "O arquivo possuí caracteres inválidos");
					e.printStackTrace();
				}

			}
		});
		logExportarButton.setBackground(SystemColor.window);
		logExportarButton.setBounds(405, 520, 144, 32);
		LogPanel.add(logExportarButton);

		/**************************************************************************************/
		/************************************ HOME ******************************************/
		HomePanel = new JPanel();
		frame.getContentPane().add(HomePanel, "name_148599297405900");
		HomePanel.setLayout(null);
		HomePanel.setBackground(SystemColor.menu);

		JButton homeNovoArquivoButton = new JButton("NOVO ARQUIVO");
		homeNovoArquivoButton.setBackground(SystemColor.window);
		homeNovoArquivoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				apagaCamposEditor();
				session.setDocumentID(null);
				editorSalvarButton.setEnabled(false);
				frame.setTitle("Editor - "+editorDescricaoArquivoLabel2.getText());
				EditorPanel.setVisible(true);
				if (!editorDescricaoCriadorLabel2.getText().equals(session.getClientUsername())) {
					editorExcluirButton.setEnabled(false);
				} else {
					editorExcluirButton.setEnabled(true);
				}
				HomePanel.setVisible(false);
			}
		});
		homeNovoArquivoButton.setBounds(516, 516, 171, 36);
		HomePanel.add(homeNovoArquivoButton);
		
		JButton homeAbrirButton = new JButton("ABRIR");
		homeAbrirButton.setBackground(SystemColor.window);
		homeAbrirButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				editorSalvarButton.setEnabled(false);
				int valor = homeTabelaArquivos.getSelectedRow();
				String name = (String) homeTabelaArquivos.getValueAt(valor , 0);
				String username = (String) homeTabelaArquivos.getValueAt(valor , 1);
				String date = (String) homeTabelaArquivos.getValueAt(valor , 2);
				
				service.obterArquivo(name, username, date, listOfFiles, formatador);
				
			}

		});
		homeAbrirButton.setBounds(321, 516, 171, 36);
		HomePanel.add(homeAbrirButton);
		
		  String[][] data2 = {};
	        String[] columnNames2 = { "Nome", "Data", "Criador"}; 
	         homeTabelaArquivos = new JTable(data2, columnNames2);
	         homeTabelaArquivos.setDefaultEditor(Object.class, null);
	         homeTabelaArquivos.setShowHorizontalLines(false);
	         homeTabelaArquivos.setShowVerticalLines(false);
	         JTableHeader header = homeTabelaArquivos.getTableHeader();
	         header.setBackground(Color.WHITE); 		
	         JScrollPane homeTabelArquivosScrollPane = new JScrollPane(homeTabelaArquivos);
		homeTabelArquivosScrollPane.setBounds(311, 21, 384, 484);
		HomePanel.add(homeTabelArquivosScrollPane);
		homeTabelArquivosScrollPane.getViewport().setBackground(Color.WHITE);

		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(SystemColor.controlHighlight);
		lblNewLabel.setBounds(302, 11, 400, 501);
		lblNewLabel.setOpaque(true);
		HomePanel.add(lblNewLabel);		
		
		JLabel lblNewLabel_1 = new JLabel("•");
		lblNewLabel_1.setForeground(new Color(241, 57, 83));
		lblNewLabel_1.setBounds(143, 177, 18, 74);
		HomePanel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		
		JPanel recadoPanel = new JPanel();
		recadoPanel.setBackground(Color.ORANGE);
		recadoPanel.setBounds(10, 191, 279, 223);
		HomePanel.add(recadoPanel);
		recadoPanel.setLayout(null);
		
		lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));

		lblNewLabel_2.setBounds(10, 38, 259, 174);
		recadoPanel.add(lblNewLabel_2);
		
		/**************************************************************************************/

	}
	
	
	private boolean validarCadastro() {
		
		boolean ehValido = true;
		
		if (signUpUsuarioTextField.getText() == null || signUpUsuarioTextField.getText().equals("")) { 
			signUpUsuarioTextField.setOpaque(true);
			signUpUsuarioTextField.setBackground(Color.RED);
			ehValido = false;
		}  else {
			signUpUsuarioTextField.setOpaque(false);
		}
		
		if (signUpPasswordField.getPassword() == null || new String(signUpPasswordField.getPassword()).equalsIgnoreCase("")) { 
			signUpPasswordField.setOpaque(true);
			signUpPasswordField.setBackground(Color.RED);
			ehValido = false;
		} else {
			signUpPasswordField.setOpaque(false);
		}
		
		if (signUpEmailTextField.getText() == null || signUpEmailTextField.getText().equals("")) {
			signUpEmailTextField.setOpaque(true);
			signUpEmailTextField.setBackground(Color.RED);
			ehValido = false;
		} else {
			signUpEmailTextField.setOpaque(false);
		}
		
		if (!ehValido)
			JOptionPane.showMessageDialog(SignUpPanel, "Por favor, preencha os campos destacados em vermelho!", "Atenção", JOptionPane.ERROR_MESSAGE);
		
		return ehValido;
	}
	
	public void recebeEventos(Event eventoRecebido) {
		
		if (eventoRecebido == null)
			return;
		
		EventType eventType = eventoRecebido.getEventType();
				
		switch (eventType) {
		
		case LOGIN :
			processarLogin(eventoRecebido);
			break;
			
		case NEWFILE:
			processarNewFile(eventoRecebido);
			break;
		
		case HOME:
			processarHome(eventoRecebido);
			break;
			
		case EDITOR:
			processarEditor(eventoRecebido);
			break;
			
		case UPDATEFILE:
			processarUpdate(eventoRecebido);
			break;
		
		case EDITING: 
			processarEditing(eventoRecebido);
			break;
		
		case UNSHARE:
			processarUnshareFile(eventoRecebido);
			break;
		
		case SHARE:
			processarShareFile();
			break;
			
		case REGISTER:
			processarRegistrar(eventoRecebido);
			break;	
			
		case REQUEST_EDITION:
			processarRequestEdition(eventoRecebido);
			break;
			
		case DELETE:
			processarDelete(eventoRecebido);
			break;
			
		default:
			//DO NOTHING
			break;
		}
		
	}
	
	public void senhaIncorreta () {
		
		loginSenhaIncorretaLabel.setOpaque(true);
		loginSenhaIncorretaLabel.setText("USUÁRIO E/OU SENHA INCORRETA!");
		loginSenhaIncorretaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginSenhaIncorretaLabel.setVerticalAlignment(SwingConstants.CENTER);
		loginSenhaIncorretaLabel.setBackground(Color.RED);
		loginSenhaIncorretaLabel.setForeground(Color.WHITE);
		loginSenhaIncorretaLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

	}

	public void processarHome(Event e) { 
		
		lblNewLabel_2.setText(String.format("<html>Bem vindo, %s <br/><br/>"
				+ "Caso você tenha alguma dúvida, por favor, entre em contato com a equipe desenvolvedora.<br/><br/>"
				+ "Leonardo Negri 		<br/>      lcnegri@usp.br<br/>"
				+ "Lucas Tamaribuchi	<br/>      lutamaribuchi@usp.br<br/>"
				+ "Paulo Oliveira		<br/>      paulo.rogerio.oliveira@usp.br</html>", session.getClientUsername()));

		
		HomeResponse homeResponse = (HomeResponse) e.getContent();
		
		listOfFiles = homeResponse.getListOfFiles();
		
		int row = 0;

		Object[][] rows = new Object[homeResponse.getListOfFiles().size()][3];
		Object[]columns = new Object[] {"Nome","Autor", "Data Criação"};

		for (File m : homeResponse.getListOfFiles()) { 
			rows[row][0] = m.getName();
			rows[row][1] = m.getOwner();
			rows[row][2] = formatador.format(m.getCreationDate());
			row++;
		}
		
		homeTabelaArquivos.setModel(new DefaultTableModel(rows,columns));

	}
	
	
	public void processarEditor(Event e) {
		EditorResponse editorResponse = (EditorResponse) e.getContent();
		editorTextPane.setText(editorResponse.getFile().getContent());
		editorDescricaoArquivoLabel2.setText(editorResponse.getFile().getName());
		editorDescricaoCriadorLabel2.setText(editorResponse.getFile().getOwner());
		editorDescricaoCriacaoLabel2.setText(formatador.format(editorResponse.getFile().getCreationDate()));
		
		if (editorResponse.getModificacoes() != null && !editorResponse.getModificacoes().isEmpty()) {
			editorDescricaoModificacaoLabel2.setText(formatador.format(editorResponse.getModificacoes().get(editorResponse.getModificacoes().size() - 1).getDataMoficacao()));
		} else {
			editorDescricaoModificacaoLabel2.setText(formatador.format(editorResponse.getFile().getCreationDate()));

		}
		
		if (editorResponse.getUserdEditing() == null) {
			
			editorEditarButton.setBackground(new Color(46, 139, 87));
			editorEditarButton.setText("EDITAR");
			
		} else {
			
			editorEditarButton.setBackground(Color.RED);
			editorEditarButton.setText("SOLICITAR EDIÇÃO");
			editorUsuarioEditandoLabel2.setText(editorResponse.getUserdEditing());
		}
		frame.setTitle("Editor - "+editorDescricaoArquivoLabel2.getText());
		EditorPanel.setVisible(true);
		if (!editorDescricaoCriadorLabel2.getText().equals(session.getClientUsername())) {
			editorExcluirButton.setEnabled(false);
		} else {
			editorExcluirButton.setEnabled(true);
		}
		HomePanel.setVisible(false);
		frame.setTitle(String.format("Editor - %s", editorResponse.getFile().getName())); 
		session.setDocumentID(editorResponse.getFile().getId());
		
		modificacoes = editorResponse.getModificacoes();
		atualizarLogModificacoes();

	}
	
	public void processarUpdate(Event e) {
		
		EditorResponse er = (EditorResponse) e.getContent();
		editorTextPane.setText(er.getFile().getContent());
		if (er.getModificacoes() != null && !er.getModificacoes().isEmpty()) {
			editorDescricaoModificacaoLabel2.setText(formatador.format(er.getModificacoes().get(er.getModificacoes().size() - 1).getDataMoficacao()));
		}
		modificacoes = er.getModificacoes();
		atualizarLogModificacoes();

	}
	
	
	
	public void atualizarLogModificacoes() {
		
		int linha = 0;

		Object[][] valores = new Object[modificacoes.size()][2];
		Object[]header = new Object[] {"Data","Autor"};

		for (Modificacao m :modificacoes) { 
			valores[linha][0] = formatador.format(m.getDataMoficacao());
			valores[linha][1] = m.getUsuario();
			linha++;
		}
		
		logTabelaAlteracoes.setModel(new DefaultTableModel(valores,header));

	}
	
	public void processarUnshareFile(Event e) { 
		UnshareResponse ur = (UnshareResponse) e.getContent();
		
		if ((EditorPanel.isVisible() && session.getDocumentID().intValue() == ur.getFileID().intValue()) || HomePanel.isVisible()) {
			
			session.setDocumentID(null);
			
			if (EditorPanel.isVisible()) {
				
				JOptionPane.showMessageDialog(EditorPanel, "O seu acesso ao arquivo "+editorDescricaoArquivoLabel2.getText()+" foi revogado");
				frame.setTitle("Home");
				HomePanel.setVisible(true);
				EditorPanel.setVisible(false);
				
				if (editorEditarButton.getText().equals("LIBERAR EDIÇÃO"))
					service.enviarEventoEdicao(false);
				
			}
			
			service.obterListaArquivos(session.getClientUsername());

		}
	}
		
	public void processarShareFile() {
		if ( HomePanel.isVisible() ) {
			service.obterListaArquivos(session.getClientUsername());
		}
	}
	
	
	
	public void processarNewFile(Event e) {
		NewFileResponse newFileResponse = (NewFileResponse) e.getContent();
		editorTextPane.setText(newFileResponse.getFile().getContent());
		editorDescricaoArquivoLabel2.setText(newFileResponse.getFile().getName());
		editorDescricaoCriadorLabel2.setText(newFileResponse.getFile().getOwner());
		editorDescricaoCriacaoLabel2.setText(formatador.format(newFileResponse.getFile().getCreationDate()));
		editorDescricaoModificacaoLabel2.setText(formatador.format(newFileResponse.getFile().getCreationDate()));
		session.setDocumentID(newFileResponse.getFile().getId());
		editorEditarButton.setBackground(new Color(46, 139, 87));
		editorEditarButton.setText("EDITAR");
		editorUsuarioEditandoLabel2.setText("");
		editorTextPane.setEditable(false);
		modificacoes = new LinkedList<>();
		atualizarLogModificacoes();
	}
	
	
	public void processarRegistrar(Event e) {
		SignUpResponse signUpResponse = (SignUpResponse) e.getContent();
		SignUpStatus status = signUpResponse.getSignUpStatus();
		
		switch (status) {
		
		case SUCCESS:
			JOptionPane.showMessageDialog(SignUpPanel, "Cadastro realizado com sucesso!");
			frame.setTitle("Cadastro");
			LoginPanel.setVisible(true);
			SignUpPanel.setVisible(false);
			break;
			
		case USERNAME_EMAIL_REGISTERED:
			JOptionPane.showMessageDialog(SignUpPanel, "Usuário e E-mail já cadastrados\n Clique em Esqueci a Senha na tela de Login ou tente realizar o cadastro com outros dados", "Atenção", JOptionPane.ERROR_MESSAGE);
			break;
			
		case EMAIL_REGISTERED:
			JOptionPane.showMessageDialog(SignUpPanel, "E-mail já cadastrado\n Clique em Esqueci a Senha na tela de Login ou tente realizar o cadastro com outros dados", "Atenção", JOptionPane.ERROR_MESSAGE);
			break;
			
		case USERNAME_REGISTERED:
			JOptionPane.showMessageDialog(SignUpPanel, "O nome de usuário já está sendo utilizado\n Por favor, tente realizar o cadastro com outro nome de usuário.", "Atenção", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
	
	
	public void processarRequestEdition(Event e) {
		RequestEditionResponse rer = (RequestEditionResponse) e.getContent();
		if (EditorPanel.isVisible())
			JOptionPane.showMessageDialog(EditorPanel, String.format("O usuário %s deseja editar o documento", rer.getUsername()));

	}
	
	
	public void processarDelete(Event e) { 
		DeleteResponse deleteResponse = (DeleteResponse) e.getContent();
		if (EditorPanel.isVisible() && session.getDocumentID().intValue() == deleteResponse.getFileID().intValue()) {
			JOptionPane.showMessageDialog(EditorPanel, "O documento foi deletado pelo dono!");
			HomePanel.setVisible(true);
			EditorPanel.setVisible(false);
			service.obterListaArquivos(session.getClientUsername());
		} else if (HomePanel.isVisible()){
			service.obterListaArquivos(session.getClientUsername());
		}
	}
	
	
	public void processarLogin(Event e) {
		
		LoginResponse loginResponse = (LoginResponse) e.getContent();
		
		if (loginResponse.getLoginStatus() == LoginStatus.NOTALLOWED) {
			
			senhaIncorreta();
			
		} else if (loginResponse.getLoginStatus() == LoginStatus.ALLOWED) {
			
			session.setClientUsername(loginUsuarioTextField.getText());
			service.obterListaArquivos(session.getClientUsername());
			frame.setTitle("Home");
			HomePanel.setVisible(true);
			LoginPanel.setVisible(false);
			
		} else if (loginResponse.getLoginStatus() == LoginStatus.ALREADYLOGGED && LoginPanel.isVisible()) {
			
			JOptionPane.showMessageDialog(LoginPanel, "O usuário já está logado no sistema!");

		}
	}
	
	public void processarEditing(Event e) { 
		
		EditingResponse editingResponse = (EditingResponse) e.getContent();
		
		if (editingResponse.isEstaEditando()) {
			
			editorEditarButton.setBackground(Color.RED);
			editorEditarButton.setText("SOLICITAR EDIÇÃO");
			editorUsuarioEditandoLabel2.setText(editingResponse.getUsername());

			editorTextPane.setEditable(false);

		} else {
			
			editorEditarButton.setBackground(new Color(46, 139, 87));
			editorEditarButton.setText("EDITAR");
			editorUsuarioEditandoLabel2.setText("");
			editorTextPane.setEditable(false);

		}
	}

	public void exportarArquivo() {
	    try {
	        JFileChooser fileChooser = new JFileChooser();
            int r = fileChooser.showSaveDialog(null); 
            if (r == JFileChooser.APPROVE_OPTION)  {
            	String path = fileChooser.getSelectedFile().getAbsolutePath();
            	path = path.contains(".TXT") || path.contains(".txt") ? path : path.concat(".txt");
		    	BufferedWriter writer = new BufferedWriter(new FileWriter(path));
		    	
				writer.write(editorTextPane.getText());
				writer.close();
				
				JOptionPane.showMessageDialog(LogPanel, "O arquivo foi exportado!");
            }
		} catch (IOException e) {
			JOptionPane.showMessageDialog(EditorPanel, "O arquivo possuí caracteres inválidos");
			e.printStackTrace();
		}
	}
	

	public void apagaCamposEditor() {
		editorDescricaoArquivoLabel2.setText("");
		editorDescricaoCriacaoLabel2.setText("");
		editorDescricaoCriadorLabel2.setText("");
		editorDescricaoModificacaoLabel2.setText("");
		editorTextPane.setText("");
	}
	
}
