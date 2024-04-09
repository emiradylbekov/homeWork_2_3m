package com.example.hw_2_3m;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextInputLayout emailInputLayout = findViewById(R.id.edittxt_email);
        final TextInputLayout subjectInputLayout = findViewById(R.id.theme);
        final TextInputLayout messageInputLayout = findViewById(R.id.message);
        Button sendEmailButton = findViewById(R.id.button);

        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Objects.requireNonNull(emailInputLayout.getEditText()).getText().toString();
                String subject = Objects.requireNonNull(subjectInputLayout.getEditText()).getText().toString();
                String message = Objects.requireNonNull(messageInputLayout.getEditText()).getText().toString();
                sendEmail(email, subject, message);
            }
        });
    }

    private void sendEmail(String email, String subject, String message) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        } catch (android.content.ActivityNotFoundException ex) {
            // Если нет почтового клиента на устройстве
            Log.e("MyApp", "Error: " + ex.getMessage());
        }
    }

}