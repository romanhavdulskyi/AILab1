package com.havdulskyi.ailab1.useCases

abstract class BaseUseCaseTwoParam<in T, in P, out M> {
    abstract suspend fun invoke(param : T, param2 : P) : M
}