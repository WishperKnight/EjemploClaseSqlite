package ies.carrillo.ejemploclasesqlite.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ies.carrillo.ejemploclasesqlite.daos.CarDAO;
import ies.carrillo.ejemploclasesqlite.daos.PersonDAO;
import ies.carrillo.ejemploclasesqlite.models.Car;
import ies.carrillo.ejemploclasesqlite.models.Person;

@Database(entities = {Person.class, Car.class}, version = 2)
public abstract class DatabaseHelper extends RoomDatabase {

    //Persona DAO
    public abstract PersonDAO personDAO();
    //Car DAO
    public abstract CarDAO carDAO();

    //Singleton
    private static DatabaseHelper instance;

    //synchronized es para que solo se pueda crear una instancia de la bd
    public static synchronized DatabaseHelper getInstance(Context context) {
        //Si no se ha creado la bd, se crea
        if (instance == null) {
            instance = Room.databaseBuilder(context, DatabaseHelper.class, "ContactBook").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
