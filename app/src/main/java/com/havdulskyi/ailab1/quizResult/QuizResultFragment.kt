package com.havdulskyi.ailab1.quizResult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.havdulskyi.ailab1.R
import com.havdulskyi.ailab1.data.QuizState
import com.havdulskyi.ailab1.databinding.FragmentLoginBinding
import com.havdulskyi.ailab1.databinding.FragmentQuizePageBinding
import com.havdulskyi.ailab1.databinding.FragmentResultPageBinding
import com.havdulskyi.ailab1.login.LoginViewModel
import com.havdulskyi.ailab1.navigation.QuizNavigator
import org.koin.android.ext.android.inject

class QuizResultFragment : Fragment() {

    private val quizResultViewModel : QuizResultViewModel by inject()
    private val quizNavigator : QuizNavigator by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mViewBinding = DataBindingUtil.inflate<FragmentResultPageBinding>(inflater, R.layout.fragment_result_page, container, false)
        mViewBinding.viewmodel = quizResultViewModel
        mViewBinding.lifecycleOwner = viewLifecycleOwner
        mViewBinding.executePendingBindings()
        val adapter = ResultsViewAdapter(viewLifecycleOwner, quizResultViewModel)
        mViewBinding.recyclerView.adapter = adapter
        mViewBinding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        quizResultViewModel.quizResults.observe(viewLifecycleOwner, {
            value ->
            adapter.setData(value)
        })
        quizResultViewModel.currentQuizState.observe(viewLifecycleOwner, {
            value ->
            if(value is QuizState.QuizOpened)
                quizNavigator.navigateBack()
        })
        return mViewBinding.root
    }
}