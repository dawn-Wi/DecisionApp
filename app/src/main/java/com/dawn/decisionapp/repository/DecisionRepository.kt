package com.dawn.decisionapp.repository

import android.util.Log
import com.dawn.decisionapp.model.Song
import com.dawn.decisionapp.remote.DecisionApi
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class DecisionRepository @Inject constructor(
    private val decisionApi: DecisionApi
) {
    private val _foodList = ArrayList<String>()
    private val _song = ArrayList<Song>()


}