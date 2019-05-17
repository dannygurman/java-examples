package lambdaAndStream.ex1;

/**
 * Created by dannyg on 6/25/2017.
 */
public interface StateChangeListener {
     void onStateChange(State oldState, State newState);
}
