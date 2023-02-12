package fr.systemathicdev.data.utils

import android.content.Intent

class IntentFactory{
    fun create(action: String) =
        Intent(action)
}