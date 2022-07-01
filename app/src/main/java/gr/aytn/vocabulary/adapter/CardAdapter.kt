package gr.aytn.vocabulary.adapter

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.animation.doOnEnd
import androidx.viewpager.widget.PagerAdapter
import gr.aytn.vocabulary.R
import gr.aytn.vocabulary.model.WordPair


class CardAdapter(private val mList: ArrayList<WordPair>,private val context: Context): PagerAdapter() {
    lateinit var front_animation: AnimatorSet
    lateinit var back_animation: AnimatorSet


    override fun getCount(): Int {
        return mList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view.equals(`object`)
    }
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View = LayoutInflater.from(container.context).inflate(R.layout.card_container, container, false)

        var isFront = true

        val word = view.findViewById<TextView>(R.id.card_word)
        val translation = view.findViewById<TextView>(R.id.card_translation)

        val card_front: CardView = view.findViewById(R.id.card)
        val card_back: CardView = view.findViewById(R.id.card_back)


        translation.setText(mList.get(position).translation)
        word.setText(mList.get(position).word)
//        front_animation = AnimatorInflater.loadAnimator(context, R.animator.front_animator) as AnimatorSet
//        back_animation = AnimatorInflater.loadAnimator(context, R.animator.back_animator) as AnimatorSet
        view.setOnClickListener {
            if(isFront)
            {
                flipCard(context,card_back,card_front)
                isFront = false

            }
            else
            {
                flipCard(context,card_front,card_back)
                isFront =true

            }
        }
        container.addView(view, 0)
        return view
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    fun flipCard(context: Context, visibleView: View, inVisibleView: View) {
//
//        visibleView.visibility = View.VISIBLE
        val scale = context.resources.displayMetrics.density
        val cameraDist = 8000 * scale
        visibleView.cameraDistance = cameraDist
        inVisibleView.cameraDistance = cameraDist
        val flipOutAnimatorSet =
            AnimatorInflater.loadAnimator(
                context,
                R.animator.front_animator
            ) as AnimatorSet
        flipOutAnimatorSet.setTarget(inVisibleView)
        val flipInAnimatorSet =
            AnimatorInflater.loadAnimator(
                context,
                R.animator.back_animator
            ) as AnimatorSet
        flipInAnimatorSet.setTarget(visibleView)
        flipOutAnimatorSet.start()
        flipInAnimatorSet.start()
//        flipInAnimatorSet.doOnEnd {
//            inVisibleView.visibility = View.GONE
//        }

    }
}