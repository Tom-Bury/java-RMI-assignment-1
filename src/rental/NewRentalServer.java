package rental;


import carRentalAgency.RentalAgencyServer;
import carRentalCompanies.CarRentalCompany;
import carRentalCompanies.ICarRentalCompany;
import namingService.NamingServiceServer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class NewRentalServer {

    public static final int PORT_NUMBER = 0;
    private static final String HOST = "localhost";



    public static void main(String[] args) throws IOException, ReservationException {

        List<CarRentalCompany> initialCompanies = fetchInitialCRCs();
        System.out.println("INFO @NewRentalServer: Succesfully fetched all initial companies from csv data.\n");

        List<String> initialCrcNames = registerCrcsWithRMIRegistry(initialCompanies);

        List<ICarRentalCompany> initialCrcLookups = lookupInitialCRCs(initialCrcNames);

        // Start the NamingServiceServer with the initial CarRentalCompany stubs
        NamingServiceServer namingServiceServer = new NamingServiceServer();
        namingServiceServer.startServer(PORT_NUMBER, initialCrcLookups);
        System.out.println("INFO @NewRentalServer: Succesfully started NamingServiceServer with initial data.\n");

        // Start the RentalAgencyServer
        String namingServiceRMIregName= namingServiceServer.getRemoteName();
        RentalAgencyServer rentalAgencyServer = new RentalAgencyServer();
        rentalAgencyServer.startServer(PORT_NUMBER, HOST, namingServiceRMIregName);
        System.out.println("INFO @NewRentalServer: Succesfully started RentalAgencyServer.\n");
        System.out.println("==================== START SCRIPT ====================\n");
    }


    /**
     * Creates the CarRentalCompany objects from the csv files.
     */
    private static List<CarRentalCompany> fetchInitialCRCs() throws IOException, ReservationException {
        List<CarRentalCompany> crcsList = new ArrayList<>();

        CrcData hertzData = loadData("hertz.csv");
        crcsList.add(new CarRentalCompany(hertzData.name, hertzData.regions, hertzData.cars));

        CrcData dockxData= loadData("dockx.csv");
        crcsList.add(new CarRentalCompany(dockxData.name, dockxData.regions, dockxData.cars));

        return crcsList;
    }

    /**
     * Registers the given CarRentalCompany objects in the RMI Registry, under the name of the company.
     * Returns a list containing the names with which the companies were registered.
     */
    private static List<String> registerCrcsWithRMIRegistry(List<CarRentalCompany> carRentalCompanies) {

        List<String> names = new ArrayList<>();

        for (CarRentalCompany crc : carRentalCompanies) {
            try {
                System.setSecurityManager(null);
                ICarRentalCompany iCarRentalCompany = (ICarRentalCompany) UnicastRemoteObject.exportObject(crc, PORT_NUMBER);

                Registry registry = LocateRegistry.getRegistry(HOST);

                // Rebind will replace any existing binding for the name within rmiregistry.
                // If there was no match, the object will be bound to the name within the registry as usual.
                registry.rebind(crc.getName(), iCarRentalCompany);

                names.add(crc.getName());
                System.out.println("INFO: CarRentalCompany " + crc.getName() + " bound with RMI registry.");
            } catch (Exception e) {
                System.err.println("\nERROR @NewRentalServer: Binding error: " + e.toString());
                e.printStackTrace();
            }
        }

        return names;
    }

    /**
     * Does an RMI Registry lookup for every given company name in the list.
     */
    private static List<ICarRentalCompany> lookupInitialCRCs(List<String> initialNames) {
        System.setSecurityManager(null);
        List<ICarRentalCompany> result = new ArrayList<>();

        for (String name : initialNames) {
            try {
                Registry registry = LocateRegistry.getRegistry("localhost");
                ICarRentalCompany crc = (ICarRentalCompany) registry.lookup(name);
                result.add(crc);

            } catch (Exception e) {
                System.err.println("ERROR @NewRentalServer: CarRentalCompany lookup error for: " + name + "\n" + e.toString());
                e.printStackTrace();
            }
        }

        return result;
    }







    /**
     * METHODS TO LOAD GIVEN CRC DATA
     */

    public static CrcData loadData(String datafile)
            throws ReservationException, NumberFormatException, IOException {

        CrcData out = new CrcData();
        int nextuid = 0;

        // open file
        BufferedReader in = new BufferedReader(new FileReader(datafile));
        StringTokenizer csvReader;

        try {
            // while next line exists
            while (in.ready()) {
                String line = in.readLine();

                if (line.startsWith("#")) {
                    // comment -> skip
                } else if (line.startsWith("-")) {
                    csvReader = new StringTokenizer(line.substring(1), ",");
                    out.name = csvReader.nextToken();
                    out.regions = Arrays.asList(csvReader.nextToken().split(":"));
                } else {
                    // tokenize on ,
                    csvReader = new StringTokenizer(line, ",");
                    // create new car type from first 5 fields
                    CarType type = new CarType(csvReader.nextToken(),
                            Integer.parseInt(csvReader.nextToken()),
                            Float.parseFloat(csvReader.nextToken()),
                            Double.parseDouble(csvReader.nextToken()),
                            Boolean.parseBoolean(csvReader.nextToken()));
                    System.out.println(type);
                    // create N new cars with given type, where N is the 5th field
                    for (int i = Integer.parseInt(csvReader.nextToken()); i > 0; i--) {
                        out.cars.add(new Car(nextuid++, type));
                    }
                }
            }
        } finally {
            in.close();
        }

        return out;
    }

    static class CrcData {
        public List<Car> cars = new LinkedList<Car>();
        public String name;
        public List<String> regions =  new LinkedList<String>();
    }
}
