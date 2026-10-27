
package com.example.mycvapp;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Patterns;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edtEmail = findViewById(R.id.edtEmail);
        Button btnContact = findViewById(R.id.btnContact);
        Button btnShare = findViewById(R.id.btnShare);

        btnContact.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();

            if (email.isEmpty()) {
                edtEmail.setError("Enter an email address");
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                edtEmail.setError("Enter a valid email");
                return;
            }

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:" + Uri.encode(email)));
            intent.putExtra(Intent.EXTRA_SUBJECT, "MyCV App - Contact");
            intent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Hello, I would like to get in touch."
            );

            try {
                startActivity(intent);
            } catch (android.content.ActivityNotFoundException e) {
                Toast.makeText(
                        this,
                        "No email app found",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        btnShare.setOnClickListener(v -> {
            String cv = "MY CV\n\n"
                    + "Name: Your Name\n"
                    + "Career: Student / Professional\n\n"
                    + "About Me:\n"
                    + "I am motivated and eager to learn.\n\n"
                    + "Education:\n"
                    + "Your Institution and Course\n\n"
                    + "Skills:\n"
                    + "Communication, Teamwork, Technology\n";

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My CV");
            shareIntent.putExtra(Intent.EXTRA_TEXT, cv);

            startActivity(Intent.createChooser(
                    shareIntent, "Share My CV"
            ));
        });
    }
}
