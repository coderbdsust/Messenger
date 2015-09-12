/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coderbd.messenger.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Biswajit Debnath
 */
public class Filter {

    public static String dataFilter(String message) {
        String[] splits = message.split(Simulator.SEPERATOR);
        String data = "";

        if (splits[0].equals(Simulator.FILE)) {
            String header = Simulator.FILE + Simulator.SEPERATOR + splits[1] + Simulator.SEPERATOR;
            data = message.substring(header.length());
        } else {
            String header = Simulator.TEXT + Simulator.SEPERATOR;
            data = message.substring(header.length());
        }
        return data;
    }

    public static String getFileName(String message) {
        String[] splitData = message.split(Simulator.SEPERATOR);
        if (splitData.length < 1) {
            return new SimpleDateFormat("ddMMyyyy-hhmmssSS").format(new Date()).toString() + "." + Simulator.FILE_TYPE;
        }
        return splitData[1];
    }

    public static boolean isFileData(String data) {
        String[] splits = data.split(Simulator.SEPERATOR);
        if (splits[0].equals(Simulator.FILE)) {
            return true;
        }
        return false;
    }

}
