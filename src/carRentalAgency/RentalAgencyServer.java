package carRentalAgency;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RentalAgencyServer {

    private static final int PORT = 0;

    public static void main(String[] args) {

        CarRentalAgency agency = new CarRentalAgency();

        // BINDING WITH RMI_REGISTRY
        try {
            System.setSecurityManager(null);
            ICarRentalAgency iCarRentalAgency = (ICarRentalAgency) UnicastRemoteObject.exportObject(agency, PORT);

            Registry registry = LocateRegistry.getRegistry();

            // Rebind will replace any existing binding for the name within rmiregistry.
            // If there was no match, the object will be bound to the name within the registry as usual.
            String remoteName = ICarRentalAgency.class.getName();
            registry.rebind(remoteName, iCarRentalAgency);
            System.err.println("INFO: CarRentalAgencyServer ready\n");
        } catch (Exception e) {
            System.err.println("\nERROR @CarRentalAgency: Binding error: " + e.toString());
            e.printStackTrace();
        }
    }
}
