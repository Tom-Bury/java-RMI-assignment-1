package client;

import rental.Car;
import rental.CarType;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface CarRentalCompanyInterface extends Remote {

    Set<CarType> getAvailableCarTypes(Date start, Date end) throws RemoteException;
}
