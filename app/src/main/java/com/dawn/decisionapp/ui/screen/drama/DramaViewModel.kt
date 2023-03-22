package com.dawn.decisionapp.ui.screen.drama

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.graphics.createBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.dawn.decisionapp.remote.DecisionApi
import com.dawn.decisionapp.service.SnackbarService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.BufferedInputStream
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class DramaViewModel @Inject constructor(
    private val navController: NavHostController,
    private val decisionApi: DecisionApi,
    private val snackbarService: SnackbarService,
) : ViewModel() {

    private val _randomDramaName = MutableStateFlow("")
    val randomDramaName = _randomDramaName.asStateFlow()

//    private val _randomDramaImageUrl = MutableStateFlow(createBitmap(10,10,Bitmap.Config.ARGB_8888))
    private val _randomDramaImageUrl = MutableStateFlow("")
    val randomDramaImageUrl = _randomDramaImageUrl.asStateFlow()

    private val _randomDramaCategory = MutableStateFlow("")
    val randomDramaCategory = _randomDramaCategory.asStateFlow()

    fun onEvent(event: DramaUiEvent) {
        when (event) {
            DramaUiEvent.RetryButtonPressed -> {
                getRandomDrama()
            }
        }
    }

    private fun getRandomDrama() {
        viewModelScope.launch {
            val randomDrama = decisionApi.getRandomDrama().body()!!
            _randomDramaName.value = randomDrama.name
            _randomDramaCategory.value = randomDrama.category
            _randomDramaImageUrl.value = randomDrama.image_url
//            _randomDramaImageUrl.value = getImageFormUrl(randomDrama.image_url)
        }
    }
//
//    private fun getImageFormUrl(imageUrl: String): Bitmap {
//        var bitmap: Bitmap? = null
//
//        val url = URL(imageUrl)
//        val connection = url.openConnection()
//        connection.connect()
//
//        val size = connection.contentLength
//        val bis = BufferedInputStream(connection.getInputStream(), size)
//        bitmap = BitmapFactory.decodeStream(bis)
//
//        bis.close()
//
//        return bitmap
//    }

    init {
        getRandomDrama()
    }
}

sealed class DramaUiEvent {
    object RetryButtonPressed : DramaUiEvent()
}