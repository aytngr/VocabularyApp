package gr.aytn.vocabulary.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import gr.aytn.vocabulary.R

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val scoreView: TextView = findViewById(R.id.tv_score)
        val btnFinish: Button = findViewById(R.id.btn_finish)
        val tvMessage: TextView = findViewById(R.id.message)
        val img: ImageView = findViewById(R.id.img)

        val correctAnswers = intent.getIntExtra("correct_answers", 0)
        val totalQuestions = intent.getIntExtra("total_questions", 0)
        if(correctAnswers==totalQuestions){
            img.setImageResource(R.drawable.star)
            tvMessage.text = "You are the best!"
        }
        else if (correctAnswers>=totalQuestions/2){
            img.setImageResource(R.drawable.happy)
            tvMessage.text = "Congratulations!"
        }
        else if (correctAnswers<totalQuestions/2){
            img.setImageResource(R.drawable.pleading)
            tvMessage.text = "Better next time!"
        }
        scoreView.text = "Your Score is $correctAnswers out of $totalQuestions"
        btnFinish.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}