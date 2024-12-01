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

// Execute software
class MainActivity : AppCompatActivity() {

    private val ListViewContent = mutableListOf<String>()
    // Built-in Adapter
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private val covidViewModel: CovidViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Built-in Adapter
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ListViewContent)
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = arrayAdapter

        // Observe the LiveData from ViewModel
        covidViewModel.Observee_CovidData.observe(this, Observer { covidData ->
            covidData?.let {
                // Iterate the CovidObject list mentioned in CovidAPIService
                for (item in it) {
                    var stringToPresent: String = item.country + ".\n"
                    if (item.region != "") {
                        stringToPresent += "(in the region of ${item.region})\n"
                    }
                    stringToPresent += "Total reported cases of COVID: ${item.cases.total}.\n" +
                            "Increment of COVID cases today: ${item.cases.new}."

                    // Add new CovidObject (refined) to the display
                    ListViewContent.add(stringToPresent)
                }
                // Once the list its ready, notify display update
                arrayAdapter.notifyDataSetChanged()
            }
        })

        // Handle error messages
        covidViewModel.Observee_ErrorMessage.observe(this, Observer { error ->
            ListViewContent.add("Error: The data is empty/unavailable or the date is incorrect. ($error)")
            arrayAdapter.notifyDataSetChanged()
        })

        // Button configuration to delete previous content and prompt CovidViewModel
        val searchView = findViewById<SearchView>(R.id.query_searchView_id)
        val searchButton = findViewById<Button>(R.id.query_button_id)
        searchButton.setOnClickListener {
            ListViewContent.clear()  // Clear up display content
            val query = searchView.query.toString() // Get string from search bar
            if (query.isNotEmpty()) {
                covidViewModel.getCovidData(query) // Trigger the fetch for searchView string
            }
        }
    }
}
