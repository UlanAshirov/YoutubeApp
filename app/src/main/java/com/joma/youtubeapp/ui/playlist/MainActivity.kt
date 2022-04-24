package com.joma.youtubeapp.ui.playlist

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.joma.youtubeapp.common.Resource
import com.joma.youtubeapp.databinding.ActivityMainBinding
import com.joma.youtubeapp.ext.showToast
import com.joma.youtubeapp.ui.chekInternet.NoInternetActivity
import com.joma.youtubeapp.ui.videos.VideosActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), PlaylistAdapter.SendKeyPlayList<String> {
    lateinit var binding: ActivityMainBinding

    lateinit var adapter: PlaylistAdapter
    lateinit var viewModel: PlaylistViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        checkInternetIsOnline()
    }

    private fun checkInternetIsOnline() {
        if (checkForInternet(this)) {
            setupUI()
            setupObservers()
        } else {
            transaction()
        }
    }

    private fun transaction() {
        val intent = Intent(this, NoInternetActivity::class.java)
        startActivity(intent)
    }

    private fun checkForInternet(context: Context): Boolean {

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            val network = connectivityManager.activeNetwork ?: return false

            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    private fun setupUI() {
        adapter = PlaylistAdapter(this)
        binding.rvPlaylist.adapter = adapter
        viewModel = ViewModelProvider(this)[PlaylistViewModel::class.java]
        viewModel.getPlaylist()
    }

    private fun setupObservers() {
        viewModel.liveData.observe(this, {
            when (it.status) {
                Resource.Status.SUCCESS -> adapter.setPlaylist(it.data!!.items)
                Resource.Status.LOADING -> showToast("Loading")
                Resource.Status.ERROR -> {
                    showToast(it.message + "")
                    Log.e("-------", it.message + "")
                }
            }
        })
    }

    override fun onItemClick(data: String) {
        var intent = Intent(this, VideosActivity::class.java)
        intent.putExtra("playlistId", data)
        startActivity(intent)
    }
}
