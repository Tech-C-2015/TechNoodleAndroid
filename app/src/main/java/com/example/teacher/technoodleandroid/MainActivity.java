package com.example.teacher.technoodleandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends ActionBarActivity {
    private Spinner selectSpinner1;
    private Spinner selectSpinner2;
    private Spinner selectSpinner3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Spinnerの設定
        ArrayAdapter<CharSequence> adapter1 =
                ArrayAdapter.createFromResource(this, R.array.flavor,
                        android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapter2 =
                ArrayAdapter.createFromResource(this, R.array.low,
                        android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapter3 =
                ArrayAdapter.createFromResource(this, R.array.high,
                        android.R.layout.simple_spinner_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        selectSpinner1 = (Spinner) findViewById(R.id.spinner1);
        selectSpinner1.setAdapter(adapter1);

        selectSpinner2 = (Spinner) findViewById(R.id.spinner2);
        selectSpinner2.setAdapter(adapter2);

        selectSpinner3 = (Spinner) findViewById(R.id.spinner3);
        selectSpinner3.setAdapter(adapter3);

        // ボタンの設定
        Button buttonCheckSelected;
        buttonCheckSelected = (Button)findViewById(R.id.button1);
        buttonCheckSelected.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(getApplication(), ListActivity.class);
                intent.putExtra("flavor", selectSpinner1.getSelectedItem().toString());
                intent.putExtra("low", selectSpinner2.getSelectedItem().toString());
                intent.putExtra("high", selectSpinner3.getSelectedItem().toString());
                startActivity(intent);
            }
        });
    }
}
