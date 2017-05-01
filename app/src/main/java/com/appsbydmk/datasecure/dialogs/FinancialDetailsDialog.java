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
import android.widget.Spinner;

import com.appsbydmk.datasecure.R;

import java.util.List;


public class FinancialDetailsDialog extends Dialog implements View.OnClickListener {
    private Spinner spBankAccountTypes;
    private ArrayAdapter<String> bankAccountsTypeAdapter;
    private Context myContext;

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
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, @Nullable Menu menu, int deviceId) {

    }
}
