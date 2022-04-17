package ca.mcgill.ecse321.grocerystore;

import android.app.Application;

public class MyApplication extends Application {

    private String username;

    public String getSomeVariable() {
        return username;
    }

    public void setSomeVariable(String someVariable) {
        this.username = someVariable;
    }
}