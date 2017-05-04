package com.appsbydmk.datasecure.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appsbydmk.datasecure.R;
import com.appsbydmk.datasecure.helpers.UserInformationHelper;

import java.util.ArrayList;
import java.util.List;

public class WebIdDetailsDialog extends Dialog implements View.OnClickListener {
    private Context myContext;
    private Button btnSave, btnCancel;
    private EditText etSecondaryEmailAddress, etFacebookId, etTwitterId, etSnapchatId, etInstagramId;

    public WebIdDetailsDialog(Context context) {
        super(context);
        this.myContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_web_ids_details);
        btnSave = (Button) this.findViewById(R.id.btn_save);
        btnCancel = (Button) this.findViewById(R.id.btn_cancel);
        etSecondaryEmailAddress = (EditText) this.findViewById(R.id.et_secondary_email_id);
        etFacebookId = (EditText) this.findViewById(R.id.et_facebook_id);
        etTwitterId = (EditText) this.findViewById(R.id.et_twitter_id);
        etSnapchatId = (EditText) this.findViewById(R.id.et_snapchat_id);
        etInstagramId = (EditText) this.findViewById(R.id.et_instagram_id);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                this.writeWebDetails();
                Toast.makeText(myContext, "Web Details Saved!", Toast.LENGTH_SHORT).show();
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

    private void writeWebDetails() {
        ArrayList<String> webDetails = new ArrayList<>();
        String secondaryEmailAddress = etSecondaryEmailAddress.getText().toString();
        String facebook = etFacebookId.getText().toString();
        String twitter = etTwitterId.getText().toString();
        String snapchat = etSnapchatId.getText().toString();
        String instagram = etInstagramId.getText().toString();
        webDetails.add("Secondary Email Address: " + secondaryEmailAddress);
        webDetails.add("Facebook: " + facebook);
        webDetails.add("Twitter: " + twitter);
        webDetails.add("Snapchat: " + snapchat);
        webDetails.add("Instagram: " + instagram);
        UserInformationHelper.writeWebDetails(myContext, webDetails);
    }
}
