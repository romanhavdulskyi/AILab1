package com.havdulskyi.ailab1.quizResult

data class QuizResultItemCategoryViewModel(val categoryName : String,
                                           val overallResult : String,
                                           val questionsResults : List<Pair<String, Float>>,
                                           val overallItem : Boolean) {

}