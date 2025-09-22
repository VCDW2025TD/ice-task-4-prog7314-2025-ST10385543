package com.example.memestreamapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memestreamapp.data.model.Gif
import com.example.memestreamapp.data.network.GiphyService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class FeedViewModel : ViewModel() {

    private val _gifs = MutableLiveData<List<Gif>>()
    val gifs: LiveData<List<Gif>> get() = _gifs

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.giphy.com/v1/gifs/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val service = retrofit.create(GiphyService::class.java)

    fun loadTrending() {
        viewModelScope.launch {
            try {
                val response = service.getTrending(apiKey = "YOUR_API_KEY", limit = 25)
                _gifs.postValue(response.data)
            } catch (e: Exception) {
                _gifs.postValue(emptyList())
            }
        }
    }
}
