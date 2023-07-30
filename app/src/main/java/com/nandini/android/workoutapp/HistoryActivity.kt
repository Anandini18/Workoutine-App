package com.nandini.android.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nandini.android.workoutapp.databinding.ActivityBmiCalculatorBinding
import com.nandini.android.workoutapp.databinding.ActivityHistoryBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {

    private var binding :ActivityHistoryBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarHistory)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title="History "
        }
        binding?.toolbarHistory?.setNavigationOnClickListener {
            onBackPressed()
        }

        val dao=(application as WorkOutineApp).db.historyDao()
        getDates(dao)


    }

    private fun getDates(historyDao:HistoryDao){
        lifecycleScope.launch {
            historyDao.fetchDates().collect {
                allDatesList -> if (allDatesList.isNotEmpty()){
                    binding?.textView3?.visibility= View.VISIBLE
                    binding?.textView4?.visibility=View.INVISIBLE
                    binding?.recyclerViewDate?.visibility=View.VISIBLE

                binding?.recyclerViewDate?.layoutManager=LinearLayoutManager(this@HistoryActivity)
                val dates=ArrayList<String>()
                for(date in allDatesList){
                    dates.add(date.date)
                }
                val historyAdapter=HistoryAdapter(dates)
                binding?.recyclerViewDate?.adapter=historyAdapter

            }else{
                binding?.textView3?.visibility= View.GONE
                binding?.textView4?.visibility=View.VISIBLE
                binding?.recyclerViewDate?.visibility=View.GONE
            }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }


}