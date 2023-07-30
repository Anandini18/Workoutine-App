package com.nandini.android.workoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import com.nandini.android.workoutapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding:ActivityMainBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnStart?.setOnClickListener{Toast.makeText(this,"Timer Started", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@MainActivity,ExerciseActivity::class.java)
        startActivity(intent)}

        binding?.btnBmi?.setOnClickListener{
            val intent = Intent(this@MainActivity,BmiCalculatorActivity::class.java)
            startActivity(intent)}

        binding?.btnHistory?.setOnClickListener{
            val intent = Intent(this@MainActivity,HistoryActivity::class.java)
            startActivity(intent)}


    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}