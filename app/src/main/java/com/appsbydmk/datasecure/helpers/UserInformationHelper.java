package com.appsbydmk.datasecure.helpers;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class UserInformationHelper {

    public static void writePersonalDetails(Context context, ArrayList<String> personalInfo) {
        File dataDir = context.getFilesDir();
        File pdFile = new File(dataDir + "/" + HelperConstants.PERSONAL_DETAILS_FILE);
        BufferedWriter personalDetailWriter = null;
        try {
            personalDetailWriter = new BufferedWriter(new OutputStreamWriter
                    (new FileOutputStream(pdFile)));
            for (String p : personalInfo) {
                if (!p.equals(""))
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
        File dataDir = context.getFilesDir();
        File edFile = new File(dataDir + "/" + HelperConstants.EDUCATION_DETAILS_FILE);
        BufferedWriter educationWriter = null;
        try {
            educationWriter = new BufferedWriter(new OutputStreamWriter
                    (new FileOutputStream(edFile)));
            for (String p : educationDetails) {
                if (!p.equals(""))
                    educationWriter.write(p + "\n");
            }
            educationWriter.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (educationWriter != null)
                    educationWriter.close();
            } catch (IOException | NullPointerException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void writeFinancialDetails(Context context, ArrayList<String> financialDetails) {
        File dataDir = context.getFilesDir();
        File fdFile = new File(dataDir + "/" + HelperConstants.FINANCIAL_DETAILS_FILE);
        BufferedWriter financialDetailsWriter = null;
        try {
            financialDetailsWriter = new BufferedWriter(new OutputStreamWriter
                    (new FileOutputStream(fdFile)));
            for (String p : financialDetails) {
                if (!p.equals(""))
                    financialDetailsWriter.write(p + "\n");
            }
            financialDetailsWriter.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (financialDetailsWriter != null)
                    financialDetailsWriter.close();
            } catch (IOException | NullPointerException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void writeInsuranceDetails(Context context, ArrayList<String> insuranceDetails) {
        File dataDir = context.getFilesDir();
        File idFile = new File(dataDir + "/" + HelperConstants.INSURANCE_DETAILS_FILE);
        BufferedWriter insuranceDetailsWriter = null;
        try {
            insuranceDetailsWriter = new BufferedWriter(new OutputStreamWriter
                    (new FileOutputStream(idFile)));
            for (String p : insuranceDetails) {
                if (!p.equals(""))
                    insuranceDetailsWriter.write(p + "\n");
            }
            insuranceDetailsWriter.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (insuranceDetailsWriter != null)
                    insuranceDetailsWriter.close();
            } catch (IOException | NullPointerException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void writeWebDetails(Context context, ArrayList<String> webDetails) {
        File dataDir = context.getFilesDir();
        File wdFile = new File(dataDir + "/" + HelperConstants.WEB_DETAILS_FILE);
        BufferedWriter webDetailsWriter = null;
        try {
            webDetailsWriter = new BufferedWriter(new OutputStreamWriter
                    (new FileOutputStream(wdFile)));
            for (String p : webDetails) {
                if (!p.equals(""))
                    webDetailsWriter.write(p + "\n");
            }
            webDetailsWriter.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (webDetailsWriter != null)
                    webDetailsWriter.close();
            } catch (IOException | NullPointerException ex) {
                ex.printStackTrace();
            }
        }
    }
}
