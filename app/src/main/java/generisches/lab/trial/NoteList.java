package generisches.lab.trial;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class NoteList extends AppCompatActivity {

    RecyclerView rec;
    private  NoteRecyclerAdapter mNoteRecyclerAdapter;
    private DatabaseReference mDatabaseReference;
    List<NoteInfo> noteList = new ArrayList<>();
    public static List<String> header = new ArrayList<>();
    private Dialog mDialog;

    public static String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        mDialog = new Dialog(NoteList.this);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users")
                                .child(getIntent().getStringExtra("uid"))
                                .child("Notes");
        Log.e("onCreate(): DB Ref : ",mDatabaseReference.toString());
        uid = getIntent().getStringExtra("uid");
        rec = findViewById(R.id.list_notes_recycler);
        rec.setHasFixedSize(false);
        LinearLayoutManager notesLayoutManager = new LinearLayoutManager(this);
        notesLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rec.setLayoutManager(notesLayoutManager);

        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot uniqKeySnapshot : dataSnapshot.getChildren()) {
                    header.add(uniqKeySnapshot.getKey());
                    final NoteInfo local_noteInfo = new NoteInfo();
                    local_noteInfo.mText = uniqKeySnapshot.child("NoteText").getValue(String.class);
                    local_noteInfo.mTitle = uniqKeySnapshot.child("NoteTitle").getValue(String.class);
                    noteList.add(local_noteInfo);
                    Log.e("mDbRef.+Lis4SingValEv: ","In here");
                }
                mNoteRecyclerAdapter = new NoteRecyclerAdapter(noteList);
                rec.setAdapter(mNoteRecyclerAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO: Implement notify datasetchanged
        //https://stackoverflow.com/questions/8785221/retrieve-a-fragment-from-a-viewpager/15261142#15261142
        Log.e("onResume(): ","In here");
        mNoteRecyclerAdapter = new NoteRecyclerAdapter(noteList);
        mNoteRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("onPause(): ","In here");
    }

    private void showPopup(View view) {
        Log.e("showpopup(): ","In here");
        Button create, close;
        final EditText title, text;
        mDialog.setContentView(R.layout.layout_notefill_popup);
        create = mDialog.findViewById(R.id.create_note_btn);
        close = mDialog.findViewById(R.id.cancel_note_btn);
        title = mDialog.findViewById(R.id.edit_note_title);
        text = mDialog.findViewById(R.id.edit_note_text);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users")
                        .child(getIntent().getStringExtra("uid"))
                        .child("Notes");
                SimpleDateFormat date = new SimpleDateFormat("yyMMddkkmmss");
                String name = date.format(new Date());

                NoteInfo n = new NoteInfo();
                n.mTitle = title.getText().toString();
                n.mText = text.getText().toString();

                //mDatabaseReference.child(name).child("NoteTitle").setValue(n.mTitle);
                //mDatabaseReference.child(name).child("NoteText").setValue(n.mText);
                HashMap<String, String> hash = new HashMap<>();
                hash.put("NoteTitle",n.mTitle);
                hash.put("NoteText",n.mText);
                mDatabaseReference.child(name).setValue(hash);
                mDialog.dismiss();
            }
        });
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();
    }

}
