package com.claratoll.tabatatimer

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.w3c.dom.Text

class ExerciseAdapter (val context: Context, val exercise : List <Exercise>): RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_exercise, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exercise = exercise[position]
        holder.exerciseName.text = exercise.name
        holder.exGoTime.text = exercise.workoutNumber.toString()
        holder.exRestTime.text = exercise.restNumber.toString()
        holder.roundsTime.text = exercise.rounds.toString()
        // Bind other data to the views in the item layout as needed

        holder.exercisePosition = position
    }

    override fun getItemCount() = exercise.size

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val exerciseName = itemView.findViewById<TextView>(R.id.exNameView)
        val exGoTime = itemView.findViewById<TextView>(R.id.goTimeView)
        val exRestTime = itemView.findViewById<TextView>(R.id.restTimeView)
        val roundsTime = itemView.findViewById<TextView>(R.id.roundsTextView)
        val switch = itemView.findViewById<Switch>(R.id.switch1)
        var exercisePosition = 0
    }

}
