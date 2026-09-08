package com.example.presentation.route.topic

import io.ktor.resources.Resource

@Resource("/quiz/topics")
class TopicRoutesPath {

    @Resource("/create&update")
    data class CreateTopic (
        val parent: TopicRoutesPath = TopicRoutesPath(),
        val topicId: String? = null
        )
    @Resource("/getTopic/{topicId?}")
    data class GetTopic (
        val parent: TopicRoutesPath = TopicRoutesPath(),
        val topicId: String? = null
    )
    @Resource("/deleteTopic/{topicId?}")
    data class DeleteTopic (
        val parent: TopicRoutesPath = TopicRoutesPath(),
        val topicId: String? = null
    )

}