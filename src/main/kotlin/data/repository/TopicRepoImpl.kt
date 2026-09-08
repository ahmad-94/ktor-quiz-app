package com.example.data.repository

import com.example.data.database.entity.TopicEntity
import com.example.data.database.mapper.toQuestionTopic
import com.example.data.database.mapper.toQuestionTopicEntity
import com.example.data.util.Constants.TOPIC_COLLECTION
import com.example.domain.model.Topic
import com.example.domain.repository.TopicRepo
import com.example.domain.util.DataError
import com.example.domain.util.Result
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Sorts
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class TopicRepoImpl(
    database: MongoDatabase
): TopicRepo {
    val topicCollection = database
        .getCollection<TopicEntity>(TOPIC_COLLECTION)

    override suspend fun getAllTopics(): Result<List<Topic>, DataError> {
        return try {
            val sort = Sorts.ascending(Topic::code.name)
            val topics = topicCollection
                .find()
                .sort(sort)
                .map { it.toQuestionTopic() }
                .toList()
            if (topics.isNotEmpty()) {
                Result.Success(topics)
            } else {
                Result.Failure(DataError.NotFound)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun getTopicById(id: String?): Result<Topic, DataError> {
        if (id.isNullOrEmpty()) {
            Result.Failure(DataError.Validation)
        }
        return try {
            val filteredTopic = Filters.eq(
                TopicEntity::_id.name, id
            )
            val topic = topicCollection
                .find(filteredTopic)
                .map { it.toQuestionTopic() }
                .firstOrNull()
            return if (topic != null) {
                Result.Success(topic)
            } else {
                Result.Failure(DataError.NotFound)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun upsertTopic(topic: Topic): Result<Unit, DataError> {
        return try {
            if (topic.id == null) {
                topicCollection.insertOne(topic.toQuestionTopicEntity())
            } else {
                val filteredTopicById = Filters.eq(
                    TopicEntity::_id.name, topic.id
                )
                val updatedTopicById = Updates.combine(
                    Updates.set(TopicEntity::name.name, topic.name ),
                    Updates.set(TopicEntity::imageUrl.name, topic.imageUrl ),
                    Updates.set(TopicEntity::code.name, topic.code ),
                )
                val updatedTopic = topicCollection.updateOne(filteredTopicById, updatedTopicById)
                if (updatedTopic.modifiedCount == 0L) {
                    return Result.Failure(DataError.NotFound)
                }
            }
            return Result.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun deleteTopicById(id: String?): Result<Unit, DataError> {
        if (id.isNullOrEmpty()) {
            Result.Failure(DataError.Validation)
        }
        return try {
            val filteredTopicById = Filters.eq(
                TopicEntity::_id.name, id
            )
            val deletedTopic = topicCollection
                .deleteOne(filteredTopicById)
            val isDeleted = deletedTopic.deletedCount > 0
            if (isDeleted) {
                Result.Success(Unit)
            } else {
                Result.Failure(DataError.NotFound)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.Failure(DataError.Database)
        }
    }
}