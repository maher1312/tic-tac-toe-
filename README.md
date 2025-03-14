Here’s the updated `README.md` file with the adjusted methods from the `Game.java` and `Cell.java` classes:

---

# Tic Tac Toe Game

This is a simple Tic Tac Toe game implemented in Java. The game supports two modes:
1. **1v1**: Two players can play against each other.
2. **1vAI**: A player can play against a basic AI.

## Difficulty Levels

The game now includes two difficulty levels for the AI:
1. **Easy**: The AI makes random moves.
2. **Hard**: The AI uses a basic strategy to block the player and prioritize winning moves.

When starting a game in 1vAI mode, you will be prompted to select the difficulty level.

---

## Project Structure

The project consists of the following classes, each with a specific role in the game:

### 1. **Cell.java**
   - **Description**: An abstract class representing a cell in the Tic Tac Toe grid. It contains basic properties and methods for cell manipulation.
   - **Key Methods**:
     - `getX()`, `getY()`: Retrieve the cell's coordinates.
     - `setX(int x)`, `setY(int y)`: Set the cell's coordinates.
     - `equals(Cell cell)`: Check if two cells are equal based on their coordinates.
     - `isAdjacent(Cell cell)`: Check if a cell is adjacent to another cell.
     - `cellIsEmpty(Cell cell)`: Check if a cell is empty.
     - `sameClassType(Cell cell)`: Check if two cells are of the same type (e.g., both are `X_cell` or `O_cell`).
   - **Subclasses**:
     - `Empty_cell.java`: Represents an empty cell.
     - `X_cell.java`: Represents a cell occupied by player X.
     - `O_cell.java`: Represents a cell occupied by player O.

---

### 2. **Empty_cell.java**
   - **Description**: A subclass of `Cell` representing an empty cell.
   - **Key Methods**:
     - `toString()`: Returns a space character (`" "`) to represent an empty cell.

---

### 3. **X_cell.java**
   - **Description**: A subclass of `Cell` representing a cell occupied by player X.
   - **Key Methods**:
     - `toString()`: Returns a red-colored `"X"` to represent the player's mark.

---

### 4. **O_cell.java**
   - **Description**: A subclass of `Cell` representing a cell occupied by player O.
   - **Key Methods**:
     - `toString()`: Returns a blue-colored `"O"` to represent the player's mark.

---

### 5. **Map.java**
   - **Description**: Represents the Tic Tac Toe grid. It manages the cells and provides methods to set and get cells.
   - **Key Methods**:
     - `Map()`: Constructor initializes a 3x3 grid with empty cells.
     - `setCell(Cell cell)`: Places a cell (X or O) on the grid.
     - `getCell(int x, int y)`: Retrieves the cell at the specified coordinates.
     - `toString()`: Displays the grid in a user-friendly format with row and column labels.
     - `getx()`, `gety()`: Returns the dimensions of the grid.

---

### 6. **Game.java**
   - **Description**: Manages the game logic, including player turns, win conditions, and tie detection.
   - **Key Methods**:
     - `Game(Map map)`: Constructor initializes the game with a map and sets the initial game state.
     - `getCoordinates(String input)`: Converts input (e.g., "A1") into grid coordinates.
     - `setCellOnMap(String input)`: Places a cell (X or O) on the grid based on the player's input.
     - `isFeasable(String input)`: Checks if the selected cell is empty and valid.
     - `flipTurn(String turn)`: Switches the turn between two players in 1v1 mode.
     - `flipTurnAi(String turn)`: Switches the turn between the player and the AI in 1vAI mode.
     - `autoGenerateO()`: Implements a basic AI strategy to place an O on the grid (Hard mode).
     - `autoGenerateO_Easy()`: Places an O randomly on the grid (Easy mode).
     - `tryToWinOrBlock(char player)`: Checks if the AI can win or block the player in the next move.
     - `checkWin(char player)`: Checks if the specified player has won the game.
     - `play1v1()`: Handles the game flow for 1v1 mode.
     - `play1vAI_Hard()`: Handles the game flow for 1vAI mode with Hard difficulty.
     - `play1vAI_Easy()`: Handles the game flow for 1vAI mode with Easy difficulty.
     - `handleInput()`: Processes player input and validates it.
     - `win()`: Checks if a player has won the game.
     - `tie()`: Checks if the game has ended in a tie.

---

### 7. **Input.java**
   - **Description**: Contains constants for valid input positions on the grid (e.g., A1, B2, C3).
   - **Key Constants**:
     - `A1`, `A2`, `A3`, `B1`, `B2`, `B3`, `C1`, `C2`, `C3`: Represent the valid cell positions on the grid.

---

### 8. **driver.java**
   - **Description**: The main class that starts the game and handles user input for game mode selection.
   - **Key Methods**:
     - `main(String[] args)`: Entry point of the program. Prompts the user to choose between 1v1 and 1vAI modes and starts the game accordingly.

---

### 9. **Human_user.java**
   - **Description**: Represents a human player in the game.
   - **Key Methods**:
     - `Human_user(String name)`: Constructor initializes a human player with a given name.
     - `getName()`: Returns the name of the human player.

---

### 10. **Computer_user.java**
   - **Description**: Represents the AI player in the game.
   - **Key Methods**:
     - `Computer_user()`: Constructor initializes the AI player with the name "AI".
     - `getName()`: Returns the name of the AI player.

---

## Class Relationships

- **Cell** is the base class for all cell types (`Empty_cell`, `X_cell`, `O_cell`). It provides common functionality for all cells.
- **Map** uses the `Cell` class to manage the grid. It stores a 2D array of `Cell` objects and provides methods to manipulate them.
- **Game** interacts with the `Map` class to update the grid based on player moves and checks for win/tie conditions.
- **Input** provides constants that are used by the `Game` class to validate player input.
- **driver** initializes the game and interacts with the `Game` class to start the appropriate game mode.
- **Human_user** and **Computer_user** extend the `User` class to represent human and AI players, respectively.

---

## How to Play

1. **Clone the repository** or download the source code.
2. **Compile the code** using a Java compiler.
3. **Run the `driver.java`** file to start the game.
4. Choose the game mode:
   - **1v1**: Two players take turns to place X and O on the grid.
   - **1vAI**: Play against the AI, which will automatically place O on the grid.
5. Follow the prompts to enter the cell index (e.g., A1, B2, C3) to place your mark.
6. The game will announce the winner or a tie once the game is over.

---

## Game Rules

- The game is played on a 3x3 grid.
- Players take turns to place their marks (X or O) on the grid.
- The first player to get three of their marks in a row (horizontally, vertically, or diagonally) wins.
- If all cells are filled and no player has three marks in a row, the game ends in a tie.

---

## Features

- **Color-coded output**: X is displayed in red, and O is displayed in blue.
- **Input validation**: The game ensures that players can only place their marks in empty cells.
- **Basic AI**: The AI uses a simple strategy to place its mark, prioritizing winning moves, blocking the player, and selecting the center or corners.

---

## Future Improvements

- **Enhanced AI**: Implement a more sophisticated AI using algorithms like Minimax for better decision-making.
- **GUI**: Add a graphical user interface for a more interactive experience.
- **Multiplayer**: Implement an online multiplayer mode.
- **Score Tracking**: Add a feature to track scores across multiple games.

---

## Dependencies

- **Java**: The project requires Java to be installed on your system.

---

## Running the Game

To run the game, use the following command in your terminal or command prompt:

```bash
javac driver.java
java driver
```

Enjoy playing Tic Tac Toe!

---

