package ciit.com.abdullah.donateforlife2.pkgDonationRequests;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import ciit.com.abdullah.donateforlife2.R;
import ciit.com.abdullah.donateforlife2.Signin.SessionManager;

public class Create_Donation_Request extends AppCompatActivity {

    private static final String REGISTER_URL = "https://donate-for-life.000webhostapp.com/php/CreateRequest.php";
    public Context ctx;
    SessionManager sessionManager;

    private EditText pn,email,amount,desc;
    private Button createreq;
    private String pname;
    private String email1;
    private String amoun;
    private String date;
    private String description;
    private String  date2;
    private static final String TAG = "";
    String email4;

    private TextView cdate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_donation__request);
        pn=(EditText)findViewById(R.id.dr_projectname);
        email=(EditText)findViewById(R.id.dr_email);
        amount=(EditText)findViewById(R.id.dr_amount);
        cdate=(TextView) findViewById(R.id.dr_completiondate);
        desc=(EditText)findViewById(R.id.dr_projectdescription);
        createreq=(Button) findViewById(R.id.dr_createrequest);
        sessionManager=new SessionManager(getApplicationContext());
        email4=sessionManager.getUserDetails().get("email");
        email.setText(email4);
        createreq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createrequest();
            }
        });
        cdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Create_Donation_Request.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                date2 = month + "/" + day + "/" + year;
                cdate.setText(date2);
            }
        };

    }
    public void createrequest(){
        initialize();
        if(!validate()){
            Toast.makeText(this,"Request Creation has failed \n Try Again!",Toast.LENGTH_SHORT).show();
        }
        else {
            onSuccess();
        }
    }
    private String getcurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
        Date date1 = new Date();
        return dateFormat.format(date1);
    }
    public void onSuccess(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest sr = new StringRequest(com.android.volley.Request.Method.POST, REGISTER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                Log.i("response",response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Internet Connection Error", Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                Log.i("Email",email4);
                Log.i("Email",String.valueOf(amoun));
                Log.i("Email",description);
                Log.i("Email","https://donate-for-life.000webhostapp.com/php/ProjectImages/crowdfund.png");
                Log.i("RequestStatus", "none");
                Log.i("Received","0");
                Log.i("Email",getcurrentDate());
                Log.i("Email",date2);


                params.put("UserEmail", email4);
                params.put("Amount", String.valueOf(amoun));
                params.put("ProjectName", pname);
                params.put("Description", description);
                params.put("FormPicture", "https://donate-for-life.000webhostapp.com/php/ProjectImages/crowdfund.png");
                params.put("StartDate",getcurrentDate());
                params.put("EndDate",date2);
                return params;
            }
        };
        queue.add(sr);
    }
    public boolean validate(){
        boolean valid=true;
        if(pname.isEmpty()|pname.length()>100){
            pn.setError("Enter valid project name");
            valid = false;
        }
        if(email1.isEmpty()|!Patterns.EMAIL_ADDRESS.matcher(email1).matches()){
            email.setError("Enter valid Email Address");
            valid = false;
        }
        if(amoun.isEmpty()){
            amount.setError("Enter valid Amount");
            valid = false;
        }
        if(date.isEmpty()){
            cdate.setError("Enter valid Date");
            valid = false;
        }
        if(description.isEmpty()){
            desc.setError("Not Valid Description");
            valid = false;
        }
        return valid;
    }

    public void initialize(){
        pname=pn.getText().toString().trim();
        email1=email.getText().toString().trim();
        amoun=amount.getText().toString().trim();
        date=cdate.getText().toString().trim();
        description=desc.getText().toString().trim();
    }
}
