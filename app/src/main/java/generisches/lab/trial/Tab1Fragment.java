package generisches.lab.trial;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

public class Tab1Fragment extends Fragment {
    public static final String TAG = "Tab1Fragment";
    private Button btnTest;
    private TextView tv;
    Dialog mDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment, container, false);
        btnTest = view.findViewById(R.id.tstBtn1);
        tv = view.findViewById(R.id.textTab1);

        mDialog = new Dialog(getActivity());
        tv.setText("This is tab 1");

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Testing btn click 1", Toast.LENGTH_SHORT).show();
                showPopup(v);
            }
        });
        return view;
    }

    public void showPopup(View v){
        TextView txtClose;
        Button btnFollow;
        mDialog.setContentView(R.layout.custompopup);
        txtClose = mDialog.findViewById(R.id.closetxt);
        btnFollow = mDialog.findViewById(R.id.followbtn);
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();
    }
}
