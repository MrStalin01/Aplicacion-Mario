package com.example.mrstalin01;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextInputLayout registerTILuserName = findViewById(R.id.registerTILuserName);
        TextInputLayout registerTILemail = findViewById(R.id.registerTILemail);
        TextInputLayout registerTILpassword = findViewById(R.id.registerTILpassword);
        TextInputLayout registerTILpasswordDoubleChek = findViewById(R.id.registerTILpassword2);
        TextView registerLogin = findViewById(R.id.loginTVRegister);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();

        registerLogin.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            }
        );

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                boolean canContinue = true;
                if (isUserNameEmpty(registerTILuserName)){
                    registerTILuserName.setErrorEnabled(true);
                    registerTILuserName.setError("nombre vacío");
                    canContinue = false;

                }
                if (!isEmailCorrect(registerTILemail)){
                    registerTILemail.setErrorEnabled(true);
                    registerTILemail.setError("Tu email esta mal escrito");
                    canContinue = false;
                }
                if (!arePasswordsTheSame(registerTILpassword, registerTILpasswordDoubleChek))  {
                    registerTILpasswordDoubleChek.setErrorEnabled(true);
                    registerTILpasswordDoubleChek.setError("La contraseña no es válida");
                    if (isRegisterPasswordEmpty(registerTILpassword) && isRegisterPasswordEmpty(registerTILpasswordDoubleChek)) {
                        Toast.makeText(Register.this, "No has escrito nada en el campo constraseña", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Register.this, "Las dos contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
                    canContinue = false;
                }
                if (canContinue) {

                    String userName = getTextFromTIL(registerTILuserName);
                    String email = getTextFromTIL(registerTILemail);
                    String password = getTextFromTIL(registerTILpassword);

                    editor.putString("userName", userName);
                    editor.putString("email", email);
                    editor.putString("password", hashPassword(password));
                    editor.apply();
                    Intent intent = new Intent(Register.this, MainActivity.class);
                    intent.putExtra("nombre", userName);
                    startActivity(intent);
                }
            }

        });

    }

    private String getTextFromTIL(TextInputLayout til) {
        if (til.getEditText() != null) {
            return til.getEditText().getText().toString().trim();
        }
        return "";
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception e) {
            return password; // fallback
        }
    }

    public boolean isEmailCorrect(TextInputLayout emailTIL) {
        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern p = Pattern.compile(emailPattern);
        String email = String.valueOf(emailTIL.getEditText().getText());
        Matcher m = p.matcher(email);
        return m.find();
    }

    public boolean isUserNameEmpty(TextInputLayout userNameTIL) {
        String username = String.valueOf(userNameTIL.getEditText().getText());
        return username.isEmpty();
    }
    public boolean isRegisterPasswordEmpty(TextInputLayout userNameTIL) {
        String username = String.valueOf(userNameTIL.getEditText().getText());
        return username.isEmpty();
    }
    public boolean arePasswordsTheSame(TextInputLayout textInputLayout, TextInputLayout textInputLayoutCheck) {
        String firstPassword = String.valueOf(textInputLayout.getEditText().getText());
        String secondPassword = String.valueOf(textInputLayoutCheck.getEditText().getText());
        return firstPassword.equals(secondPassword) && !firstPassword.isEmpty();
    }
}
