package com.datastax.demo.stargate.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.WebContext;

import com.datastax.demo.stargate.chevrons.ChevronRepository;
import com.datastax.demo.stargate.destinations.DestinationPrimaryKey;
import com.datastax.demo.stargate.destinations.DestinationRepository;

/**
 * Home Controller, we want to show the gate.
 *
 * @author Cedrick LUNVEN (@clunven)
 */
@Controller
@RequestMapping(value="/")
public class HomeController extends AbstractController {
    
    /** Vie name. */
    private static final String HOME_VIEW = "home";
    
    @Autowired
    private DestinationRepository destinationRepository;
    
    @Autowired
    private ChevronRepository chevronRepository;
    
    /** Reference to the chevron URL. */
    private Map<Integer, String> chevronMap= new HashMap<>();
    
    /** {@inheritDoc} */
    @Override
    public String getSuccessView() {
        return HOME_VIEW;
    }
    
    /** {@inheritDoc} */
    @Override
    public void get(HttpServletRequest req, HttpServletResponse res, WebContext ctx) 
    throws Exception {
        HomeBean hb = new HomeBean();
        // Put it in the webs
        hb.setDestination(destinationRepository
                .findById(new DestinationPrimaryKey("Milky Way", "Chulak"))
                .get());
        
        // Populate Chevrons
        if (chevronMap.isEmpty()) {
            chevronRepository.findByKeyArea("Milky Way").forEach(chevron -> {
                chevronMap.put(chevron.getKey().getCode(), chevron.getName());
            });
        }
        hb.setChevronMap(chevronMap);
        ctx.setVariable("homebean", hb);
    }
    
    /** {@inheritDoc} */
    @Override
    public void processPost(HttpServletRequest req, HttpServletResponse res, WebContext ctx) 
    throws Exception {}
    
}
