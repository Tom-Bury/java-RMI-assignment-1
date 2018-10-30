package carRentalAgency;

import namingService.INamingService;

public abstract class Session {

    /**
     * VARIABLES
     */

    private int id;

    private INamingService  namingService;


    /**
     * GETTERS
     */

    public int getId() {
        return id;
    }

    public INamingService getNamingService() {
        return namingService;
    }
}
