package ies.carrillo.ejemploclasesqlite.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

import ies.carrillo.ejemploclasesqlite.R;
import ies.carrillo.ejemploclasesqlite.adapters.PersonAdapter;
import ies.carrillo.ejemploclasesqlite.models.Person;
import ies.carrillo.ejemploclasesqlite.services.PersonService;

public class MainActivity extends AppCompatActivity {
    private PersonService personService;
    private ListView lvPersons;
    private Button btnAddPersons;
    private PersonAdapter adapter;

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
        loadingComponents();
    }

    void loadingComponents() {
        personService = new PersonService(getApplication());
        lvPersons = findViewById(R.id.lvPersons);
        btnAddPersons = findViewById(R.id.btnAddPersons);
        adapter = new PersonAdapter(getApplicationContext(), 0, personService.getAll());
        lvPersons.setAdapter(adapter);
        btnAddPersons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                Integer randomValue = r.nextInt(100);
                Person person = new Person();
                person.setName("Jose " + String.valueOf(randomValue));
                person.setAge(randomValue);
                person.setSurname("Perez " + String.valueOf(randomValue));
                person.setEmail("email" + String.valueOf(randomValue) + "@gmail.com");
                personService.insert(person);

                long idPersonSaved = personService.insert(person);
                Toast.makeText(getApplicationContext(), "Person saved with id: " + idPersonSaved, Toast.LENGTH_SHORT).show();
                refreshList();
            }
        });

    }

    void refreshList() {
        adapter = new PersonAdapter(getApplicationContext(), 0, personService.getAll());
        lvPersons.setAdapter(adapter);
    }
}