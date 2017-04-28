package com.appsbydmk.datasecure.helpers;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class UserDetailsFileHelper {
    Context context;

    public UserDetailsFileHelper(Context context) {
        this.context = context;
    }

    public boolean writeUserDetails(String username, String password) {
        BufferedWriter userDetailsWriter = null;
        try {
            userDetailsWriter = new BufferedWriter(new OutputStreamWriter
                    (context.openFileOutput(HelperConstants.USER_DETAILS_FILE, Context.MODE_PRIVATE)));
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

    public ArrayList<String> getUserDetails() {
        ArrayList<String> userDetails = new ArrayList<>();
        BufferedReader userDetailsReader = null;
        String line;
        try {
            userDetailsReader = new BufferedReader(new InputStreamReader(context.openFileInput
                    (HelperConstants.USER_DETAILS_FILE)));
            while ((line = userDetailsReader.readLine()) != null)
                userDetails.add(line);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (userDetailsReader != null)
                    userDetailsReader.close();
            } catch (IOException | NullPointerException ex) {
                ex.printStackTrace();
            }
        }
        return userDetails;
    }
}
