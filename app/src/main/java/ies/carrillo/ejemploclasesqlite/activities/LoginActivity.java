package ies.carrillo.ejemploclasesqlite.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

import ies.carrillo.ejemploclasesqlite.R;
import ies.carrillo.ejemploclasesqlite.models.UserAccount;
import ies.carrillo.ejemploclasesqlite.services.UserAccountService;

public class LoginActivity extends AppCompatActivity {
    private EditText etUserName;
    private EditText etPassword;
    private Button btnLogin;
    private UserAccountService accountService;
    private UserAccount user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        loadComponents();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void loadComponents() {
        accountService = new UserAccountService(getApplication());
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String username = etUserName.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            user = accountService.getByUsername(username);
            if (user != null&&BCrypt.checkpw(password,user.getUserPassword())) {
                Intent logged = new Intent(LoginActivity.this, MainActivity.class);
                SharedPreferences sharedPreferences = getSharedPreferences("SESSION", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userName", user.getUsername());
                editor.putLong("Id", user.getUserId());
                editor.apply();
                startActivity(logged);
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
