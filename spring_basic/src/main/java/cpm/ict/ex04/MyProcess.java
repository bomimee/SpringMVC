package cpm.ict.ex04;

public class MyProcess {

	 private String name;
	 private String jumin;
	 private String gender;
	 
	 private Age age;
	 public MyProcess () {
		 
	 }
	 public MyProcess( String name, String jumin, String gender, Age age) {
		 this.age = age;
		 this.name = name;
		 this.jumin = jumin;
			this.gender = gender;
				 
	 }
	 public void prn() {
		 System.out.println(" name: "+ name);
		 System.out.println(" age: "+ age);
	 }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJumin() {
		return jumin;
	}
	public void setJumin(String jumin) {
		this.jumin = jumin;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Age getAge() {
		return age;
	}
	public void setAge(Age age) {
		this.age = age;
	}
	 
	 
}
