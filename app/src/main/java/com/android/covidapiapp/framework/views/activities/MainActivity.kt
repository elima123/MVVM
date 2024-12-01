package com.android.covidapiapp.framework.views.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
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
            covidData?.let {
                // Handle the new COVID data
                for (item in it) {
                    var stringToPresent: String = item.country + ".\n"
                    if (item.region != "") {
                        stringToPresent += "(in the region of ${item.region})\n"
                    }
                    stringToPresent += "Total reported cases of COVID: ${item.cases.total}.\n" +
                            "Increment of COVID cases today: ${item.cases.new}."
                    commentBodies.add(stringToPresent)  // Add comment to list
                }

                arrayAdapter.notifyDataSetChanged()
            }
        })

        covidViewModel.errorMessage.observe(this, Observer { error ->
            // Handle error messages
            commentBodies.add("Error: The data is empty/unavailable or the date is incorrect.")
            arrayAdapter.notifyDataSetChanged()
        })

        // Set up SearchView and Button
        val searchView = findViewById<SearchView>(R.id.query_searchView_id)
        val searchButton = findViewById<Button>(R.id.query_button_id)

        // Set up search button click listener
        searchButton.setOnClickListener {
            commentBodies.clear()  // Clear old data
            val query = searchView.query.toString().trim()  // Get query from SearchView
            if (query.isNotEmpty()) {
                // Trigger the fetch for the entered date
                covidViewModel.getCovidData(query)  // Use the query value as the date
            }
        }
    }
}
