package generisches.lab.trial;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class NofityMe extends AppCompatActivity {

    NotificationCompat.Builder notif;
    private static final int uniqID = 1234;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nofity_me);

        b = findViewById(R.id.btn_notify);

        notif = new NotificationCompat.Builder(this);
        notif.setAutoCancel(true);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notif.setSmallIcon(R.drawable.ic_menu_send);
                notif.setTicker("ticker");
                notif.setWhen(System.currentTimeMillis());
                notif.setContentTitle("title");
                notif.setContentText("Text bosy is just a frigging notification.. ");

                Intent i = new Intent(NofityMe.this, MainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(NofityMe.this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
                notif.setContentIntent(pi);

                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                nm.notify(uniqID, notif.build());
            }
        });
    }
    public void buttonClicked(View view){
        notif.setSmallIcon(R.drawable.ic_menu_send);
        notif.setTicker("ticker");
        notif.setWhen(System.currentTimeMillis());
        notif.setContentTitle("title");
        notif.setContentText("Text bosy is just a frigging notification.. ");

        Intent i = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        notif.setContentIntent(pi);

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqID, notif.build());
    }
}
