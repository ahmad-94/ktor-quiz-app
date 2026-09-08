package com.example.data.repository

import com.example.data.database.entity.QuestionEntity
import com.example.data.database.mapper.toQuizQuestion
import com.example.data.database.mapper.toQuizQuestionEntity
import com.example.data.util.Constants
import com.example.domain.model.Question
import com.example.domain.repository.QuestionRepo
import com.example.domain.util.DataError
import com.example.domain.util.Result
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlin.text.isNullOrEmpty

class QuestionRepoImpl(
     mongoDatabase: MongoDatabase
): QuestionRepo {

    val questionCollection = mongoDatabase.getCollection<QuestionEntity>(Constants.QUIZ_COLLECTION)

    override suspend fun getAllQuestions(
        topicCode: Int?,
        limit: Int?
    ): Result<List<Question>, DataError> {

        return try {
           val topicCodeQuery = topicCode?.let {
               Filters.eq(QuestionEntity::topicCode.name, topicCode)
           } ?: Filters.empty()

           val limitQuery = limit?.takeIf { it > 0 } ?: 10

           val questions = questionCollection
               .find(topicCodeQuery)
               .limit(limitQuery)
               .map { it.toQuizQuestion() }
               .toList()
           if (questions.isNotEmpty()) {
               Result.Success(questions)
           } else {
               Result.Failure(DataError.NotFound)
           }
       } catch (e: Exception) {
           print(e)
           Result.Failure(DataError.Database)
       }

    }

    override suspend fun getQuestionById(id: String?): Result<Question, DataError> {
        if (id.isNullOrEmpty()) {
           return Result.Failure(DataError.Validation)
        }
        return try {
             val filteredQuery = Filters.eq(
                 QuestionEntity::_id.name, id
             )
            val questionEntity = questionCollection
                .find(filteredQuery)
                .firstOrNull()
            if (questionEntity != null) {
                val question = questionEntity.toQuizQuestion()
                Result.Success(question)
            } else {
               Result.Failure(DataError.NotFound)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.Failure(DataError.Database)
        }

    }

    override suspend fun upsertQuestion(question: Question): Result<Unit, DataError> {

        return try {
            if (question.id == null) {
                questionCollection.insertOne(question.toQuizQuestionEntity())
            } else {
                val questionById = Filters.eq(
                    QuestionEntity::_id.name, question.id
                )
                val updatedQuestionById = Updates.combine(
                    Updates.set(QuestionEntity::question.name, question.question),
                    Updates.set(QuestionEntity::correctAnswer.name, question.correctAnswer),
                    Updates.set(QuestionEntity::incorrectAnswers.name, question.incorrectAnswers),
                    Updates.set(QuestionEntity::explanation.name, question.explanation),
                    Updates.set(QuestionEntity::topicCode.name, question.topicCode)
                )
                val updatedResult = questionCollection.updateOne(questionById, updatedQuestionById)
                if (updatedResult.modifiedCount == 0L) {
                    return Result.Failure(DataError.NotFound)
                }
            }
            Result.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun deleteQuestionById(id: String?): Result<Unit, DataError> {
        if (id.isNullOrEmpty()) {
            return Result.Failure(DataError.Validation)
        }
        return try {
            val filteredQuery = Filters.eq(
                QuestionEntity::_id.name, id
            )
            val deleteResult = questionCollection
                .deleteOne(filteredQuery)
            val isDeleted = deleteResult.deletedCount > 0
            if (isDeleted) {
                Result.Success(Unit)
            } else {
                Result.Failure(DataError.NotFound)
            }

        } catch (e: Exception) {
            print(e)
            return Result.Failure(DataError.Database)
        }
    }
}