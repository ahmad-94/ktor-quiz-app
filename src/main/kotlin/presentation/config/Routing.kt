package com.example.presentation.config

import com.example.domain.repository.IssueReportRepo
import com.example.domain.repository.QuestionRepo
import com.example.domain.repository.TopicRepo
import com.example.presentation.route.issue.deleteIssueById
import com.example.presentation.route.issue.getIssues
import com.example.presentation.route.issue.insertIssue
import com.example.presentation.route.question.getAllQuizQuestions
import com.example.presentation.route.question.getQuestionById
import com.example.presentation.route.question.insertAllQuestions
import com.example.presentation.route.question.quizQuestionById
import com.example.presentation.route.question.upsertQuestion
import com.example.presentation.route.root
import com.example.presentation.route.topic.deleteTopicById
import com.example.presentation.route.topic.getAllTopics
import com.example.presentation.route.topic.getTopicById
import com.example.presentation.route.topic.upsertQuestionTopic
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val questionRepository: QuestionRepo by inject()

    val topicRepository: TopicRepo by inject()
    val issueRepository: IssueReportRepo by inject()


    routing {
        root()

        // Questions
        getAllQuizQuestions(questionRepository)
        upsertQuestion(questionRepository)
        insertAllQuestions(questionRepository)
        getQuestionById(questionRepository)
        quizQuestionById(questionRepository)

        // Topics
        getAllTopics(topicRepository)
        getTopicById(topicRepository)
        deleteTopicById(topicRepository)
        upsertQuestionTopic(topicRepository)

        // Issue
        getIssues(issueRepository)
        insertIssue(issueRepository)
        deleteIssueById(issueRepository)

        staticResources(
            basePackage = "images",
            remotePath = "/images"
        )
    }
}


