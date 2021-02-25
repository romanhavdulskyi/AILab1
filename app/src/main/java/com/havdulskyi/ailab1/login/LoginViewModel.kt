package com.havdulskyi.ailab1.login

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.havdulskyi.ailab1.useCases.LoginUserCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class LoginViewModel(private val loginUserCase: LoginUserCase) : ViewModel() {

    val loginProperty = ObservableField("")
    val passProperty = ObservableField("")
    val state = MutableLiveData<LoginState>()

    init {
        state.value = LoginState.Waiting
    }

    fun processLogin(v : View?)
    {
        viewModelScope.launch {
            loginUserCase.invoke(loginProperty.get() ?: "",
                passProperty.get() ?: "").collect {
                    res ->
                state.value = if(res)
                    LoginState.Successful
                else
                    LoginState.Error

            }
        }
    }

    sealed class LoginState{
        object Waiting : LoginState()
        object Error : LoginState()
        object Successful : LoginState()
    }
}