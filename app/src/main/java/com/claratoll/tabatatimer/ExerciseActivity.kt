package com.claratoll.tabatatimer

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.PopupWindow
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ExerciseActivity : AppCompatActivity() {

    private lateinit var popupWindow: PopupWindow

    private lateinit var editExerciseName : EditText
    private lateinit var editWorkSeconds : EditText
    private lateinit var editRestSeconds : EditText
    private lateinit var editRounds : EditText
    private lateinit var startButton : Button
    private lateinit var customButton : Button

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ExerciseAdapter

    private val exerciseList: MutableList<Exercise> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ExerciseAdapter(this, exerciseList)
        recyclerView.adapter = adapter

        val openNewExButton = findViewById<Button>(R.id.AddPopUpExerciseButton)
        openNewExButton.setOnClickListener {
            CreatePopUpWindow()
        }

        val startWorkoutButton = findViewById<Button>(R.id.startWorkoutButton2)
        startWorkoutButton.setOnClickListener {
            goToWorkActivity()
        }
    }

    private fun StartWorkout() {

    }


    fun goToWorkActivity(){
        val intent = Intent(this, WorkoutActivity::class.java)
        intent.putExtra("exerciseList", exerciseList)
        startActivity(intent)
    }


    private fun CreatePopUpWindow() {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.newexercisepopup, null)

        // Retrieve references to text fields in the popup layout
        editExerciseName = popupView.findViewById<EditText>(R.id.editExercisePop)
        editWorkSeconds = popupView.findViewById<EditText>(R.id.editGoTimePop)
        editRestSeconds = popupView.findViewById<EditText>(R.id.editRestTimePop)
        editRounds = popupView.findViewById<EditText>(R.id.EditRoundsPop)

        // Create the PopupWindow
        popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true // Set this to true to allow dismissing the popup when touched outside
        )

        // Set optional properties for the popup window
        popupWindow.isOutsideTouchable = true // Set to true if you want to dismiss the popup when touched outside
      //  popupWindow.animationStyle = R.style.PopupAnimation // Optional animation style

        // Show the popup window at a specific location
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0)

        // Example: Set a click listener for a button inside the popup window
        val btnClosePopup = popupView.findViewById<Button>(R.id.savePopUpButton)
        btnClosePopup.setOnClickListener {

            // Retrieve the text values from the text fields
            val editName = editExerciseName.text.toString()
            val editWork = editWorkSeconds.text.toString().toIntOrNull() ?: 0
            val editRest = editRestSeconds.text.toString().toIntOrNull() ?: 0
            val editRou = editRounds.text.toString().toIntOrNull() ?: 0

            val newExercise = Exercise(editName, editWork, editRest, editRou)

            exerciseList.add(newExercise)

            Log.v("!!!", exerciseList.size.toString())

            adapter.notifyDataSetChanged()
            popupWindow.dismiss() // Close the popup window
        }
    }
}