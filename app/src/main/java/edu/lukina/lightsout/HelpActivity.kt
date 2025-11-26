package edu.lukina.lightsout

/**
 * Created by Yelyzaveta Lukina on 08/06/25.
 */

// Imports the Bundle class, used for passing data between activities and saving instance state.
import android.os.Bundle
// Imports the enableEdgeToEdge function, which allows the app to draw behind system bars.
import androidx.activity.enableEdgeToEdge
// Imports AppCompatActivity, a base class for activities that use the support library action bar features.
import androidx.appcompat.app.AppCompatActivity
// Imports ViewCompat, a utility class for accessing features of View in a backward-compatible way.
import androidx.core.view.ViewCompat
// Imports WindowInsetsCompat, which provides information about system window insets (like status bars and navigation bars).
import androidx.core.view.WindowInsetsCompat

// Declares the HelpActivity class, which inherits from AppCompatActivity.
class HelpActivity : AppCompatActivity() {
    // Called when the activity is first created. This is where most initialization should go.
    override fun onCreate(savedInstanceState: Bundle?) {
        // Calls the superclass's onCreate method to perform default initialization.
        super.onCreate(savedInstanceState)
        // Enables edge-to-edge display for this activity, allowing content to be drawn under system bars.
        enableEdgeToEdge()
        // Sets the user interface layout for this activity from the activity_help.xml file.
        setContentView(R.layout.activity_help)
        // Sets an OnApplyWindowInsetsListener on the view with ID "main" (likely the root view of activity_help.xml).
        // This listener is triggered when the window insets (e.g., status bar, navigation bar) change.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            // Gets the insets for system bars (status bar, navigation bar, etc.).
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Sets padding on the view 'v' to account for the system bars, preventing content from being obscured.
            // The padding is applied to the left, top, right, and bottom of the view.
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            // Returns the original insets, allowing other listeners to also process them if needed.
            insets
        }
    }
}