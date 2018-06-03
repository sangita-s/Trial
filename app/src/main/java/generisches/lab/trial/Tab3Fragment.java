package generisches.lab.trial;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Sangita on 08-05-2018.
 */

public class Tab3Fragment extends Fragment {
    public static final String TAG = "Tab3Fragment";
    private Button btnTest;
    private TextView tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3_fragment, container, false);
        btnTest = view.findViewById(R.id.tstBtn3);
        tv = view.findViewById(R.id.textTab3);

        tv.setText("This is tab 3");

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Testing btn click 3", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), SchonConstraint.class);
                startActivity(i);
            }
        });

        return view;
    }
}
