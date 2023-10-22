import java.io.*;
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;

public class Main
{
	public static Animal AddNewAnimal(Scanner scanner) {
		while(true) {
			Integer typeNumber, weight, age;

			Printer.Clear();
			Printer.NewAnimalType();
			typeNumber = scanner.nextInt();

			Printer.Clear();
			Printer.NewAnimalWeight();
			weight = scanner.nextInt();

			Printer.Clear();
			Printer.NewAnimalAge();
			age = scanner.nextInt();

			switch (typeNumber) {
				case 1:
					return new Waterfowl(weight, age);

				case 2:
					return new Feathered(weight, age);

				case 3:
					return new Ungulates(weight, age);

				case 4:
					return new ColdBlooded(weight, age);
			
				default:
					Printer.InputError();
					continue;
			}
		}
	}

	public static Aviary[] ReadFromFile() throws IOException {
		Aviary aquarium = new Aviary(Aviary.typesOfAviaries.aquarium);
		Aviary meshCoveredAviary = new Aviary(Aviary.typesOfAviaries.meshCoveredAviaries);
		Aviary openAirAviary = new Aviary(Aviary.typesOfAviaries.openAirAviaries);
		Aviary aviaryWithInfraredLighting = new Aviary(Aviary.typesOfAviaries.aviariesWithInfraredLighting);

		Scanner fileScanner = new Scanner(new File("data-base.txt"));
		while (fileScanner.hasNextLine()) {
			String[] line = fileScanner.nextLine().split(" ");

			if(Objects.equals(line[0], aquarium.GetType().name())) {
				Waterfowl newAnimal = new Waterfowl(Integer.parseInt(line[1]), Integer.parseInt(line[2]));
				newAnimal.Move(aquarium);
			} else if (Objects.equals(line[0], meshCoveredAviary.GetType().name())) {
				Feathered newAnimal = new Feathered(Integer.parseInt(line[1]), Integer.parseInt(line[2]));
				newAnimal.Move(meshCoveredAviary);
			} else if (Objects.equals(line[0], openAirAviary.GetType().name())) {
				Ungulates newAnimal = new Ungulates(Integer.parseInt(line[1]), Integer.parseInt(line[2]));
				newAnimal.Move(openAirAviary);
			} else if (Objects.equals(line[0], aviaryWithInfraredLighting.GetType().name())) {
				ColdBlooded newAnimal = new ColdBlooded(Integer.parseInt(line[1]), Integer.parseInt(line[2]));
				newAnimal.Move(aviaryWithInfraredLighting);
			}
		}

		fileScanner.close();
		return new Aviary[] { aquarium, meshCoveredAviary, openAirAviary, aviaryWithInfraredLighting };
	}

	public static void WriteAnimalsToFile(Aviary[] aviaries) throws IOException {
		for (int i = 0; i < aviaries.length; i++) {
			Animal[] animals = aviaries[i].GetAnimals();

			for (int j = 0; j < animals.length; j++) {
				WriteToFile(animals[j] + "\n", "data-base.txt");
			}
		}
	}

	public static void WriteToFile(String value, String fileName) throws IOException{
		FileWriter writer = new FileWriter(fileName, true);
		writer.write(value);
		writer.close();
	}

	public static void MainMenu(Config config) throws IOException{
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome, " + config.GetUsername() + "!");
		Printer.Continue();
		scanner.next();

		Aviary aquarium = new Aviary(Aviary.typesOfAviaries.aquarium);
		Aviary meshCoveredAviary = new Aviary(Aviary.typesOfAviaries.meshCoveredAviaries);
		Aviary openAirAviary = new Aviary(Aviary.typesOfAviaries.openAirAviaries);
		Aviary aviaryWithInfraredLighting = new Aviary(Aviary.typesOfAviaries.aviariesWithInfraredLighting);

		while(true) {
			try {
				Printer.Clear();
				Printer.MainMenu();
				Integer command = scanner.nextInt();
				
				switch (command) {
				case 0:
					scanner.close();
					return;
			
				case 1:
					Printer.Clear();
					aquarium.PrintAnimals();
					Printer.Continue();
					scanner.next();
					break;

				case 2:
					Printer.Clear();
					meshCoveredAviary.PrintAnimals();
					Printer.Continue();
					scanner.next();
					break;
					
				case 3:
					Printer.Clear();
					openAirAviary.PrintAnimals();
					Printer.Continue();
					scanner.next();
					break;
					
					case 4:
					Printer.Clear();
					aviaryWithInfraredLighting.PrintAnimals();
					Printer.Continue();
					scanner.next();
					break;

				case 5:
					Printer.Clear();

					try {
						Animal newAnimal = AddNewAnimal(scanner);
						
						switch (newAnimal.GetType()) {
						case aquarium:
							newAnimal.Move(aquarium);
							break;
							
						case meshCoveredAviaries:
							newAnimal.Move(meshCoveredAviary);
							break;

						case openAirAviaries:
							newAnimal.Move(openAirAviary);
							break;

							case aviariesWithInfraredLighting:
							newAnimal.Move(aviaryWithInfraredLighting);
							break;
							
							default:
							break;
						}
					} catch (java.util.InputMismatchException e) {
						config.ErrorLog("Incorrect input\n", "logs.txt");
					}

					Printer.Continue();
					scanner.nextLine();
					break;
						
					case 6:
					Printer.Clear();
					try {
						WriteAnimalsToFile(new Aviary[] {aquarium, meshCoveredAviary, openAirAviary, aviaryWithInfraredLighting});
						config.WriteLog("logs.txt");
					} catch (IOException e) {
						config.ErrorLog("Write error!\n", "logs.txt");
					}
					Printer.Continue();
					scanner.next();
					break;

				case 7:
					Printer.Clear();
					try {
						Aviary[] aviaries = ReadFromFile();
						aquarium = aviaries[0];
						meshCoveredAviary = aviaries[1];
						openAirAviary = aviaries[2];
						aviaryWithInfraredLighting = aviaries[3];
						config.ReadLog("logs.txt");
					} catch (IOException e) {
						config.ErrorLog("Read error!\n", "logs.txt");
					}
					Printer.Continue();
					scanner.next();
					break;

				default:
					continue;
				}
			} catch (java.util.InputMismatchException e) {
				config.ErrorLog("Incorrect input\n", "logs.txt");
				scanner.nextLine();
				continue;
			}
		}
	}

	public static void main(String[] args) {
		Config config = new Config("config.ini");

		try {
			config.StartLog("logs.txt");
			MainMenu(config);
			config.EndLog("logs.txt");
		}
		catch (IOException e) {
			config.ErrorLog(e.getMessage(), "logs.txt");
		}
	}
}
