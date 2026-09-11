package com.example.domain.repository

import com.example.domain.model.Question
import com.example.domain.util.DataError
import com.example.domain.util.Result

interface QuestionRepo {
    suspend fun getAllQuestions(topicCode: Int?, limit: Int?): Result<List<Question>, DataError>
    suspend fun getQuestionById(id: String?): Result<Question, DataError>
    suspend fun upsertQuestion(question: Question):Result<Unit, DataError>
    suspend fun insertAllQuestions(questions: List<Question>):Result<Unit, DataError>
    suspend fun deleteQuestionById(id: String?): Result<Unit, DataError>
}