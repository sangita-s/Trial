package generisches.lab.trial;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DBMain extends AppCompatActivity {

    EditText reg, name, gpa;
    Button ins, upd, del, see;
    String r, n, g;
    DB d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbmain);

        reg = findViewById(R.id.edit_regno);
        name = findViewById(R.id.edit_name);
        gpa = findViewById(R.id.edit_gpa);
        ins = findViewById(R.id.btn_db_insert);
        upd = findViewById(R.id.btn_db_update);
        del = findViewById(R.id.btn_db_delete);
        see = findViewById(R.id.btn_db_view);
        d = new DB(this);

        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r = reg.getText().toString();
                n = name.getText().toString();
                g = gpa.getText().toString();
                if (r.equals("") || n.equals("") || g.equals("")) {
                    Toast.makeText(DBMain.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean ch = d.insert(r, n, g);
                    if (ch) {
                        Toast.makeText(DBMain.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DBMain.this, "Error Inserting Data", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r = reg.getText().toString();
                n = name.getText().toString();
                g = gpa.getText().toString();
                if (r.equals("") || n.equals("") || g.equals("")) {
                    Toast.makeText(DBMain.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean ch = d.update(r, n, g);
                    if (ch) {
                        Toast.makeText(DBMain.this, "Data Updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DBMain.this, "Error Updating Data", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r = reg.getText().toString();
                if (r.equals("")) {
                    Toast.makeText(DBMain.this, "Please fill all the register number", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean ch = d.delete(r);
                    if (ch) {
                        Toast.makeText(DBMain.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DBMain.this, "Error Deleting Data", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r = reg.getText().toString();
                Cursor c;
                StringBuffer buffer = new StringBuffer();

                if (r.equals("")) {
                    c = d.viewall();
                    if (c.getCount() == 0) {
                        showmsg("Error", " ** Table is empty ** ");
                        //Toast.makeText(DBMain.this, "Error in Showing all", Toast.LENGTH_SHORT).show();
                    } else {
                        while (c.moveToNext()) {
                            buffer.append("Reg No: " + c.getString(0) + "\n");
                            buffer.append("Name: " + c.getString(1) + "\n");
                            buffer.append("GPA: " + c.getString(2) + "\n");
                        }
                        showmsg("Database", buffer.toString());
                        //Toast.makeText(DBMain.this, "Showing all", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    c = d.view1(r);
                    if (c.moveToNext()) {
                        buffer.append("Reg No: " + c.getString(0) + "\n");
                        buffer.append("Name: " + c.getString(1) + "\n");
                        buffer.append("GPA: " + c.getString(2) + "\n");
                        showmsg("Database", buffer.toString());
                        //Toast.makeText(DBMain.this, "Showing one", Toast.LENGTH_SHORT).show();
                    } else {
                        showmsg("Error", "Data not found");
                        //Toast.makeText(DBMain.this, "Error in Showing one", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void showmsg(String error, String s) {
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setCancelable(true);
        build.setTitle(error);
        build.setMessage(s);
        build.show();
    }
}
