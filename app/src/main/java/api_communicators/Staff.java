package api_communicators;

import java.io.Serializable;

/**
 * Created by stlaumade on 7/11/2017.
 */
public class Staff implements Serializable{

    int staff_id;
    String staff_type;
    String username;
    int iterations;
    String salt;
    String passHash;

    public Staff(int staff_id, String staff_type, String username, int iterations, String salt, String passHash) {
        this.staff_id = staff_id;
        this.staff_type = staff_type;
        this.username = username;
        this.iterations = iterations;
        this.salt = salt;
        this.passHash = passHash;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public String getStaff_type() {
        return staff_type;
    }

    public String getUsername() {
        return username;
    }

    public int getIterations() {
        return iterations;
    }

    public String getSalt() {
        return salt;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public void setStaff_type(String staff_type) {
        this.staff_type = staff_type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }
}
