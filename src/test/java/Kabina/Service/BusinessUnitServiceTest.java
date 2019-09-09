package Kabina.Service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
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

import Kabina.Repository.BusinessUnitRepository;
import Kabina.DTO.BusinessUnitShelves;
import Kabina.Model.BusinessUnit;
import Kabina.Model.Shelf;

@RunWith(SpringRunner.class)
public class BusinessUnitServiceTest {

	@TestConfiguration
    static class BusinessUnitServiceTestContextConfiguration {
  
        @Bean
        public BusinessUnitService businessUnitService() {
            return new BusinessUnitService();
        }
    }
	
	@Autowired
    private BusinessUnitService businessUnitService;
 
    @MockBean
    private BusinessUnitRepository businessUnitRepository;
    
    @Before
    public void setUp() {
    	BusinessUnit businessUnit = new BusinessUnit(1,"Insurance",null,null);
    	Shelf shelf1 = new Shelf(1,12,10,"Available",businessUnit);
    	Shelf shelf2 = new Shelf(1,20,27,"Booked",businessUnit);
    	List<Shelf> listShelf = new ArrayList<>();
    	listShelf.add(shelf1);
    	listShelf.add(shelf2);
    	List<BusinessUnit> listBusinessUnit = new ArrayList<>();
    	businessUnit.setShelves(listShelf);
    	listBusinessUnit.add(businessUnit);
        Mockito.when(businessUnitRepository.findAll()).thenReturn(listBusinessUnit);
    }
    
    @Test
    public void businessUnitShelvesShouldBeAnalyzedIntoAvailableListAndBookedList() {
        List<BusinessUnitShelves> listBusinessUnitShelves = businessUnitService.analyzeBusinessUnitShelves();   
        assertEquals(12,listBusinessUnitShelves.get(0).getAvailableShelves().get(0).getFloor());
        assertEquals(10,listBusinessUnitShelves.get(0).getAvailableShelves().get(0).getPositionNumber());
        assertEquals(20,listBusinessUnitShelves.get(0).getBookedShelves().get(0).getFloor());
        assertEquals(27,listBusinessUnitShelves.get(0).getBookedShelves().get(0).getPositionNumber());
     }
}
