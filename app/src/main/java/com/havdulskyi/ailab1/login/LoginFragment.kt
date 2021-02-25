package com.havdulskyi.ailab1.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.havdulskyi.ailab1.R
import com.havdulskyi.ailab1.databinding.FragmentLoginBinding
import com.havdulskyi.ailab1.navigation.QuizNavigator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.inject

@ExperimentalCoroutinesApi
class LoginFragment : Fragment() {

    private val loginViewModel : LoginViewModel by inject()
    private val quizNavigator : QuizNavigator by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mViewBinding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login, container, false)
        mViewBinding.viewmodel = loginViewModel
        mViewBinding.lifecycleOwner = viewLifecycleOwner
        mViewBinding.executePendingBindings()
        loginViewModel.state.observe(viewLifecycleOwner, {
            tmp ->
            if(tmp is LoginViewModel.LoginState.Successful)
                quizNavigator.navigateToCategoryPage()
        })
        return mViewBinding.root
    }
}