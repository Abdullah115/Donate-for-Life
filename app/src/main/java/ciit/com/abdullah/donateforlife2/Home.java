package ciit.com.abdullah.donateforlife2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import ciit.com.abdullah.donateforlife2.NGOs.NGOs;
import ciit.com.abdullah.donateforlife2.Signin.GetCurrentUserDetail;
import ciit.com.abdullah.donateforlife2.Signin.SessionManager;
import ciit.com.abdullah.donateforlife2.Signin.signin;
import ciit.com.abdullah.donateforlife2.UserProfile.Userprofile;
import ciit.com.abdullah.donateforlife2.pkgDonationRequests.Create_Donation_Request;
import ciit.com.abdullah.donateforlife2.pkgDonationRequests.Requests;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView ngos1,cu1,dr1,hp1,au1,logout1,cr1,proimage;
    private TextView ngos2,cu2,dr2,hp2,au2,logout2,cr2;
    private LinearLayout ngos,cu,dr,hp,au,logout,cr,logou;
    String fname,lname,email,image;
    TextView txtname,txtEmail;
    SessionManager sessionManager;
    GetCurrentUserDetail currUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sessionManager=new SessionManager(getApplicationContext());
        currUser=new GetCurrentUserDetail(getApplicationContext());
        currUser.getCurrentUserDetails();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header =navigationView.getHeaderView(0);

        txtname = (TextView) header.findViewById(R.id.txtName);
        txtEmail=(TextView) header.findViewById(R.id.txtEmail);
        proimage=(ImageView) header.findViewById(R.id.imageView);

        navigationView.setNavigationItemSelectedListener(this);

        ngos=(LinearLayout) findViewById(R.id.home_ngos);
        ngos1=(ImageView) findViewById(R.id.home_ngos1);
        ngos2=(TextView) findViewById(R.id.home_ngos2);

        cu=(LinearLayout) findViewById(R.id.home_contactus);
        cu1=(ImageView) findViewById(R.id.home_contactus1);
        cu2=(TextView)findViewById(R.id.home_contactus2);

        dr=(LinearLayout) findViewById(R.id.home_request);
        dr1=(ImageView) findViewById(R.id.home_request1);
        dr2=(TextView) findViewById(R.id.home_request2);

        hp=(LinearLayout) findViewById(R.id.home_profile);
        hp1=(ImageView) findViewById(R.id.home_profile1);
        hp2=(TextView) findViewById(R.id.home_profile2);

        au=(LinearLayout)findViewById(R.id.home_aboutus);
        au1=(ImageView) findViewById(R.id.home_aboutus1);
        au2=(TextView) findViewById(R.id.home_aboutus2);

        logout=(LinearLayout)findViewById(R.id.home_logout);
        logout1=(ImageView) findViewById(R.id.home_logout1);
        logout2=(TextView) findViewById(R.id.home_logout2);
        logou=(LinearLayout)findViewById(R.id.log);
        if(sessionManager.isLoggedIn()){
            logou.setVisibility(View.VISIBLE);
        }
        cr=(LinearLayout)findViewById(R.id.home_createreq);
        cr1=(ImageView) findViewById(R.id.home_createreq1);
        cr2=(TextView) findViewById(R.id.home_createreq2);

        ngos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ngoslist();
            }
        });
        ngos1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ngoslist();
            }
        });
        ngos2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ngoslist();
            }
        });

        cu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactus();
            }
        });
        cu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactus();
            }
        });
        cu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactus();
            }
        });

        dr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donationrequest();
            }
        });
        dr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donationrequest();
            }
        });
        dr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donationrequest();
            }
        });

        hp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeprofile();
            }
        });
        hp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeprofile();
            }
        });
        hp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeprofile();
            }
        });

        au.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutus();
            }
        });
        au1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutus();
            }
        });
        au2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutus();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        logout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        logout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        cr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createnewreq();
            }
        });
        cr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createnewreq();
            }
        });
        cr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createnewreq();
            }
        });

        getnavigationvalues();
        /*Glide.with(Home.this)
                .load(image)
                .into(proimage);*/
        txtname.setText(fname+" "+lname);
        txtEmail.setText(email);

    }
    private void donationrequest() {
        Intent donationrequest=new Intent(Home.this,Requests.class);
        startActivity(donationrequest);
    }
    private void contactus() {
        Intent contactusIntent=new Intent(Home.this, Contact_us.class);
        startActivity(contactusIntent);
    }
    private void ngoslist() {
        Intent listngosIntent=new Intent(Home.this, NGOs.class);
        startActivity(listngosIntent);
    }
    private void homeprofile() {
        if(sessionManager.isLoggedIn()){
        Intent uprofileIntent=new Intent(Home.this, Userprofile.class);
        currUser.getCurrentUserDetails();
        startActivity(uprofileIntent);}
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.Theme_AppCompat_DayNight_Dialog);
            builder.setTitle("Signin to view Profile");
            builder.setCancelable(false);
            builder.setPositiveButton("Signin", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent=new Intent(Home.this,signin.class);
                    startActivity(intent);
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        }
    }
    public void aboutus(){
        Intent aboutusIntent=new Intent(Home.this, Aboutus.class);
        startActivity(aboutusIntent);
    }
    public void logout(){
        sessionManager.logoutUser();
        Intent intent=new Intent(Home.this,Home.class);
        startActivity(intent);
        finish();
    }
    public void createnewreq(){
        if(sessionManager.isLoggedIn()){
            Intent intent=new Intent(Home.this,Create_Donation_Request.class);
            startActivity(intent);
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.Theme_AppCompat_DayNight_Dialog);
            builder.setTitle("Signin to Create Request");
            builder.setCancelable(false);
            builder.setPositiveButton("Signin", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent=new Intent(Home.this,signin.class);
                    startActivity(intent);
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.homes) {
           /* Intent intent=new Intent(Home.this,Home.class);
            startActivity(intent);*/
        } else if (id == R.id.NGOss) {
            ngoslist();
        } else if (id == R.id.Requestss) {
            donationrequest();
        } else if (id == R.id.Profiles) {
            currUser.getCurrentUserDetails();
            homeprofile();
        } else if (id == R.id.Logouts) {
            sessionManager.logoutUser();
            Intent intent=new Intent(Home.this,signin.class);
            startActivity(intent);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void getnavigationvalues(){
        fname=sessionManager.getAllInfo().get("FirstName");
        lname=sessionManager.getAllInfo().get("LastName");
        image=sessionManager.getAllInfo().get("Image");
        email=sessionManager.getUserDetails().get("email");
    }
}
