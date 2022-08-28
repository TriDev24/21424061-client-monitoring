/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ClientMonitoring;

import Configs.ClientHandler;
import Configs.ActionName;
import Configs.FileStructure;
import Configs.IHandler;
import Configs.ServerRequestPackage;
import Configs.ShippingData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author bin
 */
public class ServerScreen extends javax.swing.JFrame {

    public ServerSocket serverSocket;
    private ArrayList<String> clientIPsConnected;
    private String IP;
    private int port;
    private Thread threadConnector = null;
    private HashMap<String, ClientHandler> clientInformation;
    private DefaultTableModel tableModel;
    private DefaultTableModel notificationTableModel;
    private JTree fileStructureTree;
    private HashMap<String, FileStructure> clientFileStructureContainer;
    private JFrame folderChooserFrame;
    private JButton changeDirectoryButton;
    private TableRowSorter<TableModel> tableSorter;
    private Socket socket;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    private int STT = 1;
    private int notificationCounter = 1;
    private JPopupMenu alertPopup;

    /**
     * Creates new form ServerScreen
     */
    public ServerScreen() {

        initComponents();
        this.bootstrap();
    }

    private void bootstrap() {
        try {
            // Start
            this.IP = InetAddress.getLocalHost().getHostAddress();
            this.port = 1234;
            // Set Label
            ServerIPLabel.setText(ServerIPLabel.getText() + " " + this.IP);
            ServerPortLabel.setText(ServerPortLabel.getText() + " " + this.port);
            // Init
            clientInformation = new HashMap<String, ClientHandler>();
            clientFileStructureContainer = new HashMap<String, FileStructure>();
            folderChooserFrame = new JFrame("Select your folder");
            changeDirectoryButton = new JButton("Change Directory");
            changeDirectoryButton.setBounds(638, 339, 78, 29);
            folderChooserFrame.getContentPane().add(changeDirectoryButton);

            // Table
            String[] header = new String[]{"STT", "Client IP", "Action", "Default Directory", "Create At"};
            this.tableModel = new DefaultTableModel();
            this.tableModel.setColumnIdentifiers(header);
            this.tableSorter = new TableRowSorter<TableModel>(this.tableModel);
            ClientConnectedListTable.setModel(tableModel);
            ClientConnectedListTable.setRowSorter(this.tableSorter);
            
            String[] notificationTableHeader = new String[]{"STT", "Client IP", "Time", "Action", "Description"};
            this.notificationTableModel = new DefaultTableModel();
            this.notificationTableModel.setColumnIdentifiers(notificationTableHeader);
            ClientDirectoryNotificationTable.setModel(notificationTableModel);
            
            // Event handlers
            eventHandlers();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServerScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eventHandlers() {
        BrowseFileClientChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedClient
                        = ClientConnectedListTable.getValueAt(
                                ClientConnectedListTable.getSelectedRow(), 1
                        ).toString();

                showFileStructure(selectedClient);
            }
        });

        changeDirectoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean hasSelectedItem = fileStructureTree != null && !fileStructureTree.isSelectionEmpty();
                if (hasSelectedItem) {
                    String path = fileStructureTree.getSelectionPath().getLastPathComponent().toString();

                    String clientIP = ClientConnectedListTable.getValueAt(ClientConnectedListTable.getSelectedRow(), 1).toString();
                    clientInformation.get(clientIP).changeFolderPath(path);
                    folderChooserFrame.setVisible(false);
                    
                    System.out.println("selected row: " + ClientConnectedListTable.getSelectedRow());
                    System.out.println("selected column: " + ClientConnectedListTable.getSelectedColumn());
                    
                    tableModel.setValueAt(path, 
                            ClientConnectedListTable.getSelectedRow(),
                                ClientConnectedListTable.getSelectedColumn());
                    
                }
            }
        });
    }

    public String getIP() {
        return this.IP;
    }

    public int getPort() {
        return this.port;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        SearchButton = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        FileStructureTree = new javax.swing.JScrollPane();
        jTree3 = new javax.swing.JTree();
        ClearAllNotificationButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ServerIPLabel = new javax.swing.JLabel();
        ServerPortLabel = new javax.swing.JLabel();
        StartServerButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ClientDirectoryNotificationTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        ClientConnectedListTable = new javax.swing.JTable();
        RemoveClientButton1 = new javax.swing.JButton();
        StopServerButton = new javax.swing.JButton();
        ServerConnectionStatusLabel = new javax.swing.JLabel();
        ClientIPSearchInputField = new javax.swing.JTextField();
        SearchClientIPButton = new javax.swing.JButton();
        BrowseFileClientChangeButton = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        SearchButton.setText("Search");

        jScrollPane6.setEnabled(false);
        jScrollPane6.setViewportView(jTree1);

        FileStructureTree.setViewportView(jTree3);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ClearAllNotificationButton.setText("Clear All");
        ClearAllNotificationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearAllNotificationButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Server Information:");

        ServerIPLabel.setText("IP:");

        ServerPortLabel.setText("Port:");

        StartServerButton.setText("Start Server");
        StartServerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartServerButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Client Connected List");

        ClientDirectoryNotificationTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Client IP", "Time", "Action", "Description"
            }
        ));
        jScrollPane4.setViewportView(ClientDirectoryNotificationTable);

        jLabel3.setText("Client Directory Notifications");

        ClientConnectedListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Client IP", "Action", "Default Directory", "CreatedAt"
            }
        ));
        jScrollPane5.setViewportView(ClientConnectedListTable);

        RemoveClientButton1.setText("Remove");

        StopServerButton.setText("Stop Server");
        StopServerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StopServerButtonActionPerformed(evt);
            }
        });

        ServerConnectionStatusLabel.setText("Server is offline");

        SearchClientIPButton.setText("Search");
        SearchClientIPButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchClientIPButtonActionPerformed(evt);
            }
        });

        BrowseFileClientChangeButton.setText("Browse");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ServerPortLabel)
                                    .addComponent(ServerIPLabel)
                                    .addComponent(jLabel1)
                                    .addComponent(ServerConnectionStatusLabel)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(211, 211, 211)
                                .addComponent(jLabel2)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(StartServerButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(StopServerButton)
                                .addGap(98, 98, 98)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(179, 179, 179)
                                        .addComponent(ClearAllNotificationButton))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(ClientIPSearchInputField, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(SearchClientIPButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BrowseFileClientChangeButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(RemoveClientButton1)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ServerIPLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ServerPortLabel)
                .addGap(12, 12, 12)
                .addComponent(ServerConnectionStatusLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StartServerButton)
                    .addComponent(StopServerButton))
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RemoveClientButton1)
                    .addComponent(ClientIPSearchInputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BrowseFileClientChangeButton)
                    .addComponent(SearchClientIPButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(ClearAllNotificationButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StartServerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartServerButtonActionPerformed
        try {
            serverSocket = new ServerSocket(port);

            Runnable socketRunnable = new Runnable() {
                @Override
                public void run() {
                    //create the socket server object
                    try {
                        while (true) {
                            socket = serverSocket.accept();

                            oos = new ObjectOutputStream(socket.getOutputStream());
                            ois = new ObjectInputStream(socket.getInputStream());

                            ShippingData data = (ShippingData) ois.readObject();
                            FileStructure fileStructure = data.getFileStructure();
                            LocalDateTime createdAt = data.getCreatedAt();
                            String clientIP = data.getClientIP();
                            String defaultDirectory = data.getDefaultDirectory();

                            tableModel.addRow(new Object[]{STT, clientIP, ActionName.Register, defaultDirectory, createdAt});
                            updateTableModel(ClientConnectedListTable, tableModel);

                            clientFileStructureContainer.put(clientIP, fileStructure);
                            oos.writeObject(new ServerRequestPackage(ActionName.ServerAccepted, defaultDirectory));
                            openCommunication(clientIP, socket, ois, oos);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServerScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };

            this.threadConnector = new Thread(socketRunnable);
            this.threadConnector.start();

            ServerConnectionStatusLabel.setText("Server is on running...");
            StartServerButton.setEnabled(false);
            StopServerButton.setEnabled(true);
        } catch (IOException ex) {
            Logger.getLogger(ServerScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_StartServerButtonActionPerformed

    private void showFileStructure(String clientIP) {
        if(clientIP == null) {
            JOptionPane.showMessageDialog(null, "You have not select any client");
        }
        
        fileStructureTree = new JTree(clientFileStructureContainer.get(clientIP));

        JScrollPane scrollPopup = new JScrollPane(fileStructureTree);
        folderChooserFrame.getContentPane().add(scrollPopup);
        folderChooserFrame.setSize(700, 900);
        folderChooserFrame.setVisible(true);
    }

    private void updateTableModel(JTable table, DefaultTableModel newModel) {
        newModel.fireTableDataChanged();
        table.setModel(newModel);
    }

    private void ClearAllNotificationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearAllNotificationButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ClearAllNotificationButtonActionPerformed

    private void StopServerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StopServerButtonActionPerformed
        try {
            this.closeConnect();

            this.tableModel.setRowCount(0);
            clientInformation.clear();

            oos.writeObject(new ServerRequestPackage(ActionName.ServerStopped, null));

            ServerConnectionStatusLabel.setText("Server has stopped...");
            StartServerButton.setEnabled(true);
            StopServerButton.setEnabled(false);
        } catch (IOException ex) {
            Logger.getLogger(ServerScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_StopServerButtonActionPerformed

    private void SearchClientIPButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchClientIPButtonActionPerformed
        String searchValue = ClientIPSearchInputField.getText();

        if (searchValue.length() == 0) {
            this.tableSorter.setRowFilter(null);
        } else {
            try {
                this.tableSorter.setRowFilter(RowFilter.regexFilter(searchValue, 1));
            } catch (PatternSyntaxException pse) {
                System.out.println("Bad regex pattern");
            }
        }
    }//GEN-LAST:event_SearchClientIPButtonActionPerformed

    private void closeConnect() throws IOException {
        this.threadConnector.interrupt();
        serverSocket.close();
    }

    private void openCommunication(String clientIP, Socket socket, ObjectInputStream ois, ObjectOutputStream oos) {
        ClientHandler clientHandler = new ClientHandler(oos, ois, socket, clientIP, new IHandler() {
            @Override
            public void handleProcess(ShippingData data) {
                writeToNotificationModel(data);
            }
        });
        
        clientInformation.put(clientIP, clientHandler);
        clientHandler.start();
    }
    
    private void writeToNotificationModel(ShippingData data) {
        String action = data.getAction();
        String clientIP = data.getClientIP();
        String description = data.getDescription();
        
        this.notificationTableModel.addRow(new Object[] {this.notificationCounter++, clientIP, LocalDateTime.now(), action, description});
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
            java.util.logging.Logger.getLogger(ServerScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BrowseFileClientChangeButton;
    private javax.swing.JButton ClearAllNotificationButton;
    private javax.swing.JTable ClientConnectedListTable;
    private javax.swing.JTable ClientDirectoryNotificationTable;
    private javax.swing.JTextField ClientIPSearchInputField;
    private javax.swing.JScrollPane FileStructureTree;
    private javax.swing.JButton RemoveClientButton1;
    private javax.swing.JButton SearchButton;
    private javax.swing.JButton SearchClientIPButton;
    private javax.swing.JLabel ServerConnectionStatusLabel;
    private javax.swing.JLabel ServerIPLabel;
    private javax.swing.JLabel ServerPortLabel;
    private javax.swing.JButton StartServerButton;
    private javax.swing.JButton StopServerButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTree jTree1;
    private javax.swing.JTree jTree3;
    // End of variables declaration//GEN-END:variables
}
