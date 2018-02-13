package com.example.ntt.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class goodpage extends AppCompatActivity {

    int point;

    TextView resultText;

    TextView resultComent;

    ImageView resultImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodpage);

        Intent intent = getIntent();

        point = intent.getIntExtra("point", 0);

        Log.d("percent" ,"point is ..." + point);

        resultText = findViewById(R.id.result);
        resultComent = findViewById(R.id.coment);
        resultImage = findViewById(R.id.imageResult);

        int pointPercent = (int) ((float) point / 55f * 100f);

        if (point >= 45) {
            resultText.setText("ブラック度 " + pointPercent + "％ (T_T)");
            resultComent.setText("超ブラック！ \n 生きてるうちに辞めて〜！");
            resultImage.setImageResource(R.mipmap.sbad);

        } else if (point < 45 && point >= 34) {

            resultText.setText("ブラック度　" + pointPercent + "％ ...");
            resultComent.setText("ブラックかも〜 \n 転職活動はじめましょ〜");
            resultImage.setImageResource(R.mipmap.bad);

        } else if (point > 11 && point <= 22) {

            resultText.setText("ブラック度　" + pointPercent + "％ ！");
            resultComent.setText("まあホワイトじゃない？ \n 多少の不満は目をつむって〜");
            resultImage.setImageResource(R.mipmap.good);

        } else if (point <= 11) {

            resultText.setText("ブラック度　" + pointPercent + "％　(^_^)");
            resultComent.setText("超ホワイト！ \n 定年までしがみついて〜！");
            resultImage.setImageResource(R.mipmap.sgood);

        } else {

            resultText.setText("ブラック度　" + pointPercent + "％");
            resultComent.setText("フツー。 \n 世の中こんなもんですよ〜");
            resultImage.setImageResource(R.mipmap.middle);

        }

    }

    public void click(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}
