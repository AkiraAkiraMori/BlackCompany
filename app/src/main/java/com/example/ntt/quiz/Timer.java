package com.example.ntt.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by ntt on 2018/02/13.
 */

public class Timer extends AsyncTask <Integer, Integer, Integer> {





    public boolean isFinish = false;
    public Activity mActivity;

    // 非同期処理
    @Override
    protected Integer doInBackground(Integer... params) {

        // 10秒数える処理
        do{
            try {
                //　1sec sleep
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            Log.d("debug",""+params[0]);
            params[0]++;
            // 途中経過を返す
            publishProgress(params[0]);

        }while(params[0]<7);

        return params[0] ;
    }

    // 途中経過をメインスレッドに返す
    @Override
    protected void onProgressUpdate(Integer... progress) {

    }

    // 非同期処理が終了後、結果をメインスレッドに返す
    @Override
    protected void onPostExecute(Integer result) {

       mActivity.startActivity(new Intent(mActivity,goodpage.class));

    }


}
