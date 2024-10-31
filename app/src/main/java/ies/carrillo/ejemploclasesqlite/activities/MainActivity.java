package ies.carrillo.ejemploclasesqlite.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Random;

import ies.carrillo.ejemploclasesqlite.R;
import ies.carrillo.ejemploclasesqlite.adapters.PersonAdapter;
import ies.carrillo.ejemploclasesqlite.models.Person;
import ies.carrillo.ejemploclasesqlite.models.UserAccount;
import ies.carrillo.ejemploclasesqlite.services.CarService;
import ies.carrillo.ejemploclasesqlite.services.PersonService;
import ies.carrillo.ejemploclasesqlite.services.UserAccountService;

public class MainActivity extends AppCompatActivity {
    private UserAccountService userAccountService;
    private PersonService personService;
    private CarService carService;
    private ListView lvPersons;
    private Button btnAddPersons;
    private PersonAdapter adapter;
    private EditText etCheckpswd;
    private Button btnValidate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Services iniziliced
        userAccountService = new UserAccountService(getApplication());
        personService = new PersonService(getApplication());
        carService = new CarService(getApplication());

        lvPersons = findViewById(R.id.lvPersons);
        btnAddPersons = findViewById(R.id.btnAddPersons);
        adapter = new PersonAdapter(getApplicationContext(), 0, personService.getAll());
        lvPersons.setAdapter(adapter);

        lvPersons.setOnItemClickListener((parent, view, position, id) -> {
            Person person = (Person) lvPersons.getItemAtPosition(position);
            Intent intent = new Intent(getApplicationContext(), DetailsPersonActivity.class);
            intent.putExtra("person", person);
            startActivity(intent);
        });

        btnAddPersons.setOnClickListener(v -> {
            Random r = new Random();
            Integer randomValue = r.nextInt(100);
            UserAccount userAccount = new UserAccount();
            userAccount.setUsername("user" + randomValue);
            String pswdHashed = "Usuario" + randomValue + "?";
            pswdHashed = BCrypt.hashpw(pswdHashed, BCrypt.gensalt(5));
            userAccount.setUserPassword(pswdHashed);
            Log.i("Crypt password: ", pswdHashed + "");

            long idUserSaved = userAccountService.insert(userAccount);
            Log.i("Id user added: ", userAccount.getUserId() + "");

            Person person = new Person();
            person.setUserAccountID(idUserSaved);
            person.setName("Jose " + randomValue);
            person.setAge(randomValue);
            person.setSurname("Perez ");
            person.setEmail("email" + randomValue + "@gmail.com");

            long idPersonSaved = personService.insert(person);
            Log.i("Id person added: ", person.getId() + "");
            Toast.makeText(getApplicationContext(), "Person saved with id: " + idPersonSaved, Toast.LENGTH_SHORT).show();
            refreshList();
        });

        etCheckpswd = findViewById(R.id.etCheckpswd);
        SharedPreferences sharedPreferences = getSharedPreferences("SESSION", MODE_PRIVATE);
        etCheckpswd.setText(sharedPreferences.getString("userName", "Not Logged")+"-"+String.valueOf(sharedPreferences.getLong("Id", 0)));
        btnValidate = findViewById(R.id.btnValidate);

        btnValidate.setOnClickListener(v -> {
            String password = etCheckpswd.getText().toString();
            List<UserAccount> users = userAccountService.getAll();
            boolean findOne=false;

            //check by stream
            users.stream().filter(userAccount -> BCrypt.checkpw(password, userAccount.getUserPassword())).forEach(userAccount -> {
                Toast.makeText(getApplicationContext(), "User found by stream"+ userAccount.getUsername(), Toast.LENGTH_SHORT).show();
            });

            //check by for iteration
            for (UserAccount user : users) {
                if (BCrypt.checkpw(password, user.getUserPassword())) {
                    Toast.makeText(getApplicationContext(), "User found by for iteration" + user.getUsername(), Toast.LENGTH_SHORT).show();
                   findOne= true;
                    return;
                }
            }

            if(!findOne){
                Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void refreshList() {
        adapter = new PersonAdapter(getApplicationContext(), 0, personService.getAll());
        lvPersons.setAdapter(adapter);
    }
}