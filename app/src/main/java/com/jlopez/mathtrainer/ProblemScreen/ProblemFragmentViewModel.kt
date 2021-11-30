package com.jlopez.mathtrainer.ProblemScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jlopez.mathtrainer.remote.responses.MathQuestion
import com.jlopez.mathtrainer.repository.MathRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProblemFragmentViewModel @Inject constructor(
    private val mathRepository: MathRepository
): ViewModel() {

    // State Flows
    private val _firstOperand = MutableStateFlow<Int>(0)
    val firstOperand: StateFlow<Int> = _firstOperand

    private val _secondOperand = MutableStateFlow<Int>(0)
    val secondOperand: StateFlow<Int> = _secondOperand

    private val _operator = MutableStateFlow<String>("+")
    val operator: StateFlow<String> = _operator

    private val _answer = MutableStateFlow<Int>(0)
    val answer: StateFlow<Int> = _answer

    private val _currentQuestion = MutableStateFlow<Int>(0)
    val currentQuestion: StateFlow<Int> = _currentQuestion

    private val _totalQuestions = MutableStateFlow<Int>(3)
    val totalQuestions: StateFlow<Int> = _totalQuestions

    private val _isQuizFinished = MutableStateFlow<Boolean>(false)
    val isQuizFinished: StateFlow<Boolean> = _isQuizFinished

    // Question Statistics
    var questionsCorrect = 0
    val questionAttempts: MutableList<Int> = ArrayList()
    private var questionIncorrectFlag = false

    // List of Questions
    val questionListFirstOperand = mutableListOf<Int>()
    val questionListSecondOperand = mutableListOf<Int>()
    val questionListOperator = mutableListOf<String>()
    val questionListAnswer = mutableListOf<Int>()

    private lateinit var currentMathQuestion: MathQuestion



    fun getNewMathProblem() {
        questionAttempts.add(0)
        viewModelScope.launch {
            currentMathQuestion = when(_operator.value) {
                "+" -> mathRepository.getMathAdd().data!!
                "-" -> mathRepository.getMathSub().data!!
                "*" -> mathRepository.getMathMul().data!!
                "/" -> mathRepository.getMathDiv().data!!
                else -> mathRepository.getMathRandom().data!!
            }
            setNewValues()
        }
    }


    private fun setNewValues() {
        _firstOperand.value = currentMathQuestion.first
        questionListFirstOperand.add(currentMathQuestion.first)

        _secondOperand.value = currentMathQuestion.second
        questionListSecondOperand.add(currentMathQuestion.second)

        _operator.value = currentMathQuestion.operation
        questionListOperator.add(currentMathQuestion.operation)

        _answer.value = currentMathQuestion.answer
        questionListAnswer.add(currentMathQuestion.answer)
    }

    fun checkAnswer(userAnswer: Int): Boolean {
        if(userAnswer == _answer.value) {
            incrementCorrectAmount()
            incrementQuestion()
            return true
        }
        questionAttempts[currentQuestion.value]++
        questionIncorrectFlag = true

        return false
    }

    private fun incrementCorrectAmount(){
        if(!questionIncorrectFlag) {
            questionsCorrect++
        }
    }

    private fun incrementQuestion() {
        _currentQuestion.value++
        if(_currentQuestion.value >= _totalQuestions.value){
            _isQuizFinished.value = true
            questionIncorrectFlag = false
        }
    }

    fun setOperator(newOperator: String) {
        _operator.value = newOperator
    }
}