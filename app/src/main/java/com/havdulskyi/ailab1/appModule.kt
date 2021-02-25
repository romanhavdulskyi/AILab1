package com.havdulskyi.ailab1

import com.havdulskyi.ailab1.data.QuizDB
import com.havdulskyi.ailab1.data.repository.RepositoryFacade
import com.havdulskyi.ailab1.login.CredentialManager
import com.havdulskyi.ailab1.login.LoginViewModel
import com.havdulskyi.ailab1.navigation.QuizNavigator
import com.havdulskyi.ailab1.navigation.QuizNavigatorImpl
import com.havdulskyi.ailab1.quizChooseCategory.QuizChooseCategoryViewModel
import com.havdulskyi.ailab1.quizPage.QuizPageViewModel
import com.havdulskyi.ailab1.quizResult.QuizResultViewModel
import com.havdulskyi.ailab1.useCases.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val appModule = module {
    single(named("QuizDatabaseSupplier")) { QuizDB.createSupplier(get()) }
    single { CredentialManager() }
    single { RepositoryFacade(get(named("QuizDatabaseSupplier")), get()) }
    single { LoginUserCase(get(), get()) }
    single { GetAvailableQuestionsCase(get()) }
    single { GetCurrentQuizStateCase(get()) }
    single { GetCurrentUserStateCase(get()) }
    single { ResetAllAnswerStateCase(get()) }
    single { ResetLastCategoryAnswerStateCase(get()) }
    single { ResetCategoryAnswerStateCase(get()) }
    single { SaveAnswerStateCase(get()) }
    single { GetQuizResultCase(get()) }
    single { GetCurrentQuizStateForResultPageCase(get()) }
    single { QuizNavigatorImpl() } bind QuizNavigator::class
    viewModel  { LoginViewModel(get()) }
    viewModel  { QuizChooseCategoryViewModel(get(), get()) }
    viewModel  { QuizPageViewModel(get(), get(), get(), get(), get()) }
    viewModel  { QuizResultViewModel(get(), get(), get(), get()) }


}