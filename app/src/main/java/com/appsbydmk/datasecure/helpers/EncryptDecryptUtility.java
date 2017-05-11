package com.appsbydmk.datasecure.helpers;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class EncryptDecryptUtility {
    public static void generateKey(Context context) {
        File dataDir = context.getFilesDir();
        File keyFile = new File(dataDir + HelperConstants.KEY_FILE);
        ObjectOutputStream keyOS = null;
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(HelperConstants.ALGORITHM);
            SecretKey secretKey = keyGen.generateKey();

            if (!keyFile.exists()) {
                keyFile.createNewFile();
            }

            keyOS = new ObjectOutputStream(new FileOutputStream(keyFile));
            keyOS.writeObject(secretKey);
            keyOS.flush();
        } catch (NoSuchAlgorithmException | IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (keyOS != null) {
                    keyOS.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static boolean encrypt(Context context, String fileName) {
        File dataDir = context.getFilesDir();
        File srcFile = new File(dataDir + "/" + fileName);
        File targetFile = new File(dataDir + "/encrypted_" + fileName);
        FileInputStream fileIS = null;
        FileOutputStream fileOS = null;
        CipherOutputStream cOS = null;
        boolean encrypted;
        if (srcFile.exists()) {
            SecretKey secretKey = EncryptDecryptUtility.getSecretKey(context);
            try {
                Cipher cipher = Cipher.getInstance(HelperConstants.ALGORITHM);
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                fileIS = new FileInputStream(srcFile);
                fileOS = new FileOutputStream(targetFile);
                byte[] inputBytes = new byte[(int) srcFile.length()];
                cOS = new CipherOutputStream(fileOS, cipher);
                fileIS.read(inputBytes);
                String s = new String(inputBytes, "UTF-8");
                inputBytes = s.getBytes("UTF-8");
                cOS.write(inputBytes);
                cOS.flush();
                srcFile.delete();
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                    | IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (cOS != null)
                        cOS.close();
                    if (fileIS != null)
                        fileIS.close();
                    if (fileOS != null)
                        fileOS.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            encrypted = true;
        } else {
            encrypted = false;
        }
        return encrypted;
    }

    private static SecretKey getSecretKey(Context context) {
        File dataDir = context.getFilesDir();
        File keyFile = new File(dataDir + HelperConstants.KEY_FILE);
        ObjectInputStream keyIS = null;
        SecretKey secretKey = null;
        if (keyFile.exists()) {
            try {
                keyIS = new ObjectInputStream(new FileInputStream(keyFile));
                secretKey = (SecretKey) keyIS.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (keyIS != null) {
                        keyIS.close();
                    }
                } catch (IOException | NullPointerException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return secretKey;
    }

    public static String decrypt(Context context, String fileName) {
        File dataDir = context.getFilesDir();
        File srcFile = new File(dataDir + "/" + fileName);
        FileInputStream fileIS = null;
        String finalString = "";
        CipherInputStream cIS = null;
        if (srcFile.exists()) {
            SecretKey secretKey = EncryptDecryptUtility.getSecretKey(context);
            try {
                Cipher cipher = Cipher.getInstance(HelperConstants.ALGORITHM);
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
                fileIS = new FileInputStream(srcFile);
                cIS = new CipherInputStream(fileIS, cipher);
                byte[] inputBytes = new byte[(int) srcFile.length()];
                cIS.read(inputBytes);
                String s = new String(inputBytes, "UTF-8");
                inputBytes = s.getBytes("UTF-8");
                finalString = new String(inputBytes);
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                    | IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (cIS != null)
                        cIS.close();
                    if (fileIS != null)
                        fileIS.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return finalString;
    }
}
