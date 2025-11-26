Lights Out App 🔆

A minimalist Android puzzle game built with Kotlin.
Your goal: turn off all the lights on the 3×3 board.
Each tap toggles a tile — and its neighbors — creating a surprisingly tricky little brain-teaser.

📸 Screenshots
Game Board (Start)
<img src="screenshots/start.png" width="300"/>
After Tapping a Tile
<img src="screenshots/new_game.png" width="300"/>
Help Screen
<img src="screenshots/help.png" width="300"/>
Color Selection Menu
<img src="screenshots/change_color.png" width="300"/>
Board After Color Change
<img src="screenshots/after_change_color.png" width="300"/>
✨ Features
🎮 Puzzle Gameplay

Classic Lights Out mechanics

Tap a tile → toggles itself and its neighbors

Win by turning every tile dark

New Game button to restart instantly

🎨 Custom Colors

Choose between four color themes

UI updates automatically when a new theme is applied

❓ Help Menu

Simple explanation of the game rules

Clean and beginner-friendly layout

🧩 Tech Stack

Kotlin

Android Studio

ConstraintLayout

Drawable resources

Multiple activities (Main, Help, Color Selection)

📁 Project Structure
app/
├── src/
│   └── main/
│       ├── java/edu/lukina/lightsout/     → game logic + app activities
│       ├── res/layout/                     → XML UI layouts
│       └── res/drawable/                   → tile styles, custom graphics
screenshots/                                 → images shown in this README

🚀 How to Play

Tap any tile in the 3×3 grid.

The selected tile + its four neighbors flip color.

Turn off all the lights to win!

Use Change Color to pick your favorite theme.

Press Help anytime to review the rules.
