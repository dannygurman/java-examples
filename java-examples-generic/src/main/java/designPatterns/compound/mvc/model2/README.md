To run the DJView Servlet on localhost
Download Tomcat: http://tomcat.apache.org/index.html Place the folder (apache-tomcat-VERSION) in a folder somewhere. The instructions below refer to this folder as the "tomcat" folder.

Build the code and note where the binary algorithems.files are generated (in the bin/ directory in your project directory).

In Eclipse, add the servlet and jsp jars:

lib/servlet-api-3.1.jar
lib/jsp/javax.servlet.jsp-2.3.2.jar
From tomcat folder, run:

bin/startup.sh
Navigate to localhost:8080 in your browser; if you see a page, Tomcat is running!

Under tomcat/webapps make a new folder, djview/

Copy the jsp/index.html file and the jsp/djview.jsp file there.

Make a new folder in the tomcat/webapps/djview/ folder named "WEB-INF".

Place a copy of the jsp/web.xml file there. This file maps the Servlet class to a URL for the form post/get in index.html

Create a set of folders that create the path: tomcat/webapps/djview/WEB-INF/classes/headfirst/designpatterns/combined/djview/

Copy the following classes from headfirst/designpatterns/combined/djview/ to the tomcat/webapps/djview/WEB-INF/classes/headfirst/designpatterns/combined/djview/ folder:

BeatModel.class
BeatModelInterface.class
BeatController.class
BeatObserver.class
BPMObserver.class
ControllerInterface.class
DJViewServlet.class
Restart tomcat from the top tomcat folder:

bin/shutdown.sh
bin/startup.sh
Navigate to localhost:8080/djview/djview.jsp