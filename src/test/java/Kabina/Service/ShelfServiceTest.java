package Kabina.Service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import Kabina.Repository.ShelfRepository;
import Kabina.DTO.FloorShelves;
import Kabina.Model.BusinessUnit;
import Kabina.Model.Shelf;

@RunWith(SpringRunner.class)
public class ShelfServiceTest {
	
	@TestConfiguration
    static class ShelfServiceTestContextConfiguration {
  
        @Bean
        public ShelfService shelfService() {
            return new ShelfService();
        }
    }
	
	@Autowired
    private ShelfService shelfService;
 
    @MockBean
    private ShelfRepository shelfRepository;
    
    @Before
    public void setUp() {
    	BusinessUnit businessUnit = new BusinessUnit(1,"Insurance",null,null);
    	Shelf shelf1 = new Shelf(1,12,10,"Available",businessUnit);
    	Shelf shelf2 = new Shelf(2,12,15,"Booked",businessUnit);
    	Shelf shelf3 = new Shelf(3,27,9,"Available",businessUnit);
    	Shelf shelf4 = new Shelf(4,27,12,"Available",businessUnit);
    	Shelf shelf5 = new Shelf(5,30,21,"Booked",businessUnit);
    	Shelf shelf6 = new Shelf(6,30,27,"Booked",businessUnit);
    	List<Shelf> listShelf = new ArrayList<>();
    	listShelf.add(shelf1);
    	listShelf.add(shelf2);
    	listShelf.add(shelf3);
    	listShelf.add(shelf4);
    	listShelf.add(shelf5);
    	listShelf.add(shelf6);
        Mockito.when(shelfRepository.findAll()).thenReturn(listShelf);
    }
    
    @Test
    public void floorShelvesShouldBeAnalyzedIntoAvailableListAndBookedList() {
    	List<FloorShelves> listFloorShelves = shelfService.analyzeFloorShelves();
    	Comparator<FloorShelves> comparedByFloorNumber = (FloorShelves f1, FloorShelves f2) -> new Long(f1.getFloorNumber()).compareTo(f2.getFloorNumber());
    	Collections.sort(listFloorShelves, comparedByFloorNumber);
    	 
        assertEquals(1,listFloorShelves.get(0).getAvailable());
        assertEquals(1,listFloorShelves.get(0).getBooked());
        assertEquals(2,listFloorShelves.get(1).getAvailable());
        assertEquals(2,listFloorShelves.get(2).getBooked());
     }
}
