package Interface;

import RMI.Cliente;
import RMI.Servidor;
import java.awt.CardLayout;
import static java.lang.Thread.sleep;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

/**
 * @author raul rodrigues magalhaes
 */
public class JanelaJogo extends javax.swing.JFrame {
    	
    private static final long serialVersionUID = 1L;
    Servidor servidor = null;
    Cliente usuario = null;
    PainelTabuleiro tabuleiro = null;
    int num = 0;
    String chat = "";
    String msgLogServidor = "";
    boolean minhaVez = false;
    CardLayout c1 = null;
    
    public JanelaJogo() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelRaiz = new javax.swing.JPanel();
        painelLogin = new javax.swing.JPanel();
        painelFormularioLogin = new javax.swing.JPanel();
        labelNomeUsuarioLogin = new javax.swing.JLabel();
        campoNomeUsuario = new javax.swing.JTextField();
        botaoJogarLogin = new javax.swing.JButton();
        painelPrincipal = new javax.swing.JPanel();
        painelJogo = new javax.swing.JPanel();
        labelPontosP2 = new javax.swing.JLabel();
        labelPontosP1 = new javax.swing.JLabel();
        painelTabuleiro = new javax.swing.JPanel();
        painelChat = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaTextoChat = new javax.swing.JTextArea();
        campoTextoChat = new javax.swing.JTextField();
        botaoEnviar = new javax.swing.JButton();
        painelServidor = new javax.swing.JPanel();
        painelListaUsuarios = new javax.swing.JPanel();
        labelListaUsuarios = new javax.swing.JLabel();
        scrollLlistaUsuarios = new javax.swing.JScrollPane();
        listaUsuarios = new javax.swing.JList<>();
        painelLogServidor = new javax.swing.JPanel();
        painelScrollLogServidor = new javax.swing.JScrollPane();
        areaTextoLogServidor = new javax.swing.JTextArea();
        labelLogServidor = new javax.swing.JLabel();
        butaoStartServidor = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login - Jogo");
        setResizable(false);

        painelRaiz.setLayout(new java.awt.CardLayout());

        painelLogin.setPreferredSize(new java.awt.Dimension(800, 500));

        labelNomeUsuarioLogin.setText("Nome:");

