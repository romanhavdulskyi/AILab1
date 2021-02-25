package com.havdulskyi.ailab1.quizPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.havdulskyi.ailab1.R
import com.havdulskyi.ailab1.data.QuizState
import com.havdulskyi.ailab1.databinding.FragmentLoginBinding
import com.havdulskyi.ailab1.databinding.FragmentQuizePageBinding
import com.havdulskyi.ailab1.login.LoginViewModel
import com.havdulskyi.ailab1.navigation.QuizNavigator
import org.koin.android.ext.android.inject

class QuizPageFragment : Fragment() {

    private val quizPageViewModel : QuizPageViewModel by inject()
    private val quizNavigator : QuizNavigator by inject()
    private var category : String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mViewBinding = DataBindingUtil.inflate<FragmentQuizePageBinding>(inflater, R.layout.fragment_quize_page, container, false)
        mViewBinding.viewmodel = quizPageViewModel
        mViewBinding.lifecycleOwner = viewLifecycleOwner
        mViewBinding.executePendingBindings()
        arguments?.let {
            category = it.getString(QuizNavigator.NavOption.Category.toString(), null)
        }
        savedInstanceState?.let {
            category = it.getString(QuizNavigator.NavOption.Category.toString(), null)
        }
        quizPageViewModel.loadCategory(category)
        quizPageViewModel.currentQuizState.observe(viewLifecycleOwner, {
            tmp ->
            if(tmp is QuizState.ResultOpened)
                quizNavigator.navigateQuizPageToResultPage(category)
        })
        return mViewBinding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(QuizNavigator.NavOption.Category.toString(), category)
    }
}