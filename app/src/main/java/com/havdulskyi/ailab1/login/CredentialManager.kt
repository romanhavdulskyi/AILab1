package com.havdulskyi.ailab1.login

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged

@ExperimentalCoroutinesApi
class CredentialManager {

    private val _credentialFlow  = MutableSharedFlow<Credential>(replay = 1)

    val credentialFlow : Flow<Credential> = _credentialFlow

    fun putCredential(credential: Credential)
    {
        _credentialFlow.tryEmit(credential)
    }

    fun putCredential(name : String, pass : String)
    {
        _credentialFlow.tryEmit(Credential(name, pass))
    }

    fun clearCredential()
    {
        _credentialFlow.tryEmit(Credential("", ""))
    }
}