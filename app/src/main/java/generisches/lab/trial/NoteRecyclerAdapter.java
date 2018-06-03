package generisches.lab.trial;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sangita on 06-05-2018.
 */

public class NoteRecyclerAdapter extends RecyclerView.Adapter<NoteRecyclerAdapter.NotesViewHolder>{

    private Dialog myDialog;
    private Dialog delDialog;
    private DatabaseReference mDatabaseReference;

    private String uniqID = NoteList.uid;

    private int selectedPos = RecyclerView.NO_POSITION;

    private final List<NoteInfo> mNotes;
    public NoteRecyclerAdapter(List<NoteInfo> notes) {
        mNotes = notes;
    }


    @Override
    public NotesViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_note, parent, false);

        //For dialog start
        final NoteRecyclerAdapter.NotesViewHolder vh = new NoteRecyclerAdapter.NotesViewHolder(v);
        myDialog = new Dialog(parent.getContext());
        myDialog.setContentView(R.layout.layout_notefill_popup);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //Onclick for item in recycler view
        vh.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = myDialog.findViewById(R.id.dialog_notefill_title);
                tv.setText("Update Note");

                Button update, cancel;

                final EditText title = myDialog.findViewById(R.id.edit_note_title);
                final EditText text = myDialog.findViewById(R.id.edit_note_text);

                //Set Text from get adapter position from viewholder
                title.setText(mNotes.get(vh.getAdapterPosition()).mTitle);
                text.setText(mNotes.get(vh.getAdapterPosition()).mText);

                //Onclick for cancel
                cancel = myDialog.findViewById(R.id.cancel_note_btn);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });

                //onclick for update
                update = myDialog.findViewById(R.id.create_note_btn);
                update.setText("Update");
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users")
                                .child(uniqID)
                                .child("Notes")
                                .child(NoteList.header.get(vh.getAdapterPosition()));
                        //Toast.makeText(parent.getContext(),"Header is " + header,Toast.LENGTH_SHORT).show();

                        Map<String,Object> taskMap = new HashMap<String,Object>();
                        taskMap.put("NoteTitle",title.getText().toString());
                        taskMap.put("NoteText",text.getText().toString());
                        mDatabaseReference.updateChildren(taskMap);
                        //mDatabaseReference.setValue(hash);
                        myDialog.dismiss();
                    }
                });

                myDialog.show();
                Toast.makeText(parent.getContext(),"Clicked " + String.valueOf(vh.getAdapterPosition()),Toast.LENGTH_SHORT).show();
            }
        });
        //For dialog end
        vh.mLinearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                notifyItemChanged(selectedPos);
                selectedPos = vh.getAdapterPosition();
                notifyItemChanged(selectedPos);

                delDialog = new Dialog(parent.getContext());
                delDialog.setContentView(R.layout.layout_notedel_popup);
                delDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                Button delete, cncl;
                delete = delDialog.findViewById(R.id.delete_note_btn);
                cncl = delDialog.findViewById(R.id.cancel_note_btn);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users")
                                .child(uniqID)
                                .child("Notes")
                                .child(NoteList.header.get(selectedPos));
                        mDatabaseReference.removeValue();
                        Toast.makeText(parent.getContext(),"Header pos" + selectedPos, Toast.LENGTH_SHORT).show();
                        delDialog.dismiss();
                        selectedPos = RecyclerView.NO_POSITION;
                    }
                });
                cncl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        delDialog.dismiss();
                    }
                });
                delDialog.show();
                Toast.makeText(parent.getContext(),"Fabulous," + selectedPos, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        //return new NotesViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(NotesViewHolder holder, int position) {
        NoteInfo n = mNotes.get(position);
        holder.mNoteText.setText(n.mText);
        holder.mNoteTitle.setText(n.mTitle);
        holder.itemView.setSelected(selectedPos == position);
        //holder.mFloatingActionButton.setImageResource(R.drawable.ic_add_circle);
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }


    public class NotesViewHolder extends RecyclerView.ViewHolder {

        String header;
        private TextView mNoteTitle;
        private TextView mNoteText;
        private LinearLayout mLinearLayout;
        private FloatingActionButton mFloatingActionButton;

        public NotesViewHolder(View itemView) {
            super(itemView);
            mNoteTitle = itemView.findViewById(R.id.text_notetitle);
            mNoteText = itemView.findViewById(R.id.text_notetext);
            mLinearLayout = itemView.findViewById(R.id.note_item_id);
            mFloatingActionButton = itemView.findViewById(R.id.fab);
        }
    }
}
