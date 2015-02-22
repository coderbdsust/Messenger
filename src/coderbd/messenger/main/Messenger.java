/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coderbd.messenger.main;

import coderbd.messenger.gui.Screen;

/**
 *
 * @author Biswajit
 */
public class Messenger {

    /**
     * @param args the command line arguments
     */
  public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Screen screen = new Screen();
                screen.setVisible(true);
                screen.setResizable(false);
            }
        });
      
//      Screen messengerScreen = new Screen();
    }
    
}
