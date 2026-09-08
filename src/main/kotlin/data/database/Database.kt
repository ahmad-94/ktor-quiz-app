package data.database

import com.example.data.util.Constants
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase

object Database {
    fun create(): MongoDatabase {
        val connectionString = System.getenv(Constants.MONGODB_URI)
        val databaseName = Constants.QUIZ_DB
        val mongoClient = MongoClient.create(connectionString)
        return mongoClient.getDatabase(databaseName)

    }
}