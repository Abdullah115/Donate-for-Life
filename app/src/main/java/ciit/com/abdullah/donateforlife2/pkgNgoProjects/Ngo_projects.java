package ciit.com.abdullah.donateforlife2.pkgNgoProjects;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
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

public class Ngo_projects extends AppCompatActivity {
    RecyclerView recyclerView;
    List<project> projectList;

    String NGO_Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_projects);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        projectList = new ArrayList<>();
        loadProjects();

        recyclerView.addOnItemTouchListener(new ProjectTouchLiustener(getApplicationContext(), recyclerView, new ProjectTouchLiustener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                project projects= projectList.get(position);
                Context c=view.getContext();
                Intent i=new Intent(c,project_description.class);
                i.putExtra("img",projects.getImage_id());
                i.putExtra("pName",projects.getTitles());
                i.putExtra("pdesc",projects.getDetails());
                c.startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        setupBottomNavigationView();

    }

private void loadProjects() {
    String Ngo_projects_URL="https://donate-for-life.000webhostapp.com/php/ngo_projects.php";
    StringRequest stringRequest = new StringRequest(Request.Method.POST, Ngo_projects_URL,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray array = new JSONArray(response);
                        for (int i = 0; i < array.length(); i++) {
                            project obj=new project();
                            JSONObject projects = array.getJSONObject(i);


                            obj.setDetails(projects.getString("ProjectDescription"));
                            obj.setTitles(projects.getString("ProjectName"));
                            obj.setImage_id(projects.getString("ProjectImage"));
                            projectList.add(obj);
                            ProjectAdapter adapter = new ProjectAdapter(Ngo_projects.this,projectList);
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
        @Override
        protected Map<String, String> getParams() {
            Map<String, String> Params = new HashMap<String, String>();
            Params.put("NGOName", getIntent().getStringExtra("Ngo_name"));
            return Params;
        }

    };
    Volley.newRequestQueue(this).add(stringRequest);
}
    private void setupBottomNavigationView(){

        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(Ngo_projects.this, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
    }
}

