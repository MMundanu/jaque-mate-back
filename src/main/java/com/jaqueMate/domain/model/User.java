package main.java.com.jaqueMate.domain.model;

import com.jaqueMate.domain.model.UserRole;

public class User {
    private static int NEXT_ID = 1;
    private final int id;
    private String name;
    private String email;
    private String password;
    private UserRole role;

    public  User(String name, String email, String password, UserRole role){
        this.id=NEXT_ID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        NEXT_ID++;
    }
    public  User(int id, String name, String email, String password, UserRole role){
        this.id=id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }


    public int getId() {return this.id;}

    public String getName() { return this.name; }

    public String getEmail() { return  this.email; }

    public String getPassword() { return this.password; }

    public UserRole getRole() { return this.role; }

    public void  setName(String name) {
            this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.role = role;
    };

}
