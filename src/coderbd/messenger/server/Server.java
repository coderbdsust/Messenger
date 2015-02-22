/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coderbd.messenger.server;

import coderbd.messenger.gui.ChatBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Biswajit
 */


public class Server {

    private ServerSocket serverSocket;
    public String username=null;
    public JTextField chatField = new JTextField();
    final private ArrayList<SocketStream> socketList;

    public Server(int port, String username, JTextField chatField) throws IOException {
        this.socketList = new ArrayList<SocketStream>();
        this.username=username;
        this.chatField=chatField;
        
        chatField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String message = getUsername() + event.getActionCommand();
                sendToAll(message);
                ChatBox.addMessage(message);
                ChatBox.setEmptyField();
            }
        });
        
        listen(port);
    }
    
    private String getUsername(){
        return this.username;
    }

    @SuppressWarnings("empty-statement")
    private void listen(int port) throws IOException {

        serverSocket = new ServerSocket(port);
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                DataInputStream dataInput = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());
                ChatBox.chatPermission(true);
                socketList.add(new SocketStream(dataOutput, socket));
                new ServerThread(this, socket, dataInput).start();

            } catch (IOException ex) {
                System.out.println("Listen: " + ex);
            }
        }
    }

    public void sendToAll(String message) {
        for (SocketStream socketStream : socketList) {
            sendMessage(socketStream, message);
        }
    }
    
    public void sendToAll(String message, Socket socket) {
        for (SocketStream socketStream : socketList) {
            if(socketStream.socket!=socket)
                sendMessage(socketStream, message);
        }
    }

    public void removeConnection(Socket socket) {
        for(SocketStream socketStream: socketList){
            if(socketStream.socket.equals(socket)){
                socketList.remove(socketStream);
                break;
            }
        }
    }

    private void sendMessage(SocketStream socketStream, String message) {
        try {
            socketStream.dataOutput.writeUTF(message);
            socketStream.dataOutput.flush();
        } catch (IOException ex) {
            System.out.println("Send message 1 : " + ex);
        }
    }


}
