package ciit.com.abdullah.donateforlife2.pkgDonationRequests;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ciit.com.abdullah.donateforlife2.BottomNavigationViewHelper;
import ciit.com.abdullah.donateforlife2.R;
import ciit.com.abdullah.donateforlife2.Signin.SessionManager;
import ciit.com.abdullah.donateforlife2.pkgNgoProjects.ProjectTouchLiustener;

public class Requests extends AppCompatActivity {

    private static final int ACTIVITY_NUM = 0;
    RecyclerView recyclerView;
    List<Request> requestsList;
    private ImageButton createnewrequest;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        createnewrequest=(ImageButton) findViewById(R.id.createnew);
        sessionManager=new SessionManager(getApplicationContext());
        if (sessionManager.isLoggedIn()){
            createnewrequest.setVisibility(View.VISIBLE);
        }
        createnewrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createnewreq();
            }
        });

        requestsList = new ArrayList<>();
        loadRequests();


        recyclerView.addOnItemTouchListener(new ProjectTouchLiustener(getApplicationContext(), recyclerView, new ProjectTouchLiustener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Request requests= requestsList.get(position);
                Context c=view.getContext();
                Intent i=new Intent(c,Request_Description.class);
                i.putExtra("img",requests.getImage_id());
                i.putExtra("rName",requests.getTitles());
                i.putExtra("rDesc",requests.getDetails());
                c.startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
        setupBottomNavigationView();

    }

    private void loadRequests() {
        String Requests_URL="https://donate-for-life.000webhostapp.com/php/Requests.php";
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, Requests_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Requests",response);
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                Request obj=new Request();
                                JSONObject requests = array.getJSONObject(i);

                                obj.setDetails(requests.getString("Description"));
                                obj.setTitles(requests.getString("ProjectName"));
                                obj.setImage_id(requests.getString("FormPicture"));
                                requestsList.add(obj);
                                DonationRequestsAdapter adapter = new DonationRequestsAdapter(requestsList,Requests.this);
                                recyclerView.setAdapter(adapter);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(),"Internet Connection Error",Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Internet Connection Error",Toast.LENGTH_SHORT).show();

                    }
                }){
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }



    private void setupBottomNavigationView(){

        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(Requests.this, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
    private void createnewreq() {
        Intent newrequest=new Intent(Requests.this,Create_Donation_Request.class);
        startActivity(newrequest);
    }
}

