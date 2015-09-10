/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coderbd.messenger.utils;

import static com.sun.org.apache.xerces.internal.util.FeatureState.is;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 *
 * @author Biswajit Debnath
 */
public class FileConverterFactory {

    private static FileConverterFactory ownInstance = new FileConverterFactory();

    public static final FileConverterFactory getInstance() {
        return ownInstance;
    }

    public byte[] convertFileToBytes(File file) {
        try {
            System.out.println("CFB FILE SIZE:"+file.length());
            byte[] fileBytes = new byte[(int) file.length()];
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            bis.read(fileBytes, 0, fileBytes.length);
            return fileBytes;
        } catch (FileNotFoundException fnfe) {
            System.out.println("[FILE CONVERT]: FILE NOT FOUND!");
            return null;
        } catch (IOException ioe) {
            System.out.println("[FILE CONVERT]: IO EXCEPTION FOUND!");
            return null;
        }
    }

    public String convertBytesToString(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public byte[] convertStringToBytes(String data) {
        return Base64.getDecoder().decode(data);
    }

    public void convertBytesToFile(String fileName, byte[] fileBytes) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bos.write(fileBytes, 0, fileBytes.length);
            bos.close();
        } catch (IOException ioe) {
            System.out.println("[FILE CONVERT]: IO EXCEPTION FOUND!");
        }
    }

}
