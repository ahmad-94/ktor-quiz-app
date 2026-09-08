package com.example.domain.repository

import com.example.domain.model.Topic
import com.example.domain.util.DataError
import com.example.domain.util.Result

interface TopicRepo {
    suspend fun getAllTopics(): Result<List<Topic>, DataError>
    suspend fun getTopicById(id: String?): Result<Topic, DataError>
    suspend fun upsertTopic(topic: Topic): Result<Unit, DataError>
    suspend fun deleteTopicById(id: String?): Result<Unit, DataError>
}