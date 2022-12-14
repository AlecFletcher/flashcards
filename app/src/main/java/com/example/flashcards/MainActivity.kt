package com.example.flashcards

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.w3c.dom.Text
import java.util.*
import kotlin.math.max
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

class MainActivity : AppCompatActivity() {



    lateinit var flashcardDatabase: FlashcardDatabase
    var allFlashcards = mutableListOf<Flashcard>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var flashcardIndex = 0
        setContentView(R.layout.activity_main)

        flashcardDatabase = FlashcardDatabase(this)
        allFlashcards = flashcardDatabase.getAllCards().toMutableList()


        val question = findViewById<TextView>(R.id.flashcard_question)
        val answer = findViewById<TextView>(R.id.flashcard_answer)
        val add_question_button = findViewById<View>(R.id.add_question_button)
        val nextFlashcardButton = findViewById<FloatingActionButton>(R.id.next_flashcard_button)
        val deleteFlashcardButton = findViewById<FloatingActionButton>(R.id.deleteFlashcard)


        val leftOutAnim = AnimationUtils.loadAnimation(this, R.anim.left_out)
        val rightInAnim = AnimationUtils.loadAnimation(this, R.anim.right_in)
        val topOutQuestion = AnimationUtils.loadAnimation(question.getContext(), R.anim.top_out)
        val topOutAnswer = AnimationUtils.loadAnimation(question.getContext(), R.anim.top_out)



        if(allFlashcards.size > 0){
            question.text = allFlashcards[flashcardIndex].question
            answer.text = allFlashcards[flashcardIndex].answer
        }

        question.setOnClickListener{
            val cx = answer.width / 2
            val cy = answer.height / 2
            val finalRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()
            val anim = ViewAnimationUtils.createCircularReveal(answer, cx, cy, 0f, finalRadius)

            question.setCameraDistance(25000f)
            answer.setCameraDistance(25000f)

            question.animate()
                .rotationY(90f)
                .setDuration(200)
                .withEndAction(
                    Runnable {
                        question.visibility = (View.INVISIBLE)
                        answer.visibility = View.VISIBLE
                        // second quarter turn
                        answer.rotationY = -90f
                        answer.animate()
                            .rotationY(0f)
                            .setDuration(200)
                            .start()
                    }
                ).start()

        }

        answer.setOnClickListener{

            answer.setCameraDistance(25000f)
            question.setCameraDistance(25000f)

            answer.animate()
                .rotationY(90f)
                .setDuration(200)
                .withEndAction(
                    Runnable {
                        question.visibility = View.VISIBLE
                        answer.visibility = View.INVISIBLE
                        // second quarter turn
                        question.rotationY = -90f
                        question.animate()
                            .rotationY(0f)
                            .setDuration(200)
                            .start()
                    }
                ).start()
        }

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val data: Intent? = result.data
            allFlashcards = flashcardDatabase.getAllCards().toMutableList()

            if(data != null){
                val question_string = data.getStringExtra("QUESTION_KEY")
                val answer_string = data.getStringExtra("ANSWER_KEY")

                    question.text = question_string
                    answer.text = answer_string

                flashcardDatabase.insertCard(Flashcard(question_string.toString(), answer_string.toString()))
                allFlashcards = flashcardDatabase.getAllCards().toMutableList()


            }
        }



        add_question_button.setOnClickListener{
            val i = Intent(this@MainActivity, AddCardActivity::class.java)
            resultLauncher.launch(i);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }

        nextFlashcardButton.setOnClickListener{
            if(allFlashcards.size == 0){
                question.text = ""
                answer.text = ""
            }
            else {
                fun getRandom(minNumber: Int, maxNumber: Int) {
                    flashcardIndex = Random.nextInt(minNumber, maxNumber)
                }
                getRandom(0, allFlashcards.size)

                findViewById<View>(R.id.flashcard_question).startAnimation(leftOutAnim)
                leftOutAnim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {
                        // this method is called when the animation first starts


                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        // this method is called when the animation is finished playing
                        question.text = allFlashcards[flashcardIndex].question
                        answer.text = allFlashcards[flashcardIndex].answer
                        findViewById<View>(R.id.flashcard_question).startAnimation(rightInAnim)
                    }

                    override fun onAnimationRepeat(animation: Animation?) {
                        // we don't need to worry about this method
                    }
                })






            }

        /*if(allFlashcards.size == flashcardIndex +1) {    //If we're at the last item in the list
                flashcardIndex = 0
                question.text = allFlashcards[flashcardIndex].question
                answer.text = allFlashcards[flashcardIndex].answer
            }
            else{
                flashcardIndex++
                question.text = allFlashcards[flashcardIndex].question
                answer.text = allFlashcards[flashcardIndex].answer
            }
             */
        }




        deleteFlashcardButton.setOnClickListener{
            flashcardDatabase.deleteCard(allFlashcards[flashcardIndex].question)
            findViewById<View>(R.id.flashcard_question).startAnimation(topOutQuestion)
            findViewById<View>(R.id.flashcard_question).startAnimation(topOutAnswer)
            allFlashcards = flashcardDatabase.getAllCards().toMutableList()
            if(flashcardIndex == 0 && allFlashcards.size == 0){
                question.text = ""
                answer.text = ""
            }
            else if(flashcardIndex == 0 && allFlashcards.size > 0){
                question.text = allFlashcards[flashcardIndex].question
                answer.text = allFlashcards[flashcardIndex].answer
            }
            else{
                flashcardIndex--
                question.text = allFlashcards[flashcardIndex].question
                answer.text = allFlashcards[flashcardIndex].answer
            }


        }


        allFlashcards = flashcardDatabase.getAllCards().toMutableList()







    }
}






