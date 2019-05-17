package cloning.ex4.case1;

public class PointOne {
    private Integer x;
	private Integer y;
    
    public PointOne(Integer x, Integer y) {	
		this.x = x;
		this.y = y;
	}


 
    public PointOne(PointOne point){ 
        this.x = point.x;
        this.y = point.y;
    }
}