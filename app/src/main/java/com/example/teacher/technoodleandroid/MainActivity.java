package com.example.teacher.technoodleandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ActionBarActivity {
    EditText editWord;
    TextView txtResult;
    Button btnGet;
    Button btnComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editWord = (EditText) findViewById(R.id.edit_word);
        btnGet = (Button) findViewById(R.id.btn_get);
        editWord.setText("東京");
        //click監視
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBtn(v);
            }
        });

        btnComment = (Button)findViewById(R.id.btn_comment);
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comment(v);
            }
        });

    }

    //get送信
    protected void getBtn(View view) {
        startActivity(new Intent(getApplication(), ListActivity.class));
    }

    void comment(View view){
        Intent intent = new Intent(MainActivity.this,CommentActivity.class);
        startActivity(intent);
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
