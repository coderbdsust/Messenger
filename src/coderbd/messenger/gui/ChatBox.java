/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coderbd.messenger.gui;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Biswajit
 */
public class ChatBox {

    public static JTextArea chatBox = new JTextArea();
    public static JTextField chatField = new JTextField();

    public ChatBox(JTextArea chatBox, JTextField chatField) {
        ChatBox.chatBox = chatBox;
        ChatBox.chatField = chatField;
    }
    
    public static void setEmptyField(){
        chatField.setText("");
    }

    public static void addMessage(final String message) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        chatBox.append("\n" + message);
                    }
                });
    }
    
     public static void chatPermission(final boolean permission){
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        chatField.setEditable(permission);
                    }
                });
    }

}
