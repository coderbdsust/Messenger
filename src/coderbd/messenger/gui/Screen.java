/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coderbd.messenger.gui;

import coderbd.messenger.utils.StringUtils;
import coderbd.messenger.client.ClientHandler;
import coderbd.messenger.server.Server;
import com.sun.glass.events.KeyEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Biswajit
 */
public class Screen extends javax.swing.JFrame {

    private String MESSENGERSTATE = "";
    private String username = "";
    private int portId = -1;
    private String host = "";
    private String message = "";
    private DataInputStream dataInput;
    private DataOutputStream dataOutput;
    private Server Server;
    Thread serverThread;
    Thread clientThread;

    /**
     * Creates new form Screen
     */
    public Screen() {
        initComponents();
        ChatBox.chatBox = this.chatBox;
        ChatBox.chatField = this.chatField;
        ChatBox.chatPermission(false);
        changePanel(choicePanel, serverInfoPanel);
        changePanel(mainPanel, accountInfoPanel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void getUserInfo() {

        MESSENGERSTATE = comboBox.getSelectedItem().toString();

        if (MESSENGERSTATE.equals("SERVER")) {
            username = StringUtils.removeSpace(serverUsernameField.getText().toUpperCase());
            if (!serverPortField.getText().isEmpty()) {
                portId = Integer.parseInt(serverPortField.getText());
            }

            System.out.println("Username: " + username + " Port-ID: " + portId);
        } else {
            username = StringUtils.removeSpace(clientUsernameField.getText().toUpperCase());
            if (!clientPortField.getText().isEmpty()) {
                portId = Integer.parseInt(clientPortField.getText());
            }

            if (!clientIpAddressField.getText().isEmpty()) {
                host = clientIpAddressField.getText();
            }
            System.out.println("Username: " + username + " Port-ID: " + portId + " IP Address: " + host);
        }
    }

    private void resetField() {
        serverUsernameField.setText("");
        serverPortField.setText("");
        clientUsernameField.setText("");
        clientPortField.setText("");
        clientIpAddressField.setText("");
        chatBox.setText("");
    }

    private void accountLogin() {
        getUserInfo();
        if (MESSENGERSTATE.equals("SERVER")) {

            if (StringUtils.isValid(username, portId)) {

                System.out.println("MESSENGER STATE: " + MESSENGERSTATE);
                changePanel(mainPanel, chatPanel);
                serverThread = new Thread() {
                    public void run() {
                        try {
                            new Server(portId, username + " [ADMIN] :: ", chatField);
                        } catch (IOException ex) {
                            Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
                serverThread.start();
            } else {
                JOptionPane.showMessageDialog(null, "Please, Give Correct Server Input !", "ERROR", JOptionPane.ERROR_MESSAGE);
                resetField();
            }
        }

        if (MESSENGERSTATE.equals("CLIENT")) {

            if (StringUtils.isValid(username, portId, host)) {
                System.out.println("MESSENGER STATE: " + MESSENGERSTATE);
                changePanel(mainPanel, chatPanel);
                clientThread = new Thread() {
                    public void run() {

//                        new Client(host, portId, username + " :: ", chatField, attachFileLabel);
                        new ClientHandler(host, portId, username + " :: ", chatField, attachFileLabel);
                    }
                };
                clientThread.start();;
            } else {
                JOptionPane.showMessageDialog(null, "Please, Give Correct Client Input !", "ERROR", JOptionPane.ERROR_MESSAGE);
                resetField();
            }
        }
    }

    private void debugLog(String log, Exception error) {
        System.out.println(log + " " + error);
    }

    private void debugPane(String errorMessage, Exception error) {
        JOptionPane.showMessageDialog(null, errorMessage + error);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        accountInfoPanel = new javax.swing.JPanel();
        comboBox = new javax.swing.JComboBox();
        choicePanel = new javax.swing.JPanel();
        serverInfoPanel = new javax.swing.JPanel();
        serverUsernameField = new javax.swing.JTextField();
        serverPortField = new javax.swing.JTextField();
        usernameServerLabel = new javax.swing.JLabel();
        portserverLabel = new javax.swing.JLabel();
        clientInfoPanel = new javax.swing.JPanel();
        clientUsernameField = new javax.swing.JTextField();
        usernameCleintLabel = new javax.swing.JLabel();
        clientPortField = new javax.swing.JTextField();
        clientIpAddressField = new javax.swing.JTextField();
        portClientLabel = new javax.swing.JLabel();
        ipAdressLabel = new javax.swing.JLabel();
        createButton = new javax.swing.JButton();
        chatPanel = new javax.swing.JPanel();
        messageContainerPanel = new javax.swing.JPanel();
        messagePanel = new javax.swing.JPanel();
        chatTextLabel = new javax.swing.JLabel();
        chatField = new javax.swing.JTextField();
        attachFileLabel = new javax.swing.JLabel();
        messageViewPanel = new javax.swing.JPanel();
        chatViewScrollPane = new javax.swing.JScrollPane();
        chatBox = new javax.swing.JTextArea();
        messengerMenuBar = new javax.swing.JMenuBar();
        messengerMenu = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        myIPMenuItem = new javax.swing.JMenuItem();
        chatProcessMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(400, 600));

        mainPanel.setBackground(new java.awt.Color(255, 102, 102));
        mainPanel.setMaximumSize(new java.awt.Dimension(388, 600));
        mainPanel.setMinimumSize(new java.awt.Dimension(0, 0));
        mainPanel.setPreferredSize(new java.awt.Dimension(388, 550));
        mainPanel.setLayout(new java.awt.CardLayout());

        accountInfoPanel.setBackground(new java.awt.Color(255, 255, 255));
        accountInfoPanel.setMaximumSize(new java.awt.Dimension(388, 500));
        accountInfoPanel.setPreferredSize(new java.awt.Dimension(388, 500));

        comboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SERVER", "CLIENT" }));
        comboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxActionPerformed(evt);
            }
        });

        choicePanel.setMaximumSize(new java.awt.Dimension(388, 600));
        choicePanel.setPreferredSize(new java.awt.Dimension(300, 370));
        choicePanel.setLayout(new java.awt.CardLayout());

        serverInfoPanel.setBackground(new java.awt.Color(255, 255, 255));
        serverInfoPanel.setPreferredSize(new java.awt.Dimension(300, 370));

        serverPortField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                serverPortFieldKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                serverPortFieldKeyTyped(evt);
            }
        });

        usernameServerLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        usernameServerLabel.setText("Your Name");

        portserverLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        portserverLabel.setText("Port-ID");

        javax.swing.GroupLayout serverInfoPanelLayout = new javax.swing.GroupLayout(serverInfoPanel);
        serverInfoPanel.setLayout(serverInfoPanelLayout);
        serverInfoPanelLayout.setHorizontalGroup(
            serverInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(serverInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(serverInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(portserverLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(usernameServerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addGroup(serverInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(serverPortField)
                    .addComponent(serverUsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        serverInfoPanelLayout.setVerticalGroup(
            serverInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(serverInfoPanelLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(serverInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverUsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameServerLabel))
                .addGap(18, 18, 18)
                .addGroup(serverInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverPortField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portserverLabel))
                .addContainerGap(238, Short.MAX_VALUE))
        );

        choicePanel.add(serverInfoPanel, "card2");

        clientInfoPanel.setBackground(new java.awt.Color(255, 255, 255));
        clientInfoPanel.setPreferredSize(new java.awt.Dimension(300, 370));

        usernameCleintLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        usernameCleintLabel.setText("Your Name");

        clientPortField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                clientPortFieldKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                clientPortFieldKeyTyped(evt);
            }
        });

        clientIpAddressField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                clientIpAddressFieldKeyTyped(evt);
            }
        });

        portClientLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        portClientLabel.setText("Port-ID");

        ipAdressLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ipAdressLabel.setText("IP Address");

        javax.swing.GroupLayout clientInfoPanelLayout = new javax.swing.GroupLayout(clientInfoPanel);
        clientInfoPanel.setLayout(clientInfoPanelLayout);
        clientInfoPanelLayout.setHorizontalGroup(
            clientInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(clientInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usernameCleintLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ipAdressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .addComponent(portClientLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(clientInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(clientUsernameField, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(clientPortField)
                    .addComponent(clientIpAddressField))
                .addContainerGap())
        );
        clientInfoPanelLayout.setVerticalGroup(
            clientInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientInfoPanelLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(clientInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientUsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameCleintLabel))
                .addGap(18, 18, 18)
                .addGroup(clientInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientPortField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portClientLabel))
                .addGap(18, 18, 18)
                .addGroup(clientInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientIpAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ipAdressLabel))
                .addContainerGap(202, Short.MAX_VALUE))
        );

        choicePanel.add(clientInfoPanel, "card3");

        createButton.setBackground(new java.awt.Color(255, 255, 255));
        createButton.setText("Create");
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout accountInfoPanelLayout = new javax.swing.GroupLayout(accountInfoPanel);
        accountInfoPanel.setLayout(accountInfoPanelLayout);
        accountInfoPanelLayout.setHorizontalGroup(
            accountInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accountInfoPanelLayout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(149, Short.MAX_VALUE))
            .addGroup(accountInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(accountInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(choicePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, accountInfoPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        accountInfoPanelLayout.setVerticalGroup(
            accountInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accountInfoPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(choicePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(createButton)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        mainPanel.add(accountInfoPanel, "card2");

        chatPanel.setBackground(new java.awt.Color(204, 237, 255));
        chatPanel.setMaximumSize(new java.awt.Dimension(388, 600));
        chatPanel.setPreferredSize(new java.awt.Dimension(388, 500));
        chatPanel.setLayout(new java.awt.CardLayout());

        messageContainerPanel.setBackground(new java.awt.Color(255, 102, 102));
        messageContainerPanel.setPreferredSize(new java.awt.Dimension(400, 500));
        messageContainerPanel.setLayout(new java.awt.BorderLayout());

        chatTextLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chatTextLabel.setText("Message");

        chatField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chatFieldActionPerformed(evt);
            }
        });

        attachFileLabel.setBackground(new java.awt.Color(221, 212, 212));
        attachFileLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        attachFileLabel.setText("<html><u>Attach File</u></html> ");
        attachFileLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                attachFileLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout messagePanelLayout = new javax.swing.GroupLayout(messagePanel);
        messagePanel.setLayout(messagePanelLayout);
        messagePanelLayout.setHorizontalGroup(
            messagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(messagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(messagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(messagePanelLayout.createSequentialGroup()
                        .addComponent(chatTextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 280, Short.MAX_VALUE))
                    .addComponent(chatField)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, messagePanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(attachFileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        messagePanelLayout.setVerticalGroup(
            messagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(messagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chatTextLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chatField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(attachFileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        messageContainerPanel.add(messagePanel, java.awt.BorderLayout.PAGE_END);

        messageViewPanel.setBackground(new java.awt.Color(204, 255, 204));
        messageViewPanel.setLayout(new java.awt.CardLayout());

        chatBox.setColumns(20);
        chatBox.setRows(5);
        chatViewScrollPane.setViewportView(chatBox);

        messageViewPanel.add(chatViewScrollPane, "card2");

        messageContainerPanel.add(messageViewPanel, java.awt.BorderLayout.CENTER);

        chatPanel.add(messageContainerPanel, "card2");

        mainPanel.add(chatPanel, "card3");

        messengerMenu.setText("Messenger");

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        messengerMenu.add(exitMenuItem);

        messengerMenuBar.add(messengerMenu);

        helpMenu.setText("Help");

        myIPMenuItem.setText("My IP");
        myIPMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myIPMenuItemMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                myIPMenuItemMousePressed(evt);
            }
        });
        helpMenu.add(myIPMenuItem);

        chatProcessMenuItem.setText("How to use");
        chatProcessMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                chatProcessMenuItemMousePressed(evt);
            }
        });
        helpMenu.add(chatProcessMenuItem);

        aboutMenuItem.setText("About");
        aboutMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                aboutMenuItemMousePressed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        messengerMenuBar.add(helpMenu);

        setJMenuBar(messengerMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxActionPerformed
        // TODO add your handling code here:
        if (comboBox.getSelectedItem().toString().equals("SERVER")) {
            resetField();
            changePanel(choicePanel, serverInfoPanel);
            createButton.setText("CREATE");
            createButton.setEnabled(true);
        }

        if (comboBox.getSelectedItem().toString().equals("CLIENT")) {
            resetField();
            changePanel(choicePanel, clientInfoPanel);
            createButton.setText("CONNECT");
            createButton.setEnabled(true);
        }
    }//GEN-LAST:event_comboBoxActionPerformed

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        // TODO add your handling code here:
        accountLogin();
    }//GEN-LAST:event_createButtonActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void clientPortFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_clientPortFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            accountLogin();
        }
    }//GEN-LAST:event_clientPortFieldKeyPressed

    private void clientPortFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_clientPortFieldKeyTyped
        // TODO add your handling code here:
        char ch = evt.getKeyChar();
        if (!(Character.isDigit(ch) || ch == KeyEvent.VK_BACKSPACE || ch == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_clientPortFieldKeyTyped

    private void clientIpAddressFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_clientIpAddressFieldKeyTyped
        // TODO add your handling code here:
        char ch = evt.getKeyChar();
        if (!(Character.isDigit(ch) || ch == KeyEvent.VK_BACKSPACE || ch == KeyEvent.VK_DELETE || ch == KeyEvent.VK_PERIOD)) {
            evt.consume();
        }
    }//GEN-LAST:event_clientIpAddressFieldKeyTyped

    private void myIPMenuItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myIPMenuItemMouseClicked

    }//GEN-LAST:event_myIPMenuItemMouseClicked

    private void myIPMenuItemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myIPMenuItemMousePressed
        // TODO add your handling code here:
        try {
            // get the host ip and name and display it
            InetAddress host = InetAddress.getLocalHost();
            JOptionPane.showMessageDialog(null, "IP Address : " + host.getHostAddress() + "\nHost Name : " + host.getHostName(), "My IP", JOptionPane.INFORMATION_MESSAGE);
        } catch (UnknownHostException ex) {
            System.out.println("Unknown Host ERROR");
        }
    }//GEN-LAST:event_myIPMenuItemMousePressed

    private void aboutMenuItemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutMenuItemMousePressed
        // TODO add your handling code here:

        JOptionPane.showMessageDialog(null, "Name: Biswajit Debnath\nEmail: biswajit.sust@gmail.com", "Developer Info", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_aboutMenuItemMousePressed

    private void chatProcessMenuItemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chatProcessMenuItemMousePressed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,
                "- If you start the chat then select Server mode.\n"
                + "- For joining with Server select Client mode.\n"
                + "- In Server mode you have to provide the Port Id.\n"
                + "- You can see your IP address from the MyIP menu.\n"
                + "- In Client mode you have to provide the Server Ip and Server Port Id.\n"
                + "- Use the input text field to enter your chat message.\n",
                //                + "- Save your chat using the save chat option from the file menu.\n"
                "Messenger Use",
                JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_chatProcessMenuItemMousePressed

    private void serverPortFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_serverPortFieldKeyTyped
        // TODO add your handling code here:
        char ch = evt.getKeyChar();
        if (!(Character.isDigit(ch) || ch == KeyEvent.VK_BACKSPACE || ch == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_serverPortFieldKeyTyped

    private void serverPortFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_serverPortFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            accountLogin();
        }
    }//GEN-LAST:event_serverPortFieldKeyPressed

    private void chatFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chatFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chatFieldActionPerformed

    private void attachFileLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_attachFileLabelMouseClicked
        // TODO add your handling code here: 
    }//GEN-LAST:event_attachFileLabelMouseClicked

    private void changePanel(JPanel outerPanel, JPanel innerPanel) {
        outerPanel.removeAll();
        outerPanel.repaint();
        outerPanel.revalidate();
        outerPanel.add(innerPanel);
        outerPanel.repaint();
        outerPanel.revalidate();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JPanel accountInfoPanel;
    private javax.swing.JLabel attachFileLabel;
    public javax.swing.JTextArea chatBox;
    public javax.swing.JTextField chatField;
    private javax.swing.JPanel chatPanel;
    private javax.swing.JMenuItem chatProcessMenuItem;
    private javax.swing.JLabel chatTextLabel;
    private javax.swing.JScrollPane chatViewScrollPane;
    private javax.swing.JPanel choicePanel;
    private javax.swing.JPanel clientInfoPanel;
    private javax.swing.JTextField clientIpAddressField;
    private javax.swing.JTextField clientPortField;
    private javax.swing.JTextField clientUsernameField;
    private javax.swing.JComboBox comboBox;
    private javax.swing.JButton createButton;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel ipAdressLabel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel messageContainerPanel;
    private javax.swing.JPanel messagePanel;
    private javax.swing.JPanel messageViewPanel;
    private javax.swing.JMenu messengerMenu;
    private javax.swing.JMenuBar messengerMenuBar;
    private javax.swing.JMenuItem myIPMenuItem;
    private javax.swing.JLabel portClientLabel;
    private javax.swing.JLabel portserverLabel;
    private javax.swing.JPanel serverInfoPanel;
    private javax.swing.JTextField serverPortField;
    private javax.swing.JTextField serverUsernameField;
    private javax.swing.JLabel usernameCleintLabel;
    private javax.swing.JLabel usernameServerLabel;
    // End of variables declaration//GEN-END:variables
}
