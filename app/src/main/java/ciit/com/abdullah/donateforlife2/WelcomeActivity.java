package ciit.com.abdullah.donateforlife2;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import ciit.com.abdullah.donateforlife2.Signin.signin;

public class WelcomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ActionBar actionBar = getSupportActionBar();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if(null != actionBar){
            actionBar.hide();
        }

        Thread thread=new Thread(){
            public void run(){
                try {
                    sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    Intent welcome=new Intent(WelcomeActivity.this,Home.class);
                    startActivity(welcome);
                }
            }
        };
        thread.start();
    }
    public void onPause(){
        super.onPause();
        finish();
    }

}
