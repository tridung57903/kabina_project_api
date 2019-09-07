package Kabina.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Kabina.DTO.BusinessUnitShelves;
import Kabina.DTO.FloorShelves;
import Kabina.Service.BusinessUnitService;
import Kabina.Service.ShelfService;

@RestController
public class AdminReportController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminReportController.class);
	
	@Autowired
	private BusinessUnitService businessUnitService; 
	
	@Autowired
	private ShelfService shelfService;
	
	@RequestMapping(value="/admin/shelvesReport", method = RequestMethod.GET)
	public ResponseEntity<?> getShelvesReport() {
		List<FloorShelves> listFloorShelves = shelfService.analyzeFloorShelves();
		List<BusinessUnitShelves> listBusinessUnitShelves = businessUnitService.analyzeBusinessUnitShelves();
		Map<String,Object> mapResponse = new HashMap<String,Object>();
		mapResponse.put("FloorReport", listFloorShelves);
		mapResponse.put("BusinessUnitReport", listBusinessUnitShelves);
		
		return ResponseEntity.ok(mapResponse);
	}
	
}
