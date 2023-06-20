package com.claratoll.tabatatimer

import android.view.View
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val exerciseNameTextView: TextView = itemView.findViewById(R.id.exNameView)
    val exGoTimeView: TextView = itemView.findViewById(R.id.goTimeView)
    val exRestTimeView: TextView = itemView.findViewById(R.id.restTimeView)
    val switch: Switch = itemView.findViewById(R.id.switch1)
    // Add references to other views in the item layout as needed
}
