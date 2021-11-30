package com.jlopez.mathtrainer.ResultScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jlopez.mathtrainer.R
import com.jlopez.mathtrainer.remote.responses.MathQuestion
import org.w3c.dom.Text

class ResultScreenAdapter(private val questionList: List<MathQuestion>, private val attemptsList: List<Int>) : RecyclerView.Adapter<ResultScreenAdapter.ViewHolder> () {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ResultScreenAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.quiestionitem_list, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentQuestion = questionList[position]
        holder.questionText.text = currentQuestion.expression
        holder.attemptsText.text = attemptsList[position].toString()

    }

    class ViewHolder(MathQuestion: View) : RecyclerView.ViewHolder(MathQuestion) {
        val questionText: TextView = itemView.findViewById(R.id.questionTextTv)
        val attemptsText: TextView = itemView.findViewById(R.id.attemptsTextTv)
    }


}