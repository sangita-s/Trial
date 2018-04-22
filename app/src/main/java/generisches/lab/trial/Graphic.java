package generisches.lab.trial;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class Graphic extends AppCompatActivity {

    ImageView iv;
    Bitmap bmp;
    Canvas cv;
    Paint p;

    float downx = 0, downy = 0, upx = 0, upy = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);

        iv = findViewById(R.id.IV);

        Display cd = getWindowManager().getDefaultDisplay();
        float cdw = cd.getWidth();
        float cdh = cd.getHeight();

        bmp = Bitmap.createBitmap((int) cdw, (int) cdh, Bitmap.Config.ARGB_8888);
        cv = new Canvas(bmp);
        p = new Paint();

        p.setStrokeWidth(5);
        p.setColor(Color.RED);

        iv.setImageBitmap(bmp);

        View.OnTouchListener otld = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        downx = event.getX();
                        downy = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        upx = event.getX();
                        upy = event.getY();
                        cv.drawLine(downx, downy - 20, upx, upy - 20, p);
                        iv.invalidate();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                    default:
                        break;
                }
                return true;
            }
        };
        iv.setOnTouchListener(otld);
    }
}
