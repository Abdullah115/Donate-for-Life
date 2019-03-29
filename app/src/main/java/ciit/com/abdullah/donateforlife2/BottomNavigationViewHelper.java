package ciit.com.abdullah.donateforlife2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import ciit.com.abdullah.donateforlife2.NGOs.NGOs;
import ciit.com.abdullah.donateforlife2.UserProfile.Userprofile;
import ciit.com.abdullah.donateforlife2.pkgDonationRequests.Requests;

public class BottomNavigationViewHelper {
    private static final String TAG = "BottomNavigationViewHel";

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx){
        Log.d(TAG, "setupBottomNavigationView: Setting up BottomNavigationView");
        bottomNavigationViewEx.enableAnimation(true);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);
    }
    public static void enableNavigation(final Context context, BottomNavigationViewEx view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_house:
                        Intent intent0 = new Intent(context, Home.class);
                        context.startActivity(intent0);
                        break;
                    case R.id.ic_ngo:
                        Intent intent1 = new Intent(context, NGOs.class);//ACTIVITY_NUM = 0
                        context.startActivity(intent1);
                        break;

                    case R.id.ic_request:
                        Intent intent2  = new Intent(context, Requests.class);//ACTIVITY_NUM = 1
                        context.startActivity(intent2);
                        break;

                    case R.id.ic_feedback:
                        Intent intent3 = new Intent(context, Contact_us.class);//ACTIVITY_NUM = 2
                        context.startActivity(intent3);
                        break;

                    case R.id.ic_profile:
                        Intent intent4 = new Intent(context, Userprofile.class);//ACTIVITY_NUM = 3
                        context.startActivity(intent4);
                        break;
                }

                return false;
            }
        });

    }
}
