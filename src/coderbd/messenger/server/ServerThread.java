/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coderbd.messenger.server;

import coderbd.messenger.utils.Filter;
import coderbd.messenger.utils.Simulator;
import coderbd.messenger.gui.ChatBox;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 *
 * @author Biswajit
 */
public class ServerThread extends Thread {

    Server server;
    Socket socket;
    DataInputStream dataInput;

    public ServerThread(Server server, Socket socket, DataInputStream dataInput) {
        this.server = server;
        this.socket = socket;
        this.dataInput = dataInput;
    }

    @Override
    public void run() {
        try {
            //SEND THE CONNETION BUILDUP MESSAFE 
            String message = dataInput.readUTF();
            ChatBox.addMessage(message);
            server.sendToAll(message, socket);

            while (true) {
                /**
                 * Get the message from client side
                 */

                message = dataInput.readUTF();
//                showMessage(message);
                String tempMessage = Filter.dataFilter(message);

                if (Filter.isFileData(message)) {
                    ChatBox.addMessage("FILE DATA FORWARDED");
                } else {
                    ChatBox.addMessage(tempMessage);
                }

                /**
                 * Send to message to all other client via server
                 */
                server.sendToAll(message, socket);
            }
        } catch (EOFException ex) {
            System.out.println("EOF EXCEPTION : " + ex);
        } catch (IOException ex) {
            System.out.println("CLIENT DISCONNECTED : " + ex);
        } finally {
            
            server.removeConnection(socket);
            
        }
    }
}
