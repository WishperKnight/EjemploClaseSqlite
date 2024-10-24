package ies.carrillo.ejemploclasesqlite.activities;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import ies.carrillo.ejemploclasesqlite.R;
import ies.carrillo.ejemploclasesqlite.adapters.CarAdapter;
import ies.carrillo.ejemploclasesqlite.models.Car;
import ies.carrillo.ejemploclasesqlite.models.Person;
import ies.carrillo.ejemploclasesqlite.services.CarService;

public class DetailsPersonActivity extends AppCompatActivity {
    private CarService carService;
    private Person person;
    private ListView lvCars;
    private List<Car> cars;
    private CarAdapter adapter;
    private TextView tvPersonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details_person);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        carService = new CarService(getApplication());
        person = (Person) getIntent().getSerializableExtra("person");

        tvPersonId = findViewById(R.id.tvPersonId);
        tvPersonId.setText("ID: " + person.getId() + "-NAME: " + person.getName() + "-SURNAME: " + person.getSurname());

        lvCars = findViewById(R.id.lvCars);
        cars = carService.getCarsByPersonId(person.getId());
        adapter = new CarAdapter(getApplicationContext(), 0, cars);
        lvCars.setAdapter(adapter);


    }
}