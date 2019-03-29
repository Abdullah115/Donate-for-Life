package ciit.com.abdullah.donateforlife2.pkgDonationRequests;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ciit.com.abdullah.donateforlife2.R;
import ciit.com.abdullah.donateforlife2.donate;

public class Request_Description extends AppCompatActivity {

    TextView rname,rdatail;
    ImageView rimage;
    Button donationrequestdonate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_description);
        rimage=(ImageView)findViewById(R.id.request_image);
        rname=(TextView) findViewById(R.id.request_name);
        rdatail=(TextView) findViewById(R.id.request_detail);
        donationrequestdonate=(Button)findViewById(R.id.donationrequest_donate);
        Glide.with(Request_Description.this)
                .load(getIntent().getStringExtra("img"))
                .into(rimage);
        rname.setText(getIntent().getStringExtra("rName"));
        rdatail.setText(getIntent().getStringExtra("rDesc"));

        donationrequestdonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Request_Description.this,donate.class);
                startActivity(intent);
            }
        });
    }
}