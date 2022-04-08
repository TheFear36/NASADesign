import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun View.snack(text: Int, actionText: Int, action: Unit){
    Snackbar.make(this, text, Snackbar.LENGTH_INDEFINITE)
        .setAction(actionText) { action }
        .show()
}
fun View.show(): View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

fun View.hide(): View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}

fun Context.toast(text: String) {
    Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
}

fun Context.toast(text: Int) {
    Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
}