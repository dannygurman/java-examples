package generics.ex5;

public  class IntegerCompare  implements Comparable <IntegerCompare> {

	private Integer integer;          

	public Integer getInteger() {
		return integer;
	}

	public IntegerCompare(Integer integer) {
		set(integer);
	}
	
	public void set(Integer integer) {
		this.integer = integer;
	}

	public int compareTo(IntegerCompare toComare) {
		if (this.getInteger() > toComare.getInteger()) {
			return 1;	
		}
		else{
			return 0;	
		}
	}
}
