package com.datastax.demo.stargate.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.datastax.demo.stargate.destinations.Destination;

/**
 * Web bean to be displayed on home page
 *
 * @author Cedrick LUNVEN (@clunven)
 */
public class HomeBean implements Serializable {
    
    /** Serial. */
    private static final long serialVersionUID = -4329564412692576275L;   
    
    private Destination destination;
    
    private Map<Integer, String> chevronMap = new HashMap<>();
    
    /** List of available planets. */
    private List<Destination> catalog = new ArrayList<>();
    
    /**
     * Getter accessor for attribute 'destination'.
     *
     * @return
     *       current value of 'destination'
     */
    public Destination getDestination() {
        return destination;
    }

    /**
     * Setter accessor for attribute 'destination'.
     * @param destination
     * 		new value for 'destination '
     */
    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    /**
     * Getter accessor for attribute 'chevronMap'.
     *
     * @return
     *       current value of 'chevronMap'
     */
    public Map<Integer, String> getChevronMap() {
        return chevronMap;
    }

    /**
     * Setter accessor for attribute 'chevronMap'.
     * @param chevronMap
     * 		new value for 'chevronMap '
     */
    public void setChevronMap(Map<Integer, String> chevronMap) {
        this.chevronMap = chevronMap;
    }

    /**
     * Getter accessor for attribute 'catalog'.
     *
     * @return
     *       current value of 'catalog'
     */
    public List<Destination> getCatalog() {
        return catalog;
    }

    /**
     * Setter accessor for attribute 'catalog'.
     * @param catalog
     * 		new value for 'catalog '
     */
    public void setCatalog(List<Destination> catalog) {
        this.catalog = catalog;
    }
    

}
