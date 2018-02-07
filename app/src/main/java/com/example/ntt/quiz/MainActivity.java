package com.example.ntt.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //変数の宣言
    TextView countTextView;
    TextView contentTextView;

    //Button　の配列（ボタンを３つセットで管理したいので配列で宣言）
    Button[] optionButtons;

    //現在の問題番号
    int questionNumber;

    //獲得したポイント数
    int point;

    //Listという、配列よりデータを追加しやすい型を使う
    List<Quiz> quizList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初期化処理
        //viewの関連付け
        countTextView = findViewById(R.id.countTextView);
        contentTextView = findViewById(R.id.contentTextView);

        //配列を初期化
        //今回、ボタンは３つなので３の大きさの配列を用意

        optionButtons = new Button[3];
        optionButtons[0] = findViewById(R.id.optionButton1);
        optionButtons[1] = findViewById(R.id.optionButton2);
        optionButtons[2] = findViewById(R.id.optionButton3);
        for (Button button : optionButtons) {
            //レイアウトではなくコードからonClickを設定
            //setOnClickListenerに、thisを入れて呼び出す
            button.setOnClickListener(this);
        }

        //Quizを1から始めるのでリセットする
        resetQuiz();

    }

    //出題するクイズのリストを作成
    void createQuizlist() {
        Quiz quiz1 = new Quiz("１ヶ月の平均時間外は？", "30時間以内", "30〜50時間", "数えられません...", "答え１");
        Quiz quiz2 = new Quiz("時間外手当はちゃんと出る？", "当然。出ないことなんてあるの？", "たまにはサービス残業もいいかも", "時間外手当って何ですか？", "答え５");
        Quiz quiz3 = new Quiz("休日はちゃんと休める？", "カレンダー通り", "月１回は休日出勤", "ここ１ヶ月、休んだ記憶がない...", "答え９");
        Quiz quiz4 = new Quiz("有給休暇はちゃんと取れる？", "気持ちよく使い切ってます！", "たまに取りづらい雰囲気あり...", "有給って使っていいんですか？", "答え１１");
        Quiz quiz5 = new Quiz("職場の人間関係は？", "誰とでも何でも話せます！", "うーん、上司には物申しづらい...", "上司に逆らった人が翌日から消える", "答え１５");
        Quiz quiz6 = new Quiz("社員の定着率は？", "みんな定年まで働きます", "30歳までに転職する人が多いかも", "みんな１年以内に辞めます...", "答え１５");
        Quiz quiz7 = new Quiz("出社時間は？", "定時出社です", "1時間前には出社しないと怒られる", "毎日、始発で出社してます...", "答え１５");
        Quiz quiz8 = new Quiz("退社時間は？", "定時退社です", "上司より先には帰れない", "週に２〜３日は帰れない", "答え１５");
        Quiz quiz9 = new Quiz("ハラスメントはある？", "ハラスメントって何ですか？", "たまにあるけど許容範囲", "毎日がハラスメント漬け...", "答え１５");
        Quiz quiz10 = new Quiz("会社の飲み会は？", "自由参加", "断ると上司の視線が冷たい", "何があっても強制参加...", "答え１５");
        Quiz quiz11 = new Quiz("プライベートは？", "一切干渉されない", "あれこれ報告義務あり", "上司の手伝いでよく借り出される...", "答え１５");
        Quiz quiz12 = new Quiz("福利厚生は？", "各種補助から社宅まで納得の内容！", "必要最低限って感じかな...", "福利厚生って何ですか？", "答え１５");
        Quiz quiz13 = new Quiz("各自の仕事量は？", "ほぼ均等に割り振り", "繁忙期などはバラツキあり", "特定の人だけ死ぬほど忙しい", "答え１５");
        Quiz quiz14 = new Quiz("社員の年齢分布は？", "各世代が満遍なくいる", "就職氷河期世代だけいない", "年配者しかいない...", "答え１５");
        Quiz quiz15 = new Quiz("社内の雰囲気は？", "私がやる・協力する・楽しくやる", "自分の業務以外は絶対やらない", "常に足の引っ張り合い...", "答え１５");

        //Listを実装したArrayListを使う
        quizList = new ArrayList<>();
        quizList.add(quiz1);
        quizList.add(quiz2);
        quizList.add(quiz3);
        quizList.add(quiz4);
        quizList.add(quiz5);
        quizList.add(quiz6);
        quizList.add(quiz7);
        quizList.add(quiz8);
        quizList.add(quiz9);
        quizList.add(quiz10);
        quizList.add(quiz11);
        quizList.add(quiz12);
        quizList.add(quiz13);
        quizList.add(quiz14);
        quizList.add(quiz15);

        //リストの中身をシャッフル
        Collections.shuffle(quizList);
    }

    //クイズを表示
    void showQuiz() {
        countTextView.setText("第" + (questionNumber + 1) + "問");

        //表示する問題をリストから取得
        //配列では番号だったが、リストではget(番号)で取得。配列と同じく0からスタート
        Quiz quiz = quizList.get(questionNumber);
        contentTextView.setText(quiz.content);
        optionButtons[0].setText(quiz.option1);
        optionButtons[1].setText(quiz.option2);
        optionButtons[2].setText(quiz.option3);

    }

    //クイズのリセット
    void resetQuiz() {
        questionNumber = 0;
        point = 0;
        createQuizlist();
        showQuiz();

    }

    //クイズのアップデート
    void updateQuiz() {
        questionNumber++;
        if (questionNumber < quizList.size()) {
            showQuiz();

//        } else if (point >= 21) {
//            Intent intent = new Intent(this, goodpage.class);
//            intent.putExtra("good",point);
//            startActivity(intent);
//
//
//        } else if (point <= 10) {
//            Intent intent = new Intent(this, badpage.class);
//            intent.putExtra("bad",point);
//            startActivity(intent);

        } else {
            Intent intent = new Intent(this, stresspage.class);
            intent.putExtra("point",point);
            startActivity(intent);
        }
    }

            //全ての問題を解いてしまったので結果を表示します
            //結果表示にはDialogを使います。
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle("診断結果");
//            builder.setMessage(quizList.size()*3 + "点中、" + point + "点でした！");
//            builder.setPositiveButton("リトライ", new DialogInterface.OnClickListener(){


//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    //もう一度クイズをやり直す
//                    resetQuiz();
//                }
        // builder.show();


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.optionButton1:
                point += 0;
                break;
            case R.id.optionButton2:
                point += 1;
                break;
            case R.id.optionButton3:
                point += 3;
                break;

            default:
                break;
        }

        //次の問題にアップデートします
        updateQuiz();

    }

}


    //setOnClickListerを呼び出したViewをクリックした時に呼び出されるメソッド
//    @Override
//    public void onClick(View view) {
//        //引数のViewには押されたViewが入っています。
//        //Buttonが押された時しか呼ばれないのでキャストと言って型を示してあげます
//        Button clickedButton = (Button) view;
//        Quiz quiz = quizList.get(questionNumber);
//
//        //ボタンの文字を押した時、どの選択肢かチェックします
//        if (TextUtils.equals(clickedButton.getText(), quiz.answer)) {
//
//            //１つ目の選択肢だと３点、２つ目の選択肢だと１点、３つ目の選択肢だと０点のポイント加算します。
//            point + 3;
//
//            Toast.makeText(option1, "すごい！", Toast.LENGTH_SHORT).show();
//        } else {
//
//            Toast.makeText(this, "残念！", Toast.LENGTH_SHORT).show();
//        }
//
//        //次の問題にアップデートします
//        updateQuiz();
//
//    }
