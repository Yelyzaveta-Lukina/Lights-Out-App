package edu.lukina.lightsout

/**
 * Created by Yelyzaveta Lukina on 08/06/25.
 */

// Imports the Random class from Kotlin's standard library for generating random boolean values.
import kotlin.random.Random

// Defines a constant for the size of the game grid (e.g., 3 for a 3x3 grid).
const val GRID_SIZE = 3

// Declares the LightsOutGame class, which encapsulates the logic of the game.
class LightsOutGame {
    // Declares a private 2D array to represent the grid of lights.
    // It's initialized as a GRID_SIZE x GRID_SIZE array where all lights are initially set to 'true' (on).
    private val lightsGrid = Array(GRID_SIZE) { Array(GRID_SIZE) { true } }

    // Declares a public property 'state' of type String to get or set the game's current state.
    var state: String
        // Defines the custom getter for the 'state' property.
        get() {
            // Creates a StringBuilder to efficiently build the string representation of the board.
            val boardString = StringBuilder()
            // Iterates through each row of the lightsGrid.
            for (row in 0 until GRID_SIZE) {
                // Iterates through each column in the current row.
                for (col in 0 until GRID_SIZE) {
                    // Determines the character representation ('T' for true/on, 'F' for false/off) of the light's state.
                    val value = if (lightsGrid[row][col]) 'T' else 'F'
                    // Appends the character to the boardString.
                    boardString.append(value)
                }
            }
            // Returns the complete string representation of the game board.
            return boardString.toString()
        }
        // Defines the custom setter for the 'state' property.
        set(value) {
            // Initializes an index to traverse the input string 'value'.
            var index = 0
            // Iterates through each row of the lightsGrid.
            for (row in 0 until GRID_SIZE) {
                // Iterates through each column in the current row.
                for (col in 0 until GRID_SIZE) {
                    // Sets the state of the light at the current [row][col] based on the character in the input string.
                    // 'T' means the light is on (true), otherwise it's off (false).
                    lightsGrid[row][col] = value[index] == 'T'
                    // Increments the index to move to the next character in the input string.
                    index++
                }
            }
        }

    // Public function to start a new game.
    fun newGame() {
        // Iterates through each row of the lightsGrid.
        for (row in 0 until GRID_SIZE) {
            // Iterates through each column in the current row.
            for (col in 0 until GRID_SIZE) {
                // Sets the state of each light to a random boolean value (either true or false).
                lightsGrid[row][col] = Random.nextBoolean()
                // Comment indicating the grid coordinates being processed (for debugging or understanding).
                // (0,0), (0,1), (0,2)
                // (1,0), (1,1), (1,2)
                // (2,0), (2,1), (2,2)
            }
        }
    }

    // Public function to check if a light at a specific row and column is on.
    fun isLightOn(row: Int, col: Int): Boolean {
        // Returns the boolean state (true if on, false if off) of the light at the given coordinates.
        return lightsGrid[row][col]
    }

    // Public function to handle the selection of a light at a specific row and column.
    fun selectLight(row: Int, col: Int) {
        // Toggles the state of the selected light (on to off, or off to on).
        lightsGrid[row][col] = !lightsGrid[row][col]
        // Checks if there is a light above the selected light.
        if (row > 0) {
            // Toggles the state of the light directly above the selected one.
            lightsGrid[row - 1][col] = !lightsGrid[row - 1][col]
        }
        // Checks if there is a light below the selected light.
        if (row < GRID_SIZE - 1) {
            // Toggles the state of the light directly below the selected one.
            lightsGrid[row + 1][col] = !lightsGrid[row + 1][col]
        }
        // Checks if there is a light to the left of the selected light.
        if (col > 0) {
            // Toggles the state of the light directly to the left of the selected one.
            lightsGrid[row][col - 1] = !lightsGrid[row][col - 1]
        }
        // Checks if there is a light to the right of the selected light.
        if (col < GRID_SIZE - 1) {
            // Toggles the state of the light directly to the right of the selected one.
            lightsGrid[row][col + 1] = !lightsGrid[row][col + 1]
        }
    }

    // Public read-only property to check if the game is over (all lights are off).
    val isGameOver: Boolean
        // Defines the custom getter for the 'isGameOver' property.
        get() {
            // Iterates through each row of the lightsGrid.
            for (row in 0 until GRID_SIZE) {
                // Iterates through each column in the current row.
                for (col in 0 until GRID_SIZE) {
                    // Checks if the current light is on.
                    if (lightsGrid[row][col]) {
                        // If any light is found to be on, the game is not over, so return false.
                        return false
                    }
                }
            }
            // If the loops complete without finding any lights on, all lights are off, and the game is over.
            return true
        }

    // Public function to turn all lights off.
    fun turnLightsOff() {
        // Iterates through each row of the lightsGrid.
        for (row in 0 until GRID_SIZE) {
            // Iterates through each column in the current row.
            for (col in 0 until GRID_SIZE) {
                // Sets the state of the current light to false (off).
                lightsGrid[row][col] = false

                // Comment indicating the grid coordinates being processed (for debugging or understanding).
                // (0,0), (0,1), (0,2)
                // (1,0), (1,1), (1,2)
                // (2,0), (2,1), (2,2)
            }
        }
    }
}