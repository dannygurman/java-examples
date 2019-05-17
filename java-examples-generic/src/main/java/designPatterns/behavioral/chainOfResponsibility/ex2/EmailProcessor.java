package designPatterns.behavioral.chainOfResponsibility.ex2;

/**
 * Created by DannyG on 08/01/2015.
 */
public class EmailProcessor
{
    //maintain a reference to the previous handler so we can add the next one
    private EmailHandler firstHandler;


    //LIFO - first in last out (last handled)
    public void addHandler(EmailHandler handler)
    {
        String handlerName = handler.getClass().getSimpleName();
        System.out.println("Adding handler:" + handlerName);
        if(firstHandler != null)
        {
            handler.setNext(firstHandler);
        }
        firstHandler = handler;
    }


    public void handleRequest(Email email) {
        firstHandler.handleRequest(email);
    }
}
