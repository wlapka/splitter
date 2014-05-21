/**
 * @author wlapka
 *
 * @created May 16, 2014 9:32:23 AM
 */
package net.thoiry.lapka.splitter.family;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import net.thoiry.lapka.splitter.family.model.Family;
import net.thoiry.lapka.splitter.family.model.Person;
import net.thoiry.lapka.splitter.service.MessageGenerator;

/**
 * @author wlapka
 * 
 */
public class FamilyGeneratorImpl implements MessageGenerator<Family> {

	private static final AtomicLong NEXTFAMILYID = new AtomicLong(1);
	private static final int MAXNUMBEROFFAMILYMEMBERS = 10;
	private final Random random = new Random();

	@Override
	public Family generateMessage() {
		Family family = new Family(NEXTFAMILYID.getAndIncrement(), "");
		family.getMembers().addAll(this.generatePersons());
		return family;
	}

	private List<Person> generatePersons() {
		List<Person> persons = new ArrayList<>();
		int itemId = 1;
		for (int i = 0; i < random.nextInt(MAXNUMBEROFFAMILYMEMBERS); i++) {
			persons.add(this.generatePerson(itemId++));
		}
		return persons;
	}

	private Person generatePerson(int id) {
		return new Person(id, "");
	}
}
