package fr.systemathicdev.commons.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import fr.systemathicdev.commons.databinding.ViewHorizontalDividerBinding

class HorizontalDivider(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private val binding: ViewHorizontalDividerBinding
    private val layoutInflater: LayoutInflater by lazy { LayoutInflater.from(context) }

    init {
        binding = ViewHorizontalDividerBinding.inflate(layoutInflater, this, true)
    }
}