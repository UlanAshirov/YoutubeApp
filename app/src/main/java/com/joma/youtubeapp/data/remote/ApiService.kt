package com.joma.youtubeapp.data.remote

import com.joma.youtubeapp.BuildConfig.API_KEY
import com.joma.youtubeapp.BuildConfig.CHANNEl_ID
import com.joma.youtubeapp.data.model.playlist.MainResponce
import com.joma.youtubeapp.data.model.playlistItems.Videos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    fun getPlaylists(
        @Query("part") part: String,
        @Query("channelId") channelId: String = CHANNEl_ID,
        @Query("key") key: String = API_KEY,
        @Query("maxResults") max: Int = 10
    ): Call<MainResponce>

    @GET("playlistItems")
    fun getPlaylistVideos(
        @Query("part") part: String,
        @Query("playlistId") playlistsId: String,
        @Query("key") key: String = API_KEY
    ): Call<Videos>
}