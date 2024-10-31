package ies.carrillo.ejemploclasesqlite.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "person", foreignKeys = {@ForeignKey(entity = UserAccount.class, parentColumns = "userId", childColumns = "user_account_id", onDelete = ForeignKey.CASCADE)})
public class Person implements Serializable  {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "user_account_id")
    @NonNull
    private long userAccountID;

    @Embedded
    private Address address;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "surname")
    private String surname;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "age")
    private Integer age;

    /**
     * Constructor without params
     */
    public Person() {
    }

    //Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public long getUserAccountID() {
        return userAccountID;
    }

    public void setUserAccountID(long userAccountID) {
        this.userAccountID = userAccountID;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", address=" + address.toString() +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", userAccountID=" + userAccountID + '\'' +
                ", age=" + age +
                '}';
    }
}