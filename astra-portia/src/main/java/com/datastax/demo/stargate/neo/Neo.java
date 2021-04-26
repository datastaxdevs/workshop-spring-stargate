package com.datastax.demo.stargate.neo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class Neo implements Serializable {
    
    /** Serial. */
    private static final long serialVersionUID = 3138868630196905390L;

    @JsonProperty("flag")
    private String flag = "1";
    
    @JsonProperty("orbit_class")
    private String orbitClass;
    
    @JsonProperty("designation")
    private String designation;  
    
    @JsonProperty("discovery_date")
    private String discoveryDate;  
    
    @JsonProperty("h_mag")
    private String h_mag;  
    
    @JsonProperty("i_deg")
    private String i_deg;  
    
    @JsonProperty("moid_au")
    private String moid_au;  
    
    @JsonProperty("period_yr")
    private Double period_yr;  
    
    @JsonProperty("pha")
    private String pha; 
    
    @JsonProperty("q_au_1")
    private Double q_au_1; 
    
    @JsonProperty("q_au_2")
    private Double q_au_2;

    /**
     * Getter accessor for attribute 'orbitClass'.
     *
     * @return
     *       current value of 'orbitClass'
     */
    public String getOrbitClass() {
        return orbitClass;
    }

    /**
     * Setter accessor for attribute 'orbitClass'.
     * @param orbitClass
     * 		new value for 'orbitClass '
     */
    public void setOrbitClass(String orbitClass) {
        this.orbitClass = orbitClass;
    }

    /**
     * Getter accessor for attribute 'designation'.
     *
     * @return
     *       current value of 'designation'
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Setter accessor for attribute 'designation'.
     * @param designation
     * 		new value for 'designation '
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * Getter accessor for attribute 'discoveryDate'.
     *
     * @return
     *       current value of 'discoveryDate'
     */
    public String getDiscoveryDate() {
        return discoveryDate;
    }

    /**
     * Setter accessor for attribute 'discoveryDate'.
     * @param discoveryDate
     * 		new value for 'discoveryDate '
     */
    public void setDiscoveryDate(String discoveryDate) {
        this.discoveryDate = discoveryDate;
    }

    /**
     * Getter accessor for attribute 'h_mag'.
     *
     * @return
     *       current value of 'h_mag'
     */
    public String getH_mag() {
        return h_mag;
    }

    /**
     * Setter accessor for attribute 'h_mag'.
     * @param h_mag
     * 		new value for 'h_mag '
     */
    public void setH_mag(String h_mag) {
        this.h_mag = h_mag;
    }

    /**
     * Getter accessor for attribute 'i_deg'.
     *
     * @return
     *       current value of 'i_deg'
     */
    public String getI_deg() {
        return i_deg;
    }

    /**
     * Setter accessor for attribute 'i_deg'.
     * @param i_deg
     * 		new value for 'i_deg '
     */
    public void setI_deg(String i_deg) {
        this.i_deg = i_deg;
    }

    /**
     * Getter accessor for attribute 'moid_au'.
     *
     * @return
     *       current value of 'moid_au'
     */
    public String getMoid_au() {
        return moid_au;
    }

    /**
     * Setter accessor for attribute 'moid_au'.
     * @param moid_au
     * 		new value for 'moid_au '
     */
    public void setMoid_au(String moid_au) {
        this.moid_au = moid_au;
    }

    /**
     * Getter accessor for attribute 'period_yr'.
     *
     * @return
     *       current value of 'period_yr'
     */
    public Double getPeriod_yr() {
        return period_yr;
    }

    /**
     * Setter accessor for attribute 'period_yr'.
     * @param period_yr
     * 		new value for 'period_yr '
     */
    public void setPeriod_yr(Double period_yr) {
        this.period_yr = period_yr;
    }

    /**
     * Getter accessor for attribute 'pha'.
     *
     * @return
     *       current value of 'pha'
     */
    public String getPha() {
        return pha;
    }

    /**
     * Setter accessor for attribute 'pha'.
     * @param pha
     * 		new value for 'pha '
     */
    public void setPha(String pha) {
        this.pha = pha;
    }

    /**
     * Getter accessor for attribute 'q_au_1'.
     *
     * @return
     *       current value of 'q_au_1'
     */
    public Double getQ_au_1() {
        return q_au_1;
    }

    /**
     * Setter accessor for attribute 'q_au_1'.
     * @param q_au_1
     * 		new value for 'q_au_1 '
     */
    public void setQ_au_1(Double q_au_1) {
        this.q_au_1 = q_au_1;
    }

    /**
     * Getter accessor for attribute 'q_au_2'.
     *
     * @return
     *       current value of 'q_au_2'
     */
    public Double getQ_au_2() {
        return q_au_2;
    }

    /**
     * Setter accessor for attribute 'q_au_2'.
     * @param q_au_2
     * 		new value for 'q_au_2 '
     */
    public void setQ_au_2(Double q_au_2) {
        this.q_au_2 = q_au_2;
    }

    /**
     * Getter accessor for attribute 'flag'.
     *
     * @return
     *       current value of 'flag'
     */
    public String getFlag() {
        return flag;
    }

    /**
     * Setter accessor for attribute 'flag'.
     * @param flag
     * 		new value for 'flag '
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }        
    
}
