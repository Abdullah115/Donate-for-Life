package ciit.com.abdullah.donateforlife2.UserProfile;

import android.content.Context;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import ciit.com.abdullah.donateforlife2.BottomNavigationViewHelper;
import ciit.com.abdullah.donateforlife2.R;
import ciit.com.abdullah.donateforlife2.Signin.GetCurrentUserDetail;
import ciit.com.abdullah.donateforlife2.Signin.SessionManager;

public class Userprofile extends AppCompatActivity {
    private static final int ACTIVITY_NUM = 3;
    GetCurrentUserDetail currUser;
    private Context mContext = Userprofile.this;
    private TextView u_name,u_email,u_contactno,u_address;
    Button update_profile;
    ImageView u_pimage;
    String fname,lname,email,contactno,address,pass,image;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        sessionManager=new SessionManager(getApplicationContext());
        currUser=new GetCurrentUserDetail(getApplicationContext());

        u_name=(TextView) findViewById(R.id.profile_name);
        u_email=(TextView) findViewById(R.id.profile_email);
        u_contactno=(TextView) findViewById(R.id.profile_contactno);
        u_address=(TextView) findViewById(R.id.profile_address);
        u_pimage=(ImageView)findViewById(R.id.profile_image);


        fname=sessionManager.getAllInfo().get("FirstName");
        lname=sessionManager.getAllInfo().get("LastName");
        email=sessionManager.getUserDetails().get("email");
        contactno=sessionManager.getAllInfo().get("ContactNo");
        address=sessionManager.getAllInfo().get("Address");
        pass=sessionManager.getUserDetails().get("pass");
        image=sessionManager.getAllInfo().get("Image");


        //Set values
       /* Glide.with(Userprofile.this)
                .load(image)
                .into(u_pimage);*/
        u_name.setText(fname+" "+lname);
        u_email.setText(email);
        u_contactno.setText(contactno);
        u_address.setText(address);

        update_profile=(Button)findViewById(R.id.updateprofile);
        update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Userprofile.this,UpdateAccount.class);
                intent.putExtra("fname",fname);
                intent.putExtra("lname",lname);
                intent.putExtra("email",email);
                intent.putExtra("contactno",contactno);
                intent.putExtra("address",address);
                intent.putExtra("pass",pass);
                intent.putExtra("proimage",image);
                startActivity(intent);
                finish();
            }
        });
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
