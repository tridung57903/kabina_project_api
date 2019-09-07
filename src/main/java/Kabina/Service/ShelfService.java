package Kabina.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Kabina.Repository.ShelfRepository;
import Kabina.DTO.FloorShelves;
import Kabina.Model.Shelf;

@Service
public class ShelfService {

	@Autowired
	private ShelfRepository shelfRepository;
	
	private Map<Long,List<Shelf>> classifyDAOShelves() {
		List<Shelf> listShelves = shelfRepository.findAll();
		Map<Long,List<Shelf>> mapDAOShelves = new HashMap<Long , List<Shelf>>();
		
		for (int i=0;i<listShelves.size();i++) {
		    long key  = listShelves.get(i).getFloor();
		    if(mapDAOShelves.containsKey(key)){
		        List<Shelf> list = mapDAOShelves.get(key);
		        list.add(listShelves.get(i));
		    }else{
		    	List<Shelf> list = new ArrayList<Shelf>();
		        list.add(listShelves.get(i));
		        mapDAOShelves.put(key, list);
		    }
		}
		return mapDAOShelves;
	}
	
	public List<FloorShelves> analyzeFloorShelves(){
		List<FloorShelves> listFloorShelves = new ArrayList<>();
		Map<Long,List<Shelf>> mapDAOShelves = classifyDAOShelves();
		
		for (Map.Entry<Long,List<Shelf>> entry : mapDAOShelves.entrySet()) {  
			List<Shelf> list = entry.getValue();
			List<Shelf> availableShelves = new ArrayList<>();
			List<Shelf> bookedShelves = new ArrayList<>();
			
			for(int i=0;i<list.size();i++) {
				if(list.get(i).getStatus().equals("Available")) {
					availableShelves.add(list.get(i));
				}
				if(list.get(i).getStatus().equals("Booked")) {
					bookedShelves.add(list.get(i));
				}
			}
			
			FloorShelves floorShelves = new FloorShelves(entry.getKey(),availableShelves,bookedShelves);
			listFloorShelves.add(floorShelves);
		}

		return listFloorShelves;
	}

}
