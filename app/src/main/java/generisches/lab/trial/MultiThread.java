package generisches.lab.trial;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MultiThread extends AppCompatActivity {

    Handler h = new Handler();
    Button b;
    TextView c, t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_thread);

        c = findViewById(R.id.txt_countdown);
        b = findViewById(R.id.btn_thread);
        t = findViewById(R.id.txt_static);

        h.postDelayed(r, 1000);
    }

    Runnable r = new Runnable() {
        @Override
        public void run() {
            updateTime();
        }

        private void updateTime() {
            c.setText("" + (Integer.parseInt(c.getText().toString()) - 1));
            if (Integer.parseInt(c.getText().toString()) == 0) {
                b.setVisibility(View.VISIBLE);
                t.setText("THe button has appeared");
            } else {
                h.postDelayed(r, 1000);
            }
        }

    };



}
