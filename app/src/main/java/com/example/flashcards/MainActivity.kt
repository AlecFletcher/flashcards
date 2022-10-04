package com.example.flashcards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val question = findViewById<View>(R.id.flashcard_question)
        val answer = findViewById<View>(R.id.flashcard_answer)
        var add_question = findViewById<View>(R.id.add_question_button)


        question.setOnClickListener{
            question.visibility = View.INVISIBLE
            answer.visibility = View.VISIBLE
        }

        answer.setOnClickListener{
            question.visibility = View.VISIBLE
            answer.visibility = View.INVISIBLE
        }
        }



    }






