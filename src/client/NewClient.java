package client;

import carRentalAgency.ICarRentalAgency;
import carRentalAgency.IManagerSession;
import carRentalAgency.IReservationSession;
import rental.CarType;
import rental.Quote;
import rental.Reservation;
import rental.ReservationConstraints;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
     * CONSTRUCTOR
     */

    public NewClient(String scriptName) {
        super(scriptName);

        this.agency = null;

        // LOOKUP CarRentalAgency in RMI Registry
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



    /**
     * METHODS INHERITED FROM TESTING INTERFACE: ReservationSession Methods
     */

    @Override
    protected IReservationSession getNewReservationSession(String name) throws Exception {
        return this.agency.getNewReservationSession(name);
    }

    @Override
    protected String getCheapestCarType(IReservationSession iReservationSession, Date start, Date end, String region) throws Exception {
        //throw new UnsupportedOperationException("TODO");
        // TODO
        String cheapestCarType = iReservationSession.getCheapestCarType(start, end, region);

       // System.out.println("The cheapest car is of type: " + cheapestCarType);
    return cheapestCarType;
    }

    @Override
    protected void checkForAvailableCarTypes(IReservationSession iReservationSession, Date start, Date end) throws Exception {
        Map<String, Set<CarType>> availableCarTypes = iReservationSession.getAvailableCarTypes(start, end);

        printInfoHeader("checkForAvailableCarTypes");

        for (String crcName : availableCarTypes.keySet()) {
            System.out.println("Company \"" + crcName + "\" has the following car types available: ");

            for (CarType ct : availableCarTypes.get(crcName)) {
                System.out.println("    " + ct.getName());
            }
        }
    }

    @Override
    protected void addQuoteToSession(IReservationSession iReservationSession, String name, Date start, Date end, String carType, String region) throws Exception {
        printInfoHeader("addQuoteToSession");

        ReservationConstraints reservationConstraints = new ReservationConstraints(start, end, carType, region);
        Quote createdQuote = iReservationSession.createQuote(reservationConstraints);


        System.out.println("    QUOTE CREATED:  " + createdQuote.toString());
    }

    @Override
    protected List<Reservation> confirmQuotes(IReservationSession iReservationSession, String name) throws Exception {
        printInfoHeader("confirmQuotes");

        List<Reservation> reservations = iReservationSession.confirmQuotes();

        System.out.println("--- Made " + reservations.size() + " reservations:");

        for (Reservation r : reservations) {
            System.out.println(r.toString());
        }

        return reservations;
    }



    /**
     * METHODS INHERITED FROM TESTING INTERFACE: ManagerSession Methods
     */

    @Override
    protected IManagerSession getNewManagerSession(String name, String carRentalName) throws Exception {
        // throw new UnsupportedOperationException("TODO");
        // TODO
        return this.agency.getNewManagerSession(name);
    }

    @Override
    protected Set<String> getBestClients(IManagerSession ms) throws Exception {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }

    @Override
    protected CarType getMostPopularCarTypeIn(IManagerSession ms, String carRentalCompanyName, int year) throws Exception {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }

    @Override
    protected int getNumberOfReservationsBy(IManagerSession ms, String clientName) throws Exception {
        //throw new UnsupportedOperationException("TODO");
        // TODO
        int nbRes = ms.getNbOfReservationsBy(clientName);
        return nbRes;
    }

    @Override
    protected int getNumberOfReservationsForCarType(IManagerSession ms, String carRentalName, String carType) throws Exception {
        // throw new UnsupportedOperationException("TODO");
        // TODO
        int nbOfReservations = ms.getNbOfReservationsForCarTypeInCompany(carType, carRentalName);

        System.out.println("\nCLIENT MAIN: cartype " + carType + " from company " + carRentalName +
                " has " + nbOfReservations + " reservations.");

        return nbOfReservations;
    }


    /**
     * HELPER METHODS
     */

    private void printInfoHeader(String methodName) {
        System.out.println("\n\n\n=============INFO @NewClient - " + methodName + ":");
    }
}
