package concurrency.thread.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
* ThreadLocal  provides thread-local variables.  These variables differ from
* their normal counterparts in that each thread that accesses one (via its
* <tt>get</tt> or <tt>set</tt> method) has its own, independently initialized
* copy of the variable.  <tt>ThreadLocal</tt> instances are typically private
* static fields in classes that wish to associate state with a thread (e.g.,
* a user ID or Transaction ID).
*
* <p>For example, the class below generates unique identifiers local to each
* thread.
* A thread's id is assigned the first time it invokes <tt>ThreadId.get()</tt>
* and remains unchanged on subsequent calls.
*/

public class ThreadLocalExample {

	// SimpleDateFormat is not thread-safe, so give one to each thread
	private static final ThreadLocal<SimpleDateFormat> formatter = new ThreadLocal<SimpleDateFormat>(){
		@Override
		protected SimpleDateFormat initialValue()
		{
			return new SimpleDateFormat("yyyyMMdd HHmm");
		}
	};

	public String formatIt(Date date)
	{
		return formatter.get().format(date);
	}
}
