package com.appsbydmk.datasecure.helpers;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class UserDetailsFileHelper {

    public static boolean writeUserDetails(Context context, String username, String password) {
        File dataDir = context.getFilesDir();
        File userDetails = new File(dataDir + "/" + HelperConstants.USER_DETAILS_FILE);
        BufferedWriter userDetailsWriter = null;
        try {
            userDetailsWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(userDetails)));
            userDetailsWriter.write(username);
            userDetailsWriter.write("\n");
            userDetailsWriter.write(password);
            userDetailsWriter.flush();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (userDetailsWriter != null)
                    userDetailsWriter.close();
            } catch (IOException | NullPointerException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public static ArrayList<String> getUserDetails(Context context) {
        ArrayList<String> userDetailsList = new ArrayList<>();
        String userDetails = EncryptDecryptUtility.decrypt(context, "encrypted_" + HelperConstants.USER_DETAILS_FILE);
        String[] users = userDetails.split("\n");
        for (String s : users) {
            userDetailsList.add(s);
        }
        return userDetailsList;
    }
}
