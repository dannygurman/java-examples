package passingParameter.ex3;

public class CounterRepo {

	Counter counter;
	String reponame;
	
	public CounterRepo(String reponame , Counter counter) {	
		this.reponame = reponame;
		this.counter = counter;		
	}

	
	public void increment() {
		System.out.println(this.reponame+" increment");
		counter.increment();
	}
	
	public void printVal() {
		System.out.println(this.reponame+":"+counter.i);
	}

}
