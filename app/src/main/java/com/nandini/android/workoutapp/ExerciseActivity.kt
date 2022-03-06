package com.nandini.android.workoutapp

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.nandini.android.workoutapp.databinding.ActivityExerciseBinding
import com.nandini.android.workoutapp.databinding.CustomDialogBinding
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var binding:ActivityExerciseBinding?= null
    private var tts:TextToSpeech?=null

    private var restTimer: CountDownTimer?=null
    private var restProgress=0
    private var restTimerDuration:Long=1

    private var exerciseTimer:CountDownTimer?=null
    private var exerciseProgress=0
    private var exerciseTimerDuration:Long=1

    private var exerciseList:ArrayList<ExerciseModel>?=null
    private var currentExercisePosition=-1

    private var player:MediaPlayer?=null
    private var exerciseAdapter:ExerciseStatusAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarExercise)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarExercise?.setNavigationOnClickListener {
            customDialogForBackButton()
        }
        setUpRestView()


        exerciseList=Constants.defaultExerciseList()
        tts= TextToSpeech(this,this)

        setUpExerciseStatusRecyclerView()

    }

    override fun onBackPressed() {
        customDialogForBackButton()
    }

    private fun customDialogForBackButton(){
        val customDialog= Dialog(this)
        val dialogBinding=CustomDialogBinding.inflate(layoutInflater)
        customDialog.setContentView(dialogBinding.root)
        customDialog.setCanceledOnTouchOutside(false)
        dialogBinding.btnYes.setOnClickListener { this@ExerciseActivity.finish()
        customDialog.dismiss()}
        dialogBinding.btnNo.setOnClickListener {  customDialog.dismiss()}
        customDialog.show()
    }

    private fun setUpExerciseStatusRecyclerView(){
        binding?.rvExerciseStatus?.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        exerciseAdapter= ExerciseStatusAdapter(exerciseList!!)
        binding?.rvExerciseStatus?.adapter=exerciseAdapter
    }

    private fun setUpRestView(){
        player?.stop()
        try{
            val soundUri= Uri.parse("android.resource://com.nandini.android.workoutapp/"+R.raw.rest_music)
            player=MediaPlayer.create(applicationContext,soundUri)
            player?.isLooping=false
            player?.start()
        }catch (e:Exception){
            e.printStackTrace()
        }

        binding?.flProgressBar?.visibility= View.VISIBLE
        binding?.flProgressBarExercise?.visibility= View.INVISIBLE
        binding?.titleTv?.visibility=View.VISIBLE
        binding?.exName?.visibility=View.INVISIBLE
        binding?.exImg?.visibility=View.INVISIBLE
        binding?.upcomingExLabel?.visibility=View.VISIBLE
        binding?.upcomingExName?.visibility=View.VISIBLE

        if (restTimer!=null){
        restTimer?.cancel()
        restProgress=0
        binding?.upcomingExName?.text=exerciseList!![currentExercisePosition+1].getName()
    }

        setRestProgressBar()

    }

    private fun setUpExerciseView(){
        player?.stop()
        try{
            val soundUri= Uri.parse("android.resource://com.nandini.android.workoutapp/"+R.raw.exercise_music)
            player=MediaPlayer.create(applicationContext,soundUri)
            player?.isLooping=false
            player?.start()
        }catch (e:Exception){
            e.printStackTrace()
        }
        binding?.flProgressBar?.visibility= View.INVISIBLE
        binding?.flProgressBarExercise?.visibility= View.VISIBLE
        binding?.titleTv?.visibility=View.INVISIBLE
        binding?.exName?.visibility=View.VISIBLE
        binding?.exImg?.visibility=View.VISIBLE
        binding?.upcomingExLabel?.visibility=View.INVISIBLE
        binding?.upcomingExName?.visibility=View.INVISIBLE
        if(exerciseTimer!=null){
            exerciseTimer?.cancel()
            exerciseProgress=0
        }


        binding?.exImg?.setAnimation(exerciseList!![currentExercisePosition].getImage())
        binding?.exImg?.playAnimation();
//        binding?.exImg?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.exName?.text=exerciseList!![currentExercisePosition].getName()


        setExerciseProgressBar()
    }

    private fun setRestProgressBar(){
        binding?.progressBar?.progress=restProgress
        restTimer=object:CountDownTimer(restTimerDuration*1000,1000){
            override fun onTick(p0: Long) {
                restProgress++
                binding?.progressBar?.progress=10-restProgress
                binding?.timerTv?.text=(10-restProgress).toString()
            }

            override fun onFinish() {
                currentExercisePosition++

                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()

                setUpExerciseView()
            }
        }.start()
    }

    private fun setExerciseProgressBar(){
        binding?.progressBarExercise?.progress=exerciseProgress
        exerciseTimer=object :CountDownTimer(exerciseTimerDuration*3000,1000){
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding?.progressBarExercise?.progress=30-exerciseProgress
                binding?.timerTvExercise?.text=(30-exerciseProgress).toString()
            }

            override fun onFinish() {

                if (currentExercisePosition < exerciseList?.size!!-1){
                    exerciseList!![currentExercisePosition].setIsSelected(false)
                    exerciseList!![currentExercisePosition].setIsCompleted(true)
                    exerciseAdapter!!.notifyDataSetChanged()
                    setUpRestView()
                }
                else{
                    Toast.makeText(this@ExerciseActivity,"Exercises Completed ! ",Toast.LENGTH_SHORT).show()
                    finish()
                    val intent=Intent(this@ExerciseActivity,FinishActivity::class.java)
                    startActivity(intent)
                }

            }

        }.start()
    }



    override fun onDestroy() {
        super.onDestroy()
        if (restTimer!=null){
            restTimer?.cancel()
            restProgress=0
        }
        if(exerciseTimer!=null){
            exerciseTimer?.cancel()
            exerciseProgress=0
        }
        if(tts!=null){
            tts!!.stop()
            tts!!.shutdown()
        }
        if(player!=null){
            player!!.stop()
        }
        binding=null
    }

    override fun onInit(status: Int) {
        if (status==TextToSpeech.SUCCESS){
            val result=tts?.setLanguage(Locale.US)
            if(result==TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","The language specified is not supported ! ")
            }
        }else{
            Log.e("TTS","Initialization Failed !")
        }
    }
    private fun speakOut (text:String){
        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }


}