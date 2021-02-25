package com.havdulskyi.ailab1.useCases

abstract class BaseUseCase<in T, out M> {
    abstract suspend fun invoke(vararg param : T) : M
}