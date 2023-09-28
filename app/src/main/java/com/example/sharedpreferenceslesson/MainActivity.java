package com.example.sharedpreferenceslesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText name, age, food;
    ConstraintLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.editTextText);
        age = findViewById(R.id.editTextText2);
        food = findViewById(R.id.editTextText3);

        mainLayout = findViewById(R.id.mainLayout);
    }

    protected void saveInfo(View v){
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        // write all the data entered by the user in SharedPreference and apply
        myEdit.putString("name", name.getText().toString());
        myEdit.putInt("age", Integer.parseInt(age.getText().toString()));
        myEdit.putString("food", food.getText().toString());
        myEdit.apply();

        Snackbar snackbar = Snackbar.make(v, "Info saved", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    protected void clearInfo(){
        name.setText("");
        age.setText("");
        food.setText("");
    }

    protected void refreshInfo(View v) {
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s1 = sh.getString("name", "");
        int a = sh.getInt("age", 0);
        String s2 = sh.getString("food", "");

        name.setText(s1);
        age.setText(String.valueOf(a));
        food.setText(s2);

        Snackbar snackbar = Snackbar.make(v, "Info retrieved", Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}