package fr.systemathicdev.commons.views

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BackFragment : Fragment() {

    lateinit var onBackPressed: OnBackPressedCallback

    protected var _binding: ViewBinding? = null

    open fun createOnBackPressed(): OnBackPressedCallback {
        return object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onBackPressed = createOnBackPressed()
        requireActivity().onBackPressedDispatcher.addCallback(this,onBackPressed)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.explode)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}