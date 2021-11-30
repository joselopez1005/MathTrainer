package com.jlopez.mathtrainer.ResultScreen

import androidx.lifecycle.ViewModel
import com.jlopez.mathtrainer.remote.responses.MathQuestion
import dagger.hilt.android.lifecycle.HiltViewModel

class ResultScreenViewModel: ViewModel() {

    var correctAmount: Int = 0
    var incorrectAmount: Int = 0
    var totalQuestions: Int = 0
    val questionList: MutableList<MathQuestion> = ArrayList()
    var attemptList: MutableList<Int> = ArrayList()

    fun setInitialValues(args: ResultScreenArgs){
        correctAmount = args.correctAmount
        totalQuestions = args.totalQuestions
        incorrectAmount = totalQuestions - correctAmount

        for(i in args.firstOperands.indices) {
            var operation = "${args.firstOperands[i]} + ${args.secondOperands[i]} = ${args.answers[i]}"
            questionList.add(MathQuestion(args.answers[i],operation, args.firstOperands[i], args.operators[i], args.secondOperands[i]))
        }
        attemptList = args.questionAttempts.toMutableList()

    }




}