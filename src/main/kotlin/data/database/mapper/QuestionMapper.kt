package com.example.data.database.mapper

import com.example.data.database.entity.QuestionEntity
import com.example.domain.model.Question

fun QuestionEntity.toQuizQuestion() = Question(
    id = _id,
    question = question,
    correctAnswer = correctAnswer,
    incorrectAnswers = incorrectAnswers,
    explanation = explanation,
    topicCode = topicCode,
)

fun  Question.toQuizQuestionEntity() = QuestionEntity(
    question = question,
    correctAnswer = correctAnswer,
    incorrectAnswers = incorrectAnswers,
    explanation = explanation,
    topicCode = topicCode,
)