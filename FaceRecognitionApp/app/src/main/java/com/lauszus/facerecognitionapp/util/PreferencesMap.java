package com.lauszus.facerecognitionapp.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesMap {

    private static SharedPreferences sharedPreferences;

    private PreferencesMap(){}

    public static SharedPreferences getSharedPreferences(Context context){
        if(sharedPreferences == null){
            sharedPreferences = getPreferences(context);
        }

        return sharedPreferences;
    }

    private static SharedPreferences getPreferences(Context context) {

        return context
                .getSharedPreferences("cgponto", Context.MODE_PRIVATE);
    }
}
