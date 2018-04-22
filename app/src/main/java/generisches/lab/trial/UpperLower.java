package generisches.lab.trial;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UpperLower extends AppCompatActivity {

    TextView tvOut;
    Button btnUpp,btnLOw;
    LinearLayout l1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upper_lower);
        tvOut = (TextView) findViewById(R.id.tvOut);
        btnUpp = (Button) findViewById(R.id.btnUp);
        btnLOw = (Button) findViewById(R.id.btnLo);
        l1 = (LinearLayout) findViewById(R.id.linearLayout1);

        btnUpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOut.setText("Button Upper clicked".toUpperCase());
                tvOut.setTextColor(Color.BLUE);
                l1.setBackgroundColor(Color.RED);
            }
        });
        btnLOw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOut.setText("Button Lower clicked".toLowerCase());
                tvOut.setTextColor(Color.MAGENTA);
                l1.setBackgroundColor(Color.CYAN);
            }
        });
    }
}