        campoNomeUsuario.setToolTipText("Digite seu nome");
        campoNomeUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        campoNomeUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNomeUsuarioActionPerformed(evt);
            }
        });

        botaoJogarLogin.setText("Jogar");
        botaoJogarLogin.setToolTipText("Clique para definir o nome e entrar na sala de jogo");
        botaoJogarLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoJogarLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelFormularioLoginLayout = new javax.swing.GroupLayout(painelFormularioLogin);
        painelFormularioLogin.setLayout(painelFormularioLoginLayout);
        painelFormularioLoginLayout.setHorizontalGroup(
            painelFormularioLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFormularioLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNomeUsuarioLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(campoNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(painelFormularioLoginLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(botaoJogarLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelFormularioLoginLayout.setVerticalGroup(
            painelFormularioLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFormularioLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelFormularioLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNomeUsuarioLogin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoJogarLogin)
                .addContainerGap())
        );

        campoNomeUsuario.getAccessibleContext().setAccessibleName("");
        botaoJogarLogin.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout painelLoginLayout = new javax.swing.GroupLayout(painelLogin);
        painelLogin.setLayout(painelLoginLayout);
        painelLoginLayout.setHorizontalGroup(
            painelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelLoginLayout.createSequentialGroup()
                .addContainerGap(283, Short.MAX_VALUE)
                .addComponent(painelFormularioLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(280, 280, 280))
        );
        painelLoginLayout.setVerticalGroup(
            painelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelLoginLayout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(painelFormularioLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(235, Short.MAX_VALUE))
        );

        painelRaiz.add(painelLogin, "telaLogin");
        painelLogin.getAccessibleContext().setAccessibleName("");

        painelPrincipal.setRequestFocusEnabled(false);

        painelJogo.setPreferredSize(new java.awt.Dimension(478, 0));

        labelPontosP2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelPontosP2.setText("Usuario P2:");

        labelPontosP1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelPontosP1.setText("Usuario P1:");

        painelTabuleiro.setPreferredSize(new java.awt.Dimension(402, 402));

        javax.swing.GroupLayout painelTabuleiroLayout = new javax.swing.GroupLayout(painelTabuleiro);
        painelTabuleiro.setLayout(painelTabuleiroLayout);
        painelTabuleiroLayout.setHorizontalGroup(
            painelTabuleiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 402, Short.MAX_VALUE)
        );
        painelTabuleiroLayout.setVerticalGroup(
            painelTabuleiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 402, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout painelJogoLayout = new javax.swing.GroupLayout(painelJogo);
        painelJogo.setLayout(painelJogoLayout);
        painelJogoLayout.setHorizontalGroup(
            painelJogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelJogoLayout.createSequentialGroup()
                .addGroup(painelJogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelJogoLayout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addGroup(painelJogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelPontosP2)
                            .addComponent(labelPontosP1)))
                    .addGroup(painelJogoLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(painelTabuleiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        painelJogoLayout.setVerticalGroup(
            painelJogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelJogoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelPontosP2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(painelTabuleiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(labelPontosP1)
                .addContainerGap())
        );

        areaTextoChat.setColumns(20);
        areaTextoChat.setRows(5);
        areaTextoChat.setEditable (false);
        areaTextoChat.setLineWrap(true);        
        areaTextoChat.setWrapStyleWord(true);      
        jScrollPane1.setViewportView(areaTextoChat);

        campoTextoChat.setToolTipText("Digite para enviar uma mensagem ao seu oponente");

        botaoEnviar.setText("Enviar");
        botaoEnviar.setToolTipText("Clique para enviar ");
        botaoEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelChatLayout = new javax.swing.GroupLayout(painelChat);
        painelChat.setLayout(painelChatLayout);
        painelChatLayout.setHorizontalGroup(
            painelChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelChatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .addGroup(painelChatLayout.createSequentialGroup()
                        .addComponent(campoTextoChat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoEnviar)))
                .addContainerGap())
        );
        painelChatLayout.setVerticalGroup(
            painelChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelChatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoEnviar)
                    .addComponent(campoTextoChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout painelPrincipalLayout = new javax.swing.GroupLayout(painelPrincipal);
        painelPrincipal.setLayout(painelPrincipalLayout);
        painelPrincipalLayout.setHorizontalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelJogo, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(painelChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        painelPrincipalLayout.setVerticalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelJogo, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE))
                .addContainerGap())
        );

        painelRaiz.add(painelPrincipal, "telaPrincipal");

        painelServidor.setPreferredSize(new java.awt.Dimension(800, 500));

        labelListaUsuarios.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelListaUsuarios.setText("Lista de Usuarios");

        listaUsuarios.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {"Nenum usuario Conectado"};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        scrollLlistaUsuarios.setViewportView(listaUsuarios);

        javax.swing.GroupLayout painelListaUsuariosLayout = new javax.swing.GroupLayout(painelListaUsuarios);
        painelListaUsuarios.setLayout(painelListaUsuariosLayout);
        painelListaUsuariosLayout.setHorizontalGroup(
            painelListaUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelListaUsuariosLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(labelListaUsuarios)
                .addContainerGap(85, Short.MAX_VALUE))
            .addGroup(painelListaUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(painelListaUsuariosLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollLlistaUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        painelListaUsuariosLayout.setVerticalGroup(
            painelListaUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelListaUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelListaUsuarios)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(painelListaUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelListaUsuariosLayout.createSequentialGroup()
                    .addContainerGap(69, Short.MAX_VALUE)
                    .addComponent(scrollLlistaUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        areaTextoLogServidor.setColumns(20);
        areaTextoLogServidor.setRows(5);
        areaTextoLogServidor.setEditable (false);      
        areaTextoLogServidor.setLineWrap(true);           
        areaTextoLogServidor.setWrapStyleWord(true);      
        painelScrollLogServidor.setViewportView(areaTextoLogServidor);

        labelLogServidor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelLogServidor.setText("Log Servidor");

        javax.swing.GroupLayout painelLogServidorLayout = new javax.swing.GroupLayout(painelLogServidor);
        painelLogServidor.setLayout(painelLogServidorLayout);
        painelLogServidorLayout.setHorizontalGroup(
            painelLogServidorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelLogServidorLayout.createSequentialGroup()
                .addContainerGap(194, Short.MAX_VALUE)
                .addComponent(labelLogServidor)
                .addGap(176, 176, 176))
            .addGroup(painelLogServidorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(painelLogServidorLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(painelScrollLogServidor, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        painelLogServidorLayout.setVerticalGroup(
            painelLogServidorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelLogServidorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelLogServidor)
                .addContainerGap(413, Short.MAX_VALUE))
            .addGroup(painelLogServidorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelLogServidorLayout.createSequentialGroup()
                    .addContainerGap(67, Short.MAX_VALUE)
                    .addComponent(painelScrollLogServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        butaoStartServidor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        butaoStartServidor.setText("Start");
        butaoStartServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butaoStartServidorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelServidorLayout = new javax.swing.GroupLayout(painelServidor);
        painelServidor.setLayout(painelServidorLayout);
        painelServidorLayout.setHorizontalGroup(
            painelServidorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelServidorLayout.createSequentialGroup()
                .addComponent(painelListaUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(painelServidorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelServidorLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(butaoStartServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(painelLogServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        painelServidorLayout.setVerticalGroup(
            painelServidorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelServidorLayout.createSequentialGroup()
                .addGroup(painelServidorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelLogServidor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelListaUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(butaoStartServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        painelRaiz.add(painelServidor, "telaServidor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelRaiz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelRaiz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoJogarLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoJogarLoginActionPerformed
   	c1 = (CardLayout) painelRaiz.getLayout();
    	new threadAtualizaInfo().start();

        if(campoNomeUsuario.getText().equals("serv")){
            c1.show(painelRaiz, "telaServidor");

            msgLogServidor = "O servidor ainda esta OFFLINE. Clique em \"Start\" (A lista de Usuarios ainda n�o foi implementada). Logo em seguida execute o programa novamente e entre com o nome do usuario para instanciar um cliente. Apartida iniciara quando 2 clientes tiverem sido criados e o primeiro a entrar comeca o jogo...\n";
            areaTextoLogServidor.setText(msgLogServidor);
            
        } else if(!campoNomeUsuario.getText().equals("")) {            
            String nomeUsuario = campoNomeUsuario.getText();
            c1.show(painelRaiz, "telaPrincipal");
                try {
                    usuario = new Cliente(nomeUsuario);
                    tabuleiro = new PainelTabuleiro(usuario);
                    
                    painelTabuleiro.add(tabuleiro);
                    
                    if(usuario.getTipoDePlayer().equals("p1")){
                        labelPontosP1.setText("Player 1: " + usuario.getNomeCliente() + "          Pontos: " + usuario.getPontos() );
                        labelPontosP2.setText("Player 2: -          Pontos: -");
                    }else if (usuario.getTipoDePlayer().equals("p2")) {
                         labelPontosP1.setText("Player 1: -          Pontos: -");
                    	 labelPontosP2.setText("Player 2: " + usuario.getNomeCliente() + "          Pontos: " + usuario.getPontos() );
					}
                    
                        if(usuario.isConectado()) { 
                            setTitle("Surakarta - " + nomeUsuario + " - Online");
                            if(usuario.getTipoDePlayer().equals("p1")) {
                                    JOptionPane.showMessageDialog(painelPrincipal, "Bem vindo " + campoNomeUsuario.getText() + ", \n\nAguardando um oponente para jogar com voce!\n\nPlay 1 come�a jogando!", "Voce esta conectado", INFORMATION_MESSAGE);
                            }else if (usuario.getTipoDePlayer().equals("p2")) {
                                    JOptionPane.showMessageDialog(painelPrincipal, "Bem vindo " + campoNomeUsuario.getText() + ", \n\nSeu oponente ja esta aguardando!\n\nPlay 1 come�a jogando!", "Voce esta conectado", INFORMATION_MESSAGE);
                            }
                        }else {
                            setTitle("Surakarta - " + nomeUsuario + " - Servidor OFFLINE");
                            JOptionPane.showMessageDialog(painelPrincipal, "O servidor OFFLINE \n\n E necessario fechar a pagina e voltar quando o servidor estiver startado!", "Conexao com servidor",ERROR_MESSAGE );
                        }
    		} catch (RemoteException e) { e.printStackTrace();}
            
        }
		
        
        campoTextoChat.requestFocus();
    }//GEN-LAST:event_botaoJogarLoginActionPerformed

    private void campoNomeUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNomeUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNomeUsuarioActionPerformed

    private void botaoEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEnviarActionPerformed
	  try {
	    	if (!campoTextoChat.getText().equals("") && usuario.getOponente() != null) {
	            usuario.enviaMsg(campoTextoChat.getText());
	            chat += campoTextoChat.getText() + "\n";
	            areaTextoChat.setText(chat);
	            campoTextoChat.setText("");
	        }			
	  } catch (RemoteException e) {e.printStackTrace();}
        campoTextoChat.requestFocus();
    }//GEN-LAST:event_botaoEnviarActionPerformed

    private void butaoStartServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butaoStartServidorActionPerformed
        
    	if(servidor == null){
            try {
                servidor = new Servidor();
                msgLogServidor = "";
                areaTextoLogServidor.setText(servidor.getMsgServidor());

                if(servidor.isConectado()){
                    butaoStartServidor.setText("Startado");
                    setTitle("Surakarta - Painel de Administrador - Servidor ONLINE");
                }
            } catch (Exception e) {
                try {
                    areaTextoLogServidor.setText(servidor.getMsgServidor());
                } catch (RemoteException e1) {e1.printStackTrace(); }
            }
    	} 
        
        
    }//GEN-LAST:event_butaoStartServidorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaTextoChat;
    private javax.swing.JTextArea areaTextoLogServidor;
    private javax.swing.JButton botaoEnviar;
    private javax.swing.JButton botaoJogarLogin;
    private javax.swing.JButton butaoStartServidor;
    private javax.swing.JTextField campoNomeUsuario;
    private javax.swing.JTextField campoTextoChat;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelListaUsuarios;
    private javax.swing.JLabel labelLogServidor;
    private javax.swing.JLabel labelNomeUsuarioLogin;
    private javax.swing.JLabel labelPontosP1;
    private javax.swing.JLabel labelPontosP2;
    private javax.swing.JList<String> listaUsuarios;
    private javax.swing.JPanel painelChat;
    private javax.swing.JPanel painelFormularioLogin;
    private javax.swing.JPanel painelJogo;
    private javax.swing.JPanel painelListaUsuarios;
    private javax.swing.JPanel painelLogServidor;
    private javax.swing.JPanel painelLogin;
    private javax.swing.JPanel painelPrincipal;
    private javax.swing.JPanel painelRaiz;
    private javax.swing.JScrollPane painelScrollLogServidor;
    private javax.swing.JPanel painelServidor;
    private javax.swing.JPanel painelTabuleiro;
    private javax.swing.JScrollPane scrollLlistaUsuarios;
    // End of variables declaration//GEN-END:variables

    public class threadAtualizaInfo extends Thread {
        public void run() {
        	String nomeP1 = "", nomeP2 = "";
        	int pontosP1 = 0, pontosP2 = 0;
        	
            while(true) {
                try {
                    sleep(500);
                    
                    //loop para cliente
                    if(usuario != null) {
                        if(usuario.getOponente() != null ) {
                            while(true) {
                                sleep(300);
                                
                                //chat
                                if(!usuario.getMsgRecebida().equals("")) {
                                    chat += usuario.getMsgRecebida() + "\n";
                                    areaTextoChat.setText(chat);
                                    usuario.recebeMsg("");
                                }
                                
                                //atualiza o controle de turno
                                minhaVez = usuario.isMinhaVez();
                                
                                //atualiza pontuacao
                                if(usuario.getTipoDePlayer().equals("p1")) {
                                	 nomeP1 = usuario.getNomeCliente();
                                     pontosP1 = usuario.getPontos();
                                     
                                     nomeP2 = usuario.getOponente().getNomeCliente();
                                     pontosP2 = usuario.getOponente().getPontos();
                                     
                                }else  if(usuario.getTipoDePlayer().equals("p2")){
	                               	 nomeP2 = usuario.getNomeCliente();
	                                 pontosP2 = usuario.getPontos();
	                                 
	                                 nomeP1 = usuario.getOponente().getNomeCliente();
	                                 pontosP1 = usuario.getOponente().getPontos();
                                }
                                
                                labelPontosP1.setText("Player 1: " + nomeP1 + "          Pontos: " + pontosP1);
                                labelPontosP2.setText("Player 2: " + nomeP2 + "          Pontos: " + pontosP2);
                                
                                if(pontosP1 == 12) {
                                	labelPontosP1.setText(nomeP1 + ": VOCE VENCEU!!!");
                                    labelPontosP2.setText(nomeP2 + ": VOCE PERDEU.");
                                }else  if(pontosP2 == 12){
                                	labelPontosP1.setText(nomeP1 + ": VOCE PERDEU.");
                                	labelPontosP2.setText(nomeP2 + ": VOCE VENCEU!!!");
                                }
                            }
                        }
                        
                    }
                    //loop para servidor
                    if (servidor != null) {
                        while (true) {
                            sleep(300);
                            msgLogServidor = servidor.getMsgServidor();
                            areaTextoLogServidor.setText(msgLogServidor);
                        }
                    }
                } catch (RemoteException | InterruptedException e) {e.printStackTrace();}
            }
        }
    }
    
}
