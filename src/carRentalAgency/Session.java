package carRentalAgency;

import namingService.INamingService;

public abstract class Session {

    /**
     * VARIABLES
     */

    private int id;

    private INamingService  namingService;


    /**
     * CONSTRUCTOR
     */
    protected Session(int id, INamingService namingService) {
        this.id = id;
        this.namingService = namingService;
    }


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
