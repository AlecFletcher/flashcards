package com.example.flashcards

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)

        val cancel_button = findViewById<FloatingActionButton>(R.id.cancel_button)

        cancel_button.setOnClickListener{
            finish()
            overridePendingTransition(R.anim.left_in, R.anim.right_out);
        }

        val questionEditText = findViewById<EditText>(R.id.entered_question)
        val answerEditText = findViewById<EditText>(R.id.entered_answer)



        val saveButton = findViewById<FloatingActionButton>(R.id.submit_card_button)
        saveButton.setOnClickListener{
            val questionString = questionEditText.text.toString()
            val answerString = answerEditText.text.toString()
            
            val data = Intent()
            data.putExtra("QUESTION_KEY", questionString)
            data.putExtra("ANSWER_KEY", answerString)

            setResult(RESULT_OK, data)
            finish()
            overridePendingTransition(R.anim.left_in, R.anim.right_out);
        }
    }
    }
