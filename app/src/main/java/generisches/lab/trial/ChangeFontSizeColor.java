package generisches.lab.trial;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChangeFontSizeColor extends AppCompatActivity {

    EditText tv1,tv2,tv3,tv4;
    TextView tv;
    Button bu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_font_size_color);
        tv1= findViewById(R.id.editText1);
        tv2= findViewById(R.id.editText2);
        tv3= findViewById(R.id.editText3);
        tv4= findViewById(R.id.editText4);
        tv= findViewById(R.id.textView1);
        bu= findViewById(R.id.button1);
        tv.setText("Font Color and Size Demo");

        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rr = Integer.parseInt(String.valueOf(tv1.getText()).trim());
                int bb = Integer.parseInt(String.valueOf(tv2.getText()).trim());
                int gg = Integer.parseInt(String.valueOf(tv3.getText()).trim());
                int siz = Integer.parseInt(String.valueOf(tv4.getText()).trim());
                tv.setTextColor(Color.rgb(rr,bb,gg));
                tv.setTypeface(Typeface.MONOSPACE);
                tv.setTextSize(siz);
            }
        });
    }
}
