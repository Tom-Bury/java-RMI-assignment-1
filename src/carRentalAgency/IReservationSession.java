package carRentalAgency;

import rental.CarType;
import rental.Quote;
import rental.Reservation;
import rental.ReservationException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * A ReservationSession is used by a normal client to create quotes and make reservations.
 */
public interface IReservationSession extends Remote {

    Quote createQuote(Date start, Date end, String carType, String region) throws RemoteException;

    List<Reservation> confirmQuotes() throws ReservationException, RemoteException;

    Map<String, Set<CarType>> getAvailableCarTypes(Date start, Date end) throws RemoteException;

    String getCheapestCarType(Date start, Date end, String region) throws RemoteException;

}
