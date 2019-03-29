package ciit.com.abdullah.donateforlife2.Signin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import ciit.com.abdullah.donateforlife2.Home;
import ciit.com.abdullah.donateforlife2.R;

public class signin extends AppCompatActivity {

    private static final String LOGIN_URL = "https://donate-for-life.000webhostapp.com/php/login.php";

    private Button signup,signin;
    private boolean sign_in_check;
    private EditText email,password;
    private String uemail,upassword;
    public static final String KEY_EMAIL="email";
    public static final String KEY_PASSWORD="password";
    public static final String LOGIN_SUCCESS="success";
    public static final String SHARED_PREF_NAME="tech";
    public static final String EMAIL_SHARED_PREF="email";
    public static final String LOGGEDIN_SHARED_PREF="loggedin";
    private boolean loggedIn = false;


    SessionManager sessionManager;
    GetCurrentUserDetail currUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        currUser=new GetCurrentUserDetail(getApplicationContext());
        ActionBar actionBar = getSupportActionBar();
        if(null != actionBar){
            actionBar.hide();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        sessionManager=new SessionManager(getApplicationContext());
        //sessionManager.checkLogin();

        //Initialize and set Click Listener on inputs
        email=(EditText) findViewById(R.id.signin_email);
        email.setText(getIntent().getStringExtra("email"));
        password=(EditText) findViewById(R.id.signin_password);
        password.setText(getIntent().getStringExtra("password"));
        signup=(Button)findViewById(R.id.signin_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });
        signin=(Button)findViewById(R.id.signin_login);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin();
            }
        });
    }

    //Not Registered Yet
    private void signup() {
        Intent signupIntent=new Intent(signin.this, ciit.com.abdullah.donateforlife2.Registration.signup.class);
        startActivity(signupIntent);
        finish();
    }

    //Sign in
    private void signin(){
        uemail=email.getText().toString().trim();
        upassword=password.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equalsIgnoreCase(LOGIN_SUCCESS)) {
                    SharedPreferences sharedPreferences = signin.this.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(LOGGEDIN_SHARED_PREF, true);
                    editor.putString(EMAIL_SHARED_PREF, uemail);
                    editor.commit();
                    sessionManager.createLoginSession(uemail,upassword);
                    currUser.getCurrentUserDetails();
                    String fname=sessionManager.getAllInfo().get("FirstName");
                    String lname=sessionManager.getAllInfo().get("LastName");
                    Intent intent = new Intent(signin.this, Home.class);
                    intent.putExtra("name", fname+" " +lname);
                    Toast.makeText(signin.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(signin.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(signin.this,"Internet Connection Error",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams()throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put(KEY_EMAIL,uemail);
                params.put(KEY_PASSWORD,upassword);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void onBackPressed(){
        Intent intent=new Intent(signin.this,Home.class);
        startActivity(intent);
        finish();
    }
}