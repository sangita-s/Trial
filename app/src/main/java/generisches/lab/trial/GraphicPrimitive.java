package generisches.lab.trial;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class GraphicPrimitive extends AppCompatActivity {

    Paint p;
    Bitmap bm;
    Canvas cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic_primitive);

        int x = 500;
        int y = 500;
        int rad = 200;

        bm = Bitmap.createBitmap(x,y, Bitmap.Config.ARGB_8888);
        cv = new Canvas(bm);
        p = new Paint();

        ImageView iv = findViewById(R.id.imageView2);
        iv.setImageBitmap(bm);

        //For rectangular background
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.BLUE);
        cv.drawPaint(p);

        //For face
        p.setColor(Color.YELLOW);
        cv.drawCircle(x/2, y/2, rad, p);

        //For eye 1
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.FILL);
        cv.drawRect(150, 150, 175, 175, p);

        //For eye 2
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);
        cv.drawRect(325, 150, 350, 175, p);

        RectF r = new RectF(150, 250, 350, 375);
        p.setColor(Color.BLACK);
        cv.drawArc(r, 0, 180, false, p);
    }
}
