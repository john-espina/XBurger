package passwords;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;

import com.example.espinajohn.xburger.R;

import static android.graphics.Color.RED;

/**
 * Created by stlaumade on 14/11/2017.
 */

public class PasswordStrengthChecker {


    public int pwordStrength(String password){

        int pwordstrength = 0;
        boolean hasSpecial   = !password.matches("[A-Za-z0-9 ]*");
        boolean isAtLeast8   = password.length() >= 8;
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasLowercase = !password.equals(password.toUpperCase());
        boolean hasNumber  = password.matches("[0-9]*");
        boolean hasCharacter = password.matches ("[a-zA-Z]");

        if (isAtLeast8){
            pwordstrength++;
        }

        if (hasLowercase) {
            pwordstrength++;
        }

        if (hasUppercase) {
            pwordstrength++;
        }

        if (hasSpecial) {
            pwordstrength++;
        }

        if (password.length() > 11) {
            pwordstrength++;
        }

        if (password.length() > 13) {
            pwordstrength++;
        }

        if(hasNumber){
            pwordstrength++;
        }

        if(hasCharacter){
            pwordstrength++;
        }

        return pwordstrength;
    }

}
