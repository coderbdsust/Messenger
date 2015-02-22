/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coderbd.messenger.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author Biswajit
 */
public class SocketStream {
    public  DataOutputStream dataOutput;
    public Socket socket;
    
    public SocketStream(DataOutputStream dataOutput, Socket socket) {
        this.dataOutput = dataOutput;
        this.socket = socket;
    }

}
