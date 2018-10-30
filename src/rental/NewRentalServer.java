package rental;


import carRentalAgency.RentalAgencyServer;
import carRentalCompanies.CarRentalCompany;
import namingService.NamingServiceServer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class NewRentalServer {

    private static final int PORT_NUMBER = 0;
    private static final String HOST = "localhost";



    public static void main(String[] args) throws IOException, ReservationException {

        List<CarRentalCompany> initialCompanies = fetchInitialCRCs();
        System.out.println("INFO @NewRentalServer: Succesfully fetched all initial companies from csv data.");

        // Start the NamingServiceServer with the initial CarRentalCompanies
        NamingServiceServer namingServiceServer = new NamingServiceServer();
        namingServiceServer.startServer(PORT_NUMBER, initialCompanies);
        System.out.println("INFO @NewRentalServer: Succesfully started NamingServiceServer with initial data.");

        // Start the RentalAgencyServer
        String namingServiceRMIregName= namingServiceServer.getRemoteName();
        RentalAgencyServer rentalAgencyServer = new RentalAgencyServer();
        rentalAgencyServer.startServer(PORT_NUMBER, HOST, namingServiceRMIregName);
        System.out.println("INFO @NewRentalServer: Succesfully started RentalAgencyServer.");
    }


    private static List<CarRentalCompany> fetchInitialCRCs() throws IOException, ReservationException {
        List<CarRentalCompany> crcsList = new ArrayList<>();

        CrcData hertzData = loadData("hertz.csv");
        crcsList.add(new CarRentalCompany(hertzData.name, hertzData.regions, hertzData.cars));

        CrcData dockxData= loadData("dockx.csv");
        crcsList.add(new CarRentalCompany(dockxData.name, dockxData.regions, dockxData.cars));

        return crcsList;
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
