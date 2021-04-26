package com.datastax.demo.stargate.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.WebContext;

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
        hb.setDestination(astraPortiaServices.getRandomNeo());
        ctx.setVariable("homebean", hb);
    }
    
    /** {@inheritDoc} */
    @Override
    public void processPost(HttpServletRequest req, HttpServletResponse res, WebContext ctx) 
    throws Exception {}
    
}
