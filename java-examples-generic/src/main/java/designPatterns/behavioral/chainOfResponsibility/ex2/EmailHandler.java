package designPatterns.behavioral.chainOfResponsibility.ex2;

/**
 * Created by DannyG on 08/01/2015.
 */
//Handler
public interface EmailHandler
{
    //reference to the next handler in the chain
    public void setNext(EmailHandler handler);

    //handle request
    public void handleRequest(Email email);
}

