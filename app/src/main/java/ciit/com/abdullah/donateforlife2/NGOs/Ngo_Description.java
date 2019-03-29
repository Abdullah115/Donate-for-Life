package ciit.com.abdullah.donateforlife2.NGOs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ciit.com.abdullah.donateforlife2.R;
import ciit.com.abdullah.donateforlife2.pkgNgoProjects.Ngo_projects;
import ciit.com.abdullah.donateforlife2.pkgNgoProjects.ProjectAdapter;
import ciit.com.abdullah.donateforlife2.pkgNgoProjects.project;

public class Ngo_Description extends AppCompatActivity {
    TextView tv1,description;
    Button donate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_description);


        tv1=(TextView) findViewById(R.id.tv1_name);
        description=(TextView)findViewById(R.id.ngodesc_desc);
        donate =(Button) findViewById(R.id.ngodesc_donate);


        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent donate=new Intent(Ngo_Description.this, ciit.com.abdullah.donateforlife2.donate.class);
                startActivity(donate);
            }
        });
        ngodesc();
    }

    private void ngodesc() {
        String Ngo_Description_URL="https://donate-for-life.000webhostapp.com/php/ngo_description.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Ngo_Description_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("NGO",response);
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject desc = array.getJSONObject(i);
                                Log.i("project data", String.valueOf(desc.getString("Address")));
                                tv1.setText(desc.getString("NGOName"));
                                description.setText(desc.getString("Address"));
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
                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> Params = new HashMap<String, String>();

                Params.put("NGOName", getIntent().getStringExtra("ngo_name"));
                return Params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
