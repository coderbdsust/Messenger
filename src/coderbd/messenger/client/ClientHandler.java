/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coderbd.messenger.client;

import coderbd.messenger.gui.ChatBox;
import coderbd.messenger.utils.FileConverterFactory;
import coderbd.messenger.utils.Filter;
import coderbd.messenger.utils.Simulator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author Biswajit Debnath
 */
public class ClientHandler extends Thread implements Serializable {

    Socket socket;
    DataInputStream dataInput;
    DataOutputStream dataOutput;
    public JTextField chatField = new JTextField();
    public JLabel attachFile = new JLabel();
    String username = "CLIENT";
    private File file = null;

    public ClientHandler(final String host, final int port, String username, JTextField chatField, JLabel attachFile) {

        this.chatField = chatField;
        this.username = username;
        this.attachFile = attachFile;

        chatField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String message = Simulator.TEXT + Simulator.SEPERATOR + getUsername() + event.getActionCommand();
                sendMessage(message);
                message = Filter.dataFilter(message);
                ChatBox.addMessage(message);
                ChatBox.setEmptyField();
            }
        });

        attachFile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                JFileChooser fileopen = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("File Types", Simulator.FILE_TYPE);
                fileopen.addChoosableFileFilter(filter);

                int ret = fileopen.showDialog(null, "Select file");

                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    sendFile(file);
                }
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
            System.out.println("EOF EX: " + ex);
        } catch (IOException ex) {
            ChatBox.addMessage("SERVER CONNECTION ERROR");
        }
    }

    public String getUsername() {
        return this.username;
    }

    /**
     * Sending the data to server side
     *
     * @param message
     */
    private void sendMessage(String message) {
        try {
            dataOutput.writeUTF(message);
            dataOutput.flush();
        } catch (IOException ex) {
            ChatBox.addMessage("SERVER CONNECTION ERROR");
        }
    }

    private void sendFile(File file) {
        byte[] fileBytes = FileConverterFactory.getInstance().convertFileToBytes(file);
        String fileData = FileConverterFactory.getInstance().convertBytesToString(fileBytes);
        String packetData = Simulator.FILE + Simulator.SEPERATOR + file.getName() + Simulator.SEPERATOR + fileData;
        sendMessage(packetData);
        ChatBox.addMessage("FILE SEND");
    }

    private void receiveFile(String message) {
        String fileName = Filter.getFileName(message);
        String fileData = Filter.dataFilter(message);
        byte[] fileBytes = FileConverterFactory.getInstance().convertStringToBytes(fileData);
        FileConverterFactory.getInstance().convertBytesToFile(fileName, fileBytes);
        ChatBox.addMessage("FILE RECEIVED");
    }

    /**
     * Receive the server side data and adding to the Chat box window
     */
    @Override
    public void run() {
        try {
            while (true) {
                String message = dataInput.readUTF();
                String[] type = message.split(Simulator.SEPERATOR);
                if (type.length != 0 && type[0].equals(Simulator.FILE)) {
                    receiveFile(message);
                } else {
                    String tempMessage = Filter.dataFilter(message);
                    ChatBox.addMessage(tempMessage);
                }
            }
        } catch (IOException ex) {
            ChatBox.addMessage("SERVER CONNECTION ERROR");
        }
    }
}
