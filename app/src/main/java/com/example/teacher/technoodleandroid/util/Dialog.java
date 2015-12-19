package com.example.teacher.technoodleandroid.util;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by student on 2015/12/19.
 */
public class Dialog {


        AlertDialog.Builder dialog = null;
        DatePickerDialog dateDialog = null;
        EditText edittext = null;
        TimePickerDialog timePickerDialog = null;
        ProgressDialog progressDialog = null;

        public Dialog(Context context){

            dialog = new AlertDialog.Builder(context);



        }

        void setTitleMessage( String title, String message){
            if(!(title == null) && !(title == "")){
                dialog.setTitle(title);
            }

            if(!(message == null) && !(message == "")){
                dialog.setMessage(message);
            }
        }
        public void createNormalDialog(String okButton, DialogInterface.OnClickListener okListener ,String ngButton, DialogInterface.OnClickListener ngListener){

            dialog.setPositiveButton(okButton, okListener);

            dialog.setNegativeButton(ngButton, ngListener);

            dialog.show();

        }

        public void createTextDialog(EditText edittext,String button, DialogInterface.OnClickListener listener){

            this.edittext = edittext;

            dialog.setView(this.edittext);

            dialog.setPositiveButton(button, listener);

            dialog.show();


        }

        public void createSingleSelectDialog(String items[],int checkedItem,DialogInterface.OnClickListener choiceListener, String button, DialogInterface.OnClickListener okListener){

            dialog.setSingleChoiceItems(items, checkedItem, choiceListener);

            dialog.setPositiveButton(button, okListener);

            dialog.show();



        }


        public void createDatePickerDialog(DatePickerDialog.OnDateSetListener listener, Calendar cal){

            Context context = dialog.getContext();

            dateDialog =  new DatePickerDialog(context, listener,  cal.get(Calendar.YEAR) ,cal.get(Calendar.MONTH) ,cal.get(Calendar.DAY_OF_MONTH));

            dateDialog.show();
        }

        public void createTimePickerDialog(TimePickerDialog.OnTimeSetListener timeListener,int hourOfDay, int minute, boolean is24HourView){

            Context context = dialog.getContext();

            timePickerDialog = new TimePickerDialog(context, timeListener,hourOfDay, minute, is24HourView);

            timePickerDialog.show();
        }
/*
    public void setPositiveButtonListener(String button, DialogInterface.OnClickListener listener){

        dialog.setPositiveButton(button, listener);

    }
*/

        public void createProgressDialog(int progressStyle, int max, boolean cancelable, DialogInterface.OnCancelListener cancelListener){
            Context context = dialog.getContext();
            progressDialog = new ProgressDialog(context);


            progressDialog.setIndeterminate(true);
            progressDialog.setProgressStyle(progressStyle);
            progressDialog.setMax(max);
            progressDialog.setCancelable(cancelable);
            progressDialog.setOnCancelListener(cancelListener);

            progressDialog.show();

        }

        public void setProgressToProgressDialog(int value){
            progressDialog.setProgress(value);
        }





}
