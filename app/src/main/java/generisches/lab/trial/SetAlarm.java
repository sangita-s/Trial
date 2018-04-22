package generisches.lab.trial;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class SetAlarm extends AppCompatActivity {

    TextView t;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        b = findViewById(R.id.btn_alm);
        t = findViewById(R.id.tv_alarm);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickADate();
            }
        });
    }

    private void pickADate() {
        TimePickerDialog tpd = new TimePickerDialog(SetAlarm.this, onTimeSetListener, Calendar.HOUR_OF_DAY, Calendar.MINUTE, false);
        tpd.setTitle("Set Alarm Time");
        tpd.show();
    }

    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar cal = Calendar.getInstance();
            Calendar cpy = (Calendar) cal.clone();

            cpy.set(Calendar.HOUR_OF_DAY, hourOfDay);
            cpy.set(Calendar.MINUTE, minute);
            cpy.set(Calendar.SECOND, 0);
            cpy.set(Calendar.MILLISECOND, 0);

            if(cpy.compareTo(cal) <= 0)
            {
                cpy.add(Calendar.DATE,1);
            }
            setAlarm(cpy);
        }
    };

    private void setAlarm(Calendar cpy) {
        t.setText("Alarm set for " + cpy.getTime());
        Intent i = new Intent(this, RecieveAlarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 1, i, 0);
        AlarmManager m = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        m.set(AlarmManager.RTC_WAKEUP, cpy.getTimeInMillis(),pi);
    }
}
