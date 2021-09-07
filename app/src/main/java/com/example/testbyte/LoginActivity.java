package com.example.testbyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.RenderScript;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout lyUsername;
    private TextInputLayout lyPassword;
    private TextInputEditText username;
    private TextInputEditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // iniciando la interfaz
        System.out.println("+++++++++++++++++++++++++++ initUI");
        initUI();
    }

    private void initUI(){
        lyUsername = findViewById(R.id.lyUsername);
        lyPassword = findViewById(R.id.lyPassword);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        MaterialButton btnIngresar = findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(view -> doLogin());
    }

    private boolean validInputs(){
        System.out.println("+++++++++++++++++++++++++++ doLogin");
        // usuario y password son requeridos
        System.out.println("+++++++++++++++++++++++++++ doLogin username"+username.getText());
        if (username.getText() != null && password.getText() != null){

            if (username.getText().length() == 0 && password.getText().length() == 0){
                lyUsername.setError("Usuario es requerido");
                lyPassword.setError("password es requerido");
                return false;
            }

            if (username.getText().length() == 0){
                lyUsername.setError("Usuario es requerido");
                return false;
            }

            if (password.getText().length() == 0){
                lyPassword.setError("password es requerido");
                return false;
            }

            if (username.getText().length() < 8 ){
                lyUsername.setError("El usuario no debe ser menor de 8 caracteres");
                return false;
            }

            if (password.getText().length() < 6) {
                lyPassword.setError("El password no debe ser menor de 6 caracteres");
                return false;
            }

        } else {
            lyUsername.setError("Usuario es requerido");
            lyPassword.setError("password es requerido");
            return false;
        }
        return true;
    }

    public void doLogin(){
        lyUsername.setError(null);
        lyPassword.setError(null);
        // si se cumplen las condiciones ir a statementActivity
        if (validInputs()){
            Intent intent = new Intent(this,StatementActivity.class);
            intent.putExtra("user",username.getText());
            startActivity(intent);
            //finish();
        }

    }

}