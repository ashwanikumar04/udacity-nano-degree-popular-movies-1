
package in.ashwanik.popularmovie1.interfaces;

/**
 * Interface to handle actions .
 */
public interface IActionHandler<T> {
    void handle();

    void handle(T data);
}