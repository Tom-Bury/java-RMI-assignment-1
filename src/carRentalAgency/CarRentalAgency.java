package carRentalAgency;

import namingService.INamingService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class CarRentalAgency implements ICarRentalAgency {


    /**
     * VARIABLES
     */

    private Map<Integer, ReservationSession> activeReservationSessions = new HashMap<>();

    private Map<Integer, ManagerSession> activeManagerSessions = new HashMap<>();

    private INamingService namingService;





    /**
     * Methods inherited of the interface
     */


    @Override
    public IReservationSession getNewReservationSession(int id, String clientName) throws RemoteException {
        throw new NotImplementedException();
        //TODO
    }

    @Override
    public boolean stopReservationSession(int id) throws RemoteException {
        throw new NotImplementedException();
        //TODO
    }

    @Override
    public IManagerSession getNewManagerSession(int id) throws RemoteException {
        throw new NotImplementedException();
        //TODO
    }

    @Override
    public boolean stopManagerSession(int id) throws RemoteException {
        throw new NotImplementedException();
        //TODO
    }
}
