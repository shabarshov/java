import java.io.*;
import java.util.Objects;
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

	public static Aviary[] ReadFromFile(String fileName) throws IOException {
		Aviary aquarium = new Aviary(Aviary.typesOfAviaries.aquarium);
		Aviary meshCoveredAviary = new Aviary(Aviary.typesOfAviaries.meshCoveredAviaries);
		Aviary openAirAviary = new Aviary(Aviary.typesOfAviaries.openAirAviaries);
		Aviary aviaryWithInfraredLighting = new Aviary(Aviary.typesOfAviaries.aviariesWithInfraredLighting);

		Scanner fileScanner = new Scanner(new File(fileName));
		while (fileScanner.hasNextLine()) {
			String[] line = fileScanner.nextLine().split(" ");

			if(Objects.equals(line[1], aquarium.GetType().name())) {
				Waterfowl newAnimal = new Waterfowl(Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[0]));
				newAnimal.Move(aquarium);
			} else if (Objects.equals(line[1], meshCoveredAviary.GetType().name())) {
				Feathered newAnimal = new Feathered(Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[0]));
				newAnimal.Move(meshCoveredAviary);
			} else if (Objects.equals(line[1], openAirAviary.GetType().name())) {
				Ungulates newAnimal = new Ungulates(Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[0]));
				newAnimal.Move(openAirAviary);
			} else if (Objects.equals(line[1], aviaryWithInfraredLighting.GetType().name())) {
				ColdBlooded newAnimal = new ColdBlooded(Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[0]));
				newAnimal.Move(aviaryWithInfraredLighting);
			}
		}

		fileScanner.close();
		return new Aviary[] { aquarium, meshCoveredAviary, openAirAviary, aviaryWithInfraredLighting };
	}

	public static void WriteToFile(String value, String fileName, Boolean append) throws IOException{
		FileWriter writer = new FileWriter(fileName, append);
		writer.write(value);
		writer.close();
	}

	public static void WriteAnimalsToFile(Aviary[] aviaries, Boolean append) throws IOException {
		FileWriter writer = new FileWriter("data-base.txt", append);
		for (int i = 0; i < aviaries.length; i++) {
			Animal[] animals = aviaries[i].GetAnimals();
			
			for (int j = 0; j < animals.length; j++) {
				if(i == 0 && j == 0 && append.booleanValue() == false) {
					writer.write(animals[j] + "\n");
				} else {
					writer.write(animals[j] + "\n");
				}
			}
		}
		writer.close();
	}

	public static void MainMenu(Config config) throws IOException{
		Scanner scanner = new Scanner(System.in);

		Printer.Clear();
		System.out.println("Welcome, " + config.GetUsername() + "!");
		Printer.Continue();
		scanner.next();

		Aviary fileAquarium = new Aviary(Aviary.typesOfAviaries.aquarium);
		Aviary fileMeshCoveredAviary = new Aviary(Aviary.typesOfAviaries.meshCoveredAviaries);
		Aviary fileOpenAirAviary = new Aviary(Aviary.typesOfAviaries.openAirAviaries);
		Aviary fileAviaryWithInfraredLighting = new Aviary(Aviary.typesOfAviaries.aviariesWithInfraredLighting);

		try {
			Aviary[] aviaries = ReadFromFile("data-base.txt");
			fileAquarium = aviaries[0];
			fileMeshCoveredAviary = aviaries[1];
			fileOpenAirAviary = aviaries[2];
			fileAviaryWithInfraredLighting = aviaries[3];
		} catch (IOException e) {
			config.ErrorLog("Read error!", "logs.txt");
		}

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
						config.ErrorLog("Incorrect input", "logs.txt");
					}

					Printer.Continue();
					scanner.nextLine();
					break;
						
				case 6:
					Boolean isDataBaseMenuOpen = true;
					
					while(isDataBaseMenuOpen) {
						Printer.Clear();
						Printer.DataBaseMenu();
						Integer dataBaseCommand = scanner.nextInt();
						Integer id;
						Integer newWeight;
						Integer newAge;

						switch (dataBaseCommand) {
						case 0:
							isDataBaseMenuOpen = false;
							break;

						case 1:
							Printer.Clear();
							try {
								WriteAnimalsToFile(new Aviary[] {fileAquarium, fileMeshCoveredAviary, fileOpenAirAviary, fileAviaryWithInfraredLighting}, false);
								WriteAnimalsToFile(new Aviary[] {aquarium, meshCoveredAviary, openAirAviary, aviaryWithInfraredLighting}, true);
								config.WriteLog("logs.txt");
							} catch (IOException e) {
								config.ErrorLog("Write error!", "logs.txt");
							}
							Printer.Continue();
							scanner.next();
							break;

						case 2:
							Printer.Clear();
							try {
								Aviary[] aviaries = ReadFromFile("data-base.txt");
								aquarium = aviaries[0];
								meshCoveredAviary = aviaries[1];
								openAirAviary = aviaries[2];
								aviaryWithInfraredLighting = aviaries[3];
								config.ReadLog("logs.txt");
							} catch (IOException e) {
								config.ErrorLog("Read error!", "logs.txt");
							}
							Printer.Continue();
							scanner.next();
							break;

						case 3:
							System.out.println("Aquarium: ");
							fileAquarium.PrintAnimals();
							System.out.println("Mesh covered aviary: ");
							fileMeshCoveredAviary.PrintAnimals();
							System.out.println("Open air aviary: ");
							fileOpenAirAviary.PrintAnimals();
							System.out.println("Aviary with infrared lighting: ");
							fileAviaryWithInfraredLighting.PrintAnimals();

							Printer.EnterId();
							id = scanner.nextInt();

							fileAquarium.DeleteAnimal(id);
							fileMeshCoveredAviary.DeleteAnimal(id);
							fileOpenAirAviary.DeleteAnimal(id);
							fileAviaryWithInfraredLighting.DeleteAnimal(id);

							Printer.Continue();
							scanner.next();
							break;

						case 4:
							System.out.println("Aquarium: ");
							fileAquarium.PrintAnimals();
							System.out.println("\nMesh covered aviary: ");
							fileMeshCoveredAviary.PrintAnimals();
							System.out.println("\nOpen air aviary: ");
							fileOpenAirAviary.PrintAnimals();
							System.out.println("\nAviary with infrared lighting: ");
							fileAviaryWithInfraredLighting.PrintAnimals();

							Printer.EnterId();
							id = scanner.nextInt();

							Printer.EnterWeight();
							newWeight = scanner.nextInt();

							Printer.EnterAge();
							newAge = scanner.nextInt();

							fileAquarium.UpdateAnimal(id, newWeight, newAge);
							fileMeshCoveredAviary.DeleteAnimal(id);
							fileOpenAirAviary.DeleteAnimal(id);
							fileAviaryWithInfraredLighting.DeleteAnimal(id);

							Printer.Continue();
							scanner.next();
							break;

						default:
							config.ErrorLog("Incorrect input", "logs.txt");
							scanner.nextLine();
							continue;
					}
				}

				default:
					config.ErrorLog("Incorrect input", "logs.txt");
					scanner.nextLine();
					continue;
				}
			} catch (java.util.InputMismatchException e) {
				config.ErrorLog("Incorrect input", "logs.txt");
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
