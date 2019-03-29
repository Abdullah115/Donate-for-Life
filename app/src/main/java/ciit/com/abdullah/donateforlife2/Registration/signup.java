package ciit.com.abdullah.donateforlife2.Registration;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.stetho.Stetho;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import ciit.com.abdullah.donateforlife2.R;
import ciit.com.abdullah.donateforlife2.Signin.signin;

public class signup extends AppCompatActivity {

    //private static final String REGISTER_URL = "https://donate-for-life.000webhostapp.com/php/registration.php";

    private EditText et_fname, et_lname, et_pass, et_ph, et_address, et_email, et_pass2;
    String fname, lname, email, address, phone, pass, pass2,profile;
    private ImageView pimage;
    private Button regb;
    private Button alreadymember;
    RequestQueue requestQueue;
    boolean IMAGE_STATUS = false;
    Bitmap profilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /*//Full Screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Stetho.initializeWithDefaults(this);

        /*//No Action Bar
        ActionBar actionBar = getSupportActionBar();
        if(null != actionBar){
            actionBar.hide();
        }*/
        //Initialize elements
        et_fname = (EditText) findViewById(R.id.signup_fname);
        et_lname = (EditText) findViewById(R.id.signup_lname);
        et_email = (EditText) findViewById(R.id.signup_email);
        et_ph = (EditText) findViewById(R.id.signup_phone);
        et_address = (EditText) findViewById(R.id.signup_address);
        et_pass = (EditText) findViewById(R.id.signup_password);
        et_pass2 = (EditText) findViewById(R.id.signup_password2);
        regb = (Button) findViewById(R.id.signup_createaccount);
        alreadymember = (Button) findViewById(R.id.signup_member);
        pimage=(ImageView)findViewById(R.id.pimage) ;

        //creating request queue
        requestQueue = Volley.newRequestQueue(signup.this);

        //Adding onClickListener to the ImageView to select the profile Picture
       /* pimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image*//*");
                startActivityForResult(intent, 1000);
                //result will be available in onActivityResult which is overridden
            }
        });*/

        //Apply Click Listener
        alreadymember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        regb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    //Already Member
    private void login() {
        Intent signin=new Intent(signup.this, ciit.com.abdullah.donateforlife2.Signin.signin.class);
        startActivity(signin);
        finish();
    }

    //Register User
    public void register(){
        initialize();
        if(!validate()){
            Toast.makeText(this,"Signup has failed",Toast.LENGTH_SHORT).show();
        }
        else {
            final ProgressDialog progress = new ProgressDialog(signup.this);
            progress.setTitle("Please Wait");
            progress.setMessage("Creating Your Account");
            progress.setCancelable(false);
            progress.show();
            //convertBitmapToString(profilePicture);
            RegisterRequest registerRequest = new RegisterRequest(fname, lname,email, pass, phone,address, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("Response", response);
                    progress.dismiss();
                    try {
                        if (new JSONObject(response).getBoolean("success")) {
                            Log.i("response",response);
                            Toast.makeText(signup.this, "Account Successfully Created", Toast.LENGTH_SHORT).show();
                            login();
                        }
                        else if (new JSONObject(response).getBoolean("Email")) {
                            Log.i("response", response);
                            Toast.makeText(signup.this, "Email Already Exist", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(signup.this, "Something Has Happened. Please Try Again!", Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            requestQueue.add(registerRequest);
        }
    }

    //Validate input values
    public boolean validate(){
        boolean valid=true;
        if(fname.isEmpty()|fname.length()>32){
            et_fname.setError("Enter valid name");
            valid = false;
        }
        if(lname.isEmpty()|lname.length()>32){
            et_lname.setError("Enter valid name");
            valid = false;
        }
        if(email.isEmpty()|!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            et_email.setError("Enter valid Email Address");
            valid = false;
        }
        if(phone.isEmpty()|phone.length()>11){
            et_ph.setError("Enter valid Phone Number");
            valid = false;
        }
        if(address.isEmpty()){
            et_address.setError("Enter valid Address");
            valid = false;
        }
        if(pass.isEmpty()|pass.length()>32){
            et_pass.setError("Invalid Password");
            valid = false;
        }
        if(pass2.isEmpty()|pass2.length()>32|!pass.equals(pass2)){
            et_pass2.setError("Password not matched");
            valid = false;
        }
        /*if (!IMAGE_STATUS){
            Toast.makeText(this, "Select A Profile Picture", Toast.LENGTH_SHORT).show();
            valid=false;
        }*/
        return valid;
    }
    //Initialize form elements
    public void initialize(){
        fname=et_fname.getText().toString().trim();
        lname=et_lname.getText().toString().trim();
        email=et_email.getText().toString().trim();
        phone=et_ph.getText().toString().trim();
        address=et_address.getText().toString().trim();
        pass=et_pass.getText().toString().trim();
        pass2=et_pass2.getText().toString().trim();
    }
   /* private void convertBitmapToString(Bitmap profilePicture) {
        *//*
            Base64 encoding requires a byte array, the bitmap image cannot be converted directly into a byte array.
            so first convert the bitmap image into a ByteArrayOutputStream and then convert this stream into a byte array.
        *//*
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        profilePicture.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] array = byteArrayOutputStream.toByteArray();
        profile = Base64.encodeToString(array, Base64.DEFAULT);
    }*/
    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == Activity.RESULT_OK && data != null) {
            //Image Successfully Selected
            try {
                //parsing the Intent data and displaying it in the imageview
                Uri imageUri = data.getData();//Geting uri of the data
                InputStream imageStream = getContentResolver().openInputStream(imageUri);//creating an imputstrea
                profilePicture = BitmapFactory.decodeStream(imageStream);//decoding the input stream to bitmap
                pimage.setImageBitmap(profilePicture);
                IMAGE_STATUS = true;//setting the flag
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }*/
    public void onBackPressed(){
        login();
        finish();
    }
}
