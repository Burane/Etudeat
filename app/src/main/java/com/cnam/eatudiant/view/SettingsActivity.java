package com.cnam.eatudiant.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import butterknife.BindView;
import com.cnam.eatudiant.R;
import com.cnam.eatudiant.utils.SessionManager;

public class SettingsActivity extends AppCompatActivity {
    // @BindView(R.id.toolbar)
    // Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }

        // setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("Settings");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            findPreference("logout")
                    .setOnPreferenceClickListener(
                            preference -> {
                                this.logoutUser();
                                return true;
                            });
        }

        private void logoutUser(){
            SessionManager sessionManager = new SessionManager(this.requireContext());
            sessionManager.removeAuthToken();
            startActivity(new Intent(this.getActivity(), LoginActivity.class));
        }

        @Override
        public void onResume() {
            super.onResume();

            getPreferenceScreen().getSharedPreferences()
                    .registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            super.onPause();

            getPreferenceScreen().getSharedPreferences()
                    .unregisterOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            // TODO handle pref change
            if (key.equals("logout")) {
                Log.i("eatudiant_debug", "logout pref clicked");
                // Preference connectionPref = findPreference(key);
                // Set summary to be the user-description for the selected value
                // connectionPref.setSummary(sharedPreferences.getString(key, ""));
                this.logoutUser();
            }
        }
    }
}
