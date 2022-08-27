/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ClientMonitoring;

import Configs.ActionName;
import Configs.FileStructure;
import Configs.ServerAction;
import Configs.ShippingData;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bin
 */
public class ClientScreen extends javax.swing.JFrame implements Runnable {
    private String IP;
    private ServerScreen server;
    private Socket clientSocket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Thread threadReceive;
    private FileStructure fileStructure;
    
    /**
     * Creates new form ClientScreen
     */
    public ClientScreen() {
        initComponents();
        bootstrap();
    }
    
    public void bootstrap() {
        try {
            // Init Client IP
            this.IP = InetAddress.getLocalHost().getHostAddress();
            ClientIPLabel.setText(ClientIPLabel.getText() + " " + this.IP);
            
            // Create Server Instance
            this.server = new ServerScreen(); 
            
            // Create File structure
            fileStructure = new FileStructure(new File(System.getProperty("user.home")));
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ClientIPLabel = new javax.swing.JLabel();
        ClientInformationTitle = new javax.swing.JLabel();
        ClientPortLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        ServerIPText = new javax.swing.JLabel();
        ServerIPTextInput = new javax.swing.JTextField();
        ServerIPText1 = new javax.swing.JLabel();
        ServerPortTextInput = new javax.swing.JTextField();
        ConnectServerButton = new javax.swing.JButton();
        ConnectServerStatusText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ClientIPLabel.setText("Your IP:");

        ClientInformationTitle.setText("Client Information");

        jLabel1.setText("Connect to Server");

        ServerIPText.setText("IP");

        ServerIPTextInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ServerIPTextInputActionPerformed(evt);
            }
        });

        ServerIPText1.setText("Port");

        ServerPortTextInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ServerPortTextInputActionPerformed(evt);
            }
        });

        ConnectServerButton.setText("Connect");
        ConnectServerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConnectServerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ClientPortLabel)
                            .addComponent(ClientInformationTitle)
                            .addComponent(ClientIPLabel)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(239, 239, 239)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ServerIPText)
                            .addComponent(ServerIPTextInput)
                            .addComponent(ServerIPText1)
                            .addComponent(ServerPortTextInput)
                            .addComponent(ConnectServerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(ConnectServerStatusText)))
                .addContainerGap(205, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(ClientInformationTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ClientIPLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ClientPortLabel)
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ConnectServerStatusText)
                .addGap(3, 3, 3)
                .addComponent(ServerIPText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ServerIPTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ServerIPText1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ServerPortTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ConnectServerButton)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ServerPortTextInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ServerPortTextInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ServerPortTextInputActionPerformed

    private void ServerIPTextInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ServerIPTextInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ServerIPTextInputActionPerformed

    private void ConnectServerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConnectServerButtonActionPerformed
        String serverIPInput = ServerIPTextInput.getText().trim();
        String serverPortInput = ServerPortTextInput.getText().trim();
        int parsedPortInput = Integer.parseInt(serverPortInput);
        boolean isCorrectServerConfigs =
                serverIPInput.equals(server.getIP()) && parsedPortInput == server.getPort();
        
        if(isCorrectServerConfigs) {
            try {
                this.connectToServer(this.IP, parsedPortInput);
                this.receiveConnectStatusFromServer();
            } catch (IOException ex) {
                Logger.getLogger(ClientScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_ConnectServerButtonActionPerformed

    private void connectToServer(String IP, int port) {
        try {
            this.clientSocket = new Socket(IP, port);
            
            ShippingData data = new ShippingData(IP, LocalDateTime.now(), System.getProperty("user.dir"), this.fileStructure);
            
            this.sendConnectionDataToServer(data);
            
            
        } catch (IOException ex) {
            Logger.getLogger(ClientScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void receiveConnectStatusFromServer() throws IOException {
        if (ois == null) {
            ois = new ObjectInputStream(this.clientSocket.getInputStream());
	}
        
        this.threadReceive = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        ServerAction serverAction = (ServerAction) ois.readObject();
                        String command = serverAction.getAction();
                        
                        switch(command) {
                            case ActionName.ServerAccepted: {
                                ConnectServerStatusText.setText("You have connected to Server");
                                break;
                            }
                            case ActionName.ServerStopped: {
                                ConnectServerStatusText.setText("This server has been stopped");
                                break;
                            }
                        }
                       
                    } catch (IOException ex) {
                        Logger.getLogger(ClientScreen.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ClientScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        this.threadReceive.start();
    }
    
    private void sendConnectionDataToServer(ShippingData data) throws IOException {
		
        //write to socket using ObjectOutputStream
        if (oos == null) {
            oos = new ObjectOutputStream(this.clientSocket.getOutputStream());
        }
		
        oos.writeObject(data);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ClientIPLabel;
    private javax.swing.JLabel ClientInformationTitle;
    private javax.swing.JLabel ClientPortLabel;
    private javax.swing.JButton ConnectServerButton;
    private javax.swing.JLabel ConnectServerStatusText;
    private javax.swing.JLabel ServerIPText;
    private javax.swing.JLabel ServerIPText1;
    private javax.swing.JTextField ServerIPTextInput;
    private javax.swing.JTextField ServerPortTextInput;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
