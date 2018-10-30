package carRentalAgency;


import rental.Quote;
import rental.Reservation;
import rental.ReservationException;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationSession extends Session implements IReservationSession  {

    /**
     * VARIABLES
     */

    private String clientName;

    private List<Quote> currentQuotes = new ArrayList<>();


    /**
     * INHERITED METHODS OF INTERFACE
     */

    @Override
    public Quote createQuote(Date start, Date end, String carType, String region) throws RemoteException {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }

    @Override
    public List<Reservation> confirmQuotes() throws ReservationException, RemoteException {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }

    @Override
    public List<String> getAvailableCarTypes(Date start, Date end) throws RemoteException {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }

    @Override
    public String getCheapestCarType(Date start, Date end, String region) throws RemoteException {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }
}
