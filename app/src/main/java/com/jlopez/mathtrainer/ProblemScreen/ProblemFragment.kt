package com.jlopez.mathtrainer.ProblemScreen

import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jlopez.mathtrainer.databinding.FragmentProblemBinding
import com.jlopez.mathtrainer.repository.MathRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProblemFragment : Fragment() {

    private var _binding: FragmentProblemBinding? = null
    private val binding get() = _binding!!

    private val model: ProblemFragmentViewModel by viewModels()
    private val args by navArgs<ProblemFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProblemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservables()
        setListeners()
        setModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun subscribeToObservables() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    model.firstOperand.collectLatest { binding.firstOperand.text = it.toString() }
                }
                launch {
                    model.secondOperand.collectLatest { binding.secondOperand.text = it.toString() }
                }
                launch {
                    model.operator.collectLatest { binding.operator.text = it }
                }
                launch{
                    model.currentQuestion.collectLatest {
                        binding.questionsLeft.text = "$it/10"
                    }
                }
                launch{
                    model.timer.collectLatest {
                        binding.time.text = it
                    }
                }
                launch {
                    model.isQuizFinished.collectLatest {
                        if(model.isQuizFinished.value){
                            delay(10)
                            val action = ProblemFragmentDirections.actionProblemFragmentToResultScreen(
                                model.questionListFirstOperand.toIntArray(),
                                model.questionListSecondOperand.toIntArray(),
                                model.questionListOperator.toTypedArray(),
                                model.questionListAnswer.toIntArray(),
                                model.questionAttempts.toIntArray()
                            ).setCorrectAmount(model.questionsCorrect)
                                .setTotalQuestions(model.totalQuestions.value)
                                .setTimeTaken(model.timer.value)

                            findNavController().navigate(action)
                        }
                    }
                }
            }
        }
    }

    private fun setListeners() {
        binding.submitBtn.setOnClickListener{
            if(model.checkAnswer(Integer.parseInt(binding.userAnswer.text.toString())) && !model.isQuizFinished.value){
               model.getNewMathProblem()
            }
        }
    }


    private fun setModel(){
        model.setOperator(args.operatorRequested)
        model.getNewMathProblem()
    }
}