package com.example.flashcards

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var flashcardDatabase: FlashcardDatabase
    var allFlashcards = flashcardDatabase.getAllCards().toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flashcardDatabase = FlashcardDatabase(this) //initialize the flashcardDatabase
        allFlashcards = flashcardDatabase.getAllCards().toMutableList()

        val question = findViewById<TextView>(R.id.flashcard_question)
        val answer = findViewById<TextView>(R.id.flashcard_answer)
        val add_question_button = findViewById<View>(R.id.add_question_button)

        question.setOnClickListener{
            question.visibility = View.INVISIBLE
            answer.visibility = View.VISIBLE
        }

        answer.setOnClickListener{
            question.visibility = View.VISIBLE
            answer.visibility = View.INVISIBLE
        }

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result ->

            val data: Intent? = result.data

            if(data != null){
                val question_string = data.getStringExtra("QUESTION_KEY")
                val answer_string = data.getStringExtra("ANSWER_KEY")

                    question.text = question_string
                    answer.text = answer_string


                flashcardDatabase.insertCard(Flashcard(question.toString(), answer.toString()))


            }
        }

        add_question_button.setOnClickListener{
            val i = Intent(this@MainActivity, AddCardActivity::class.java)
            resultLauncher.launch(i)
        }
        }

}






