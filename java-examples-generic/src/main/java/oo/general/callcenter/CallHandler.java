package oo.general.callcenter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by DannyG on 01/01/2017.
 */
public class CallHandler {
    static final int LEVELS = 3; // we have 3 levels of employees
    static final int NUM_FRESHERS = 5; // we have 5 freshers

    ArrayList<Employee>[] employeeLevels = new ArrayList[LEVELS];//Array of ArrayList

    // queues for each call’s rank
    Queue<Call>[] callQueues = new LinkedList[LEVELS];//Array of queues

    public CallHandler() { }



    // routes the call to an available employee, or adds to a queue
    public void dispatchCall(Call call) {
        // try to route the call to an employee with minimal rank
        Employee emp = getCallHandler(call);
        if (emp != null) {
            emp.receiveCall(call);
        } else {
            // place the call into queue according to its rank
            callQueues[call.rank].add(call);
        }
    }

    private Employee getCallHandler(Call call) {
        for (int level = call.rank; level < LEVELS - 1; level++) {
            ArrayList<Employee> employeeLevel = employeeLevels[level];
            for (Employee emp : employeeLevel) {
                if (emp.free) {
                    return emp;
                }
            }
        }
        return null;
    }


    public void getNextCall(Employee e) {

    } // look for call for e’s rank

}
