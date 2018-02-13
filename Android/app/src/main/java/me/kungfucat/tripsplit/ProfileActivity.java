package me.kungfucat.tripsplit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class ProfileActivity extends AppCompatActivity {

    private EditText  name_text;
    private EditText phone_text;
    private String name;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
         name_text= findViewById(R.id.profile_name);
         phone_text = findViewById(R.id.profile_phone);

         name = name_text.getText().toString();
         phone = phone_text.getText().toString();



    }
}
