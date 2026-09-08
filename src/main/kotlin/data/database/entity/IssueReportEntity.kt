package com.example.data.database.entity

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class IssueReportEntity(
    @BsonId
    val _id: String = ObjectId().toString(),
    val issueType: String,
    val additionalComment: String? = null,
    val userEmail: String? = null,
    val timeStamp: String
)