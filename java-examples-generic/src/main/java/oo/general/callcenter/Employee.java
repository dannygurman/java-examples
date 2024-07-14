package oo.general.callcenter;

/**
 * Created by DannyG on 01/01/2017.
 */
public class Employee {
    CallHandler callHandler;
    int rank; // 0- fresher, 1 - technical lead, 2 - product manager
    boolean free;

    Employee(int rank) {
        this.rank = rank;
    }

    void receiveCall(Call call) {
//free = false
    }

    void callHandled(Call call) {

    } // call is complete

    void cannotHandle(Call call) { // escalate call
        call.rank = rank + 1;
        callHandler.dispatchCall(call);
        free = true;
        callHandler.getNextCall(this); // look for waiting call
    }
}
