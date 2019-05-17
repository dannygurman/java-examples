package designPatterns.behavioral.chainOfResponsibility.ex2;

/**
 * Created by DannyG on 08/01/2015.
 */
public abstract class AbstractEmailHandler implements EmailHandler {

    private volatile EmailHandler next;

    public final void setNext(EmailHandler handler) {
        this.next = handler;
    }

    public final void handleRequest(Email email) {
        String handlerName = this.getClass().getSimpleName();
        System.out.println("Handling request in:"+handlerName);
        if (this.accept(email)) {
            System.out.println("Accepting email by:"+handlerName);
            this.doHandle(email);
        }
        else {
            this.next.handleRequest(email);
        }
    }

    protected abstract boolean accept(Email email);

    protected abstract void doHandle(Email email);

}
