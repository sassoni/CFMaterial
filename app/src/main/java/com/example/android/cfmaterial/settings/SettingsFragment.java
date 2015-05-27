package com.example.android.cfmaterial.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.example.android.cfmaterial.R;

/**
 * Created by ravatapa on 5/26/2015.
 */
public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
        getPreferenceManager().findPreference(getString(R.string.pref_key_phone)).setSummary("6165166730");

        Preference genderPreference = getPreferenceManager().findPreference(getString(R.string.pref_key_gender));
        Preference emailPreference = getPreferenceManager().findPreference(getString(R.string.pref_key_email));

        SharedPreferences sharedPreferences = getPreferenceManager().getDefaultSharedPreferences(getActivity());
        genderPreference.setSummary(sharedPreferences.getString(getString(R.string.pref_key_gender), ""));
        emailPreference.setSummary(sharedPreferences.getString(getString(R.string.pref_key_email), "catmktgdev@gmail.com"));

    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.pref_key_gender))){
            Preference genderPreference = findPreference(key);
            genderPreference.setSummary(sharedPreferences.getString(key,""));
        }

        if (key.equals(getString(R.string.pref_key_email))){
            Preference genderPreference = findPreference(key);
            genderPreference.setSummary(sharedPreferences.getString(key,""));
        }

    }
}
