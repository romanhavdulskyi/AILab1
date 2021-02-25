package com.havdulskyi.ailab1.quizChooseCategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.havdulskyi.ailab1.R
import com.havdulskyi.ailab1.data.QuizState
import com.havdulskyi.ailab1.databinding.FragmentChooseCategoryPageBinding
import com.havdulskyi.ailab1.databinding.FragmentLoginBinding
import com.havdulskyi.ailab1.databinding.FragmentQuizePageBinding
import com.havdulskyi.ailab1.login.LoginViewModel
import com.havdulskyi.ailab1.navigation.QuizNavigator
import org.koin.android.ext.android.inject

class QuizChooseCategoryFragment : Fragment() {

    private val quizPageViewModel : QuizChooseCategoryViewModel by inject()
    private val quizNavigator : QuizNavigator by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mViewBinding = DataBindingUtil.inflate<FragmentChooseCategoryPageBinding>(inflater, R.layout.fragment_choose_category_page, container, false)
        mViewBinding.viewmodel = quizPageViewModel

        mViewBinding.lifecycleOwner = viewLifecycleOwner
        mViewBinding.executePendingBindings()
        quizPageViewModel.currentQuizState.observe(viewLifecycleOwner, {
            tmp ->
            if(tmp is QuizState.ResultOpened)
                quizNavigator.navigateToResultPage(null)
        })
        return mViewBinding.root
    }
}