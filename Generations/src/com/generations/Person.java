package com.generations;

public class Person {
	

	private Person parent1;
	private Person parent2;
	private boolean male;
	private String name;
	
	public Person(String name, boolean male, Person parent1, Person parent2) {
		this.name = name;
		this.male = male;
		this.parent1 = parent1;
		this.parent2 = parent2;
	}
	
	public void setParent(int id, Person parent) {
		if(id == 1) {
			parent1 = parent;
		}
		
	}
	
	//TODO Jarrett Insert code to track traits here

}
