package generisches.lab.trial;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Sangita on 05-05-2018.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    private static final String REG_TOKEN = "REG_TOKEN";
    String recent_token;

    @Override
    public void onTokenRefresh() {
        recent_token = FirebaseInstanceId.getInstance().getToken();
        Log.i(REG_TOKEN, recent_token);
        Log.i(REG_TOKEN, FirebaseInstanceId.getInstance().getToken());
        Log.i(REG_TOKEN, FirebaseInstanceId.getInstance().getId());

        sendToken(recent_token);
    }
    private void sendToken(String recent_token) {
        //Send token to server
    }
}
