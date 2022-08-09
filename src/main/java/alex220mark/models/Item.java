package alex220mark.models;

import java.util.Objects;

public class Item {

	private String name;
	private Integer number;

	public Item(String name, Integer number) {
		this.name = name;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public Integer getNumber() {
		return number;
	}

	// only using name for an Item to be considered equal
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	// only using name for an Item to be considered equal
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Item))
			return false;
		Item other = (Item) obj;
		return Objects.equals(name, other.name);
	}

}
