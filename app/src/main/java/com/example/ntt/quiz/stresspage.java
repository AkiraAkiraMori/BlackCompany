package com.example.ntt.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import orz.kassy.shakegesture.ShakeGestureManager;

/**
 * Created by ntt on 2018/02/07.
 */

public class stresspage extends AppCompatActivity {

    int point;
    ShakeGestureManager mGestureManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stresspage);

        // インテントを取得
        Intent intent = getIntent();

        //　インテントに保温されたデータを取得
        point = intent.getIntExtra("point", 0);

        int pointPercent = (int) ((float) point / 45f * 100f);

        Log.d("percent" ,"point is ..." + point);
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
            // ジェスチャーを認識したらここが呼ばれる


            Intent intent = new Intent(stresspage.this, goodpage.class);
            intent.putExtra("point", point);
            startActivity(intent);

        }

        @Override
        public void onMessage(String s) {

        }
    };

}






