package com.jlopez.mathtrainer.ResultScreen

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.jlopez.mathtrainer.ProblemScreen.ProblemFragmentArgs
import com.jlopez.mathtrainer.R
import com.jlopez.mathtrainer.databinding.FragmentResultScreenBinding
import com.jlopez.mathtrainer.remote.responses.MathQuestion
import dagger.hilt.android.AndroidEntryPoint
import org.eazegraph.lib.models.PieModel

@AndroidEntryPoint
class ResultScreen : Fragment() {

    private var _binding: FragmentResultScreenBinding? = null
    private val binding get() = _binding!!

    private val questionList: MutableList<MathQuestion> = ArrayList()

    private val args by navArgs<ResultScreenArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentResultScreenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInitialResults()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.questionRecyclerView.layoutManager = LinearLayoutManager(this.context)
        for(i in args.firstOperands.indices) {
            var operation = "${args.firstOperands[i]} + ${args.secondOperands[i]} = ${args.answers[i]}"
            questionList.add(MathQuestion(args.answers[i],operation, args.firstOperands[i], args.operators[i], args.secondOperands[i]))
        }
        val attemptsList: MutableList<Int> = args.questionAttempts.toMutableList()
        val adapter = ResultScreenAdapter(questionList, attemptsList)
        binding.questionRecyclerView.adapter = adapter

    }

    private fun setInitialResults() {
        val correctAmount = args.correctAmount
        val incorrectAmount = args.totalQuestions - args.correctAmount
        val totalQuestions = args.totalQuestions
        binding.tvCorrectAmount.text = correctAmount.toString()
        binding.tvIncorrectAmount.text = incorrectAmount.toString()
        binding.piechart.addPieSlice(
            PieModel(
                "Correct",
                correctAmount/totalQuestions.toFloat(),
                Color.GREEN
            )
        )
        binding.piechart.addPieSlice(
            PieModel(
                "Incorrect",
                incorrectAmount/totalQuestions.toFloat(),
                Color.RED
            )
        )
        binding.timeTakenTv.text = args.timeTaken
    }


}