package com.generations;

public class Trait {
	
	private String name;
	private String trait;
	
	/**
	 * Constructor
	 * 
	 * @param name The name of the trait (hair color, eye color, etc)
	 * @param trait The actual trait for the person (blond, blue, etc)
	 */
	public Trait(String name, String trait) {
		this.name = name;
		this.trait = trait;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTrait() {
		return trait;
	}
	
	/**
	 * Renames the actual trait for the person (blond, blue, etc)
	 * @param trait The trait that this Trait should be set to
	 */
	public void setTrait(String trait) {
		this.trait = trait;
	}
	
	//TODO: Jarrett You can use this area for trait tracking as well

}
