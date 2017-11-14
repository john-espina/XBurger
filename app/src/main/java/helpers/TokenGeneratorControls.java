package helpers;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stlaumade on 14/11/2017.
 */

public class TokenGeneratorControls {

    static List<String> tokenStrings = new ArrayList<String> ();

    static{
        tokenStrings.add("Fox");
        tokenStrings.add("Pig");
        tokenStrings.add("Dog");
        tokenStrings.add("Cat");
        tokenStrings.add("Chicken");
        tokenStrings.add("Rhino");
        tokenStrings.add("Hippo");
        tokenStrings.add("Monkey");
        tokenStrings.add("Goat");
        tokenStrings.add("Crow");
        tokenStrings.add("Pigeon");
        tokenStrings.add("Seahorse");
    }

    public static String generateToken(){
        String token = "";
        int numberStrings = (int)(2+Math.random()*4);
        String randomString;
        int randomNumber;

        for(int i = 0; i < numberStrings; i++){
            randomString = tokenStrings.get((int)(1+Math.random() * tokenStrings.size()-1));
            token+= randomString;

            randomNumber = (int)(1+Math.random()*100);
            token+= randomNumber;
        }

        Log.d ("Generate Token Method", token);
        return token;
    }
}

