/**
 * @author wlapka
 *
 * @created May 16, 2014 9:39:32 AM
 */
package net.thoiry.lapka.splitter.family;

import java.util.ArrayList;
import java.util.List;

import net.thoiry.lapka.splitter.family.model.Family;
import net.thoiry.lapka.splitter.family.model.FamilyMember;
import net.thoiry.lapka.splitter.family.model.Person;
import net.thoiry.lapka.splitter.service.Splitter;

/**
 * @author wlapka
 * 
 */
public class FamilySplitterImpl implements Splitter<Family, FamilyMember> {

	@Override
	public List<FamilyMember> split(Family family) {
		List<FamilyMember> members = new ArrayList<>();

		int numberOfMembers = family.getMembers().size();
		int sequence = 1;
		for (Person person : family.getMembers()) {
			members.add(new FamilyMember(family.getId(), person, sequence++, numberOfMembers));
		}

		return members;
	}

}
