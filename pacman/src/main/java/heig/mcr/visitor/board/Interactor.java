package heig.mcr.visitor.board;

/**
 * An interactor is recognized by the level when triggering interactions.
 *
 * @author Loïc Herman
 * @author Massimo Stefani
 * @author Samuel Roland
 * @author Timothée Van Hove
 */
public interface Interactor {

    void interactWith(Interactable other);
}
