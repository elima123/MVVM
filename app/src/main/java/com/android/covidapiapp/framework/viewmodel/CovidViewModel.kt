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

    fun getCovidData(date: String) {
        viewModelScope.launch() {
            try {
                val data = covidListRequirement.invoke(date)
                if (data != null) {
                    _covidData.value = data!!
                } else {
                    _errorMessage.value = "No data available for this date"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
            }
        }
    }
}
