package com.generations;

public class Trait {
	
	private String name;
	private String trait;
	
	private String[] traits = {"hair color", "eye olor", "widows peak", "connected earlobes", "cleft chin"};
	
	private String[] hairColor = {"brown", "blonde", "red"};
	private String[] eyeColor = {"brown", "hazel", "green", "blue"};
	private String[] widowsPeak = {"yes", "no"};
	private String[] connectedEarlobes = {"no", "yes"};
	private String[] cleftChin = {"yes", "no"};
	
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
	
	public Person createChild(String name, boolean male, Person p1, Person p2){
		Person child = new Person(name, male, -1, p1, p2);
		
		//gets the alleles from parent 1 and parent 2
		Person gp1p1 = p1.getParent(0);
		Person gp2p1 = p1.getParent(1); 
		
		
		Person gp1p2 = p2.getParent(0);
		Person gp2p2 = p2.getParent(1);

//		combineTraits()
		
		return child;
	}
	
	public Person getAlleles(Person p1, Person p2, Person p3){
		if(traits[0].equals("hair color")){
			
		} else if(traits[1].equals("eye color")){
			
		} else if(traits[2].equals("widows peak")){
			if(p2.getTrait("widows peak") == null || p3.getTrait("widows peak") == null){
				if(p1.getTrait("widows peak").getTrait() == widowsPeak[0]){
					//alleles are 2 dominant
				} else{
					//alleles are 2 recessive
				}
			}
			
		}
		return p1;
	}
	
	public Trait combineTraits(Trait t1, Trait t2){
		if(t1.getName().equals(t2.getName())){
			System.out.println("cannot compare");
			return null;
		}
		if(t1.getName().equals("hair color")){
			
		} else if(t1.getName().equals("eye color")){
			
		} else if(t1.getName().equals("widows peak")){

		} else if(t1.getName().equals("connected earlobes")){
			
		} else if(t1.getName().equals("cleft chin")){
			
		}
		return null;
	}
}
