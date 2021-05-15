package app.kato.nim.quiz

import android.content.Intent
import android.content.IntentSender
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.activity_result.*

class QuizActivity : AppCompatActivity() {

/*
//残り時間のセット
    var second = 10
//タイマーをセットする
    val timer : CountDownTimer = object : CountDownTimer(10000,1000) {
    //タイマーが終了するときに呼ばれる
    override fun onFinish() {
        //残り時間をリセット
        second = 10
        //時間の表示をもとに戻す
        secondText.text = second.toString()


        //thisに@QuizActivityをつける！！！！！！！！！！！
        //時間切れ画面
        val timeoverIntent: Intent = Intent(this@QuizActivity, TimeOver::class.java)
        startActivity(timeoverIntent)


    }
*/

/*
    override fun onTick(millisUntilFinished: Long) {
        //残り時間を1秒ずつ減らして表示
        second = second - 1
        secondText.text = second.toString()
    }

}
*/

    val quizLists: List<List<String>> = listOf(

        listOf("10+8+5=？","18","21","23","23"),
        listOf("31+16+2=？","47","49","51","49"),
        listOf("108+23+16=？","137","147","157","147")

    )

    val shuffledLists: List<List<String>> = quizLists.shuffled()

    var quizCount: Int = 0

    var correctAnswer: String = ""

    var correctCount: Int = 0


    //最初に表示する文字
    var falseCount: Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        //まちがい　赤字のはidつくる
       /* val falseCount: Int = intent.getIntExtra("falseCount", 0)
        falseCountText.text = falseCount.toString() */

        //残り時間を表示する
        /*secondText.text = second.toString()


        //タイマーのカウントを開始する。
        timer.start()
*/




            showQuestion()

            answerButton1.setOnClickListener {

                checkAnswer(answerButton1.text.toString())
            }

            answerButton2.setOnClickListener {
                checkAnswer(answerButton2.text.toString())
            }

            answerButton3.setOnClickListener {
                checkAnswer(answerButton3.text.toString())
            }



            nextButton.setOnClickListener {
                if (quizCount == quizLists.size) {

                    val resultIntent: Intent = Intent(this, ResultActivity::class.java)
                    resultIntent.putExtra("QuizCount", quizLists.size)
                    resultIntent.putExtra("CorrectCount", correctCount)
                    startActivity(resultIntent)

                } else {

                    judgeImage.isVisible = false
                    nextButton.isVisible = false

                    answerButton1.isEnabled = true
                    answerButton2.isEnabled = true
                    answerButton3.isEnabled = true

                    correctAnswerText.text = ""

                    showQuestion()

                }

        }

    }

    fun showQuestion() {
        val question: List<String> = shuffledLists[quizCount]
        Log.d("debug", question.toString())

        quizText.text = question[0]

        answerButton1.text = question[1]
        answerButton2.text = question[2]
        answerButton3.text = question[3]
        correctAnswer = question[4]

    }


    //答えをチェックする
    fun checkAnswer(answerText: String) {
        if (answerText == correctAnswer) {
            judgeImage.setImageResource(R.drawable.maru_image)
            correctCount ++
        }else {
            judgeImage.setImageResource(R.drawable.batu_image)

            falseCount =falseCount - 1

            countText.text = falseCount.toString()

            if(falseCount == 0) {
                val timeoverIntent: Intent = Intent(this@QuizActivity, TimeOver::class.java)
                startActivity(timeoverIntent)
            } else {

            }

        }
        showAnsewer()

        quizCount ++
    }

    fun showAnsewer() {

        correctAnswerText.text = "正解: $correctAnswer"
        judgeImage.isVisible = true
        nextButton.isVisible = true
        answerButton1.isEnabled = false
        answerButton2.isEnabled = false
        answerButton3.isEnabled = false
    }





}