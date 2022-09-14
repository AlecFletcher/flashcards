package com.example.flashcards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val showAnswerButton = findViewById<Button>(R.id.hideOrShowAnswers)
        val wrongAnswer1 = findViewById<View>(R.id.fake_answer1)
        val wrongAnswer2 = findViewById<View>(R.id.fake_answer2)
        val question = findViewById<View>(R.id.flashcard_question)
        val answer = findViewById<View>(R.id.flashcard_answer)
        var showingAnswers = false



        showAnswerButton.setOnClickListener {
            if(!showingAnswers) {
                showAnswerButton.text = "Hide Answers"
                showingAnswers = true
                wrongAnswer1.visibility = View.VISIBLE
                wrongAnswer2.visibility = View.VISIBLE
                answer.visibility = View.VISIBLE
            }
            else{
                showAnswerButton.text = "Show Answers"
                showingAnswers = false
                wrongAnswer1.visibility = View.INVISIBLE
                wrongAnswer2.visibility = View.INVISIBLE
                answer.visibility = View.INVISIBLE
            }
        }

            wrongAnswer1.setOnClickListener {
                wrongAnswer1.setBackgroundResource(R.drawable.red_background_rectange)
            }

            wrongAnswer2.setOnClickListener {
                wrongAnswer2.setBackgroundResource(R.drawable.red_background_rectange)
            }
            answer.setOnClickListener {
                answer.setBackgroundResource(R.drawable.right_answer_rectangle)
            }
        }


        /*
        question.setOnClickListener{
                question.visibility = View.INVISIBLE
                answer.visibility = View.VISIBLE
        }

        answer.setOnClickListener{
            question.visibility = View.VISIBLE
            answer.visibility = View.INVISIBLE
        }
         */


    }






