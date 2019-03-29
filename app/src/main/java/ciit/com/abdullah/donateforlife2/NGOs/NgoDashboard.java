package ciit.com.abdullah.donateforlife2.NGOs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import ciit.com.abdullah.donateforlife2.R;
import ciit.com.abdullah.donateforlife2.pkgNgoProjects.Ngo_projects;

public class NgoDashboard extends AppCompatActivity {
    TextView ngo_name;
    Button ngodesc,donate,projects;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ngo_dashboard);
        ngo_name=(TextView) findViewById(R.id.ngoname);
        donate=(Button)findViewById(R.id.donate1);
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donate();
            }
        });
        Bundle bundle=getIntent().getExtras();
        if(bundle !=null){
            ngo_name.setText(bundle.getString("ngoName"));
        }
        ngodesc=(Button)findViewById(R.id.ngodashboard_description);
        ngodesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ngodescription();
            }
        });
        projects=(Button)findViewById(R.id.ngo_dashboard_projects);
        projects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ngoprojects();
            }
        });

    }

    private void donate() {
        Intent donate=new Intent(NgoDashboard.this, ciit.com.abdullah.donateforlife2.donate.class);
        startActivity(donate);
    }

    private void ngodescription() {
        Intent ngodesc=new Intent(NgoDashboard.this,Ngo_Description.class);
        ngodesc.putExtra("ngo_name",ngo_name.getText().toString());
        startActivity(ngodesc);
    }
    public void ngoprojects(){
        Intent ngoproject=new Intent(NgoDashboard.this,Ngo_projects.class);
        ngoproject.putExtra("Ngo_name",ngo_name.getText().toString());
        startActivity(ngoproject);
    }
}
