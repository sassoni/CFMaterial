package com.example.android.cfmaterial.settings;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.example.android.cfmaterial.R;

/**
 * Created by ravatapa on 5/26/2015.
 */
public class SettingsPreferenceActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

    }
}
