package com.appsbydmk.datasecure.helpers;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class UserInformationHelper {

    public static void writePersonalDetails(Context context, ArrayList<String> personalInfo) {
        BufferedWriter personalDetailWriter = null;
        try {
            personalDetailWriter = new BufferedWriter(new OutputStreamWriter
                    (context.openFileOutput(HelperConstants.PERSONAL_DETAILS_FILE, Context.MODE_PRIVATE)));
            for (String p : personalInfo) {
                personalDetailWriter.write(p + "\n");
            }
            personalDetailWriter.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (personalDetailWriter != null)
                    personalDetailWriter.close();
            } catch (IOException | NullPointerException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void writeEducationalDetails(Context context, ArrayList<String> educationDetails) {

    }

    public static void writeFinancialDetails(Context context, ArrayList<String> financialDetails) {

    }

    public static void writeInsuranceDetails(Context context, ArrayList<String> insuranceDetails) {

    }

    public static void writeWebDetails(Context context, ArrayList<String> webDetails) {

    }
}
