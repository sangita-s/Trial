package generisches.lab.trial;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class SchonConstraint extends AppCompatActivity {

    private boolean isOpen = false;
    private ConstraintSet layout1, layout2;
    private ConstraintLayout mConstraintLayout;
    private ImageView imageViewPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schon_constraint);

        //Changing status bar color to transparent
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        layout1 = new ConstraintSet();
        layout2 = new ConstraintSet();
        imageViewPhoto = findViewById(R.id.photo_const);

        mConstraintLayout = findViewById(R.id.schon_constraint);
        layout2.clone(this,R.layout.profile_expanded);
        layout1.clone(mConstraintLayout);

        imageViewPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isOpen){
                    TransitionManager.beginDelayedTransition(mConstraintLayout);
                    layout2.applyTo(mConstraintLayout);
                    isOpen = !isOpen;
                }
                else{
                    TransitionManager.beginDelayedTransition(mConstraintLayout);
                    layout1.applyTo(mConstraintLayout);
                    isOpen = !isOpen;
                }
            }
        });
    }
}
