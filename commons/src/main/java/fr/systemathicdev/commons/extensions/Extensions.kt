package fr.systemathicdev.commons.extensions

import android.content.Context
import fr.systemathicdev.commons.R
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso

fun Context.makeToast(message: String) = Toast.makeText(
    this,
    message,
    Toast.LENGTH_SHORT
).show()

fun View.setVisibility(visible: Boolean) {
    if (visible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun ImageView.setImageUrl(
    url: String,
    onStartCallback: () -> Unit = {},
    onSuccessCallback: () -> Unit = {},
    onErrorCallback: (e: Exception?) -> Unit = {},
) {
    if (url.isNotEmpty()) {
        onStartCallback.invoke()
        Picasso.get()
            .load(url)
            .into(this, object : Callback {
                    override fun onError(e: java.lang.Exception?) {
                        this@setImageUrl.setImageResource(R.drawable.ic_baseline_image_24)
                        onErrorCallback.invoke(e)
                    }

                    override fun onSuccess() {
                        onSuccessCallback.invoke()
                    }
                })
    } else {
        this.setImageResource(R.drawable.ic_baseline_image_24)
    }
}

fun NavController.navigateSafe(directions: NavDirections?) {
    if (directions != null && currentDestination?.getAction(directions.actionId) != null) {
        navigate(directions)
    }
}

fun Throwable.getMessageNotNull() = message ?: toString()