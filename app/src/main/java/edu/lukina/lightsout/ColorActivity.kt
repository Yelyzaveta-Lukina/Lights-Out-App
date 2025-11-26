package edu.lukina.lightsout

/**
 * Created by Yelyzaveta Lukina on 08/06/25.
 */

// Imports the Intent class, used for inter-component communication, like starting new activities or returning results.
import android.content.Intent
// Imports the Bundle class, used for passing data between activities and saving instance state.
import android.os.Bundle
// Imports the View class, the basic building block for user interface components.
import android.view.View
// Imports the RadioButton class, a UI element that allows users to select one option from a set.
import android.widget.RadioButton
// Imports the enableEdgeToEdge function, which allows the app to draw behind system bars (though not explicitly used in onCreate's logic here beyond setup).
import androidx.activity.enableEdgeToEdge
// Imports AppCompatActivity, a base class for activities that use the support library action bar features.
import androidx.appcompat.app.AppCompatActivity
// Imports ViewCompat, a utility class for accessing features of View in a backward-compatible way (though not explicitly used in onCreate's logic here beyond setup).
import androidx.core.view.ViewCompat
// Imports WindowInsetsCompat, which provides information about system window insets (though not explicitly used in onCreate's logic here beyond setup).
import androidx.core.view.WindowInsetsCompat

// Defines a constant string key used to pass the selected color ID via an Intent.
const val EXTRA_COLOR = "edu.lukina.lightsout.color"

// Declares the ColorActivity class, which inherits from AppCompatActivity.
class ColorActivity : AppCompatActivity() {
    // Called when the activity is first created. This is where most initialization should go.
    override fun onCreate(savedInstanceState: Bundle?) {
        // Calls the superclass's onCreate method to perform default initialization.
        super.onCreate(savedInstanceState)
        // Sets the user interface layout for this activity from the activity_color.xml file.
        setContentView(R.layout.activity_color)

        // Retrieves the color ID passed from MainActivity via an Intent extra.
        // If EXTRA_COLOR is not found in the intent, it defaults to R.color.yellow.
        val colorId = intent.getIntExtra(EXTRA_COLOR, R.color.yellow)

        // Determines the ID of the RadioButton that should be checked based on the received colorId.
        val radioId = when (colorId) {
            // If the received colorId matches R.color.red, use the ID of radio_red.
            R.color.red -> R.id.radio_red
            // If the received colorId matches R.color.orange, use the ID of radio_orange.
            R.color.orange -> R.id.radio_orange
            // If the received colorId matches R.color.green, use the ID of radio_green.
            R.color.green -> R.id.radio_green
            // For any other colorId (or the default R.color.yellow), use the ID of radio_yellow.
            else -> R.id.radio_yellow
        }

        // Finds the RadioButton view in the layout using its determined ID.
        val radioButton = findViewById<RadioButton>(radioId)
        // Sets the found RadioButton's state to checked.
        radioButton.isChecked = true

    }

    // Public function called when one of the color RadioButtons (defined in XML with android:onClick) is selected.
    fun onColorSelected(view: View) {
        // Determines the color resource ID based on the ID of the clicked RadioButton view.
        val colorId = when (view.id) {
            // If the clicked view's ID is radio_red, set colorId to R.color.red.
            R.id.radio_red -> R.color.red
            // If the clicked view's ID is radio_orange, set colorId to R.color.orange.
            R.id.radio_orange -> R.color.orange
            // If the clicked view's ID is radio_green, set colorId to R.color.green.
            R.id.radio_green -> R.color.green
            // For any other clicked view ID (assumed to be radio_yellow), set colorId to R.color.yellow.
            else -> R.color.yellow
        }

        // Creates a new Intent to send data back to the calling activity (MainActivity).
        val dataIntent = Intent()
        // Puts the selected colorId as an extra in the dataIntent.
        dataIntent.putExtra(EXTRA_COLOR, colorId)
        // Sets the result of this activity to RESULT_OK and includes the dataIntent.
        setResult(RESULT_OK, dataIntent)
        // Finishes this activity and returns to the previous activity (MainActivity).
        finish()
    }
}