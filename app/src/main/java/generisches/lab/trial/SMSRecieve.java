package generisches.lab.trial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SMSRecieve extends AppCompatActivity {

    private static SMSRecieve inst;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsrecieve);
        tv = findViewById(R.id.txt_sms_msg);
    }

    public static SMSRecieve instance() {
        return inst;
    }

    @Override
    protected void onStart() {
        super.onStart();
        inst = this;
    }

    public void updateList(final String msg) {
        tv.setText(msg);
    }
}
