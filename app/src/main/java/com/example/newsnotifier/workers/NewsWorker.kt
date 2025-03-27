package com.example.newsnotifier.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.newsnotifier.network.RetrofitInstance

class NewsWorker(context: Context, workerParams: WorkerParameters): CoroutineWorker(context,workerParams){
    override suspend fun doWork(): Result {
        val newHeadline = fetchLatestNews() ?: "No news available"
            showNotification(newHeadline)

        return Result.success()
    }

    private suspend fun fetchLatestNews(): String? {
        return try {
            val response = RetrofitInstance.api.getTopHeadlines(apiKey = "2faa7677b94a69ab5f3eafd6c1b6f686")
            Log.d("NewsWorker", "API Response: $response") // ✅ Log response
            response.articles.firstOrNull()?.title ?: "No news available"
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("NewsWorker", "API Error: ${e.message}") // ✅ Log errors
            null
        }
    }



    private fun showNotification(news:String){
        NotificationHelper(applicationContext).showNotification(news)

    }
}