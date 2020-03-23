package com.example.watchusersandpostsjava.views;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.watchusersandpostsjava.R;

/**
 * Main container for all application fragments since it's single activity application
 */
public class MainContainerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container);
    }
}
