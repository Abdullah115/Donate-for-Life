package ciit.com.abdullah.donateforlife2.pkgNgoProjects;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ciit.com.abdullah.donateforlife2.R;
import ciit.com.abdullah.donateforlife2.donate;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class project_description extends AppCompatActivity {
    TextView pname,pdesc;
    ImageView pimage;
    Button projectdescriptiondonate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_description);
        pname=(TextView) findViewById(R.id.project_name);
        pdesc=(TextView)findViewById(R.id.prj_description);
        pimage=(ImageView)findViewById(R.id.project_image);
        projectdescriptiondonate=(Button)findViewById(R.id.projectdesc_donate);
        Glide.with(project_description.this)
                .load(getIntent().getStringExtra("img"))
                .into(pimage);
        pname.setText(getIntent().getStringExtra("pName"));
        pdesc.setText(getIntent().getStringExtra("pdesc"));
        /*Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            pname.setText(bundle.getString("pname"));
        }*/
        projectdescriptiondonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(project_description.this,donate.class);
                startActivity(intent);
            }
        });
    }
}