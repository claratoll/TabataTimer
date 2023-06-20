package com.claratoll.tabatatimer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.claratoll.tabatatimer.R

class MainActivity : AppCompatActivity() {

    private lateinit var editWorkSeconds : EditText
    private lateinit var editRestSeconds : EditText
    private lateinit var editRounds : EditText
    private lateinit var startButton : Button
    private lateinit var customButton : Button

    private var workSeconds : Int = 20
    private var restSeconds : Int = 10
    private var rounds : Int = 8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        editWorkSeconds = findViewById(R.id.EditWorkSeconds)
        editRestSeconds = findViewById(R.id.EditRestSeconds)
        editRounds = findViewById(R.id.EditRounds)

        startButton = findViewById(R.id.ToWorkoutButton)
        customButton = findViewById(R.id.AddCustomButton)

        startButton.setOnClickListener {
            editNumbers()
            goToWorkActivity()
        }

        customButton.setOnClickListener {
            goToCustomActivity()
        }
    }

    fun goToCustomActivity() {
        val intent = Intent(this, ExerciseActivity::class.java)
        startActivity(intent)
    }

    fun editNumbers (){
        workSeconds = editWorkSeconds.text.toString().toInt()
        restSeconds = editRestSeconds.text.toString().toInt()
        rounds = editRounds.text.toString().toInt()
    }

    fun goToWorkActivity(){
        val intent = Intent(this, WorkoutActivity::class.java)
        intent.putExtra("workSeconds", workSeconds)
        intent.putExtra("restSeconds", restSeconds)
        intent.putExtra("rounds", rounds)
        startActivity(intent)
    }

}