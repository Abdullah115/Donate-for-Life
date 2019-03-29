package ciit.com.abdullah.donateforlife2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Aboutus extends AppCompatActivity {
    private Button donateus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        donateus=(Button) findViewById(R.id.donateus);
        donateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Aboutus.this,donate.class);
                startActivity(intent);
            }
        });
    }
}
