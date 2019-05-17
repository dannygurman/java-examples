package designPatterns.behavioral.mediator.ex1;

/**
 * Created by DannyG on 20/01/2015.
 */


//Abstract Mediator
interface IMediator {
    public void book();
    public void view();
    public void search();
    public void registerView(BtnView v);
    public void registerSearch(BtnSearch s);
    public void registerBook(BtnBook b);
    public void registerDisplay(LblDisplay d);
}
