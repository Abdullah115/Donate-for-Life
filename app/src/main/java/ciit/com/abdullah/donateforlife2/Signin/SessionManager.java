package ciit.com.abdullah.donateforlife2.Signin;

/**
 * Created by Abdullah on 3/9/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences pref;
    Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_email = "andullah.javed41@yahoo.com";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_email = "email";
    public static final String KEY_PASS = "pass";
    public static final String KEY_firstName = "FirstName";
    public static final String KEY_lastName = "LastName";
    public static final String KEY_ContactNo = "ContactNo";
    public static final String KEY_Address = "Address";
    public static final String KEY_Image = "Image";


    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_email, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void storeAllInfo(String firstName,String lastName,String contactno, String address,String pimage){
        editor.putString(KEY_firstName,firstName);
        editor.putString(KEY_lastName,lastName);
        editor.putString(KEY_ContactNo,contactno);
        editor.putString(KEY_Address,address);
        editor.putString(KEY_Image,pimage);
        editor.commit();
    }
    public HashMap<String, String> getAllInfo(){
        HashMap<String, String> userinfo = new HashMap<String, String>();
        userinfo.put(KEY_firstName, pref.getString("FirstName", ""));
        userinfo.put(KEY_lastName, pref.getString("LastName", ""));
        userinfo.put(KEY_ContactNo, pref.getString("ContactNo", ""));
        userinfo.put(KEY_Address, pref.getString("Address", ""));
        userinfo.put(KEY_Image,pref.getString("Image",""));
        return userinfo;
    }


    /**
     * Create login session
     * */
    public void createLoginSession(String email, String pass){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing email in pref
        editor.putString(KEY_email, email);

        // Storing password in pref
        editor.putString(KEY_PASS, pass);

        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, signin.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }
    }



    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user email
        user.put(KEY_email, pref.getString(KEY_email, "abc@example.com"));

        // user password
        user.put(KEY_PASS, pref.getString(KEY_PASS, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, signin.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}