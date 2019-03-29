package ciit.com.abdullah.donateforlife2.Signin;

import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Abdullah on 3/9/2018.
 */

public class GetCurrentUserDetail {
    public Context ctx;
    SessionManager sessionManager;
    JSONObject user;
    String fname,lname,email,contactno,address,pimage;


    public GetCurrentUserDetail(Context context){
        this.ctx=context;
        sessionManager=new SessionManager(ctx);
    }

   public void getCurrentUserDetails(){

       final String url="https://donate-for-life.000webhostapp.com/php/get_profile.php";
       RequestQueue queue = Volley.newRequestQueue(ctx);
       StringRequest sr = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {

               try {
                   JSONArray jsonArray = new JSONArray(response);
                   user=jsonArray.getJSONObject(0);
                   fname=user.getString("FirstName");
                   lname=user.getString("LastName");
                   email=user.getString("Email");
                   contactno=user.getString("ContactNo");
                   address=user.getString("Address");
                   pimage=user.getString("UserImage");
                   sessionManager.storeAllInfo(fname,lname,contactno,address,pimage);
               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Toast.makeText(ctx, "Internet Connection Error", Toast.LENGTH_SHORT).show();
           }
       }) {
           @Override
           protected Map<String, String> getParams() {
               Map<String, String> Params = new HashMap<String, String>();

               Params.put("Email", sessionManager.getUserDetails().get("email"));

               return Params;
           }
           @Override
           public Map<String, String> getHeaders() throws AuthFailureError {
               Map<String, String> params = new HashMap<String, String>();
               params.put("Content-Type", "application/x-www-form-urlencoded");
               return params;
           }
       };
       queue.add(sr);
   }
}
