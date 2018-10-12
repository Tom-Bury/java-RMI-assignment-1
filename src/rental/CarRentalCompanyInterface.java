package rental;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface CarRentalCompanyInterface extends Remote {

    Set<CarType> getAvailableCarTypes(Date start, Date end) throws RemoteException;

    Quote createQuote(ReservationConstraints constraints, String client) throws RemoteException, ReservationException;

    Reservation confirmQuote(Quote quote) throws ReservationException, RemoteException;

    List<Reservation> getAllReservationsFrom(String carRenter) throws RemoteException;

    int getNbReservations(String cartype) throws RemoteException;
}
