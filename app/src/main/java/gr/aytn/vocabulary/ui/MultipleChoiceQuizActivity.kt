package gr.aytn.vocabulary.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import gr.aytn.roomexample.WordPairDatabase
import gr.aytn.vocabulary.R
import gr.aytn.vocabulary.adapter.VocabularyAdapter
import gr.aytn.vocabulary.model.WordPair
import gr.aytn.vocabulary.repository.WordPairRepository
import kotlin.math.floor
import kotlin.random.Random

@AndroidEntryPoint
class MultipleChoiceQuizActivity : AppCompatActivity() {

    var tvWord: TextView? = null
    var btnSubmit: Button? = null
    private val quizViewModel: QuizViewModel by viewModels()
    private var mCurrentPosition:Int = 0
    private var mWordPairs:List<WordPair>?=null
    private var mCorrectAnswers : Int = 0
    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null

    private var optionOne : TextView? = null
    private var optionTwo: TextView? = null
    private var optionThree : TextView? = null
    private var optionFour : TextView? = null

    private var mSelectedOption: String? = null

    private var options: ArrayList<TextView?>? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiple_choice_quiz)


//        quizViewModel.getWordPairs().observe(this, Observer {
//            mWordPairs = it
//            Log.i("Quiz","$mWordPairs")
//        })

        tvWord = findViewById(R.id.quiz_word)
        btnSubmit = findViewById(R.id.submit)

        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_progress)

        optionOne = findViewById(R.id.tv_option_one)
        optionTwo = findViewById(R.id.tv_option_two)
        optionThree = findViewById(R.id.tv_option_three)
        optionFour = findViewById(R.id.tv_option_four)



        setQuestion()

        optionOne?.setOnClickListener {
            optionOne?.let { selectedOptionView(it,optionOne?.text.toString()) }
        }
        optionTwo?.setOnClickListener {
            optionTwo?.let { selectedOptionView(it,optionTwo?.text.toString()) }
        }
        optionThree?.setOnClickListener {
            optionThree?.let { selectedOptionView(it,optionThree?.text.toString()) }
        }
        optionFour?.setOnClickListener {
            optionFour?.let { selectedOptionView(it,optionFour?.text.toString()) }
        }

        btnSubmit?.setOnClickListener {
            if (mSelectedOption != null) {
                Log.i("submit", "$mSelectedOption, ${mWordPairs?.get(mCurrentPosition)?.translation}")
                if(mSelectedOption == mWordPairs?.get(mCurrentPosition)?.translation){
                    mCorrectAnswers++
                }
                mCurrentPosition++
                when {
                    mCurrentPosition < mWordPairs!!.size -> {
                        mSelectedOption = null
                        setQuestion()
                    }
                    else ->{
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra("correct_answers",mCorrectAnswers)
                        intent.putExtra("total_questions",mWordPairs?.size)
                        startActivity(intent)
                        finish()
                    }

                }
            }
        }
    }
    private fun setQuestion(){
        defaultOptionsView()

        options = arrayListOf(optionOne,optionTwo,optionThree,optionFour)
        quizViewModel.getWordPairs().observe(this, Observer {

            mWordPairs = it
            val mOptions = it.toMutableList()

            progressBar?.max = mWordPairs?.size!!
            tvProgress?.text = "${mCurrentPosition+1}/${progressBar?.max}"

            val question = mWordPairs?.get(mCurrentPosition)
            progressBar?.progress = mCurrentPosition + 1
            tvWord?.text = question?.word

            (mOptions as ArrayList<WordPair>).remove(question)

            val size = mOptions.size
            val rightOption = options?.get(Random.nextInt(0,4))
            rightOption?.text = question?.translation
            options?.remove(rightOption)
            options?.get(0)?.text = mOptions.get(Random.nextInt(0, size/3)).translation
            options?.get(1)?.text = mOptions.get(Random.nextInt(size/3, size/3*2)).translation
            options?.get(2)?.text = mOptions.get(Random.nextInt(size/3*2, size)).translation
        })
    }
    private fun defaultOptionsView(){
        var options = ArrayList<TextView>()
        optionOne?.let {
            options.add(0,it)
        }
        optionTwo?.let {
            options.add(1,it)
        }
        optionThree?.let {
            options.add(2,it)
        }
        optionFour?.let {
            options.add(3,it)
        }
        for (option in options){
            option.setTextColor(Color.parseColor("#363A43"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this,R.drawable.option_drawable)
        }
    }
    private fun selectedOptionView(tv:TextView, selectedOption:String){
        defaultOptionsView()

        mSelectedOption = selectedOption
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this,R.drawable.selected_option_border_bg)
    }
}