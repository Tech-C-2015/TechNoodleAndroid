package com.example.teacher.technoodleandroid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.teacher.technoodleandroid.util.GetRequest;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ActionBarActivity {
    EditText editWord;
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
                //実際のjsonデータはresponseに格納されてる
                txtResult.setText(response);
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
