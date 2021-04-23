package com.datastax.astraportia.neo;

import com.datastax.astraportia.stargate.StargateDocument;

/**
 * Wrapper to interact with Stargate.
 *
 * @author Cedrick LUNVEN (@clunven)
 */
public class NeoDoc extends StargateDocument<Neo> {

    /** Generated. */
    private static final long serialVersionUID = -6535742267665668264L;
    
    /** Collection Name chosen for the Near_EARTH_OBJECT. */
    public static final String NEO_DOC = "neo_doc";
    
    /**
     * default.
     */
    public NeoDoc() {}
            
    /** Constructor.  */
    public NeoDoc(Neo val) {
        super(val);
        this.collectionName = NEO_DOC;
    }
    
    /** Constructor.  */
    public NeoDoc(Neo val, String id) {
        this(val);
        this.documentId     = id;
    }

}
