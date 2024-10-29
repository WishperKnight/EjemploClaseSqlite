package ies.carrillo.ejemploclasesqlite.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

        personService = new PersonService(getApplication());
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

        btnAddPersons.setOnClickListener(v->{
            Random r = new Random();
            Integer randomValue = r.nextInt(100);
            Person person = new Person();
            person.setName("Jose " + randomValue);
            person.setAge(randomValue);
            person.setSurname("Perez " + randomValue);
            person.setEmail("email" + randomValue + "@gmail.com");

            long idPersonSaved = personService.insert(person);
            Log.i("Id person added: ", person.getId() + "");
            Toast.makeText(getApplicationContext(), "Person saved with id: " + idPersonSaved, Toast.LENGTH_SHORT).show();
            refreshList();
        });

    }

    void refreshList() {
        adapter = new PersonAdapter(getApplicationContext(), 0, personService.getAll());
        lvPersons.setAdapter(adapter);
    }
}