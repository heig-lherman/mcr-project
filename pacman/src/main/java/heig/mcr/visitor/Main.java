package heig.mcr.visitor;

/**
 * Main class for the Pac-man inspired game.
 * <p>
 * This game is a simple implementation of the Pac-man game, with a few Star Wars inspired twists.
 * Its implementation serves as a demonstration of the Visitor pattern, specifically in how it can be
 * used to handle interactions between different types of actors in the game.
 * <p>
 * The game is played on a grid, with the player controlling a character that can move in four directions.
 * The player must collect pellets and avoid the ghosts, which move in a predictable manner. The player can
 * also collect super pellets, which allows them to eat the ghosts for a short period of time. The player
 * wins the game by collecting all the pellets on the board.
 * <p>
 * The game is implemented using the Visitor pattern to handle interactions between the different types of
 * actors in the game. Each actor in the game (player, ghosts, pellets, etc.) implements an `Interactable`
 * interface, which allows them to accept a `Visitor` object. The `Visitor` object then performs some action
 * on the actor based on its type.
 * <p>
 * The game also uses the Observer pattern to notify observers when certain events occur in the game, such as
 * when the player collects a pellet or when the player is eaten by a ghost.
 * <p>
 * The builder pattern is used to construct the game board, which is represented as a grid of cells. The builder
 * pattern allows the game board to be constructed in a flexible manner, with different types of cells and actors,
 * parsed from a text file.
 * <p>
 * The game also uses the state pattern to represent the different states that the player can be in, such as
 * normal, invincible, or dead. The state classes thus encapsulate the behavior of the player in each state,
 * by being different instances of the visitor interface that can be changed dynamically at runtime.
 * <p>
 * The game is implemented using the Swing library for the GUI, and uses a simple grid-based layout for the game
 * board. The game is controlled using the arrow keys on the keyboard.
 *
 * @author Loïc Herman
 * @author Massimo Stefani
 * @author Samuel Roland
 * @author Timothée Van Hove
 *
 * @since 16.06.2024
 */
public class Main {

    public static void main(String[] args) throws Exception {
        GameWindow.getInstance().begin();
    }
}
