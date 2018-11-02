package carRentalAgency;

import namingService.INamingService;

import java.util.Date;

public abstract class Session {

    /**
     * VARIABLES
     */

    private int id;

    private INamingService  namingService;

    private Date creationDate;


    /**
     * CONSTRUCTOR
     */
    protected Session(int id, INamingService namingService, Date creationDate) {
        this.id = id;
        this.namingService = namingService;
        this.creationDate = creationDate;
    }


    /**
     * GETTERS
     */

    public int getId() {
        return id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public INamingService getNamingService() {
        return namingService;
    }
}
