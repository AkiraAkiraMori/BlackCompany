package com.example.ntt.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import orz.kassy.shakegesture.ShakeGestureManager;

/**
 * Created by ntt on 2018/02/07.
 */

public class stresspage extends AppCompatActivity {

    int point;
    int shakeCount = 0;
    ShakeGestureManager mGestureManager;

    private Handler mHandler;
    private Runnable mShowGoodPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stresspage);


        // インテントを取得
        Intent intent = getIntent();
        //　インテントに保温されたデータを取得
        point = intent.getIntExtra("point", 0);
        int pointPercent = (int) ((float) point / 45f * 100f);

        Log.d("stresspage:percent", "quiz point is ..." + point);

        mShowGoodPage = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(stresspage.this, goodpage.class);
                intent.putExtra("point", point); //追加
                intent.putExtra("shakeCount", shakeCount);
                startActivity(intent);
            }
        };

        mHandler = new Handler();
        mHandler.postDelayed(mShowGoodPage, 10 * 1000);
    }

    protected void onResume() {
        super.onResume();
        mGestureManager = new ShakeGestureManager(this, mListener);
        mGestureManager.startSensing();
    }

    protected void onPause() {
        super.onPause();
        mGestureManager.stopSensing();
    }

    private ShakeGestureManager.GestureListener mListener = new ShakeGestureManager.GestureListener() {
        @Override
        public void onGestureDetected(int gestureType, int gestureCount) {

            if (shakeCount < 5) {
                shakeCount = shakeCount + 1;

                Log.d("stresspage:percent", "shakeCount is ..." + shakeCount);

            } else {
                // 10回超シェイクを認識したらここが呼ばれる
                Log.d("stresspage:percent", "shaked shakeCount is ..." + shakeCount);
                Intent intent = new Intent(stresspage.this, goodpage.class);
                intent.putExtra("point", point);
                intent.putExtra("shakeCount", shakeCount);
//                intent.putExtra("point", point + shakeCount * 2); //追加
                startActivity(intent);

                mHandler.removeCallbacks(mShowGoodPage);
            }

        }

        @Override
        public void onMessage(String s) {

        }
    };

}






