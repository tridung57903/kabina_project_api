package Kabina.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Kabina.Repository.BusinessUnitRepository;
import Kabina.DTO.BusinessUnitShelves;
import Kabina.Model.BusinessUnit;
import Kabina.Model.Shelf;

@Service
public class BusinessUnitService {

	@Autowired
	private BusinessUnitRepository businessUnitRepository;
	
	public List<BusinessUnitShelves> analyzeBusinessUnitShelves() {
		List<BusinessUnitShelves> listBusinessUnitShelves = new ArrayList<>();
		List<BusinessUnit> listBusinessUnit = businessUnitRepository.findAll();
		
		for(int i=0;i<listBusinessUnit.size();i++) {
			List<Shelf> availableShelves = new ArrayList<>();
			List<Shelf> bookedShelves = new ArrayList<>();
			List<Shelf> shelves = listBusinessUnit.get(i).getShelves();
			long businessUnitId = listBusinessUnit.get(i).getId();
			String businessUnitName = listBusinessUnit.get(i).getName();
			
			for(int j=0;j<shelves.size();j++) {
				if(shelves.get(j).getStatus().equals("Available")) {
					availableShelves.add(shelves.get(j));
				}
				if(shelves.get(j).getStatus().equals("Booked")) {
					bookedShelves.add(shelves.get(j));
				}
			}
			
			BusinessUnitShelves businessUnitShelves = new BusinessUnitShelves(businessUnitId,businessUnitName,availableShelves,bookedShelves);
			listBusinessUnitShelves.add(businessUnitShelves);
		}
		
		return listBusinessUnitShelves;
	}

}
