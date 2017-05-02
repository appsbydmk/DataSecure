package com.appsbydmk.datasecure.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.appsbydmk.datasecure.R;
import com.appsbydmk.datasecure.dialogs.EducationalDetailsDialog;
import com.appsbydmk.datasecure.dialogs.FinancialDetailsDialog;
import com.appsbydmk.datasecure.dialogs.InsuranceDetailsDialog;
import com.appsbydmk.datasecure.dialogs.PersonalDetailsDialog;
import com.appsbydmk.datasecure.dialogs.WebIdDetailsDialog;

public class InfoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lvInfoItems;
    private ArrayAdapter<String> infoItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        lvInfoItems = (ListView) this.findViewById(R.id.lv_save_info_items);
        infoItemsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.save_info_items));
        lvInfoItems.setAdapter(infoItemsAdapter);
        lvInfoItems.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                PersonalDetailsDialog personalDetailsDialog = new PersonalDetailsDialog(this);
                personalDetailsDialog.show();
                break;
            case 1:
                FinancialDetailsDialog financialDetailsDialog = new FinancialDetailsDialog(this);
                financialDetailsDialog.show();
                break;
            case 2:
                WebIdDetailsDialog webIdDetailsDialog = new WebIdDetailsDialog(this);
                webIdDetailsDialog.show();
                break;
            case 3:
                InsuranceDetailsDialog insuranceDetailsDialog = new InsuranceDetailsDialog(this);
                insuranceDetailsDialog.show();
                break;
            case 4:
                EducationalDetailsDialog educationalDetailsDialog = new EducationalDetailsDialog(this);
                educationalDetailsDialog.show();
                break;
            default:
                break;
        }


    }
}
