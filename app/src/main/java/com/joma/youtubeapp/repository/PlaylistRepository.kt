package com.joma.youtubeapp.repository

import androidx.lifecycle.MutableLiveData
import com.joma.youtubeapp.common.Resource
import com.joma.youtubeapp.data.model.playlist.MainResponce
import com.joma.youtubeapp.data.model.playlistItems.Videos
import com.joma.youtubeapp.data.remote.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PlaylistRepository @Inject constructor(private val api: ApiService) {

    fun getPlaylists(): MutableLiveData<Resource<MainResponce?>> {
        val liveData = MutableLiveData<Resource<MainResponce?>>()
        liveData.value = Resource.loading()
        api.getPlaylists("snippet, contentDetails").enqueue(object : Callback<MainResponce> {
            override fun onResponse(call: Call<MainResponce>, response: Response<MainResponce>) {
                if (response.isSuccessful) {
                    liveData.value = Resource.success(response.body())
                }
            }

            override fun onFailure(call: Call<MainResponce>, t: Throwable) {
                liveData.value = Resource.error(t.localizedMessage)
            }
        })
        return liveData
    }

    fun getPlaylistVideos(id: String): MutableLiveData<Resource<Videos?>> {
        val liveData = MutableLiveData<Resource<Videos?>>()
        liveData.value = Resource.loading()
        api.getPlaylistVideos("snippet, contentDetails", id).enqueue(object : Callback<Videos> {
            override fun onResponse(call: Call<Videos>, response: Response<Videos>) {
                if (response.isSuccessful) {
                    liveData.value = Resource.success(response.body())
                }
            }

            override fun onFailure(call: Call<Videos>, t: Throwable) {
                liveData.value = Resource.error(t.message)
            }
        })
        return liveData
    }

}