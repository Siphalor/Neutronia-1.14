package team.hollow.neutronia.enums;

import net.minecraft.util.StringRepresentable;

public enum SidingType implements StringRepresentable {
	SINGLE("single"),
	DOUBLE("double"),
	CORNER_RIGHT("corner_right"),
	CORNER_LEFT("corner_left");

	private final String name;

	SidingType(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

	public String asString() {
		return this.name;
	}
}