package com.joma.youtubeapp.ui.playlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joma.youtubeapp.common.Resource
import com.joma.youtubeapp.data.model.playlist.MainResponce
import com.joma.youtubeapp.data.model.playlistItems.Videos
import com.joma.youtubeapp.repository.PlaylistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(private val repository: PlaylistRepository) :
    ViewModel() {

    var liveData = MutableLiveData<Resource<MainResponce?>>()

    fun getPlaylist() {
        liveData = repository.getPlaylists()
    }
}
