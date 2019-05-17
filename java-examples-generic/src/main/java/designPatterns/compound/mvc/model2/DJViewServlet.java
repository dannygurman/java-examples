package designPatterns.compound.mvc.model2;

/**
 * Created by DannyG on 31/12/2014.
 */


import designPatterns.compound.mvc.dj.BeatModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;

//he servlet is going to act as our controller;
// //it will receive Web browser input in a HTTP request and translate it
// into actions that can be applied to the model.

//Then, given the way the Web works, we need to return a view to the browser.
// To  do this we’ll pass control to the view, which takes the form of a JSP.


//We extend the HttpServlet class  so that we can do servlet kinds of things, like receive HTTP request
public class DJViewServlet extends HttpServlet  {

    private static final long serialVersionUID = 2L;


    //Here’s the init method;    this is called when the   servlet is first created.
    public void init() throws ServletException {
       // We first create a BeatModel object..
        BeatModel beatModel = new BeatModel();
        beatModel.initialize();
       //And place a reference to it in the servlet’s context  so that it’s easily accessed.
        getServletContext().setAttribute("beatModel", beatModel);
    }



    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException
    {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException
    {
       // First we grab the model from  the servlet context.
        BeatModel beatModel =  (BeatModel)getServletContext().getAttribute("beatModel");


       // Next we grab all the HTTP commands/parameters...


        //If we get a set command, then  we get the value of the set,  and tell the model.
        String bpm = request.getParameter("bpm");
        if (bpm == null) {
            bpm = beatModel.getBPM() + "";
        }


        String set = request.getParameter("set");
        if (set != null) {
            int bpmNumber = 90;
            bpmNumber = Integer.parseInt(bpm);
            beatModel.setBPM(bpmNumber);
        }


       // To increase or decrease, we get the current BPMs from the model, and   adjust up or down by one.
        String decrease = request.getParameter("decrease");
        if (decrease != null) {
            beatModel.setBPM(beatModel.getBPM() - 1);
        }
        String increase = request.getParameter("increase");
        if (increase != null) {
            beatModel.setBPM(beatModel.getBPM() + 1);
        }


        //If we get an on or off command, we   tell the model to start or stop.
        String on = request.getParameter("on");
        if (on != null) {
            beatModel.on();
        }
        String off = request.getParameter("off");
        if (off != null) {
            beatModel.off();
        }


       // Finally, our job as a controller is done.
       // All we need to do is   ask the view to take over and create an HTML view.

       // Following the Model 2 definition, we pass the JSP a bean with the  model state in it.
       // //In this case, we  pass it the actual model, since it happens to be a bean.
        request.setAttribute("beatModel", beatModel);

        RequestDispatcher dispatcher =   request.getRequestDispatcher("/djview.jsp");
        dispatcher.forward(request, response);

    }


}