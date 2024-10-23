package ies.carrillo.ejemploclasesqlite.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ies.carrillo.ejemploclasesqlite.models.Person;

@Dao
public interface PersonDAO {
    @Insert
    long insert(Person person);

    @Insert
    void insertPeople(List<Person> people);

    @Update
    void update(Person person);

    @Delete
    void delete(Person person);

    @Delete
    void deleteById(long id);

    @Query("SELECT * FROM Person")
    List<Person> getAll();

    @Query("SELECT * FROM Person p WHERE p.id = :id")
    Person getById(long id);

    @Query("SELECT * FROM Person p WHERE p.age >= 18")
    List<Person> getAdults();

}
