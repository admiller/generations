package com.generations;

public class Trait {

	private String name;
	private String trait;

	private String[] traits = { "hair color", "eye color", "widows peak",
			"connected earlobes", "cleft chin" };

	private String[] hairColor = { "brown", "blonde", "red" };
	private String[] eyeColor = { "brown", "hazel", "green", "blue" };
	private String[] widowsPeak = { "yes", "no" };
	private String[] connectedEarlobes = { "no", "yes" };
	private String[] cleftChin = { "yes", "no" };

	/**
	 * Constructor
	 * 
	 * @param name
	 *            The name of the trait (hair color, eye color, etc)
	 * @param trait
	 *            The actual trait for the person (blond, blue, etc)
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
	 * 
	 * @param trait
	 *            The trait that this Trait should be set to
	 */
	public void setTrait(String trait) {
		this.trait = trait;
	}
//<<<<<<< HEAD

	// TODO: Jarrett You can use this area for trait tracking as well

	public Person createChild(String name, boolean male, Person p1, Person p2) {
		Person child = new Person(name, male, p1, p2);

		// gets the alleles from parent 1 and parent 2
//=======
	
	//TODO: Jarrett You can use this area for trait tracking as well
	
//	public Person createChild(String name, boolean male, Person p1, Person p2){
//		Person child = new Person(name, male, -1, p1, p2);
		
		//gets the alleles from parent 1 and parent 2
//>>>>>>> f9ec377eefb7789a64ab4f1c6533a60a2521b72d
		Person gp1p1 = p1.getParent(0);
		Person gp2p1 = p1.getParent(1);
		p1 = getAlleles(p1, gp1p1, gp2p1);
		
		Person gp1p2 = p2.getParent(0);
		Person gp2p2 = p2.getParent(1);
		p2 = getAlleles(p2, gp1p2, gp2p2);

		// combineTraits()

		return child;
	}

	public Person getAlleles(Person p1, Person p2, Person p3) {
		
		
		if (p2.getTrait("widows peak").getTrait() == null
				|| p3.getTrait("widows peak").getTrait() == null) {
			if (p1.getTrait("widows peak").getTrait() == widowsPeak[0]) {
				p1.addAllele(new Trait("widows peak", "dd"));
			} else {
				p1.addAllele(new Trait("widows peak", "rr"));
			}
		} else {
			if (p1.getTrait("widows peak").getTrait() == widowsPeak[0]
					&& (p2.getTrait("widows peak").getTrait() == widowsPeak[1] || p3
							.getTrait("widows peak").getTrait() == widowsPeak[1])) {
				p1.addAllele(new Trait("widows peak", "dr"));
			} else if (p1.getTrait("widows peak").getTrait() == widowsPeak[0]) {
				p1.addAllele(new Trait("widows peak", "dd"));
			} else {
				p1.addAllele(new Trait("widows peak", "rr"));
			}
		}

		if (p2.getTrait("connected earlobes").getTrait() == null
				|| p3.getTrait("connected earlobes").getTrait() == null) {
			if (p1.getTrait("connected earlobes").getTrait() == connectedEarlobes[0]) {
				p1.addAllele(new Trait("connected earlobes", "dd"));
			} else {
				p1.addAllele(new Trait("connected earlobes", "rr"));
			}
		} else {
			if (p1.getTrait("connected earlobes").getTrait() == connectedEarlobes[0]
					&& (p2.getTrait("connected earlobes").getTrait() == connectedEarlobes[1] || p3
							.getTrait("connected earlobes").getTrait() == connectedEarlobes[1])) {
				p1.addAllele(new Trait("connected earlobes", "dr"));
			} else if (p1.getTrait("connected earlobes").getTrait() == connectedEarlobes[0]) {
				p1.addAllele(new Trait("connected earlobes", "dd"));
			} else {
				p1.addAllele(new Trait("connected earlobes", "rr"));
			}
		}
		
		if (p2.getTrait("cleft chin").getTrait() == null
				|| p3.getTrait("cleft chin").getTrait() == null) {
			if (p1.getTrait("cleft chin").getTrait() == cleftChin[0]) {
				p1.addAllele(new Trait("cleft chin", "dd"));
			} else {
				p1.addAllele(new Trait("cleft chin", "rr"));
			}
		} else {
			if (p1.getTrait("cleft chin").getTrait() == cleftChin[0]
					&& (p2.getTrait("cleftChin").getTrait() == cleftChin[1] || p3
							.getTrait("cleft chin").getTrait() == cleftChin[1])) {
				p1.addAllele(new Trait("cleft chin", "dr"));
			} else if (p1.getTrait("cleft chin").getTrait() == cleftChin[0]) {
				p1.addAllele(new Trait("cleft chin", "dd"));
			} else {
				p1.addAllele(new Trait("cleft chin", "rr"));
			}
		}
		return p1;
	}

	public Trait combineTraits(Trait t1, Trait t2) {
		if (t1.getName().equals(t2.getName())) {
			System.out.println("cannot compare");
			return null;
		}
		if (t1.getName().equals("hair color")) {

		} else if (t1.getName().equals("eye color")) {

		} else if (t1.getName().equals("widows peak")) {

		} else if (t1.getName().equals("connected earlobes")) {

		} else if (t1.getName().equals("cleft chin")) {

		}
		return null;
	}
}
