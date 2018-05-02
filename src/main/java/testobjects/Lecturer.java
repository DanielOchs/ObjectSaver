package testobjects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Lecturer {

	@JsonProperty("lecturername")
	public String name;
	public int age;
	
}
