package generisches.lab.trial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Calculator extends AppCompatActivity {

    Button nine, eig, sev, six, fiv, four, thr, two, one, zero, dot, plus, min, div, mul, eq, cl;

    EditText et;
    float res = 0, op1, op2;
    boolean flag;
    char op;
    TextView t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        nine = (Button) findViewById(R.id.btn_9);
        eig = (Button) findViewById(R.id.btn_8);
        sev = (Button) findViewById(R.id.btn_7);
        six = (Button) findViewById(R.id.btn_6);
        fiv = (Button) findViewById(R.id.btn_5);
        four = (Button) findViewById(R.id.btn_4);
        thr = (Button) findViewById(R.id.btn_3);
        two = (Button) findViewById(R.id.btn_2);
        one = (Button) findViewById(R.id.btn_1);
        zero = (Button) findViewById(R.id.btn_zero);
        dot = (Button) findViewById(R.id.btn_dot);
        plus = (Button) findViewById(R.id.btn_add);
        min = (Button) findViewById(R.id.btn_min);
        div = (Button) findViewById(R.id.btn_div);
        mul = (Button) findViewById(R.id.btn_mul);
        eq = (Button) findViewById(R.id.btn_equal);
        cl = (Button) findViewById(R.id.btn_clear);
        et = findViewById(R.id.enterNo);
        t = findViewById(R.id.textView6);

        nine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                et.setText(String.valueOf(et.getText()) + String.valueOf(nine.getText()));
            }
        });
        eig.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                et.setText(String.valueOf(et.getText()) + String.valueOf(eig.getText()));
            }
        });
        sev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                et.setText(String.valueOf(et.getText()) + String.valueOf(sev.getText()));
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                et.setText(String.valueOf(et.getText()) + String.valueOf(six.getText()));
            }
        });
        fiv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                et.setText(String.valueOf(et.getText()) + String.valueOf(fiv.getText()));
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                et.setText(String.valueOf(et.getText()) + String.valueOf(four.getText()));
            }
        });
        thr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                et.setText(String.valueOf(et.getText()) + String.valueOf(thr.getText()));
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                et.setText(String.valueOf(et.getText()) + String.valueOf(two.getText()));
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                et.setText(String.valueOf(et.getText()) + String.valueOf(one.getText()));
            }
        });
        zero.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                et.setText(String.valueOf(et.getText()) + String.valueOf(zero.getText()));
            }
        });
        dot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flag == true) {
                    et.setText(String.valueOf(et.getText()) + String.valueOf(dot.getText()));
                    flag = false;
                } else {
                    et.setText(String.valueOf(et.getText()));
                }
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                op = '+';
                op1 = Float.parseFloat(String.valueOf(et.getText()));
                et.setText("");
                flag = true;
            }
        });
        min.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                op = '-';
                op1 = Float.parseFloat(String.valueOf(et.getText()));
                et.setText("");
                flag = true;
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                op = '/';
                op1 = Float.parseFloat(String.valueOf(et.getText()));
                et.setText("");
                flag = true;
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                op = '*';
                op1 = Float.parseFloat(String.valueOf(et.getText()));
                et.setText("");
                flag = true;
            }
        });
        cl.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                et.setText("");
                flag = true;
            }
        });

        eq.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (op == '+') {
                    op2 = Float.parseFloat(String.valueOf(et.getText()));
                    res = op1 + op2;
                    String r = Float.toString(res);
                    if (r.contains(".0") == true) {
                        int r1 = (int) res;
                        t.setText(Integer.toString(r1));
                        et.setText("");
                    } else {
                        t.setText(r);
                    }
                }
                if (op == '-') {
                    op2 = Float.parseFloat(String.valueOf(et.getText()));
                    res = op1 - op2;
                    String r = Float.toString(res);
                    if (r.contains(".0") == true) {
                        int r1 = (int) res;
                        t.setText(Integer.toString(r1));
                        et.setText("");
                    } else {
                        t.setText(r);
                    }
                }
                if (op == '/') {
                    op2 = Float.parseFloat(String.valueOf(et.getText()));
                    res = op1 / op2;
                    String r = Float.toString(res);
                    if (r.contains(".0") == true) {
                        int r1 = (int) res;
                        t.setText(Integer.toString(r1));
                        et.setText("");
                    } else {
                        t.setText(r);
                    }
                }

                if (op == '*') {
                    op2 = Float.parseFloat(String.valueOf(et.getText()));
                    res = op1 * op2;
                    String r = Float.toString(res);
                    if (r.contains(".0") == true) {
                        int r1 = (int) res;
                        t.setText(Integer.toString(r1));
                        et.setText("");
                    } else {
                        t.setText(r);
                    }
                }
            }
        });
    }
}
