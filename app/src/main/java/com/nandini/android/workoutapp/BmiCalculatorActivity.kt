package com.nandini.android.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.nandini.android.workoutapp.databinding.ActivityBmiCalculatorBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BmiCalculatorActivity : AppCompatActivity() {

    private var binding: ActivityBmiCalculatorBinding?=null

    companion object{
        private const val METRIC_UNITS_VIEW="METRIC_UNIT_VIEW"
        private const val US_UNITS_VIEW="US_UNIT_VIEW"
    }

    private var currentVisibleView:String= METRIC_UNITS_VIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityBmiCalculatorBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBmi)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title="Calculate BMI"
        }
        binding?.toolbarBmi?.setNavigationOnClickListener {
            onBackPressed()
        }

        makeVisibleMetricUnitsView()

        binding?.rgUnits?.setOnCheckedChangeListener{ _,checkedInt:Int ->

            if (checkedInt==R.id.rbMetricUnits){
                makeVisibleMetricUnitsView()
            }else{
                makeVisibleUsMetricUnitsView()
            }

        }

        binding?.bmiCalculateBtn?.setOnClickListener {
        calculateUnits()
        }

    }

    private fun makeVisibleMetricUnitsView(){
        currentVisibleView= METRIC_UNITS_VIEW
        binding?.tilMetricUnitWeight?.visibility=View.VISIBLE
        binding?.tilMetricUnitHeight?.visibility=View.VISIBLE
        binding?.tilUsUnitHeightFeet?.visibility=View.INVISIBLE
        binding?.tilUsUnitHeightInch?.visibility=View.INVISIBLE
        binding?.tilUsMetricUnitWeight?.visibility=View.INVISIBLE

        binding?.etMetricUnitHeight?.text?.clear()
        binding?.etMetricUnitWeight?.text?.clear()

        binding?.llDisplayBmiResult?.visibility=View.GONE
    }

    private fun makeVisibleUsMetricUnitsView(){
        currentVisibleView= US_UNITS_VIEW
        binding?.tilMetricUnitWeight?.visibility=View.INVISIBLE
        binding?.tilMetricUnitHeight?.visibility=View.INVISIBLE
        binding?.tilUsUnitHeightFeet?.visibility=View.VISIBLE
        binding?.tilUsUnitHeightInch?.visibility=View.VISIBLE
        binding?.tilUsMetricUnitWeight?.visibility=View.VISIBLE


        binding?.etUsMetricUnitWeight?.text?.clear()
        binding?.etUsMetricUnitHeightFeet?.text?.clear()
        binding?.etUsMetricUnitHeightInch?.text?.clear()

        binding?.llDisplayBmiResult?.visibility=View.GONE
    }


    private fun displayBmiResult(bmi:Float){

        val bmiLabel: String
        val bmiDiscription: String

        if(bmi.compareTo(15f)<=0){
            bmiLabel="Very Severely Underweight"
            bmiDiscription="Oops! you are so weak.\nMaintain your proper diet."
        }
        else if(bmi.compareTo(15f)>0 && bmi.compareTo(16f)<=0){
            bmiLabel="Severely Underweight"
            bmiDiscription="Oops! you are weak.\nMaintain your proper diet."
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
            bmiDiscription="This is not good.\nYou have to work more hard. "
        }

        val bmiValue= BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
        binding?.llDisplayBmiResult?.visibility= View.VISIBLE
        binding?.tvBmiValue?.text=bmiValue
        binding?.tvBmiDiscription?.text = bmiDiscription
        binding?.tvBmiType?.text=bmiLabel
    }

    private fun validateMetricUnits():Boolean{
        var isValid= true
        if(binding?.etMetricUnitWeight?.text.toString().isEmpty()){
            isValid=false}else if(binding?.etMetricUnitHeight?.text.toString().isEmpty()){
            isValid=false}
        return isValid
    }

    private fun calculateUnits(){
        if(currentVisibleView== METRIC_UNITS_VIEW){
            if(validateMetricUnits()){
                val heightValue:Float=binding?.etMetricUnitHeight?.text.toString().toFloat()/100
                val weightValue:Float=binding?.etMetricUnitWeight?.text.toString().toFloat()
                val bmi=weightValue/(heightValue*heightValue)
                displayBmiResult(bmi)

            }else{
                Toast.makeText(this,"Enter valid values.",Toast.LENGTH_SHORT).show()
            }
        }else{
            if(validateUsMetricUnits()){
                val UsUnitHeightValueFeet:String=binding?.etUsMetricUnitHeightFeet?.text.toString()
                val UsUnitHeightValueInch:String=binding?.etUsMetricUnitHeightInch?.text.toString()
                val UsUnitWeightValue:Float=binding?.etUsMetricUnitWeight?.text.toString().toFloat()
                val heightValue=UsUnitHeightValueFeet.toFloat()+(UsUnitHeightValueInch.toFloat()*12)

                val bmi=703*(UsUnitWeightValue/(heightValue*heightValue))
                displayBmiResult(bmi)
            }else{
                Toast.makeText(this,"Enter valid values.",Toast.LENGTH_SHORT).show()
            }
        }
    }



    private fun validateUsMetricUnits():Boolean{
        var isValid= true
        if(binding?.etUsMetricUnitWeight?.text.toString().isEmpty()){
            isValid=false}
        else if(binding?.etUsMetricUnitHeightFeet?.text.toString().isEmpty()){
            isValid=false}
        else if(binding?.etUsMetricUnitHeightInch?.text.toString().isEmpty()){
            isValid=false}
        return isValid
    }

}