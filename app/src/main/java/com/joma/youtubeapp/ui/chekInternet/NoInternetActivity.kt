package com.joma.youtubeapp.ui.chekInternet

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joma.youtubeapp.databinding.ActivityNoInternetBinding
import com.joma.youtubeapp.ext.showToast
import com.joma.youtubeapp.ui.playlist.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoInternetActivity : AppCompatActivity() {
    lateinit var binding: ActivityNoInternetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoInternetBinding.inflate(layoutInflater).also { setContentView(it.root) }
        isOnline()
    }

    private fun isOnline() {
        binding.btnCheckInternet.setOnClickListener {
            if (checkInternet(this)) {
                transaction()
            } else {
                showToast("No Internet")
            }
        }
    }

    private fun checkInternet(context: Context): Boolean {
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

    private fun transaction() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}