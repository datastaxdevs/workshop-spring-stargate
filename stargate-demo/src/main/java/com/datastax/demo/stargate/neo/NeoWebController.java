package com.datastax.demo.stargate.neo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.WebContext;

import com.datastax.demo.stargate.web.AbstractController;

@Controller
@RequestMapping(value="/neo")
public class NeoWebController extends AbstractController {
    
    /** Logger for the class. */
    private static final Logger logger = LoggerFactory.getLogger(NeoWebController.class);

    /** Vie name. */
    private static final String VIEW_NEO = "neo";

    /** {@inheritDoc} */
    @Override
    public String getSuccessView() {
        return VIEW_NEO;
    }
    
    /** {@inheritDoc} */
    @Override
    public void get(HttpServletRequest req, HttpServletResponse res, WebContext ctx) 
    throws Exception {
        renderPage(ctx);
    }
    
    /** {@inheritDoc} */
    @Override
    public void processPost(HttpServletRequest req, HttpServletResponse res, WebContext ctx) 
    throws Exception {
        String msg       = null;
        String msgType   = "success";
        String operation  = req.getParameter("op");
        String documentId = req.getParameter("uid");
        if ("delete".equalsIgnoreCase(operation)) {
            logger.info("Delete NearEarthObject {}", documentId);
            //astraPortiaServices.deleteNeo(documentId);
            msg = documentId + " has been DELETED";
        }
        ctx.setVariable("msgType", msgType);
        ctx.setVariable("msgInfo", msg);
        renderPage(ctx);
    }
    
    private void renderPage(WebContext ctx) {
        //NeoWebBean web = new NeoWebBean();
        //web.setNeolist(astraPortiaServices.findAllNeos());
        ctx.setVariable("cbean", new Neo());
    }

    
}
