package concurrency.threadSafety;

public class NotThreadSafe {
	
	StringBuilder builder = new StringBuilder();

	public void add(String text){
		this.builder.append(text);
	}	
}
