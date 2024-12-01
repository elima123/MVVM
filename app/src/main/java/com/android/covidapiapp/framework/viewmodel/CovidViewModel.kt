package com.android.covidapiapp.framework.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.covidapiapp.data.network.model.CovidObject
import com.android.covidapiapp.domain.CovidListRequirement
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CovidViewModel : ViewModel() {

    private val covidListRequirement = CovidListRequirement()

    private val _covidData = MutableLiveData<List<CovidObject>>() // correct data type = List<CovidObject>
    val covidData: LiveData<List<CovidObject>> get() = _covidData

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    // Fetch the COVID data for a given date
    fun getCovidData(date: String) {
        viewModelScope.launch {
            try {
                // Fetch data using the provided date
                val data = covidListRequirement.invoke(date)

                // Check if data is not null and update LiveData
                if (!data.isNullOrEmpty()) {
                    _covidData.value = data // Set the fetched data
                } else {
                    // If data is null or empty, post an appropriate error message
                    _errorMessage.value = "No data available for this date."
                }
            } catch (e: Exception) {
                // If an exception occurs, post the error message
                _errorMessage.value = "Error: ${e.message}"
            }
        }
    }
}
