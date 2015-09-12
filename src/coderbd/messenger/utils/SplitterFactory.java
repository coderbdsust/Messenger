/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coderbd.messenger.utils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Biswajit Debnath
 */
public class SplitterFactory {

    private static SplitterFactory ownInstance = new SplitterFactory();
    private final int SPLIT_SIZE = 20000;

    public static final SplitterFactory getInstance() {
        return ownInstance;
    }

    public ArrayList<String> doSplit(String data) {
        String[] splitData = data.split("(?<=\\G.{" + SPLIT_SIZE + "})");

        ArrayList<String> dataList = new ArrayList<String>();
        int index = 0;
        for (String sd : splitData) {
            String s = String.format("%s%10d", sd, index++);
            dataList.add(s);

        }
        return dataList;
    }

    public String doMerge(ArrayList<String> splitData) {

        return "";
    }

}
