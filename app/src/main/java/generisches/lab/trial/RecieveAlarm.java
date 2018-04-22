package generisches.lab.trial;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class RecieveAlarm extends BroadcastReceiver {
    MediaPlayer ring;
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "ALARM !!!! ", Toast.LENGTH_LONG).show();
        ring = MediaPlayer.create(context, R.raw.move);
        ring.start();
    }
}
