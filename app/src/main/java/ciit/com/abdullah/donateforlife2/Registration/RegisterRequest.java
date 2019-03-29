package ciit.com.abdullah.donateforlife2.Registration;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Abdullah on 4/24/2018.
 */

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_URL = "https://donate-for-life.000webhostapp.com/php/Register.php";
    private Map<String, String> parameters;
    public RegisterRequest(String fname, String lname, String email,String pass,String phone, String address, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("FirstName", fname);
        parameters.put("LastName", lname);
        parameters.put("Email", email);
        parameters.put("Password", pass);
        parameters.put("ContactNo", phone);
        parameters.put("Address", address);
        /*parameters.put("UserImage",profile);
        Log.i("pk",profile)*/;
    }
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}
