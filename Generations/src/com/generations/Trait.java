package com.generations;

import java.util.ArrayList;

public class Trait {

	private String name;
	private String trait;

	private static String[] traits = { "hair color", "eye color", "widows peak",
			"connected earlobes", "cleft chin" };

	public static String[] hairColor = { "brown", "blonde" };
	public static String[] eyeColor = { "brown", "blue" };
	public static String[] widowsPeak = { "yes", "no" };
	public static String[] connectedEarlobes = { "no", "yes" };
	public static String[] cleftChin = { "yes", "no" };

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

	public static String[] getArray(String name) {
		if (name.equals("hair color")) {
			return hairColor;
		} else if (name.equals("eye color")) {
			return eyeColor;
		} else if (name.equals("widows peak")) {
			return widowsPeak;
		} else if (name.equals("connected earlobes")) {
			return connectedEarlobes;
		} else if (name.equals("cleft chin")) {
			return cleftChin;
		}
		return null;
	}

	// TODO: Jarrett You can use this area for trait tracking as well

	public static Person createChild(String name, boolean male, int id, Person p1,
			Person p2) {
		Person child = new Person(name, male, id, p1, p2);

		// gets the alleles from parent 1 and parent 2
		Person gp1p1 = p1.getParent(0);
		Person gp2p1 = p1.getParent(1);
		p1 = getParentAlleles(p1, gp1p1, gp2p1);

		Person gp1p2 = p2.getParent(0);
		Person gp2p2 = p2.getParent(1);
		p2 = getParentAlleles(p2, gp1p2, gp2p2);

		child = combineAlleles(name, male, id, p1, p2);

		return child;
	}

	public static Person getParentAlleles(Person p1, Person p2, Person p3) {
		// doesn't work for hair and eye color because there are more than 2
		// options
		for (int i = 0; i < traits.length; i++) {
			String[] temp = getArray(traits[i]);
			if (p2.getTrait(traits[i]).getTrait() == null
					|| p3.getTrait(traits[i]).getTrait() == null) {
				if (p1.getTrait(traits[i]).getTrait().equals(temp[0])) {
					p1.addAllele(new Trait(traits[i], "dd"));
				} else {
					p1.addAllele(new Trait(traits[i], "rr"));
				}
			} else {
				if (p1.getTrait(traits[i]).getTrait().equals(temp[0])
						&& (p2.getTrait(traits[i]).getTrait().equals(temp[1]) || p3
								.getTrait(traits[i]).getTrait().equals(temp[1]))) {
					p1.addAllele(new Trait(traits[i], "dr"));
				} else if (p1.getTrait(traits[i]).getTrait().equals(temp[0])) {
					p1.addAllele(new Trait(traits[i], "dd"));
				} else {
					p1.addAllele(new Trait(traits[i], "rr"));
				}
			}
		}
		return p1;
	}

	public static ArrayList<String> createChild(ArrayList<Trait> p1,
			ArrayList<Trait> p2) {
		ArrayList<String> child = new ArrayList<String>();
		String[] punnett = new String[4];
		for (int i = 0; i < traits.length; i++) {
			punnett[0] = p1.get(i).getTrait().substring(0, 1)
					+ p2.get(i).getTrait().substring(0, 1);
			punnett[1] = p1.get(i).getTrait().substring(1)
					+ p2.get(i).getTrait().substring(0, 1);
			punnett[2] = p1.get(i).getTrait().substring(0, 1)
					+ p2.get(i).getTrait().substring(1);
			punnett[3] = p1.get(i).getTrait().substring(1)
					+ p2.get(i).getTrait().substring(1);
			int temp = (int) (Math.random() * 4.0);
			
			if(punnett[temp].contains("d")){
				child.add(getArray(traits[i])[0]);
			} else{
				child.add(getArray(traits[i])[1]);
			}
		}
		return child;
	}

	public static Person combineAlleles(String name, boolean male, int id, Person p1,
			Person p2) {
		Person child = new Person(name, male, id, p1, p2);
		String[] punnett = new String[4];
		// makes the 4 sections of the punnett square with either dd, dr, or rr
		for (int i = 0; i < traits.length; i++) {
			punnett[0] = p1.getAllele(traits[i]).getTrait().substring(0, 1)
					+ p2.getAllele(traits[i]).getTrait().substring(0, 1);
			punnett[1] = p1.getAllele(traits[i]).getTrait().substring(0, 1)
					+ p2.getAllele(traits[i]).getTrait().substring(1);
			punnett[2] = p1.getAllele(traits[i]).getTrait().substring(1)
					+ p2.getAllele(traits[i]).getTrait().substring(0, 1);
			punnett[3] = p1.getAllele(traits[i]).getTrait().substring(1)
					+ p2.getAllele(traits[i]).getTrait().substring(1);
			int temp = (int) (Math.random() * 4.0);
			child.addAllele(new Trait(traits[i], punnett[temp]));

			if (punnett[temp].contains("d")) {
				child.addTrait(new Trait(traits[i], getArray(traits[i])[0]));
			} else {
				// this is not true for hair and eye color because they have
				// more than 2 options
				child.addTrait(new Trait(traits[i], getArray(traits[i])[1]));
			}
		}
		return child;
	}

}