package com.nandini.android.workoutapp

class ExerciseModel (
private var id:Int,
private var name:String,
private var isCompleted:Boolean,
private var isSelected:Boolean,
private var animationJson:Int)
{
    fun getId():Int{
        return id
    }
    fun setId(id:Int){
        this.id=id
        // "this" context ki id=model wali id
    }
    fun getName():String{
        return name
    }
    fun setName(name:String){
        this.name=name

    }
    fun getIsCompleted():Boolean{
        return isCompleted
    }
    fun setIsCompleted(isCompleted: Boolean){
        this.isCompleted=isCompleted

    }
    fun getIsSelected():Boolean{
        return isSelected
    }
    fun setIsSelected(isSelected: Boolean){
        this.isSelected=isSelected
    }
    fun getImage():Int{
        return animationJson
    }
    fun setImage(animationJson:Int){
        this.animationJson=animationJson
    }
}