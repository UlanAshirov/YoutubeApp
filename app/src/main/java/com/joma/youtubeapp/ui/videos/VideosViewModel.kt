package com.joma.youtubeapp.ui.videos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joma.youtubeapp.common.Resource
import com.joma.youtubeapp.data.model.playlistItems.Videos
import com.joma.youtubeapp.repository.PlaylistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideosViewModel @Inject constructor(private val repository: PlaylistRepository) :
    ViewModel() {

    var videosLiveData = MutableLiveData<Resource<Videos?>>()

    fun getPlaylistVideos(id: String) {
        videosLiveData = repository.getPlaylistVideos(id)
    }
}