package generisches.lab.trial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Sangita on 08-05-2018.
 */

public class Tab2Fragment extends Fragment {
    public static final String TAG = "Tab2Fragment";
    private Button btnTest;
    private TextView tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_fragment, container, false);

        btnTest = view.findViewById(R.id.tstBtn2);
        tv = view.findViewById(R.id.textTab2);

        tv.setText("Click to Download");

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTask myTask = new MyTask(getContext(), tv, btnTest);
                myTask.execute();
                btnTest.setEnabled(false);
                //Toast.makeText(getActivity(),"Testing btn click 2", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
