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
        BufferedWriter educationWriter = null;
        try {
            educationWriter = new BufferedWriter(new OutputStreamWriter
                    (context.openFileOutput(HelperConstants.EDUCATION_DETAILS_FILE, Context.MODE_PRIVATE)));
            for (String p : educationDetails) {
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
        BufferedWriter financialDetailsWriter = null;
        try {
            financialDetailsWriter = new BufferedWriter(new OutputStreamWriter
                    (context.openFileOutput(HelperConstants.FINANCIAL_DETAILS_FILE, Context.MODE_PRIVATE)));
            for (String p : financialDetails) {
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
        BufferedWriter insuranceDetailsWriter = null;
        try {
            insuranceDetailsWriter = new BufferedWriter(new OutputStreamWriter
                    (context.openFileOutput(HelperConstants.INSURANCE_DETAILS_FILE, Context.MODE_PRIVATE)));
            for (String p : insuranceDetails) {
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
        BufferedWriter webDetailsWriter = null;
        try {
            webDetailsWriter = new BufferedWriter(new OutputStreamWriter
                    (context.openFileOutput(HelperConstants.WEB_DETAILS_FILE, Context.MODE_PRIVATE)));
            for (String p : webDetails) {
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
