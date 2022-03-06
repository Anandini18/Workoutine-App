package com.nandini.android.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.nandini.android.workoutapp.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BmiActivity : AppCompatActivity() {

     private var binding: ActivityBmiBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBmi)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title="Calculate BMI"
        }
        binding?.toolbarBmi?.setNavigationOnClickListener {
            onBackPressed()
        }

        binding?.bmiCalculateBtn?.setOnClickListener {
            if(validateMetricUnits()){
                val heightValue:Float=binding?.etMatricUnitHeight?.text.toString().toFloat()/100
                val weightValue:Float=binding?.etMatricUnitWeight?.text.toString().toFloat()
                val bmi=weightValue/(heightValue*heightValue)
                displayBmiResult(bmi)

            }
        }
    }

    private fun displayBmiResult(bmi:Float){

        val bmiLabel: String
        val bmiDiscription: String

        if(bmi.compareTo(15f)<=0){
            bmiLabel="Very Severely Underweight"
            bmiDiscription="Oops! you are so weak , Maintain your proper diet."
        }
        else if(bmi.compareTo(15f)>0 && bmi.compareTo(16f)<=0){
            bmiLabel="Severely Underweight"
            bmiDiscription="Oops! you are weak , Maintain your proper diet."
        }
        else if(bmi.compareTo(16f)>0 && bmi.compareTo(18.5f)<=0){
            bmiLabel="Underweight"
            bmiDiscription="Oops! you are still weak.\nMaintain your proper diet."
        }
        else if(bmi.compareTo(18.5f)>0 && bmi.compareTo(25f)<=0){
            bmiLabel="Normal"
            bmiDiscription="Congratulations !\nYou are in shape."
        }
        else if(bmi.compareTo(25f)>0 && bmi.compareTo(30f)<=0){
            bmiLabel="Overweight"
            bmiDiscription="You are overweight !\nYou have to workout more !."
        }
        else {
            bmiLabel="Obese"
            bmiDiscription="This is not good.\n You have to work more hard. "
        }

        val bmiValue=BigDecimal(bmi.toDouble()).setScale(2,RoundingMode.HALF_EVEN).toString()
        binding?.llDisplayBmiResult?.visibility= View.VISIBLE
        binding?.tvBmiValue?.text=bmiValue
        binding?.tvBmiType?.text=bmiLabel
        binding?.tvBmiDiscription?.text = bmiDiscription

    }

    private fun validateMetricUnits():Boolean{
        var isValid= true
        if(binding?.etMatricUnitWeight?.text.toString().isEmpty()){
            isValid=false}else if(binding?.etMatricUnitHeight?.text.toString().isEmpty()){
            isValid=false}
        return isValid
    }




}