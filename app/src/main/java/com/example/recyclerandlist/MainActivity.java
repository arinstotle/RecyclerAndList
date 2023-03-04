package com.example.recyclerandlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.recyclerandlist.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = ActivityMainBinding.inflate(
                getLayoutInflater()
        );
        setContentView(binding.getRoot());
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.main_layout,
                            MainFragment.class, null)
                    .commit();
        }
    }
}