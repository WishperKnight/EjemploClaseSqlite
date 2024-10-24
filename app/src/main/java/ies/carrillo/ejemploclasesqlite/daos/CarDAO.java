package ies.carrillo.ejemploclasesqlite.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ies.carrillo.ejemploclasesqlite.models.Car;

@Dao
public interface CarDAO {
    @Insert
    long insert(Car car);

    @Insert
    void insertCars(List<Car> cars);

    @Update
    void update(Car car);

    @Delete
    void delete(Car car);

    @Query("DELETE FROM Car WHERE id = :id")
    void deleteById(long id);

    @Query("SELECT * FROM Car")
    List<Car> getAll();

    @Query("SELECT * FROM Car c WHERE c.id = :id")
    Car getById(long id);

    @Query("SELECT * FROM Car c WHERE c.person_id = :personId")
    List<Car> getCarsByPersonId(long personId);

    @Query("SELECT * FROM CAR c Where c.plate = :plate ")
    List<Car> getCarsByPlate(String plate);
}
