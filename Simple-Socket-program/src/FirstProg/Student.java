package FirstProg;
import java.io.*;

public class Student implements Serializable {
	String FirstName;
	String LastName;
	int Age;
	String Fav_Course;
	
	Student(String FirstName,String LastName,int Age,String Fav_Course)
	{
		this.FirstName=FirstName;
		this.LastName=LastName;
		this.Age=Age;
		this.Fav_Course=Fav_Course;
	}
	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public int getAge() {
		return Age;
	}

	public String getFav_Course() {
		return Fav_Course;
	}

	
	

	@Override
	public String toString() {
		return "Student [FirstName=" + FirstName + ", LastName=" + LastName + ", Age=" + Age + ", Fav_Course="
				+ Fav_Course + "]";
	}
	

}
