package ies.carrillo.ejemploclasesqlite.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;

public abstract class DatabaseHelper extends RoomDatabase {

    //Singleton
    private static DatabaseHelper instance;

    public static DatabaseHelper getInstance(Context context) {
        //Si no se ha creado la bd, se crea
        if (instance == null) {
            instance = Room.databaseBuilder(context, DatabaseHelper.class, "ContactBook").fallbackToDestructiveMigration(true).build();
        }
        return instance;
    }
}
