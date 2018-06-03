package generisches.lab.trial;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class SDCard extends AppCompatActivity {

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int writepermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (writepermission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    Button sv, ld;
    EditText t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdcard);

        t = findViewById(R.id.textEnter);
        sv = findViewById(R.id.saveButton);
        ld = findViewById(R.id.showData);

        sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = t.getText().toString();
                File sd = Environment.getExternalStorageDirectory();
                File f = new File(sd.getAbsoluteFile() + "/Trail");
                f.mkdirs();
                File fp = new File(f,"test.txt");

                verifyStoragePermissions(SDCard.this);
                try {
                    FileOutputStream fo = new FileOutputStream(fp, true);
                    OutputStreamWriter os = new OutputStreamWriter(fo);
                    Log.d("SDCard", data);
                    os.append(data);
                    os.flush();
                    os.close();
                    Toast.makeText(SDCard.this, "Data is saved to SD Card", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        ld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File sd = Environment.getExternalStorageDirectory();
                File f = new File(sd.getAbsoluteFile() + "/Trail");
                File fp = new File(f, "test.txt");
                FileInputStream fi;
                try {
                    fi = new FileInputStream(fp);
                    InputStreamReader isr = new InputStreamReader(fi);
                    char[] data = new char[100];
                    String fin_data = "";
                    int size;
                    while ((size = isr.read(data)) > 0) {
                        String s = String.copyValueOf(data, 0, size);
                        fin_data += s;
                        data = new char[100];
                    }
                    Toast.makeText(SDCard.this, fin_data, Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
