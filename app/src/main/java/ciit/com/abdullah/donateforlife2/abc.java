package ciit.com.abdullah.donateforlife2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ciit.com.abdullah.donateforlife2.Database.DatabaseHelper;
import ciit.com.abdullah.donateforlife2.NGOs.NGOs;

public class abc extends AppCompatActivity {
    DatabaseHelper db;
    public EditText name,cate,loc,con, email;
    public String na,ca,lo,co,em;
    public Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abc);
        db=new DatabaseHelper(this);
        name=(EditText) findViewById(R.id.ngoname);
        cate=(EditText) findViewById(R.id.category);
        loc=(EditText) findViewById(R.id.location);
        con=(EditText) findViewById(R.id.contactno);
        email=(EditText) findViewById(R.id.email);
        b=(Button) findViewById(R.id.addngo);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addngonew();

            }
        });
    }

    private void addngonew() {
        na=name.getText().toString().trim();
        ca=cate.getText().toString().trim();
        lo=loc.getText().toString().trim();
        co=con.getText().toString().trim();
        em=email.getText().toString().trim();
        db.insertNGOs(na,ca,lo,co,em);
            Intent signinIntent = new Intent(abc.this, NGOs.class);
            startActivity(signinIntent);
            Toast.makeText(this, "NGO Added", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

