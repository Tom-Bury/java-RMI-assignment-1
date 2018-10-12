package rental;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class RentalServer {

	private static CarRentalCompany obj;

	public static void main(String[] args) throws ReservationException,
			NumberFormatException, IOException {
		CrcData data  = loadData("hertz.csv");
		obj = new CarRentalCompany(data.name, data.regions, data.cars);

		// BINDING
		try {
			System.setSecurityManager(null);
			CarRentalCompanyInterface carRentalCompanyInterface = (CarRentalCompanyInterface) UnicastRemoteObject.exportObject(obj, 0);

			Registry registry = LocateRegistry.getRegistry();

			// Rebind will replace any existing binding for the name within rmiregistry.
			// If there was no match, the object will be bound to the name within the registry as usual.
			String remoteName = CarRentalCompanyInterface.class.getName();
			registry.rebind(remoteName, carRentalCompanyInterface);
			System.err.println("INFO: Server ready\n");
		} catch (Exception e) {
			System.err.println("\nERROR: Binding error: " + e.toString());
			e.printStackTrace();
		}

	}

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
