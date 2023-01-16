package com.claratoll.tabatatimer

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.claratoll.tabatatimer.R

class WorkoutActivity : AppCompatActivity() {

    lateinit var goRestTextView: TextView
    lateinit var timeView: TextView
    lateinit var roundsView: TextView

    var workoutSeconds: Int = 0
    var restSeconds: Int = 0
    var rounds: Int = 0

    var goCountDownTimer: CountDownTimer? = null
    var restCountDownTimer: CountDownTimer? = null

    val REST : Int = 1
    val WORK : Int = 0
    lateinit var layout : ConstraintLayout





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)

        goRestTextView = findViewById(R.id.GoRestTextView)
        timeView = findViewById(R.id.TimeTextView)
        roundsView = findViewById(R.id.RoundsTextView)
        layout = findViewById(R.id.workoutlayout)

        workoutSeconds = intent.getIntExtra("workSeconds", 99)
        restSeconds = intent.getIntExtra("restSeconds", 99)
        rounds = intent.getIntExtra("rounds", 99)

        workout(WORK)
    }

    fun workout(mode: Int) {

        val media = MediaPlayer.create(this, R.raw.countdown)

            if (mode == WORK) {
                layout.setBackgroundColor(getColor(R.color.bittersweet_shimmer))
                goCountDownTimer = object : CountDownTimer((workoutSeconds * 1000).toLong(), 1000) {

                    override fun onTick(millisUntilFinished: Long) {
                        timeView.text = (millisUntilFinished / 1000).toString()
                        goRestTextView.text = "GO"
                        roundsView.text = "$rounds"

                        val three : Long = 3
                        if ((millisUntilFinished/1000) == three){
                            media.start()
                        }

                    }

                    override fun onFinish() {
                        rounds--
                        workout(REST)
                        goRestTextView.text = "REST"
                    }
                }.start()


            } else if (mode == REST) {
                layout.setBackgroundColor(getColor(R.color.hookers_green))
                restCountDownTimer = object : CountDownTimer((restSeconds * 1000).toLong(), 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        timeView.text = (millisUntilFinished / 1000).toString()
                        goRestTextView.text = "REST"
                        roundsView.text = "$rounds"

                        Log.v("!!!", millisUntilFinished.toString())

                        val three : Long = 3
                        if ((millisUntilFinished/1000) == three){
                            media.start()
                        }

                    }

                    override fun onFinish() {
                        if (rounds > 0) {
                            workout(WORK)
                            goRestTextView.text = "GO"
                        } else {
                            workoutFinished()
                        }

                    }
                }.start()
            }

    }

    override fun onPause() {
        super.onPause()
        goCountDownTimer!!.cancel()
        restCountDownTimer!!.cancel()
    }

    override fun onStop() {
        super.onStop()
        goCountDownTimer!!.cancel()
        restCountDownTimer!!.cancel()
    }

    fun workoutFinished(){
        timeView.text = "done"
        layout.setBackgroundColor(getColor(R.color.true_blue))
    }

}