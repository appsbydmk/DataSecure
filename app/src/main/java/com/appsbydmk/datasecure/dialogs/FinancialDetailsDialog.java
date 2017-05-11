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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.appsbydmk.datasecure.R;
import com.appsbydmk.datasecure.helpers.EncryptDecryptUtility;
import com.appsbydmk.datasecure.helpers.HelperConstants;
import com.appsbydmk.datasecure.helpers.UserInformationHelper;

import java.util.ArrayList;
import java.util.List;


public class FinancialDetailsDialog extends Dialog implements View.OnClickListener {
    private Spinner spBankAccountTypes;
    private ArrayAdapter<String> bankAccountsTypeAdapter;
    private Context myContext;
    private Button btnSave, btnCancel;
    private String accountType;
    private EditText etBankName, etBankAccNo, etBankBranchName;

    public FinancialDetailsDialog(Context context) {
        super(context);
        this.myContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_financial_details);
        spBankAccountTypes = (Spinner) this.findViewById(R.id.sp_bank_acc_type);
        bankAccountsTypeAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                myContext.getResources().getStringArray(R.array.bank_acc_types));
        spBankAccountTypes.setAdapter(bankAccountsTypeAdapter);
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
        accountType = spBankAccountTypes.getSelectedItem().toString();
        financialDetails.add("Bank Name: " + bankName);
        financialDetails.add("Bank Branch Name: " + bankBranchName);
        financialDetails.add("Bank Account No: " + bankAccNo);
        financialDetails.add("Bank Account Type: " + accountType);
        UserInformationHelper.writeFinancialDetails(myContext, financialDetails);
    }
}
