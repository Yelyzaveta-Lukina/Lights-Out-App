package edu.lukina.lightsout

/**
 * Created by Yelyzaveta Lukina on 08/06/25.
 */

// Imports the Activity class, fundamental for Android application components.
import android.app.Activity
// Imports the Bundle class, used for passing data between activities and saving instance state.
import android.os.Bundle
// Imports the View class, the basic building block for user interface components.
import android.view.View
// Imports the GridLayout class, a layout that places its children in a rectangular grid.
import android.widget.GridLayout
// Imports the Toast class, used for showing short, transient messages to the user.
import android.widget.Toast
// Imports AppCompatActivity, a base class for activities that use the support library action bar features.
import androidx.appcompat.app.AppCompatActivity
// Imports ContextCompat for accessing context-specific features, like colors, in a backward-compatible way.
import androidx.core.content.ContextCompat
// Imports the children extension property for ViewGroup, allowing easy iteration over child views.
import androidx.core.view.children
// Imports the Intent class, used for inter-component communication, like starting new activities.
import android.content.Intent
// Imports ActivityResultContracts, part of the modern way to handle activity results.
import androidx.activity.result.contract.ActivityResultContracts

// Defines a constant string key for saving and retrieving the game state.
const val GAME_STATE = "gameState"


// Declares the MainActivity class, which inherits from AppCompatActivity.
class MainActivity : AppCompatActivity() {

    // Declares a lateinit variable for the LightsOutGame instance, which will hold the game logic.
    private lateinit var game: LightsOutGame
    // Declares a lateinit variable for the GridLayout that displays the lights.
    private lateinit var lightGridLayout: GridLayout
    // Declares a variable to store the resolved color value for "light on". Initialized to 0.
    private var lightOnColor = 0
    // Declares a variable to store the resolved color value for "light off". Initialized to 0.
    private var lightOffColor = 0
    // Declares a variable to store the resource ID of the "light on" color. Initialized to 0.
    private var lightOnColorId = 0

    // Called when the activity is first created. This is where most initialization should go.
    override fun onCreate(savedInstanceState: Bundle?) {
        // Calls the superclass's onCreate method to perform default initialization.
        super.onCreate(savedInstanceState)
        // Sets the user interface layout for this activity from the activity_main.xml file.
        setContentView(R.layout.activity_main)

        // Sets the initial resource ID for the "light on" color to yellow.
        lightOnColorId = R.color.yellow

        // Finds and assigns the GridLayout view from the layout XML using its ID.
        lightGridLayout = findViewById(R.id.light_grid)

        // Iterates over all child views (buttons) within the lightGridLayout.
        for (gridButton in lightGridLayout.children) {
            // Sets the same click listener (onLightButtonClick) for each button in the grid.
            gridButton.setOnClickListener(this::onLightButtonClick)
        }

        // Sets a long click listener on the first child of the GridLayout (button at index 0).
        lightGridLayout.getChildAt(0).setOnLongClickListener {
            // Calls the game logic to turn all lights off.
            game.turnLightsOff()
            // Updates the button colors on the screen to reflect the changes.
            setButtonColors()
            // Returns true to indicate that the long click event has been consumed.
            true
        }

        // Gets the actual color value for "light on" from the color resources.
        lightOnColor = ContextCompat.getColor(this, R.color.yellow)
        // Gets the actual color value for "light off" from the color resources.
        lightOffColor = ContextCompat.getColor(this, R.color.black)

        // Creates a new instance of the LightsOutGame class.
        game = LightsOutGame()

        // Checks if the activity is being recreated from a previous state (e.g., after rotation).
        if (savedInstanceState == null) {
            // If it's a new instance, starts a new game.
            startGame()
        } else {
            // If it's being recreated, restores the game state from the savedInstanceState Bundle.
            // The !! operator asserts that getString(GAME_STATE) will not be null.
            game.state = savedInstanceState.getString(GAME_STATE)!!
            // Restores the resource ID for the "light on" color.
            lightOnColorId = savedInstanceState.getInt(EXTRA_COLOR)
            // Gets the actual color value for "light on" using the restored color ID.
            lightOnColor = ContextCompat.getColor(this, lightOnColorId)
            // Updates the button colors on the screen to reflect the restored state.
            setButtonColors()
        }
    }

