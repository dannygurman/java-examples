package lambdaAndStream.ex1;

/**
 * Created by dannyg on 6/25/2017.
 */
public class StateOwnerUseExample {


    public void addListenerNoLambda() {
        StateOwner stateOwner = new StateOwner();
        stateOwner.addStateListener (new StateChangeListener() {
            public void onStateChange(State oldState, State newState) {
                System.out.println("State changed");
            }
        });
    }

   // The lambda expression is matched against the parameter type of the addStateListener() method's parameter.
   // If the lambda expression matches the parameter type (in this case the StateChangeListener interface) ,
   // then the lambda expression is turned into a function that implements the same interface as that parameter.

    //Java lambda expressions can only be used where the type they are matched against is a single method interface.
    // In the example above, a lambda expression is used as parameter where the parameter type was the
    // StateChangeListener interface.
    //
    // This interface only has a single method. Thus, the lambda expression is matched
    // successfully against that interface
    public void addListenerLambda() {
        StateOwner stateOwner = new StateOwner();
        stateOwner.addStateListener ((oldState, newState) -> System.out.println("State changed")
        );
    }
}
