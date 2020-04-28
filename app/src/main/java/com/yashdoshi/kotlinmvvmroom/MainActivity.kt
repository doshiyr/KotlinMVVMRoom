package com.yashdoshi.kotlinmvvmroom

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.yashdoshi.kotlinmvvmroom.adapters.ActivityAdapter
import com.yashdoshi.kotlinmvvmroom.viewmodel.BoredViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: BoredViewModel
    lateinit var activityAdapter: ActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(BoredViewModel::class.java)
        activityAdapter = ActivityAdapter(null)
        horizontalRecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        horizontalRecyclerview.adapter = activityAdapter
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(horizontalRecyclerview)
        viewModel.isLoading.observe(this, Observer {
            if(it){
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.INVISIBLE
            }
        })
        viewModel.isError.observe(this, Observer {
            Snackbar.make(container, it, Snackbar.LENGTH_SHORT).show()
        })
        viewModel.boredActivity.observe(this, Observer {boredActivityList ->
            if(boredActivityList.isNotEmpty()) {
                if(this::activityAdapter.isInitialized){
                    activityAdapter.submitList(boredActivityList)
                    horizontalRecyclerview.scrollToPosition(boredActivityList.size - 1)
                }
            }
        })
        btnNewActivity.setOnClickListener {
            viewModel.getBoredActivity()
        }
    }
}
