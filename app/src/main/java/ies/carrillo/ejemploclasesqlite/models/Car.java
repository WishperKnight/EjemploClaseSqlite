package ies.carrillo.ejemploclasesqlite.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "car", foreignKeys = {@ForeignKey(entity = Person.class, parentColumns = "id", childColumns = "person_id", onDelete = ForeignKey.CASCADE)})
public class Car implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;

    @ColumnInfo(name = "plate")
    @NonNull
    private String plate;

    @Embedded
    private Address address;

    @ColumnInfo(name = "model")
    private String model;

    @ColumnInfo(name = "person_id")
    private long personId;

    //Constructor without params
    public Car() {
    }

    //getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

       public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @NonNull
    public String getPlate() {
        return plate;
    }

    public void setPlate(@NonNull String plate) {
        this.plate = plate;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", plate='" + plate + '\'' +
                ", address=" + address +
                ", model='" + model + '\'' +
                ", personId=" + personId +
                '}';
    }
}
