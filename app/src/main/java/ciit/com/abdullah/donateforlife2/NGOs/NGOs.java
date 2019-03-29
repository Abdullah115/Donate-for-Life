package ciit.com.abdullah.donateforlife2.NGOs;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ciit.com.abdullah.donateforlife2.BottomNavigationViewHelper;
import ciit.com.abdullah.donateforlife2.R;
import ciit.com.abdullah.donateforlife2.abc;

public class NGOs extends AppCompatActivity {
    EditText textBox;

    private ListView lv;
    private static final int ACTIVITY_NUM = 0;
    private Context mContext = NGOs.this;
    private ImageButton addngo;
    private EditText searchngo;
    String JSON_URL="https://donate-for-life.000webhostapp.com/php/ngo_api.php";
    ArrayList<String> ngos=new ArrayList<>();

    ProgressBar spinner;
    List<String> listString = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngos);


        spinner = (ProgressBar)findViewById(R.id.progressBar);
        searchngo=(EditText)findViewById(R.id.search);
        new GetHttpResponse(NGOs.this).execute();
        searchngo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                NGOs.this.arrayAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        addngo=(ImageButton) findViewById(R.id.createnewngo);
        addngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NGOs.this,abc.class);
                startActivity(intent);
                finish();
            }
        });

        lv=(ListView)findViewById(R.id.ngos_list);
        setupBottomNavigationView();
    }

    private class GetHttpResponse extends AsyncTask<Void, Void, Void> {
        public Context context;

        String ResultHolder;

        public GetHttpResponse(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            spinner = new android.widget.ProgressBar(
                    NGOs.this,
                    null,
                    android.R.attr.progressBarStyle);
            spinner.getIndeterminateDrawable().setColorFilter(0xFF000000, android.graphics.PorterDuff.Mode.MULTIPLY);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpServicesClass httpServiceObject = new HttpServicesClass(JSON_URL);
            try {
                httpServiceObject.ExecutePostRequest();

                if (httpServiceObject.getResponseCode() == 200) {
                    ResultHolder = httpServiceObject.getResponse();

                    if (ResultHolder != null) {
                        JSONArray jsonArray = null;

                        try {
                            jsonArray = new JSONArray(ResultHolder);

                            JSONObject jsonObject;

                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);

                                listString.add(jsonObject.getString("NGOName").toString());
                                ngos.add(jsonObject.getString("NGOName"));
                                Log.i("ngoname", listString.toString());

                                Log.i("ngoname", jsonObject.getString("NGOName").toString());
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } else {
                    Toast.makeText(context, httpServiceObject.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            spinner.setVisibility(View.GONE);
            lv.setVisibility(View.VISIBLE);
            arrayAdapter = new ArrayAdapter<String>(NGOs.this, android.R.layout.simple_list_item_1, listString);
            Log.i("ngons", listString.toString());
            lv.setAdapter(arrayAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Snackbar.make(view, ngos.get(i), Snackbar.LENGTH_SHORT).show();
                    Intent i1 = new Intent(NGOs.this, NgoDashboard.class);
                    i1.putExtra("ngoName", ngos.get(i));
                    startActivity(i1);
                }
            });
        }
    }
    //Top Navigation View
    private void setupBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}

