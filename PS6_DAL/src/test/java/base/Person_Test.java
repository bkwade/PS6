package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person_Test {
		
	private static PersonDomainModel per1;
	private static UUID per1UUID = UUID.randomUUID();			
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		
		Date BDate = null;
		try {
			BDate = new SimpleDateFormat("yyyy-MM-dd").parse("1996-12-25");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		per1 = new PersonDomainModel();
		per1.setPersonID(per1UUID);
		per1.setFirstName("Brent");
		per1.setMiddleName("K");
		per1.setLastName("Wade");
		per1.setBirthday(BDate);
		per1.setCity("Newark");
		per1.setStreet("201 Veterans Dr.");
		per1.setPostalCode(19711);
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
	}
	
	@After
	public void tearDown() throws Exception {
		PersonDomainModel per;
		PersonDAL.deletePerson(per1.getPersonID());
		per = PersonDAL.getPerson(per1.getPersonID());
		assertNull("Person should not be in the database", per);
		
	}
	
	@Test
	public void AddPersonTest() {
		PersonDomainModel per;
		per = PersonDAL.getPerson(per1.getPersonID());
		assertNull("Person should not be in the database", per);
		PersonDAL.addPerson(per1);
		per = PersonDAL.getPerson(per1.getPersonID());
		System.out.println(per1.getPersonID() + " found.");
		assertNotNull("Person should be added to the database", per);
	}
	
	@Test
	public void UpdatePersonTest() {
		PersonDomainModel per;
		final String C_LASTNAME = "Wade";
		
		per = PersonDAL.getPerson(per1.getPersonID());
		assertNull("Person should not be in the database", per);
		PersonDAL.addPerson(per1);
		per1.setLastName(C_LASTNAME);
		PersonDAL.updatePerson(per1);
		per = PersonDAL.getPerson(per1.getPersonID());
		assertTrue("No Name Update", per1.getLastName() == C_LASTNAME);
	}
	
	@Test
	public void DeletePersonTest() {
		PersonDomainModel per;
		per = PersonDAL.getPerson(per1.getPersonID());
		assertNull("Person should not be in database", per);
		PersonDAL.addPerson(per1);
		per = PersonDAL.getPerson(per1.getPersonID());
		System.out.println(per1.getPersonID() + " found.");
		assertNotNull("Person should not be in the database", per);
		PersonDAL.deletePerson(per1.getPersonID());
		per = PersonDAL.getPerson(per1.getPersonID());
		assertNull("Person should not be in the database", per);
	}
	
	
	

}
