/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coderbd.messenger.client;

import coderbd.messenger.gui.ChatBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JTextField;

/**
 *
 * @author Biswajit
 */
public class Client extends Thread {
    
    Socket socket;
    DataInputStream dataInput;
    DataOutputStream dataOutput;
    public JTextField chatField=new JTextField();
    String username = "CLIENT";

    public Client(final String host, final int port, String username, JTextField chatField) {
        
        this.chatField=chatField;
        this.username=username;
        
        chatField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String message = getUsername() + event.getActionCommand();
                sendMessage(message);
                ChatBox.addMessage(message);
                ChatBox.setEmptyField();
            }
        });
        
        try {
            
            socket = new Socket(InetAddress.getByName(host), port);
            dataInput = new DataInputStream(socket.getInputStream());
            dataOutput = new DataOutputStream(socket.getOutputStream());
            sendMessage(username + "CONNECTED");
            ChatBox.addMessage(username + " CONNECTED");
            ChatBox.chatPermission(true);
            new Thread(this).start();
            
        } catch (EOFException ex) {
            System.out.println("EOF : " + ex);
        } catch (IOException ex) {
            ChatBox.addMessage("SERVER CONNECTION ERROR");
        }
    }
    
    public String getUsername(){
        return this.username;
    }

    private void sendMessage(String message) {
        try {
            dataOutput.writeUTF(message);
            dataOutput.flush();
        } catch (IOException ex) {
             ChatBox.addMessage("SERVER CONNECTION ERROR");
        }
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                String message = dataInput.readUTF();
                ChatBox.addMessage(message);
            }
        } catch (IOException ex) {
            ChatBox.addMessage("SERVER CONNECTION ERROR");
        }
    }
}