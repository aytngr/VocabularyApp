package gr.aytn.vocabulary.ui

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import dagger.hilt.android.AndroidEntryPoint
import gr.aytn.vocabulary.R
import gr.aytn.vocabulary.adapter.CardAdapter
import gr.aytn.vocabulary.model.WordPair


@AndroidEntryPoint
class CardsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cards)
        val vocabularyViewModel: VocabularyViewModel by viewModels()


//        front.cameraDistance = 8000 * scale
//        back.cameraDistance = 8000 * scale

        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        vocabularyViewModel.getWordPairs().observe(this, Observer {
            val adapter = CardAdapter(it as ArrayList<WordPair>,this)
            viewPager.adapter = adapter
        })
        viewPager.setPadding(0, 0, 0, 0)

        viewPager.setOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })

























//        card.setOnTouchListener(
//            View.OnTouchListener { v, event ->
//
//                // variables to store current configuration of quote card.
//                val displayMetrics = resources.displayMetrics
//                val cardWidth = card.width
//                val cardStart = (displayMetrics.widthPixels.toFloat() / 2) - (cardWidth / 2)
//                val cardEnd = (displayMetrics.widthPixels.toFloat() / 2) + (cardWidth / 2)
//
//                when (event.action) {
//                    MotionEvent.ACTION_UP -> {
//                        var currentX = card.x
//                        card.animate()
//                            .x(cardStart)
//                            .setDuration(150)
//                            .setListener(
//                                object : AnimatorListenerAdapter() {
//                                    override fun onAnimationEnd(animation: Animator) {
//                                        Log.i("sacfed","card swiped $swipe")
//                                        if (swipe == "Right"){
//                                            if (currentPosition+1 >= mWordPairList?.size!!){
//                                                currentPosition = -1
//                                            }
//                                            cardText.text = mWordPairList?.get(currentPosition+1)?.word
//                                            currentPosition++
//                                        }
//                                        if (swipe == "Left"){
//                                            if (currentPosition < mWordPairList?.size!!){
//                                                currentPosition = mWordPairList?.size!! -1
//                                            }
//                                            cardText.text = mWordPairList?.get(currentPosition-1)?.word
//                                            currentPosition--
//                                        }
//                                    }
//                                }
//                            )
//                            .start()
//                    }
//                    MotionEvent.ACTION_MOVE -> {
//                        // get the new co-ordinate of X-axis
//                        val newX = event.rawX
//                        Log.i("tag","newx: $newX, cardwidth: $cardWidth, cardstart: $cardStart,cardend: $cardEnd")
//                        // carry out swipe only if newX < cardStart, that is,
//                        // the card is swiped to the left side, not to the right
//                        if (newX - cardWidth < cardStart) {
//                            swipe = "Right"
//                            card.animate()
//                                .x(
//                                    min(cardStart, newX - (cardWidth / 2))
//                                )
//                                .setDuration(0)
//                                .start()
//
//                        }
//                        if (newX - cardStart > cardStart) {
//                            swipe = "Left"
//                            card.animate()
//                                .x(
//                                    min(cardEnd, (newX-(cardWidth/2)))
//                                )
//                                .setDuration(0)
//                                .start()
//
//                        }
//                    }
//                }
//
//                // required to by-pass lint warning
//                v.performClick()
//                return@OnTouchListener true
//                })
    }
}