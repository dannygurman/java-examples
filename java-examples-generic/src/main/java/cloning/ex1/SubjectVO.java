package cloning.ex1;

import lombok.Data;

@Data
public class SubjectVO { 
	private String name;

	public SubjectVO(String name) {
		this.name = name; 
	}
}


