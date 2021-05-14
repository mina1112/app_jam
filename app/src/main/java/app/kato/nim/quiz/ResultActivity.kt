package app.kato.nim.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val quizCount: Int = intent.getIntExtra("QuizCount",0)
        quizCountText.text = "$quizCount 問中・・・"

        val correctCount: Int = intent.getIntExtra("CorrectCount", 0)
        correctCountText.text = correctCount.toString()

        againButton.setOnClickListener {

            val quizIntent: Intent = Intent(this, QuizActivity::class.java)
            startActivity(quizIntent)

        }
    }
}