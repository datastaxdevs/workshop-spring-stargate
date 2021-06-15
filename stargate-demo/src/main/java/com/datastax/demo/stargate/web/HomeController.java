package com.datastax.demo.stargate.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.WebContext;

import com.datastax.demo.stargate.chevrons.ChevronRepository;
import com.datastax.demo.stargate.destinations.Destination;
import com.datastax.demo.stargate.destinations.DestinationPrimaryKey;
import com.datastax.demo.stargate.destinations.DestinationRepository;

/**
 * Home Controller, we want to show the gate.
 *
 * @author Cedrick LUNVEN (@clunven)
 */
@Controller
@RequestMapping(value = "/")
public class HomeController extends AbstractController {

    /** Vie name. */
    private static final String HOME_VIEW = "home";

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private ChevronRepository chevronRepository;

    /** Reference to the chevron URL. */
    private static Map<Integer, String> chevronMap = new HashMap<>();

    private static List<Destination> catalog = new ArrayList<>();

    private static final String GALAXY = "Milky Way";

    /** {@inheritDoc} */
    @Override
    public String getSuccessView() {
        return HOME_VIEW;
    }

    /** {@inheritDoc} */
    @Override
    public void get(HttpServletRequest req, HttpServletResponse res, WebContext ctx) throws Exception {
        // Populate Chevrons
        if (chevronMap.isEmpty()) {
            chevronRepository.findByKeyArea(GALAXY).forEach(chevron -> {
                chevronMap.put(chevron.getKey().getCode(), chevron.getName());
            });
        }
        if (catalog.isEmpty()) {
            catalog = destinationRepository.findByKeyGalaxy(GALAXY);
        }
        String planetName = "Chulak";
        String paramPanet = req.getParameter("planetName");
        if (null != paramPanet && paramPanet.length() > 0) {
            planetName = paramPanet;
        }

        HomeBean hb = new HomeBean();
        hb.setChevronMap(chevronMap);
        hb.setCatalog(catalog);
        hb.setDestination(destinationRepository.findById(new DestinationPrimaryKey(GALAXY, planetName)).get());
        ctx.setVariable("homebean", hb);

    }

    /** {@inheritDoc} */
    @Override
    public void processPost(HttpServletRequest req, HttpServletResponse res, WebContext ctx) throws Exception {}

}
