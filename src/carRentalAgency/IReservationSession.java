package carRentalAgency;

import rental.*;

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

    Quote createQuote(ReservationConstraints reservationConstraints) throws RemoteException;

    List<Reservation> confirmQuotes() throws ReservationException, RemoteException;

    Map<String, Set<CarType>> getAvailableCarTypes(Date start, Date end) throws RemoteException;

    String getCheapestCarType(Date start, Date end, String region) throws RemoteException;

}
