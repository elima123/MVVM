package com.android.covidapiapp.framework.views.activities

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.android.covidapiapp.R
import com.android.covidapiapp.framework.viewmodel.CovidViewModel


class MainActivity : AppCompatActivity() {

    private val commentBodies = mutableListOf<String>()
    private lateinit var arrayAdapter: ArrayAdapter<String>

    // Get the ViewModel instance using viewModels delegate
    private val covidViewModel: CovidViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up ListView and ArrayAdapter
        val listView = findViewById<ListView>(R.id.listView)
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, commentBodies)
        listView.adapter = arrayAdapter

        // Observe the LiveData from ViewModel
        covidViewModel.covidData.observe(this, Observer { covidData ->
            // Handle the new COVID data
            covidData?.let {
                // Update UI with the COVID data

                for (item in it) {
                    var stringToPresent:String = item.country + "\n"
                    if (item.region != "") {
                        stringToPresent += "(in the region of ${item.region})\n"
                    }
                    stringToPresent += "Total cases: ${item.cases.total}.\n" +
                            "New cases: ${item.cases.new}."
                    commentBodies.add(stringToPresent)  // Add comment to list
                }

                arrayAdapter.notifyDataSetChanged()
            }

            // PERFECT, now, for item in CovidData, print the strings we used on repo
        })

        covidViewModel.errorMessage.observe(this, Observer { error ->
            // Handle error messages
            Log.e("CovidApp", "Error: $error")
            commentBodies.add("Error: $error")
            arrayAdapter.notifyDataSetChanged()
        })

        // Fetch COVID data for a specific date
        covidViewModel.getCovidData("2023-01-01")
    }
}
