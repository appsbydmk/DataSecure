package com.appsbydmk.datasecure.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appsbydmk.datasecure.R;
import com.appsbydmk.datasecure.helpers.EncryptDecryptUtility;
import com.appsbydmk.datasecure.helpers.HelperConstants;
import com.appsbydmk.datasecure.helpers.UserDetailsFileHelper;

import java.util.List;


public class UserDetailsChangeDialog extends Dialog implements View.OnClickListener {
    private Context myContext;
    private Button btnSave, btnCancel;
    private EditText etUsername, etPassword, etPasswordAgain;
    private String userName, password, passwordAgain;

    public UserDetailsChangeDialog(Context context) {
        super(context);
        this.myContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_change_user_details);
        etUsername = (EditText) this.findViewById(R.id.et_username);
        etPassword = (EditText) this.findViewById(R.id.et_password);
        etPasswordAgain = (EditText) this.findViewById(R.id.et_password_again);
        btnSave = (Button) this.findViewById(R.id.btn_save);
        btnCancel = (Button) this.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                getUserDetails();
                if (validateUserDetails(userName, password, passwordAgain)) {
                    if (UserDetailsFileHelper.writeUserDetails(myContext, userName, password)) {
                        EncryptDecryptUtility.encrypt(myContext, HelperConstants.USER_DETAILS_FILE);
                        Toast.makeText(myContext, "Login Details changed!", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }
                } else {
                    Toast.makeText(myContext, "Please enter correct information!", Toast.LENGTH_SHORT).show();
                }
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

    private boolean validateUserDetails(String userName, String password, String passwordAgain) {
        if ((userName != null && !userName.isEmpty()) || (password != null && !password.isEmpty())
                || (passwordAgain != null && !passwordAgain.isEmpty())) {
            if (password.equals(passwordAgain))
                return true;
            else return false;
        } else return false;
    }

    private void getUserDetails() {
        userName = etUsername.getText().toString();
        password = etPassword.getText().toString();
        passwordAgain = etPasswordAgain.getText().toString();
    }
}
