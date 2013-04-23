package com.generations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2775671114782499169L;
	private Person parent1;
	private Person parent2;
	private boolean male;
	private String name;
	private ArrayList<Trait> traits;

	public Person(String name, boolean male, Person parent1, Person parent2) {
		this.name = name;
		this.male = male;
		this.parent1 = parent1;
		this.parent2 = parent2;
		traits = new ArrayList<Trait>();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParent(int id, Person parent) {
		if (id == 1) {
			parent1 = parent;
		} else {
			parent2 = parent;
		}
	}

	public boolean getMale() {
		return male;
	}

	public Trait getTrait(String name) {
		for (int i = 0; i < traits.size(); i++) {
			if (traits.get(i).getName().equalsIgnoreCase(name)) {
				return traits.get(i);
			}
		}
		return null;
	}

	public Person getParent(int id) {
		if (id == 1) {
			return parent1;
		}
		return parent2;
	}

	public String getName() {
		return name;
	}

	public void addTrait(Trait trait) {
		traits.add(trait);
	}

	public ArrayList<Trait> getTraits() {
		return traits;
	}

	public byte[] serialize() throws IOException {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		ObjectOutputStream o = new ObjectOutputStream(b);
		o.writeObject(this);
		return b.toByteArray();
	}

	public static Person deserialize(byte[] bytes) throws IOException,
			ClassNotFoundException {
		ByteArrayInputStream b = new ByteArrayInputStream(bytes);
		ObjectInputStream o = new ObjectInputStream(b);
		return (Person) o.readObject();
	}

	// TODO Jarrett Insert code to track traits here.
	// You may have to create other classes, but for now you can just start
	// adding info here

}
