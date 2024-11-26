package com.android.covidapiapp

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.android.covidapiapp.utils.Constants.API_KEY
import com.android.covidapiapp.utils.Constants.BASE_URL
import com.android.covidapiapp.utils.Constants.TAG
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val commentBodies = mutableListOf<String>()
    private lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up logging interceptor for debugging
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        // Build OkHttpClient (No need for AuthInterceptor since API key is in the headers)
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)  // Optional: logs request/response
            .build()

        // Set up Retrofit with the OkHttpClient
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val myApi = retrofit.create(MyApi::class.java)

        // Set up ListView and ArrayAdapter
        val listView = findViewById<ListView>(R.id.listView)
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, commentBodies)
        listView.adapter = arrayAdapter

        // Fetch comments asynchronously using the already created myApi
        getAllComments(myApi) // Pass the Retrofit API instance to fetch comments
    }

    private fun getAllComments(myApi: MyApi) {
        commentBodies.add("GETTING INFO")
        arrayAdapter.notifyDataSetChanged()

        // Use the provided myApi instance to make the request
        myApi.getComments().enqueue(object : Callback<List<Comments>> {
            override fun onResponse(call: Call<List<Comments>>, response: Response<List<Comments>>) {
                // Log response to see what is returned
                Log.d(TAG, "Response: ${response.body()}")

                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.isEmpty()) {
                            commentBodies.add("EMPTY SUCCESS")  // No comments received
                        } else {
                            for (comment in it) {
                                commentBodies.add(comment.toString())  // Add comment to list
                            }
                        }

                        // Refresh ListView after data update
                        arrayAdapter.notifyDataSetChanged()
                    } ?: run {
                        commentBodies.add("EMPTY SUCCESS - No Data")  // API response is null
                        arrayAdapter.notifyDataSetChanged()
                    }
                } else {
                    commentBodies.add("NOT SUCCESSFUL (not 200)")
                    arrayAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                // Log the error to understand why failure occurred
                Log.e(TAG, "onFailure: ${t.message}")
                commentBodies.add("FAILURE")
                arrayAdapter.notifyDataSetChanged()
            }
        })
    }
}
