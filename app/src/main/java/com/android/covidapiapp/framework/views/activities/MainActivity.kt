package com.android.covidapiapp.framework.views.activities

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.android.covidapiapp.data.network.model.CovidObject
import com.android.covidapiapp.R
import com.android.covidapiapp.data.network.CovidAPIService
import com.android.covidapiapp.data.network.NetworkModuleDI
import com.android.covidapiapp.utils.Constants.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val commentBodies = mutableListOf<String>()
    private lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up ListView and ArrayAdapter
        val listView = findViewById<ListView>(R.id.listView)
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, commentBodies)
        listView.adapter = arrayAdapter

        // Automatically returns Api Instance
        getAllComments(NetworkModuleDI()())
    }

    private fun getAllComments(covidAPIService: CovidAPIService) {
        //commentBodies.add("GETTING INFO")
        arrayAdapter.notifyDataSetChanged()

        // Use the provided myApi instance to make the request
        covidAPIService.getComments().enqueue(object : Callback<List<CovidObject>> {
            override fun onResponse(call: Call<List<CovidObject>>, response: Response<List<CovidObject>>) {
                // Log response to see what is returned
                Log.d(TAG, "Response: ${response.body()}")

                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.isEmpty()) {
                            commentBodies.add("200 RESPONSE - No data")  // No comments received
                        } else {
                            commentBodies.add("\uD835\uDDD6\uD835\uDDE2\uD835\uDDE9\uD835\uDDDC\uD835\uDDD7 \uD835\uDFED\uD835\uDFF5 \uD835\uDDE3\uD835\uDDD4\uD835\uDDE1\uD835\uDDD7\uD835\uDDD8\uD835\uDDE0\uD835\uDDDC\uD835\uDDD6 \uD835\uDDD7\uD835\uDDD4\uD835\uDDE7\uD835\uDDD4 ~ \uD835\uDDDD\uD835\uDDD4\uD835\uDDE1\uD835\uDDE8\uD835\uDDD4\uD835\uDDE5\uD835\uDDEC \uD835\uDFEE\uD835\uDFEC\uD835\uDFEE\uD835\uDFEE\n" +
                                    "\uD835\uDDE6\uD835\uDDE2\uD835\uDDE5\uD835\uDDE7\uD835\uDDD8\uD835\uDDD7 \uD835\uDDD4\uD835\uDDDF\uD835\uDDE3\uD835\uDDDB\uD835\uDDD4\uD835\uDDD5\uD835\uDDD8\uD835\uDDE7\uD835\uDDDC\uD835\uDDD6\uD835\uDDD4\uD835\uDDDF\uD835\uDDDF\uD835\uDDEC \uD835\uDDD5\uD835\uDDEC \uD835\uDDD6\uD835\uDDE2\uD835\uDDE8\uD835\uDDE1\uD835\uDDE7\uD835\uDDE5\uD835\uDDEC:")
                            for (comment in it) {
                                var stringToPresent:String = comment.country + "\n"
                                if (comment.region != "") {
                                    stringToPresent += "(in the region of ${comment.region})\n"
                                }
                                stringToPresent += "Total cases: ${comment.cases.total}.\n" +
                                                   "New cases: ${comment.cases.new}."
                                commentBodies.add(stringToPresent)  // Add comment to list
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

            override fun onFailure(call: Call<List<CovidObject>>, t: Throwable) {
                // Log the error to understand why failure occurred
                Log.e(TAG, "onFailure: ${t.message}")
                commentBodies.add("FAILURE")
                arrayAdapter.notifyDataSetChanged()
            }
        })
    }
}
