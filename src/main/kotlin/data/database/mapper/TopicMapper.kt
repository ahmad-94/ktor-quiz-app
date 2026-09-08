package com.example.data.database.mapper

import com.example.data.database.entity.TopicEntity
import com.example.domain.model.Topic

fun Topic.toQuestionTopicEntity() = TopicEntity(
    name = name,
    imageUrl = imageUrl,
    code = code
)

fun TopicEntity.toQuestionTopic() = Topic(
    id = _id,
    name = name,
    imageUrl = imageUrl,
    code = code
)