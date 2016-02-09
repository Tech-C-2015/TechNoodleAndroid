package com.example.teacher.technoodleandroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by yuto on 2016/02/09.
 */
public class CommentActivity extends ActionBarActivity {
//    String comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment);

        // comment_dialog���N��
        final Button comment_button = (Button)findViewById(R.id.comment_button);
        comment_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                commentDialog();
            }
        });
        // map�{�^�������������̏���
        final Button map_button = (Button)findViewById(R.id.map_button);
        map_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // �����Iintent ��MAP�N��
                Uri uri = Uri.parse("geo:0,0?q=" + "");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // �R�����g�_�C�A���O�ݒ�
    private void commentDialog(){

        // �J�X�^���r���[��ݒ�
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.comment_dialog, (ViewGroup)findViewById(R.id.comment_dialog));

        // �A���[�g�_�C�A���O �𐶐�
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.comment_dialog_title);
        builder.setView(layout);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // �I�u�W�F�N�g�擾
                Spinner taste = (Spinner)layout.findViewById(R.id.spi_taste);
                Spinner tooping = (Spinner)layout.findViewById(R.id.spi_topping);
                Spinner price = (Spinner)layout.findViewById(R.id.spi_price);
                EditText comment = (EditText)layout.findViewById(R.id.noodle_comment);

                // �I�u�W�F�N�g�̒l���擾����String��
                String strTaste = taste.getSelectedItem().toString();
                String strTopping = tooping.getSelectedItem().toString();
                String strPrice = price.getSelectedItem().toString();
                String strComment = comment.getText().toString();

                // Toast�o��
                showToast(strTaste+strTopping+strPrice);
                showToast(strComment);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Cancel �{�^������

            }
        });

        // �\��
        builder.create().show();
    }
    private void showToast(String s){
        Toast.makeText(this, "toast+"+s, Toast.LENGTH_LONG).show();
    }

    public void commentView(){

    }
}
