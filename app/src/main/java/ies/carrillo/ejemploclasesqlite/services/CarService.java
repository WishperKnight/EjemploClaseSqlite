package ies.carrillo.ejemploclasesqlite.services;

import android.app.Application;
import android.util.Log;

import java.util.Collections;
import java.util.List;

import ies.carrillo.ejemploclasesqlite.daos.CarDAO;
import ies.carrillo.ejemploclasesqlite.database.DatabaseHelper;
import ies.carrillo.ejemploclasesqlite.models.Car;

public class CarService implements CarDAO {
    private CarDAO carDAO;
    private List<Car> cars;
    private long insertResult;
    private Car car;

    public CarService(Application application) {
        DatabaseHelper db = DatabaseHelper.getInstance(application.getApplicationContext());
        carDAO = db.carDAO();
    }

    @Override
    public long insert(Car car) {
        Thread thread = new Thread(() -> {
            insertResult = carDAO.insert(car);
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            Log.e("Car service Insert", e.toString());
        }
        return insertResult;
    }

    @Override
    public void insertCars(List<Car> cars) {

    }

    @Override
    public void update(Car car) {

    }

    @Override
    public void delete(Car car) {

    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public List<Car> getAll() {
        return Collections.emptyList();
    }

    @Override
    public Car getById(long id) {
        return null;
    }

    @Override
    public List<Car> getCarsByPersonId(long personId) {
        Thread thread = new Thread(() -> {
            cars = carDAO.getCarsByPersonId(personId);
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            Log.e("Car service getCarsByPersonId", e.toString());
        }
        return cars;
    }

    @Override
    public List<Car> getCarsByPlate(String plate) {
        return Collections.emptyList();
    }
}
