package com.joshua.r0th.crud2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }
    public void goMain(View view){
        Intent goMain = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(goMain);

    }
    public void goDaftar(View view){
        setContentView(R.layout.daftar_activity);

    }
    public void goLogin(View view){
        setContentView(R.layout.login_activity);

    }
    public void goForgot(View view){
        setContentView(R.layout.forgot_password);

    }
}
