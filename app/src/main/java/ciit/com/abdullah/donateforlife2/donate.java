package ciit.com.abdullah.donateforlife2;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.craftman.cardform.Card;
import com.craftman.cardform.CardForm;
import com.craftman.cardform.OnPayBtnClickListner;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class donate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_donate);
        CardForm cardForm=(CardForm) findViewById(R.id.cardform);
        TextView txtdes=(TextView) findViewById(R.id.payment_amount);
        Button btnpay=(Button)findViewById(R.id.btn_pay);
        txtdes.setText("PKR99");
        btnpay.setText(String.format("Payer %s",txtdes.getText()));
        cardForm.setPayBtnClickListner(new OnPayBtnClickListner() {
            @Override
            public void onClick(Card card) {
                Toast.makeText(donate.this,String.format("Exp: %d/%d",card.getExpMonth(),card.getExpYear()),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


}
