package ciit.com.abdullah.donateforlife2.UserProfile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.HashMap;

import ciit.com.abdullah.donateforlife2.R;

public class UpdateAccount extends AppCompatActivity {
    public String u_fname,u_lname,u_email,u_phone,u_pass,u_address;
    private TextView ufname,ulname,uemail,upassword,uphone,uaddress;
    ImageView profileimage;
    private Button updateaccount,ucancel;
    public final String UpdateAccount_URL = "https://donate-for-life.000webhostapp.com/php/update_profile.php";

    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);

        profileimage=(ImageView) findViewById(R.id.userpic);
        ufname=(TextView)findViewById(R.id.update_fname);
        ulname=(TextView)findViewById(R.id.update_lname);
        uemail=(TextView)findViewById(R.id.update_email);
        upassword=(TextView)findViewById(R.id.update_password);
        uphone=(TextView)findViewById(R.id.update_phone);
        uaddress=(TextView)findViewById(R.id.update_address);
        updateaccount=(Button) findViewById(R.id.update);
        ucancel=(Button)findViewById(R.id.cancel);

        /*Glide.with(UpdateAccount.this)
                .load(getIntent().getStringExtra("proimage"))
                .into(profileimage);*/
        ufname.setText(getIntent().getStringExtra("fname"));
        ulname.setText(getIntent().getStringExtra("lname"));
        uemail.setText(getIntent().getStringExtra("email"));
        uphone.setText(getIntent().getStringExtra("contactno"));
        uaddress.setText(getIntent().getStringExtra("address"));
        upassword.setText(getIntent().getStringExtra("pass"));
        updateaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrievedata();
                updateprofile(u_fname,u_lname,u_email,u_phone,u_address,u_pass);
            }
        });
        ucancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UpdateAccount.this,Userprofile.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void updateprofile(String u_fname, String u_lname, String u_email, String u_phone, String u_address, String u_pass) {
        class RecordUpdateClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(UpdateAccount.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(UpdateAccount.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("FirstName",params[0]);
                hashMap.put("LastName",params[1]);
                hashMap.put("Email",params[2]);
                hashMap.put("ContactNo",params[3]);
                hashMap.put("Password",params[5]);
                hashMap.put("Address",params[4]);


                finalResult = httpParse.postRequest(hashMap, UpdateAccount_URL);

                return finalResult;
            }
        }

        RecordUpdateClass recordUpdateClass = new RecordUpdateClass();

        recordUpdateClass.execute(u_fname,u_lname,u_email,u_phone,u_address,u_pass);
    }


    public void retrievedata(){
        u_fname= ufname.getText().toString().trim();
        u_lname= ulname.getText().toString().trim();
        u_email= uemail.getText().toString().trim();
        u_phone= uphone.getText().toString().trim();
        u_pass= upassword.getText().toString().trim();
        u_address= uaddress.getText().toString().trim();
    }

   


}
