import java.util.ArrayList;
import java.util.List;

import controller.HouseholdDetailsHelper;
import controller.OwnerHelper;
import model.HouseholdDetails;
import model.Owner;
import model.PetList;

public class OwnerTester {

	public static void main(String[] args) {
		
		Owner rob = new Owner("Rob");
		
		HouseholdDetailsHelper hdh = new HouseholdDetailsHelper();
		
		PetList tigger = new PetList("Cat", "Tigger");
		PetList sparky = new PetList("Dog", "Sparky");
		
		List<PetList> robsPets = new ArrayList<PetList>();
		robsPets.add(tigger);
		robsPets.add(sparky);
		
		HouseholdDetails robHousehold = new HouseholdDetails("Rob's Household", rob);
		
		robHousehold.setListOfPets(robsPets);
		
		hdh.insertNewHouseholdDetails(robHousehold);
		
		List<HouseholdDetails> allHouseholds = hdh.getHouseholds();
		
			for(HouseholdDetails a: allHouseholds) {
				System.out.println(a.toString());
			}
		}
}
	


