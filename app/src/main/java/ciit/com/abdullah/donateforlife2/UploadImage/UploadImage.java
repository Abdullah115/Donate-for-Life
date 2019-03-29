package ciit.com.abdullah.donateforlife2.UploadImage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import ciit.com.abdullah.donateforlife2.R;

public class UploadImage extends AppCompatActivity {
    public Button choosebtn,uploadbtn;
    public EditText picturename;
    public ImageView uimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        choosebtn=(Button)findViewById(R.id.chooseimage);
        uploadbtn=(Button)findViewById(R.id.uploadimage);
        picturename=(EditText)findViewById(R.id.picname);
        uimage=(ImageView)findViewById(R.id.userimage);

        choosebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(intent.ACTION_GET_CONTENT);
            }
        });
    }
}
