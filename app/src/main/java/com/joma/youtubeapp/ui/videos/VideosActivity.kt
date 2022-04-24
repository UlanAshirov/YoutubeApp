package com.joma.youtubeapp.ui.videos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.joma.youtubeapp.databinding.ActivityVideosBinding
import com.joma.youtubeapp.ext.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideosActivity : AppCompatActivity() {
    lateinit var binding: ActivityVideosBinding
    lateinit var viewModel: VideosViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideosBinding.inflate(layoutInflater).also { setContentView(it.root) }
        setupUI()
        getData()
    }

    private fun setupUI() {
        binding.tvTitle.text = "HellYeahPlay"
        binding.tvDes.text = "Ты попал к 32 летнему деду-мизантропу с психическими расстройствами, который смотрит видосы, любит аниме, Minecraft и детей. ТВОИ ДЕЙСТВИЯ, КРАСАВЧИК? Прохождение игр любых жанров, реакции на видосы и специальные интерактивные, разговорные или чил стримы."
        viewModel = ViewModelProvider(this)[VideosViewModel::class.java]
    }

    private fun getData() {
        val extras: Bundle? = intent.extras
        val id = extras?.getString("playlistId")
        showToast(id + "")
    }
}
