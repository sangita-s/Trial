package generisches.lab.trial;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Sangita on 10-05-2018.
 */

public class MyTask extends AsyncTask<Void, Integer, String> {

    Context mContext;
    TextView tv;
    Button btn;
    ProgressDialog progressDiaog;
    MyTask(Context context, TextView textview, Button button){
        mContext = context;
        tv = textview;
        btn = button;
    }

    @Override
    protected String doInBackground(Void... params) {

        int i = 0;
        synchronized (this)
        {
            while(i<10)
            {
                try {
                    wait(1500);
                    i++;
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Download Complete...";
    }

    @Override
    protected void onPreExecute() {
        progressDiaog = new ProgressDialog(mContext);
        progressDiaog.setTitle("Download in progress..");
        progressDiaog.setMax(10);
        progressDiaog.setProgress(0);
        progressDiaog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDiaog.show();
    }

    @Override
    protected void onPostExecute(String result) {
        tv.setText(result);
        btn.setEnabled(true);
        progressDiaog.hide();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        progressDiaog.setProgress(progress);
        tv.setText("Downloading...");
    }
}
