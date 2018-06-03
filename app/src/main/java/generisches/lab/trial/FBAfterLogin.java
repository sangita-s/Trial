package generisches.lab.trial;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class FBAfterLogin extends AppCompatActivity {
    private Button notes, uploadimg, signout, newNotes;
    private TextView mTextView;
    private Context mContext = FBAfterLogin.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fbafter_login);

        notes = findViewById(R.id.btn_notess);
        uploadimg = findViewById(R.id.btn_uploadpage);
        signout = findViewById(R.id.btn_signout);
        mTextView = findViewById(R.id.textView8);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                toastMessage("Signing Out...");
                Intent i = new Intent(mContext, FirebaseAuthBasics.class);
                startActivity(i);
            }
        });
        uploadimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, UploadActivity.class);
                startActivity(i);
            }
        });
        //Listview Notes pressed
        mTextView.setText("Hi " + getIntent().getStringExtra("mAuthname"));
        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(mContext, NoteList.class);
                Intent i = new Intent(mContext, NoteList.class);
                i.putExtra("uid",getIntent().getStringExtra("mAuthID"));
                startActivity(i);
            }
        });
    }

    private void toastMessage(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}
