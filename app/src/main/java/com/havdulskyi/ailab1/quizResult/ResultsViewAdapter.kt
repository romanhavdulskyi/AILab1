package com.havdulskyi.ailab1.quizResult

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.havdulskyi.ailab1.R
import com.havdulskyi.ailab1.databinding.QuizResultCategoryItemBinding

class ResultsViewAdapter(private val lifecycleOwner: LifecycleOwner,
                         private val quizResultViewModel: QuizResultViewModel) : RecyclerView.Adapter<ResultsViewAdapter.ItemViewHolder>() {

    private var values: List<QuizResultItemCategoryViewModel> = mutableListOf()

    init {
        setHasStableIds(true)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

    }

    fun setData(values: List<QuizResultItemCategoryViewModel>) {
        this.values = values
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return values[position].categoryName.hashCode().toLong()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ResultsViewAdapter.ItemViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate<QuizResultCategoryItemBinding>(LayoutInflater.from(parent.context), R.layout.quiz_result_category_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ResultsViewAdapter.ItemViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = values.size

    inner class ItemViewHolder(private val quizResultCategoryItemBinding: QuizResultCategoryItemBinding)
        : RecyclerView.ViewHolder(quizResultCategoryItemBinding.root) {

        @SuppressLint("ClickableViewAccessibility")
        fun bind(item : QuizResultItemCategoryViewModel) {
            quizResultCategoryItemBinding.viewModelItem = item
            quizResultCategoryItemBinding.viewModel = quizResultViewModel
            quizResultCategoryItemBinding.lifecycleOwner = lifecycleOwner
            quizResultCategoryItemBinding.barChart.animate(item.questionsResults)
            quizResultCategoryItemBinding.executePendingBindings()
        }
    }
}
