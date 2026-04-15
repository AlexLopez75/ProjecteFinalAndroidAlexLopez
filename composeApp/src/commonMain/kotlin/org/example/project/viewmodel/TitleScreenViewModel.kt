package org.example.project.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TitleScreenViewModel: ViewModel() {
    var showMessage by mutableStateOf(false)
    private set

    fun modifyShowMessage(){
        showMessage = !showMessage
    }
}