    // Called by the system to save the activity's current dynamic state before it is potentially destroyed.
    override fun onSaveInstanceState(outState: Bundle) {
        // Calls the superclass's onSaveInstanceState method.
        super.onSaveInstanceState(outState)
        // Puts the current game state string into the outState Bundle.
        outState.putString(GAME_STATE, game.state)
        // Puts the current "light on" color resource ID into the outState Bundle.
        outState.putInt(EXTRA_COLOR, lightOnColorId)
    }

    // Private helper function to initialize and start a new game.
    private fun startGame() {
        // Calls the newGame method on the game object to reset the game logic.
        game.newGame()
        // Updates the button colors on the screen to reflect the new game state.
        setButtonColors()
    }

    // Private function called when any light button in the grid is clicked.
    private fun onLightButtonClick(view: View) {

        // Finds the index of the clicked button within the GridLayout.
        val buttonIndex = lightGridLayout.indexOfChild(view)
        // Calculates the row of the clicked button based on its index and the grid size.
        val row = buttonIndex / GRID_SIZE
        // Calculates the column of the clicked button based on its index and the grid size.
        val col = buttonIndex % GRID_SIZE

        // Calls the selectLight method on the game object to update the game state.
        game.selectLight(row, col)
        // Updates the button colors on the screen to reflect the changes.
        setButtonColors()

        // Checks if the game is over (all lights are off).
        if (game.isGameOver) {
            // If the game is over, displays a congratulatory message to the user using a Toast.
            Toast.makeText(this, R.string.congrats, Toast.LENGTH_SHORT).show()
        }
    }

    // Private helper function to update the background colors of all buttons in the grid.
    private fun setButtonColors() {

        // Iterates through each button in the GridLayout by index.
        for (buttonIndex in 0 until lightGridLayout.childCount) {
            // Gets the button view at the current index.
            val gridButton = lightGridLayout.getChildAt(buttonIndex)

            // Calculates the row of the current button based on its index and grid size.
            val row = buttonIndex / GRID_SIZE
            // Calculates the column of the current button based on its index and grid size.
            val col = buttonIndex % GRID_SIZE

            // Checks if the light at the current (row, col) is on according to the game logic.
            if (game.isLightOn(row, col)) {
                // If the light is on, sets the button's background color to lightOnColor.
                gridButton.setBackgroundColor(lightOnColor)
            } else {
                // If the light is off, sets the button's background color to lightOffColor.
                gridButton.setBackgroundColor(lightOffColor)
            }
        }
    }

    // Public function called when the "New Game" button (defined in XML with android:onClick) is clicked.
    fun onNewGameClick(view: View) {
        // Starts a new game.
        startGame()
    }

    // Public function called when the "Help" button (defined in XML with android:onClick) is clicked.
    fun onHelpClick(view: View) {
        // Creates an Intent to start the HelpActivity.
        val intent = Intent(this, HelpActivity::class.java)
        // Starts the HelpActivity.
        startActivity(intent)
    }

    // Public function called when the "Change Color" button (defined in XML with android:onClick) is clicked.
    fun onChangeColorClick(view: View) {
        // Creates an Intent to start the ColorActivity.
        val intent = Intent(this, ColorActivity::class.java)
        // Puts the current "light on" color ID as an extra in the Intent.
        intent.putExtra(EXTRA_COLOR, lightOnColorId)
        // Launches the ColorActivity using the modern Activity Result API and waits for a result.
        colorResultLauncher.launch(intent)
    }

    // Registers a callback for an Activity result. This is part of the modern Activity Result API.
    // It will be triggered when ColorActivity finishes and returns a result.
    val colorResultLauncher = registerForActivityResult(
        // Specifies that we are starting an activity for a result.
        ActivityResultContracts.StartActivityForResult()
        // Lambda expression that defines what to do when the result is received.
    ) { result ->
        // Checks if the result code from ColorActivity is RESULT_OK, indicating success.
        if (result.resultCode == Activity.RESULT_OK) {
            // Retrieves the chosen color ID from the result Intent data.
            // Uses R.color.yellow as a default if EXTRA_COLOR is not found.
            // The !! operator asserts that result.data will not be null if resultCode is RESULT_OK.
            lightOnColorId = result.data!!.getIntExtra(EXTRA_COLOR, R.color.yellow)
            // Gets the actual color value for "light on" using the newly chosen color ID.
            lightOnColor = ContextCompat.getColor(this, lightOnColorId)
            // Updates the button colors on the screen to reflect the new color.
            setButtonColors()
        }
    }
}