package com.appsbydmk.datasecure.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appsbydmk.datasecure.R;
import com.appsbydmk.datasecure.helpers.EncryptDecryptUtility;
import com.appsbydmk.datasecure.helpers.HelperConstants;
import com.appsbydmk.datasecure.helpers.UserInformationHelper;

import java.util.ArrayList;
import java.util.List;


public class FinancialDetailsDialog extends Dialog implements View.OnClickListener {
    private ArrayAdapter<String> bankAccountsTypeAdapter;
    private Context myContext;
    private Button btnSave, btnCancel;
    private String accountType;
    private EditText etBankName, etBankAccNo, etBankBranchName;
    private AutoCompleteTextView actAccType;

    public FinancialDetailsDialog(Context context) {
        super(context);
        this.myContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_financial_details);
        String[] accTypes = myContext.getResources().getStringArray(R.array.bank_acc_types);
        actAccType = (AutoCompleteTextView) this.findViewById(R.id.act_acc_type);
        bankAccountsTypeAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_dropdown_item_1line, accTypes);
        actAccType.setThreshold(1);
        actAccType.setAdapter(bankAccountsTypeAdapter);
        btnSave = (Button) this.findViewById(R.id.btn_save);
        btnCancel = (Button) this.findViewById(R.id.btn_cancel);
        etBankName = (EditText) this.findViewById(R.id.et_bank_name);
        etBankBranchName = (EditText) this.findViewById(R.id.et_bank_branch_name);
        etBankAccNo = (EditText) this.findViewById(R.id.et_bank_acc_no);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                this.writeFinancialDetails();
                EncryptDecryptUtility.encrypt(myContext, HelperConstants.FINANCIAL_DETAILS_FILE);
                Toast.makeText(myContext, "Financial Details Saved!", Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
            default:
                break;
        }
    }

    @Override
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, @Nullable Menu menu, int deviceId) {

    }

    private void writeFinancialDetails() {
        ArrayList<String> financialDetails = new ArrayList<>();
        String bankName = etBankName.getText().toString();
        String bankBranchName = etBankBranchName.getText().toString();
        String bankAccNo = etBankAccNo.getText().toString();
        accountType = actAccType.getText().toString();
        if (bankName.equals("") || bankName.isEmpty())
            financialDetails.add("");
        else
            financialDetails.add("Bank Name: " + bankName);

        if (bankBranchName.equals("") || bankBranchName.isEmpty())
            financialDetails.add("");
        else
            financialDetails.add("Branch Name: " + bankBranchName);

        if (bankAccNo.equals("") || bankAccNo.isEmpty())
            financialDetails.add("");
        else
            financialDetails.add("Account No: " + bankAccNo);

        if (accountType.equals("") || accountType.isEmpty())
            financialDetails.add("");
        else
            financialDetails.add("Account Type: " + accountType);
        UserInformationHelper.writeFinancialDetails(myContext, financialDetails);
    }
}
