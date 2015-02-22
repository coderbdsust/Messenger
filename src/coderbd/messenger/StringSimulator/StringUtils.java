/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coderbd.messenger.StringSimulator;

/**
 *
 * @author Biswajit
 */
public class StringUtils {

    public static boolean isValidIp(String address) {
        int digitBeforeDot = 0;
        int totalDot = 0;

        for (int i = 0; i < address.length(); i++) {
            char ch = address.charAt(i);
            if (ch >= '0' && ch <= '9') {
                digitBeforeDot++;
            } else if (ch == '.') {
                totalDot++;
                if (digitBeforeDot >= 1 && digitBeforeDot <= 3) {
                    digitBeforeDot = 0;
                } else {
                    return false;
                }
            }else{
                digitBeforeDot=0;
            }
        }
        return (totalDot == 3) && (digitBeforeDot >= 1 && digitBeforeDot <= 3);
    }

    public static String removeSpace(String text) {
        String newText="";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c != ' ') {
                newText += c;
            }
        }
        return newText;
    }
    
    public static boolean isValidPort(int port){
        return (port>=0 && port<=65000);
    }
    
    public static boolean isValidUsername(String username){
        return username.length()>0;
    }

    public static boolean isValid(String username, int port){
        return isValidUsername(username) && isValidPort(port);
    }
    
    public static boolean isValid(String username, int port, String ipAddress){
        return isValidUsername(username) && isValidPort(port) && isValidIp(ipAddress);
    }
}
