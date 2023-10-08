import java.io.*;
import java.util.Scanner;

public class Main
{
	public static Animal AddNewAnimal() {
		while(true) {
			Scanner scanner = new Scanner(System.in);

			Printer.Clear();
			Printer.NewAnimalType();
			Integer typeNumber = scanner.nextInt();

			Printer.Clear();
			Printer.NewAnimalWeight();
			Integer weight = scanner.nextInt();

			Printer.Clear();
			Printer.NewAnimalAge();
			Integer age = scanner.nextInt();

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

	public static void MainMenu() {
		Scanner scanner = new Scanner(System.in);

		Aviary aquarium = new Aviary(Aviary.typesOfAviaries.aquarium);
		Aviary meshCoveredAviary = new Aviary(Aviary.typesOfAviaries.meshCoveredAviaries);
		Aviary openAirAviary = new Aviary(Aviary.typesOfAviaries.openAirAviaries);
		Aviary aviaryWithInfraredLighting = new Aviary(Aviary.typesOfAviaries.aviariesWithInfraredLighting);

		while(true) {
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
					Animal newAnimal = AddNewAnimal();

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

					Printer.Continue();
					scanner.next();
					break;
						
					case 6:
					
						break;

					case 7:

						break;

				default:
					continue;
			}
		}
	}

	public static void main(String[] args) {
		MainMenu();
	}
}
