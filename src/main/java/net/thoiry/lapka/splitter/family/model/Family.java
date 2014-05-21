/**
 * @author wlapka
 *
 * @created May 16, 2014 9:22:46 AM
 */
package net.thoiry.lapka.splitter.family.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wlapka
 * 
 */
public class Family {

	private final long id;
	private final String name;
	private final List<Person> members = new ArrayList<>();

	public Family(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Person> getMembers() {
		return members;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((members == null) ? 0 : members.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Family other = (Family) obj;
		if (id != other.id)
			return false;
		if (members == null) {
			if (other.members != null)
				return false;
		} else if (!members.equals(other.members))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Family [id=" + id + ", name=" + name + ", members=" + members + "]";
	}

}