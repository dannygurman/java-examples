

<!--NOTICE that just like MVC, in Model 2the view
does NOT alter the model (that’s the controller’s job);
all it does is use its state!
-->



<!-- Here’s our bean, which the servlet passed us. -->
<jsp:useBean id="beatModel" scope="request" class="headfirst.designpatterns.combined.djview.BeatModel" />


<!-- Beginning of the HTML. -->
<html>
<head>
    <title>DJ View</title>
</head>
<body>


<!--Now we generate the view, which prints out the current beats per minute -->
<h1>DJ View</h1>
<!-- Here we use the model bean to extract the BPM property. -->
Beats per minutes = <jsp:getProperty name="beatModel" property="BPM" />
<br />
<hr>
<br />

<form method="get" action="/djview/servlet/DJViewServlet">
    BPM: <input type=text name="bpm"
                value="<jsp:getProperty name='beatModel' property='BPM' />">
    &nbsp;

    <!-- And here’s the control part of the view.
     We have a text   entry for entering a BPM   along with increase/decreas    and on/off buttons
     -->
    <input type="submit" name="set" value="set"><br />
    <input type="submit" name="decrease" value="<<">
    <input type="submit" name="increase" value=">>"><br />
    <input type="submit" name="on" value="on">
    <input type="submit" name="off" value="off"><br />
</form>


<!-- HTML end -->
</body>
</html>
