package testobjects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import json.io.datareader.ObjectSaver;

public class MainLecture {
	public static void main(String[] args) throws Exception {

		// new objectsaver instance
		ObjectSaver os = new ObjectSaver();
		
		// create a new Lecturer
		Lecturer lecturer = new Lecturer();
		lecturer.age = 5;
		lecturer.name = "Mustermann";

		// create two new lectures 
		//one from the jar file
		Lecture lecture =os.getObject(Lecture.class);

		//one from a POJO
		Lecture lecture2 = new Lecture();
		lecture2.name = "History";
		lecture2.id = 2;
		lecture2.lecturer = lecturer;
		lecture2.date = new Date();


		// Save and open a single object
		System.out.println("1:");
		os.saveObject(lecture);
		os.saveObject(lecture2);
		
		//returns lecture2 -> lecture was overwritten
		System.out.println(os.getObjectString(Lecture.class));

		//create a new List with 2 lectures
		List<Lecture> list = new ArrayList<Lecture>();
		list.add(lecture2);
		list.add(lecture);

		// Save and open a list of objects
		System.out.println("2:");
		os.saveObjectList(list, Lecture.class);
		System.out.println(os.getObjectListString(Lecture.class));
		
	}
}
