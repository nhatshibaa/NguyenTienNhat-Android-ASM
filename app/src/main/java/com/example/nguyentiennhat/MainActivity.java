package com.example.nguyentiennhat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edName, edMail, edDes;
    Spinner spinner;
    CheckBox checkBox;
    Button btnSend;
    String gender = "Gripe";
    AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = AppDatabase.getAppDatabase(this);
        checkBox = findViewById(R.id.ck);
        edName = findViewById(R.id.etName);
        edMail = findViewById(R.id.etMail);
        edDes = findViewById(R.id.etDes);
        spinner = findViewById(R.id.spinner);
        btnSend = findViewById(R.id.btnSubmit);
        btnSend.setOnClickListener(this);

        String[] genders = {"Gripe", "Blabla", "Bloblo", "Haahaha", "hohoho"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genders);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gender = genders[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void onSend() {
        if (!validateField()) {
            return;
        }
        User user = new User();
        user.fullName = edName.getText().toString();
        user.email = edMail.getText().toString();
        user.description = edDes.getText().toString();
        user.gender = gender;
        long id = database.userDAO().insertUser(user);
        if (id > 0) {
            Toast.makeText(this, "Send feedback success " + database.userDAO().getAllUser().size() + " records", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateField() {
        String msg = "";
        if (edName.getText().toString().trim().isEmpty()) {
            msg = "Pls enter full name !";
        } else if (edMail.getText().toString().trim().isEmpty()) {
            msg = "Pls enter email !";
        } else if (edDes.getText().toString().trim().isEmpty()) {
            msg = "Pls enter description !";
        }
        if (!msg.equals("")) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSubmit:
                onSend();
                break;
            default:
                break;
        }
    }
}