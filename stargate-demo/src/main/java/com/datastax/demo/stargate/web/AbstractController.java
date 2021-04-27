package com.datastax.demo.stargate.web;

import static com.datastax.demo.stargate.web.WebUtils.getCookie;
import static com.datastax.demo.stargate.web.WebUtils.getSessionAttribute;
import static com.datastax.demo.stargate.web.WebUtils.setSessionAttribute;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.datastax.astra.sdk.AstraClient;

/**
 * SuperClass for controllers
 *
 * @author Cedrick LUNVEN (@clunven)
 */
public abstract class AbstractController {
    
    /** Generic Parameters. */
    protected static final String LANG_ATTRIBUTE = "lang";
    
    /** Loger for that class. */
    protected Logger LOGGER = LoggerFactory.getLogger(getClass());
    
    @Autowired
    protected SpringTemplateEngine templateEngine;
    
    @Autowired
    protected AstraClient astraClient;
   
    /**
     * Define response Locale (Cookie <-> HttpSession <-> Request)
     *
     * @param req
     *      current request.
     * @param res
     *      current response.
     */
    protected void i18n(HttpServletRequest req, HttpServletResponse res) {
        String lang = req.getParameter(LANG_ATTRIBUTE);
        if (lang != null) {
            LOGGER.info("Changing language to {}", lang);
            
            // Update Request
            res.setLocale(new Locale(lang));
            // Update Session
            setSessionAttribute(req, LANG_ATTRIBUTE, lang);
            // Create Cookie
            Cookie cookie = new Cookie(LANG_ATTRIBUTE, lang);
            cookie.setMaxAge(365 * 24 * 60 * 60); // 1 years
            res.addCookie(cookie);
            
        } else {
            String langSession = (String) getSessionAttribute(req, LANG_ATTRIBUTE);
            if (langSession != null) {
                res.setLocale(new Locale(langSession));
            } else {
                // Not in session, look for cookie if cookie update session
               Cookie cookie = getCookie(req, LANG_ATTRIBUTE);
               if (cookie != null) {
                   setSessionAttribute(req, LANG_ATTRIBUTE, cookie.getValue());
                   res.setLocale(new Locale(cookie.getValue()));
               }
            }
        }
    }
    
    /**
     * Invoked by dispatcher.
     *
     * @param req
     *      current request
     * @param res
     *      current response
     * @throws IOException
     *      error occured.
     */
    @GetMapping
    public void get(HttpServletRequest req, HttpServletResponse res)
    throws IOException {
        // Change locale if requested
        i18n(req, res);
        
        WebContext ctx = new WebContext(req, res,  req.getSession().getServletContext(), res.getLocale());
        try {
            ctx.setVariable("namespace", astraClient.cqlSession().getKeyspace().get().asInternal());
            get(req, res, ctx);
        } catch(Throwable t) {
            ctx.setVariable("msgType", "error");
            ctx.setVariable("msgInfo", t.getMessage());
        }

        // Render to view
        templateEngine.process(getSuccessView(), ctx, res.getWriter());
    }
    
    /**
     * Invoked by dispatcher.
     *
     * @param req
     *      current request
     * @param res
     *      current response
     * @throws IOException
     *      error occured.
     */
    @PostMapping
    public void post(HttpServletRequest req, HttpServletResponse res)
    throws IOException {
        WebContext ctx = new WebContext(req, res,  req.getSession().getServletContext(), req.getLocale());
     
        // Adding attribute to response
        try {
            processPost(req, res, ctx);
        } catch(Throwable t) {
            ctx.setVariable("msgType", "error");
            ctx.setVariable("msgInfo", t.getMessage());
        }

        // Render to view
        templateEngine.process(getSuccessView(), ctx, res.getWriter());
    }
    
    
    /**
     * Create view from template.
     *
     * @param req
     *      current http request
     * @param res
     *      current http response
     * @throws IOException
     *      target error
     */
    public abstract void get(HttpServletRequest req, HttpServletResponse res, WebContext ctx)
    throws Exception;

    /**
     * Create view from template.
     *
     * @param req
     *      current http request
     * @param res
     *      current http response
     * @throws IOException
     *      target error
     */
    public abstract void processPost(HttpServletRequest req, HttpServletResponse res, WebContext ctx)
    throws Exception;

    /**
     * Getter accessor for attribute 'successView'.
     *
     * @return
     *       current value of 'successView'
     */
    public abstract String getSuccessView();

}
