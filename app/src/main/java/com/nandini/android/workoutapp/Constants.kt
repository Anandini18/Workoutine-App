package com.nandini.android.workoutapp

object Constants {

    fun defaultExerciseList():ArrayList<ExerciseModel>{
        val exerciseList=ArrayList<ExerciseModel>()
        val jumping_jack =ExerciseModel(1,"Jumping Jack",false,false, R.raw.jumping_jack)
        exerciseList.add(jumping_jack)
        val inchworms =ExerciseModel(2,"Inchworm",false,false,R.raw.inchworms)
        exerciseList.add(inchworms)
        val military_pushup =ExerciseModel(3,"Military Pushup",false,false,R.raw.military_pushup)
        exerciseList.add(military_pushup)
        val reverse_crunches =ExerciseModel(4,"Reverse Crunches",false,false,R.raw.reverse_crunches)
        exerciseList.add(reverse_crunches)
        val seated_abs_circle =ExerciseModel(5,"Seated Abs Circle",false,false,R.raw.seated_abs_circle)
        exerciseList.add(seated_abs_circle)
        val shoulder_stretch=ExerciseModel(6,"Shoulder Stretch",false,false,R.raw.shoulder_stretch)
        exerciseList.add(shoulder_stretch)
        val single_leg_hip_rotation =ExerciseModel(7,"Single Leg Hip Rotation",false,false,R.raw.single_leg_hip_rotation)
        exerciseList.add(single_leg_hip_rotation)
        val squat_kick=ExerciseModel(8,"Squat Kick",false,false, R.raw.squat_kick)
        exerciseList.add(squat_kick)
        val squat_reach_ups=ExerciseModel(9,"Squat Reach Ups",false,false,R.raw.squat_reach_ups)
        exerciseList.add(squat_reach_ups)
        val staggered_pushups =ExerciseModel(10,"Staggered Pushups",false,false,R.raw.staggered_puchups)
        exerciseList.add(staggered_pushups)
        return exerciseList
    }

}

