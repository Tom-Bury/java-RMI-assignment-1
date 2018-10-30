package client;

import carRentalAgency.ICarRentalAgency;
import carRentalAgency.IManagerSession;
import carRentalAgency.IReservationSession;
import rental.CarType;
import rental.Reservation;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class NewClient extends AbstractTestManagement<IReservationSession, IManagerSession> {

    /**
     * VARIABLES
     */

    private static final String CLIENT_INFO = "INFO CLIENT: ";
    private static final String CLIENT_ERROR = "ERROR CLIENT: ";

    private ICarRentalAgency agency;
    private final static String AGENCY_REGISTRY_NAME = ICarRentalAgency.class.getName();


    /**
     * MAIN METHOD
     */

    public static void main(String[] args) {

        NewClient client = new NewClient("trips");

        try {
            client.run();
        } catch (Exception e) {
            System.err.println("ERROR @NewClient: Error while running NewClient");
            e.printStackTrace();
        }


    }




    /**
     * METHODS INHERITED FROM TESTING INTERFACE
     */

    @Override
    protected Set<String> getBestClients(IManagerSession ms) throws Exception {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }

    @Override
    protected String getCheapestCarType(IReservationSession iReservationSession, Date start, Date end, String region) throws Exception {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }

    @Override
    protected CarType getMostPopularCarTypeIn(IManagerSession ms, String carRentalCompanyName, int year) throws Exception {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }

    @Override
    protected IReservationSession getNewReservationSession(String name) throws Exception {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }

    @Override
    protected IManagerSession getNewManagerSession(String name, String carRentalName) throws Exception {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }

    @Override
    protected void checkForAvailableCarTypes(IReservationSession iReservationSession, Date start, Date end) throws Exception {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }

    @Override
    protected void addQuoteToSession(IReservationSession iReservationSession, String name, Date start, Date end, String carType, String region) throws Exception {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }

    @Override
    protected List<Reservation> confirmQuotes(IReservationSession iReservationSession, String name) throws Exception {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }

    @Override
    protected int getNumberOfReservationsBy(IManagerSession ms, String clientName) throws Exception {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }

    @Override
    protected int getNumberOfReservationsForCarType(IManagerSession ms, String carRentalName, String carType) throws Exception {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }

    /**
     * CONSTRUCTOR
     */

    public NewClient(String scriptName) {
        super(scriptName);

        this.agency = null;

        // LOOKUP
        Registry registry;
        try {
            System.setSecurityManager(null);
            registry = LocateRegistry.getRegistry("localhost");
            this.agency = (ICarRentalAgency) registry.lookup(AGENCY_REGISTRY_NAME);

        } catch (Exception e) {
            System.err.println("ERROR @NewClient: CarRentalAgency lookup error: " + e.toString());
            e.printStackTrace();
        }
    }




}
