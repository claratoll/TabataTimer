package com.claratoll.tabatatimer

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class WorkoutActivity : AppCompatActivity() {

    lateinit var goRestTextView: TextView
    lateinit var timeView: TextView
    lateinit var roundsView: TextView

    var getWorkSeconds: Int = 0
    var getRestSeconds: Int = 0
    var rounds: Int = 0

    var timeInMillisLeft : Long = 0L

    var countDownTimer: CountDownTimer? = null

    lateinit var layout : ConstraintLayout

    var running : Boolean = true
    var WORK : Boolean = true



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)

        goRestTextView = findViewById(R.id.GoRestTextView)
        timeView = findViewById(R.id.TimeTextView)
        roundsView = findViewById(R.id.RoundsTextView)
        layout = findViewById(R.id.workoutlayout)

        getWorkSeconds = intent.getIntExtra("workSeconds", 99)
        getRestSeconds = intent.getIntExtra("restSeconds", 99)
        rounds = intent.getIntExtra("rounds", 99)

        workout()

        layout.setOnClickListener {
            if (running) {
                clickPause()
                running = false
            } else {
                val time = timeView.text.toString()
                timeInMillisLeft = time.toLong()
                clickResume()
                running = true
            }
        }
    }

    private fun workout() {
        var workoutSeconds = getWorkSeconds
        var restSeconds = getRestSeconds

        if (timeInMillisLeft != 0L){
            if (WORK){
                workoutSeconds = timeInMillisLeft.toInt()

            } else {
                Log.v("!!!", restSeconds.toString())
                restSeconds = timeInMillisLeft.toInt()
                Log.v("!!!", restSeconds.toString())

            }
            timeInMillisLeft = 0L
        }

        val media = MediaPlayer.create(this, R.raw.countdown)

            if (WORK) {
                layout.setBackgroundColor(getColor(R.color.bittersweet_shimmer))
                countDownTimer = object : CountDownTimer((workoutSeconds * 1000).toLong(), 1000) {

                    override fun onTick(millisUntilFinished: Long) {
                        timeView.text = (millisUntilFinished / 1000).toString()
                        goRestTextView.text = "GO"
                        roundsView.text = "$rounds"

                        if ((millisUntilFinished/1000) == 3L){
                            media.start()
                        }
                    }

                    override fun onFinish() {
                        rounds--
                        WORK = false
                        workout()
                        goRestTextView.text = "REST"
                        media.stop()
                    }
                }.start()


            } else if (!WORK) {
                layout.setBackgroundColor(getColor(R.color.hookers_green))
                countDownTimer = object : CountDownTimer((restSeconds * 1000).toLong(), 1000) {

                    override fun onTick(millisUntilFinished: Long) {
                        timeView.text = (millisUntilFinished / 1000).toString()
                        goRestTextView.text = "REST"
                        roundsView.text = "$rounds"

                        if ((millisUntilFinished/1000) == 3L){
                            media.start()
                        }
                    }

                    override fun onFinish() {
                        media.stop()
                        if (rounds > 0) {
                            WORK = true
                            workout()
                            goRestTextView.text = "GO"
                        } else {
                            workoutFinished()
                        }
                    }
                }.start()
            }
    }

    private fun clickPause(){
        countDownTimer!!.cancel()
    }

    private fun clickResume() {
        timeInMillisLeft = timeView.text.toString().toLong()
        workout()
    }

    override fun onPause() {
        super.onPause()
        countDownTimer!!.cancel()
    }

    override fun onStop() {
        super.onStop()
        countDownTimer!!.cancel()
    }

    fun workoutFinished(){
        timeView.text = "done"
        layout.setBackgroundColor(getColor(R.color.true_blue))
    }
}