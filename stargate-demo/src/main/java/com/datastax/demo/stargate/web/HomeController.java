package com.datastax.demo.stargate.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.datastax.demo.stargate.chevrons.ChevronRepository;
import com.datastax.demo.stargate.destinations.Destination;
import com.datastax.demo.stargate.destinations.DestinationPrimaryKey;
import com.datastax.demo.stargate.destinations.DestinationRepository;

/**
 * Home Controller, we want to show the gate.
 *
 * @author Cedrick LUNVEN (@clunven)
 * @author Sergi Almar (@sergialmar)
 */
@Controller
public class HomeController {

    /** View name. */
    private static final String HOME_VIEW = "home";

    /** Reference to the chevron URL. */
    private static Map<Integer, String> chevronMap = new HashMap<>();

    private static List<Destination> catalog = new ArrayList<>();

    private static final String GALAXY = "Milky Way";
    

    private final DestinationRepository destinationRepository;

    private final ChevronRepository chevronRepository;
    
    
    public HomeController(DestinationRepository destinationRepository, ChevronRepository chevronRepository) {
		this.destinationRepository = destinationRepository;
		this.chevronRepository = chevronRepository;
	}
    
    @PostConstruct
    public void populateChevonsAndCatalog() {
    	chevronRepository.findByKeyArea(GALAXY).forEach(chevron -> {
            chevronMap.put(chevron.getKey().getCode(), chevron.getName());
        });
    	
    	catalog = destinationRepository.findByKeyGalaxy(GALAXY);
    }

	@GetMapping("/")
    public String showHome(@RequestParam(name = "planetName", defaultValue = "Chulak") String planetName,
    		Model model) throws Exception {
        
		HomeBean hb = new HomeBean();
        hb.setChevronMap(chevronMap);
        hb.setCatalog(catalog);
        hb.setDestination(new Destination(new DestinationPrimaryKey(GALAXY, planetName), 1, 1, 1, 1, 1, 1, "ok"));
        Optional<Destination> destination = 
                destinationRepository.findById(
                        new DestinationPrimaryKey(GALAXY, planetName));
        destination.ifPresent(hb::setDestination);
        model.addAttribute("homebean", hb);
        return HOME_VIEW;
    }
}
