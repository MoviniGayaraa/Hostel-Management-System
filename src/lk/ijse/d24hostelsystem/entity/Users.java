package lk.ijse.d24hostelsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Users {
    @Id
    @Column(length = 10,name = "userId")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    public Users() {
    }

    public Users(String id, String uName, String password, String contact) {
        this.id = id;
        this.userName = uName;
        this.password = password;
        this.email = contact;

    }

    public Users(String id, String name, String userName, String password, String email) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.email = email;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String uName) {
        this.userName = uName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String contact) {
        this.email = contact;
    }


    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", uName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", contact='" + email + '\'' +
                '}';
    }
}
