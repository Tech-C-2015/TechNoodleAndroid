package com.example.teacher.technoodleandroid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


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
        /*
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item);
        adapter.add("Sample0");
        adapter.add("Sample1");
        adapter.add("Sample2");
        adapter.add("Sample3");
        adapter.add("Sample4");
        */
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        selectSpinner1 = (Spinner) findViewById(R.id.spinner1);
        selectSpinner1.setAdapter(adapter1);
        selectSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                showToast(Integer.toString(spinner.getSelectedItemPosition()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        selectSpinner2 = (Spinner) findViewById(R.id.spinner2);
        selectSpinner2.setAdapter(adapter2);
        selectSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                showToast(Integer.toString(spinner.getSelectedItemPosition()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        selectSpinner3 = (Spinner) findViewById(R.id.spinner3);
        selectSpinner3.setAdapter(adapter3);
        selectSpinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                showToast(Integer.toString(spinner.getSelectedItemPosition()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // ボタンの設定
        Button buttonCheckSelected;
        buttonCheckSelected = (Button)findViewById(R.id.button1);
        buttonCheckSelected.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                showToast((String)selectSpinner1.getSelectedItem());
                showToast((String)selectSpinner2.getSelectedItem());
                showToast((String)selectSpinner3.getSelectedItem());
            }
        });
    }

    private void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
/*    EditText editWord;
    TextView txtResult;
    Button btnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editWord = (EditText) findViewById(R.id.edit_word);
        txtResult = (TextView) findViewById(R.id.txt_result);
        btnGet = (Button) findViewById(R.id.btn_get);
        editWord.setText("東京");
        //click監視
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBtn(v);
            }
        });


    }

    //get送信
    protected void getBtn(View view) {
        Map<String, String> params = makeParameter();
        AppController app = (AppController) getApplication();
        GetRequest request = new GetRequest(new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject json = new JSONObject(response);
                    JSONObject info = json.getJSONObject("info");

                    while(info.keys().hasNext()){
                        String key = info.keys().next();
                        JSONObject data = info.getJSONObject(key);
                        data.getString("id");
                        data.getString("image");
                        data.get("");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, params);
        app.mRequestQueue.add(request);
    }

    //editwordからMapにkeyとvalueのセット
    private Map<String, String> makeParameter() {
        Map<String, String> params = new HashMap<>();
        params.put("name", editWord.getText().toString());
        return params;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
*/
}
