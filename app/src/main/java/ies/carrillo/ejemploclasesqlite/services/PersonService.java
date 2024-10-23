package ies.carrillo.ejemploclasesqlite.services;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ies.carrillo.ejemploclasesqlite.daos.PersonDAO;
import ies.carrillo.ejemploclasesqlite.database.DatabaseHelper;
import ies.carrillo.ejemploclasesqlite.models.Person;

public class PersonService implements PersonDAO {

    //global variable for inserts
    private long insertResult;
    private List<Person> people;
    private List<Person> peopleOlder;
    private Person person;

    //DAO
    private PersonDAO personDAO;

    //constructor


    public PersonService(Application application) {
        //Instanciamos la bd
        DatabaseHelper db = DatabaseHelper.getInstance(application.getApplicationContext());
        //Instanciamos el DAO
        personDAO = db.personDAO();
    }

    //implemented methods
    @Override
    public long insert(Person person) {
        Thread thread = new Thread(() -> {
            personDAO.insert(person);
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            Log.e("PersonService insertPerson", e.toString());
        }
        return insertResult;
    }

    @Override
    public void insertPeople(List<Person> people) {
        Thread thread = new Thread(() -> {
            personDAO.insertPeople(people);
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            Log.e("PersonService insertPeople", e.toString());
        }
    }

    @Override
    public void update(Person person) {
        Thread thread = new Thread(() -> {
            personDAO.update(person);
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            Log.e("PersonService updatePerson", e.toString());
        }
    }

    @Override
    public void delete(Person person) {
        Thread thread = new Thread(() -> {
            personDAO.delete(person);
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            Log.e("PersonService deletePerson", e.toString());
        }
    }

    @Override
    public void deleteById(long id) {
        Thread thread = new Thread(() -> {
            personDAO.deleteById(id);
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            Log.e("PersonService deletePersonById", e.toString());
        }
    }

    @Override
    public List<Person> getAll() {

        Thread thread = new Thread(() -> {
            people = personDAO.getAll().isEmpty() ? new ArrayList<>() : personDAO.getAll();
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            Log.e("PersonService getAll", e.toString());
        }
        return people;
    }

    @Override
    public Person getById(long id) {
        Thread thread = new Thread(() -> {
            person = personDAO.getById(id);
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            Log.e("PersonService getById", e.toString());
        }
        return person;
    }

    @Override
    public List<Person> getAdults() {
        Thread thread = new Thread(() -> {
            peopleOlder = personDAO.getAdults().isEmpty() ? new ArrayList<>() : personDAO.getAdults();
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            Log.e("PersonService getAdults", e.toString());
        }
        return peopleOlder;
    }
}
