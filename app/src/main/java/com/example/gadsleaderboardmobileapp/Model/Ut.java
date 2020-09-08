package com.example.gadsleaderboardmobileapp.Model;

import android.text.format.DateUtils;
import android.util.Log;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

public final class Ut {

    private static boolean isYesterday(Date d) {
        return DateUtils.isToday(d.getTime() + DateUtils.DAY_IN_MILLIS);
    }
    public static String TAG="busi";
    public static String getTime(long aLong) {
        SimpleDateFormat formatter = null;
        Date date = new Date(aLong);
        if (DateUtils.isToday(aLong))
            formatter = new SimpleDateFormat("hh:mm:ss a");
        else if (isYesterday(date)) return "Yesterday";
        else formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

    public static String getDateAndTime(long aLong) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date(aLong);
        return formatter.format(date);
    }

    public static boolean validateEditText(int acceptedLenght, EditText... inputs) {
        boolean result = true;
        for (int i = 0; i < inputs.length; i++) {

            if (inputs[i].getText().length() < acceptedLenght) {
                result = false;
                break;
            }

        }

        return result;
    }
    public static boolean updatePrice( EditText... inputs) {
        boolean result = true;

        return result;
    }

    public static void removeTextInEditText(EditText... inputs) {
        for (int i = 0; i < inputs.length; i++) {
            inputs[i].setText("");
        }
    }

    public static String resetDouble(  double value) {

            Formatter f= new Formatter();
         return String.valueOf(f.format("%.2f" ,value));

    }

    public static boolean isEmail(String email) {

       if(email!=null){
            String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
            return email.matches(regex);
        }

        return  false;
    }
}


