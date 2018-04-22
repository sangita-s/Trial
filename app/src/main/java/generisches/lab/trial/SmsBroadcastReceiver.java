package generisches.lab.trial;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsMessage;
import android.view.WindowManager;

public class SmsBroadcastReceiver extends BroadcastReceiver {

    public static final String SMS_BUNDLE = "pdus";

    @Override
    public void onReceive(Context context, Intent intent) {
        int j;
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] sm = (Object[]) bundle.get(SMS_BUNDLE);
            String sms = "";

            for (j = 0; j < sm.length; j++) {
                SmsMessage s = SmsMessage.createFromPdu((byte[]) sm[j]);
                String smsbody = s.getMessageBody().toString();
                String ori = s.getOriginatingAddress().toString();

                sms += "FROM: " + ori + "\n";
                sms += "BODY: " + smsbody + "\n";
            }
            AlertDialog.Builder local_builder = new AlertDialog.Builder(context);
            local_builder.setTitle("MESSAGE");
            local_builder.setMessage(sms);
            AlertDialog local_alertDialog = local_builder.create();

            local_alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            local_alertDialog.show();
            SMSRecieve inst = SMSRecieve.instance();
            inst.updateList(sms);
        }
    }
}
