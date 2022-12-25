package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table
public class UserModel {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
//    public static ArrayList<User> usersData = new ArrayList<User>();
    private   String username;
    @Column
    private String email;
    @Column
    private String password;

    public UserModel() {
    }

    public UserModel(String username, String email, String password)
    {
        // This keyword refers to parent instance itself
        setUsername(username);
        setEmail(email);
        setPassword(password);

    }
    public UserModel(int id, String username, String email, String password)
    {

        setId(id);
        setUsername(username);
        setEmail(email);
        setPassword(password);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
