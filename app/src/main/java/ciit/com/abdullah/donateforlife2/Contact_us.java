package ciit.com.abdullah.donateforlife2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class Contact_us extends AppCompatActivity {
    private EditText cu_email,cu_sub,cu_msg;
    private String email,sub,msg;
    private Button sendmsg;
    private static final int ACTIVITY_NUM = 2;

    private Context mContext = Contact_us.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        cu_email=(EditText) findViewById(R.id.contactus_email);
        cu_sub=(EditText) findViewById(R.id.contactus_subject);
        cu_msg=(EditText) findViewById(R.id.contactus_message);
        sendmsg=(Button)findViewById(R.id.contactus_sendmsg);
        sendmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendmessage();
            }
        });
        setupBottomNavigationView();
    }

    private void sendmessage() {
        initialize();
        if(!validate()){
            Toast.makeText(this,"Message sending failed",Toast.LENGTH_SHORT).show();
        }
        else {
            onSuccess();
        }
    }

    private void onSuccess() {
        Intent Intent=new Intent(Contact_us.this, Contact_us.class);
        Toast.makeText(this,"Your message is sent to Admin",Toast.LENGTH_SHORT).show();
        startActivity(Intent);
        finish();
    }

    private boolean validate() {
        boolean valid=true;
        if(sub.isEmpty()){
            cu_sub.setError("Enter Subject");
            valid = false;
        }
        if(email.isEmpty()|!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            cu_email.setError("Enter valid Email Address");
            valid = false;
        }
        if(msg.isEmpty()){
            cu_msg.setError("Enter description");
            valid = false;
        }
        return valid;
    }

    private void initialize() {
        sub=cu_sub.getText().toString().trim();
        email=cu_email.getText().toString().trim();
        msg=cu_msg.getText().toString().trim();
    }
    private void setupBottomNavigationView(){

        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}
