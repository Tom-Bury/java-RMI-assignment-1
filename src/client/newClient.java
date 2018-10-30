package client;

import rental.CarType;
import rental.Reservation;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class newClient extends AbstractTestManagement {

    private static final String CLIENT_INFO = "INFO CLIENT: ";
    private static final String CLIENT_ERROR = "ERROR CLIENT: ";










    /**
     * METHODS INHERITED FROM TESTING INTERFACE
     */


    @Override
    protected Set<String> getBestClients(Object ms) throws Exception {
        throw new NotImplementedException();
        //TODO
    }

    @Override
    protected String getCheapestCarType(Object o, Date start, Date end, String region) throws Exception {
        throw new NotImplementedException();
        //TODO
    }

    @Override
    protected CarType getMostPopularCarTypeIn(Object ms, String carRentalCompanyName, int year) throws Exception {
        throw new NotImplementedException();
        //TODO
    }

    @Override
    protected Object getNewReservationSession(String name) throws Exception {
        throw new NotImplementedException();
        //TODO
    }

    @Override
    protected Object getNewManagerSession(String name, String carRentalName) throws Exception {
        throw new NotImplementedException();
        //TODO
    }

    @Override
    protected void checkForAvailableCarTypes(Object o, Date start, Date end) throws Exception {
        throw new NotImplementedException();
        //TODO
    }

    @Override
    protected void addQuoteToSession(Object o, String name, Date start, Date end, String carType, String region) throws Exception {
        throw new NotImplementedException();
        //TODO
    }

    @Override
    protected List<Reservation> confirmQuotes(Object o, String name) throws Exception {
        throw new NotImplementedException();
        //TODO
    }

    @Override
    protected int getNumberOfReservationsBy(Object ms, String clientName) throws Exception {
        throw new NotImplementedException();
        //TODO
    }

    @Override
    protected int getNumberOfReservationsForCarType(Object ms, String carRentalName, String carType) throws Exception {
        throw new NotImplementedException();
        //TODO
    }
}
