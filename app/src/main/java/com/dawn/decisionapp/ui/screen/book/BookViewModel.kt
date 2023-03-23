package com.dawn.decisionapp.ui.screen.book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.dawn.decisionapp.remote.DecisionApi
import com.dawn.decisionapp.service.SnackbarService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val navController: NavHostController,
    private val decisionApi: DecisionApi,
    private val snackbarService: SnackbarService
): ViewModel(){
    private val _randomBookTitle = MutableStateFlow("")
    val randomBookTitle = _randomBookTitle.asStateFlow()

    private val _randomBookImageUrl = MutableStateFlow("")
    val randomBookImageUrl = _randomBookImageUrl.asStateFlow()

    private val _randomBookAuthors = MutableStateFlow("")
    val randomBookAuthors = _randomBookAuthors.asStateFlow()

    fun onEvent(event : BookUiEvent){
        when(event){
            BookUiEvent.RetryButtonPressed -> {
                getRandomBook()
            }
        }
    }

    private fun getRandomBook(){
        viewModelScope.launch {
            val randomBook = decisionApi.getRandomBook().body()!!
            _randomBookTitle.value = randomBook.title
            _randomBookImageUrl.value = randomBook.image_url
            _randomBookAuthors.value = randomBook.authors
        }
    }

    init {
        getRandomBook()
    }
}
sealed class BookUiEvent{
    object RetryButtonPressed : BookUiEvent()
}