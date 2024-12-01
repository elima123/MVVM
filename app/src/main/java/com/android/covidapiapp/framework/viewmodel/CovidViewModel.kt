package com.android.covidapiapp.framework.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.covidapiapp.data.network.model.CovidObject
import com.android.covidapiapp.domain.CovidListRequirement
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// launch a viewModelScope to fetch de data
class CovidViewModel : ViewModel() {

    private val covidListRequirement = CovidListRequirement()

    private val CovidData = MutableLiveData<List<CovidObject>>() // correct data type = List<CovidObject>
    val Observee_CovidData: LiveData<List<CovidObject>> get() = CovidData

    private val ErrorMessage = MutableLiveData<String>()
    val Observee_ErrorMessage: LiveData<String> get() = ErrorMessage

    // Fetch the COVID data for a given date
    fun getCovidData(date: String) {
        viewModelScope.launch {
            try {
                val data = covidListRequirement.invoke(date)
                if (!data.isNullOrEmpty()) {
                    CovidData.value = data
                } else {
                    ErrorMessage.value = "Data is empty."
                }
            } catch (e: Exception) {
                ErrorMessage.value = "Error: ${e.message}"
            }
        }
    }
}
