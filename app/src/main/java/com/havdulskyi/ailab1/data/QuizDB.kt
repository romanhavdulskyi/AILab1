package com.havdulskyi.ailab1.data

import android.content.Context
import android.util.Log
import androidx.core.util.Supplier
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.havdulskyi.ailab1.data.dao.QuestionDao
import com.havdulskyi.ailab1.data.dao.UserDao
import com.havdulskyi.ailab1.data.model.Answer
import com.havdulskyi.ailab1.data.model.Question
import com.havdulskyi.ailab1.data.model.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent


@TypeConverters(RoomTypeConverter::class)
@Database(entities = [Question::class, User::class], version = 1)
abstract class QuizDB : RoomDatabase() {
    abstract fun questionDao(): QuestionDao?
    abstract fun userDao(): UserDao?

    companion object {

        lateinit var quizDB: QuizDB
        fun createSupplier(context: Context?): Supplier<QuizDB> {
            return Supplier { create(context) }
        }

        fun create(context: Context?): QuizDB {
            Log.d("AppDatabase", "OnCreate")
            quizDB =  Room.databaseBuilder(context!!, QuizDB::class.java, "QUIZ_DB")
                .fallbackToDestructiveMigration()
                .addCallback(rdc)
                .build()

            return quizDB
        }

        var rdc: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {

                GlobalScope.launch {
                    val question01 = Question(
                        "1", "Переживаєте за успіх в роботі",
                        "Новачок", mutableListOf(
                            Answer(0, "Сильно", 5),
                            Answer(1, "Не дуже", 3),
                            Answer(2, "Спокійний", 2)
                        )
                    )

                    val question02 = Question(
                        "2", "Прагнете досягти швидко результату",
                        "Новачок", mutableListOf(
                            Answer(0, "Поступово", 2),
                            Answer(1, "Якомога швидше", 3),
                            Answer(2, "Дуже", 5)
                        )
                    )

                    val question03 = Question(
                        "3", "Легко попадаєте в тупик при проблемах в роботі",
                        "Новачок", mutableListOf(
                            Answer(0, "Неодмінно", 5),
                            Answer(1, "Поступово", 3),
                            Answer(2, "Зрідка", 2)
                        )
                    )

                    val question04 = Question(
                        "4", "Чи   потрібен чіткий алгоритм для вирішення задач",
                        "Новачок", mutableListOf(
                            Answer(0, "Так", 5),
                            Answer(1, "В окремих випадках", 3),
                            Answer(2, "Не потрібний", 2)
                        )
                    )

                    val question11 = Question(
                        "5", "Чи використовуєте власний досвід при вирішенні задач",
                        "Твердий початківець", mutableListOf(
                            Answer(0, "Зрідка", 5),
                            Answer(1, "Частково", 3),
                            Answer(2, "Ні", 2)
                        )
                    )

                    val question12 = Question(
                        "6", "Чи користуєтесь фіксованими правилами  для вирішення задач",
                        "Твердий початківець", mutableListOf(
                            Answer(0, "Так", 2),
                            Answer(1, "В окремих випадках", 3),
                            Answer(2, "Не потрібні", 5)
                        )
                    )

                    val question13 = Question(
                        "7", "Чи відчуваєте ви загальний контекст вирішення задачі",
                        "Твердий початківець", mutableListOf(
                            Answer(0, "Так", 2),
                            Answer(1, "Частково", 3),
                            Answer(2, "В окремих випадках", 5)
                        )
                    )

                    val question21 = Question(
                        "8", "Чи можете ви побудувати модель вирішуваної задачі",
                        "Компетентний", mutableListOf(
                            Answer(0, "Так", 5),
                            Answer(1, "Не повністю", 3),
                            Answer(2, "Зрідка", 2)
                        )
                    )

                    val question22 = Question(
                        "9", "Чи вистачає вам ініціативи при вирішенні задач",
                        "Компетентний", mutableListOf(
                            Answer(0, "Так", 5),
                            Answer(1, "Зрідка", 3),
                            Answer(2, "Потрібне натхнення", 2)
                        )
                    )

                    val question23 = Question(
                        "10", "Чи можете вирішувати проблеми, з якими ще не стикались",
                        "Компетентний", mutableListOf(
                            Answer(0, "Так", 2),
                            Answer(1, "В окремих випадках", 3),
                            Answer(2, "Ні", 5)
                        )
                    )

                    val question31 = Question(
                        "11", "Чи  необхідний вам весь контекст задачі",
                        "Досвідчений", mutableListOf(
                            Answer(0, "Так", 5),
                            Answer(1, "В окремих деталях", 3),
                            Answer(2, "В загальному", 2)
                        )
                    )

                    val question32 = Question(
                        "12", "Чи переглядаєте ви свої наміри до вирішення задачі",
                        "Досвідчений", mutableListOf(
                            Answer(0, "Так", 5),
                            Answer(1, "Зрідка", 3),
                            Answer(2, "Коли є потреба", 2)
                        )
                    )

                    val question33 = Question(
                        "13", "Чи здатні  ви  навчатись у інших",
                        "Досвідчений", mutableListOf(
                            Answer(0, "Так", 5),
                            Answer(1, "Зрідка", 3),
                            Answer(2, "Коли є потреба", 2)
                        )
                    )

                    val question41 = Question(
                        "14", "Чи обираєте ви нові методи своєї роботи",
                        "Експерт", mutableListOf(
                            Answer(0, "Так", 5),
                            Answer(1, "Вибірково", 3),
                            Answer(2, "Вистачає досвіду", 2)
                        )
                    )

                    val question42 = Question(
                        "15", "Чи допомагає власна інтуїція при вирішенні задач",
                        "Експерт", mutableListOf(
                            Answer(0, "Так", 5),
                            Answer(1, "Частково", 3),
                            Answer(2, "При емоційному напруженні", 2)
                        )
                    )

                    val question43 = Question(
                        "16", "Чи застовуєте рішення задач за аналогією",
                        "Експерт", mutableListOf(
                            Answer(0, "Частко", 5),
                            Answer(1, "Зрідка", 3),
                            Answer(2, "Тільки власний варіант", 2)
                        )
                    )

                    val user = User(0, "test", "test", "", mutableListOf())

                    quizDB.questionDao()?.insert(question01)
                    quizDB.questionDao()?.insert(question02)
                    quizDB.questionDao()?.insert(question03)
                    quizDB.questionDao()?.insert(question04)
                    quizDB.questionDao()?.insert(question11)
                    quizDB.questionDao()?.insert(question12)
                    quizDB.questionDao()?.insert(question13)
                    quizDB.questionDao()?.insert(question21)
                    quizDB.questionDao()?.insert(question22)
                    quizDB.questionDao()?.insert(question23)
                    quizDB.questionDao()?.insert(question31)
                    quizDB.questionDao()?.insert(question32)
                    quizDB.questionDao()?.insert(question33)
                    quizDB.questionDao()?.insert(question41)
                    quizDB.questionDao()?.insert(question42)
                    quizDB.questionDao()?.insert(question43)

                    quizDB.userDao()?.insert(user)
                }
            }

            override fun onOpen(db: SupportSQLiteDatabase) {
                // do something every time database is open
            }
        }
    }

}