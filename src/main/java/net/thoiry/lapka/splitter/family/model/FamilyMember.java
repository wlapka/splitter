/**
 * @author wlapka
 *
 * @created May 16, 2014 9:28:29 AM
 */
package net.thoiry.lapka.splitter.family.model;


/**
 * @author wlapka
 * 
 */
public class FamilyMember {

	private final long familyId;
	private final Person person;
	private final int position;
	private final int numberOfPersonsInFamily;

	public FamilyMember(long familyId, Person person, int position, int numberOfPersonsInFamily) {
		this.familyId = familyId;
		this.person = person;
		this.position = position;
		this.numberOfPersonsInFamily = numberOfPersonsInFamily;
	}

	public long getFamilyId() {
		return familyId;
	}

	public Person getPerson() {
		return person;
	}

	public int getPosition() {
		return position;
	}

	public int getNumberOfPersonsInFamily() {
		return numberOfPersonsInFamily;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (familyId ^ (familyId >>> 32));
		result = prime * result + numberOfPersonsInFamily;
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + position;
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
		FamilyMember other = (FamilyMember) obj;
		if (familyId != other.familyId)
			return false;
		if (numberOfPersonsInFamily != other.numberOfPersonsInFamily)
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (position != other.position)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FamilyMember [familyId=" + familyId + ", person=" + person + ", position=" + position
				+ ", numberOfPersonsInFamily=" + numberOfPersonsInFamily + "]";
	}

}
