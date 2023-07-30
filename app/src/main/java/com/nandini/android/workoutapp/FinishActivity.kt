package com.nandini.android.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.nandini.android.workoutapp.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {

    private var binding:ActivityFinishBinding?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarFinish)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarFinish?.setNavigationOnClickListener {
            onBackPressed()
        }
        binding?.btnFinish?.setOnClickListener{
            finish()
        }
        val dao=(application as WorkOutineApp).db.historyDao()
        addDateToDatabase(dao)
    }

    fun addDateToDatabase(historyDao: HistoryDao){
        val c= Calendar.getInstance()
        val dateTime=c.time
        Log.e("Date : ",""+dateTime)

        val sdf=SimpleDateFormat("dd MMM yyyy HH:mm:ss",Locale.getDefault())
        val date =sdf.format(dateTime)
        Log.e("Formatted Date : ",""+date)
        lifecycleScope.launch{
            historyDao.insert(HistoryEntity(date))
            Log.e("Date", "Added...")

//        if(name?.isNotEmpty() && email?.isNotEmpty()){
//
//                Toast.makeText(applicationContext,"Record updated.", Toast.LENGTH_SHORT).show()
//                binding?.etEmail?.text?.clear()
//                binding?.etName?.text?.clear()
//            }
//        }else{
//            Toast.makeText(applicationContext,"Enter name & email.", Toast.LENGTH_SHORT).show()
//        }
    }


}}