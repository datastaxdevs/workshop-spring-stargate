package com.datastax.astraportia.neo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Objects for 
 * @author Cedrick LUNVEN (@clunven)
 */
public class NeoWebBean  implements Serializable {

    /** Serial. */
    private static final long serialVersionUID = -3706397019005781214L;

    private List<NeoDoc> neolist = new ArrayList<>();

    /**
     * Getter accessor for attribute 'neolist'.
     *
     * @return
     *       current value of 'neolist'
     */
    public List<NeoDoc> getNeolist() {
        return neolist;
    }

    /**
     * Setter accessor for attribute 'neolist'.
     * @param neolist
     * 		new value for 'neolist '
     */
    public void setNeolist(List<NeoDoc> neolist) {
        this.neolist = neolist;
    }
    
    
}
