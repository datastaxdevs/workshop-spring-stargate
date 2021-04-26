package com.datastax.demo.stargate.web;

import java.io.Serializable;
import java.util.Random;

import com.datastax.demo.stargate.neo.Neo;

/**
 * Web bean to be displayed on home page
 * @author Cedrick LUNVEN (@clunven)
 */
public class HomeBean implements Serializable {
    
    /** Serial. */
    private static final long serialVersionUID = -4329564412692576275L;   
    
    private Neo destination = new Neo();
    
    private String chevron1 = getRandomChevron();
    private String chevron2 = getRandomChevron();
    private String chevron3 = getRandomChevron();
    private String chevron4 = getRandomChevron();
    private String chevron5 = getRandomChevron();
    private String chevron6 = getRandomChevron();
    
    private String getRandomChevron() {
        String c = String.valueOf(new Random().nextInt(38 - 2) + 2);
        return String.format("%1$" + 3 + "s", c).replace(' ', '0');
    }
    
    /**
     * Getter accessor for attribute 'destination'.
     *
     * @return
     *       current value of 'destination'
     */
    public Neo getDestination() {
        return destination;
    }
    
    /**
     * Getter accessor for attribute 'chevron1'.
     *
     * @return
     *       current value of 'chevron1'
     */
    public String getChevron1() {
        return chevron1;
    }

    /**
     * Setter accessor for attribute 'chevron1'.
     * @param chevron1
     * 		new value for 'chevron1 '
     */
    public void setChevron1(String chevron1) {
        this.chevron1 = chevron1;
    }

    /**
     * Getter accessor for attribute 'chevron2'.
     *
     * @return
     *       current value of 'chevron2'
     */
    public String getChevron2() {
        return chevron2;
    }

    /**
     * Setter accessor for attribute 'chevron2'.
     * @param chevron2
     * 		new value for 'chevron2 '
     */
    public void setChevron2(String chevron2) {
        this.chevron2 = chevron2;
    }

    /**
     * Getter accessor for attribute 'chevron3'.
     *
     * @return
     *       current value of 'chevron3'
     */
    public String getChevron3() {
        return chevron3;
    }

    /**
     * Setter accessor for attribute 'chevron3'.
     * @param chevron3
     * 		new value for 'chevron3 '
     */
    public void setChevron3(String chevron3) {
        this.chevron3 = chevron3;
    }

    /**
     * Getter accessor for attribute 'chevron4'.
     *
     * @return
     *       current value of 'chevron4'
     */
    public String getChevron4() {
        return chevron4;
    }

    /**
     * Setter accessor for attribute 'chevron4'.
     * @param chevron4
     * 		new value for 'chevron4 '
     */
    public void setChevron4(String chevron4) {
        this.chevron4 = chevron4;
    }

    /**
     * Getter accessor for attribute 'chevron5'.
     *
     * @return
     *       current value of 'chevron5'
     */
    public String getChevron5() {
        return chevron5;
    }

    /**
     * Setter accessor for attribute 'chevron5'.
     * @param chevron5
     * 		new value for 'chevron5 '
     */
    public void setChevron5(String chevron5) {
        this.chevron5 = chevron5;
    }

    /**
     * Getter accessor for attribute 'chevron6'.
     *
     * @return
     *       current value of 'chevron6'
     */
    public String getChevron6() {
        return chevron6;
    }

    /**
     * Setter accessor for attribute 'chevron6'.
     * @param chevron6
     * 		new value for 'chevron6 '
     */
    public void setChevron6(String chevron6) {
        this.chevron6 = chevron6;
    }

    /**
     * Setter accessor for attribute 'destination'.
     * @param destination
     * 		new value for 'destination '
     */
    public void setDestination(Neo destination) {
        this.destination = destination;
    }
    

}
