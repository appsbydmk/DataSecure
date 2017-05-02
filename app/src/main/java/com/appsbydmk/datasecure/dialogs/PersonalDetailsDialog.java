package com.appsbydmk.datasecure.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.view.Window;

import com.appsbydmk.datasecure.R;

import java.util.List;

public class PersonalDetailsDialog extends Dialog implements View.OnClickListener {
    private Context myContext;

    public PersonalDetailsDialog(Context context) {
        super(context);
        this.myContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_personal_details);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, @Nullable Menu menu, int deviceId) {

    }
}
