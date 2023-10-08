public class Printer {
  public static void Clear() {
		System.out.print("\033[H\033[J");
	}

  public static void Continue() {
    System.out.println("Press any key to exit");
  }

  public static void MainMenu() {
		Clear();
		System.out.println("0 - Exit");
		System.out.println("1 - Print animals from aquarium");
		System.out.println("2 - Print animals from mesh covered aviary");
		System.out.println("3 - Print animals from open-air aviary");
		System.out.println("4 - Print animals from aviary with infrared lighting");
		System.out.println("5 - Add new animal");
    System.out.println("6 - Read animals from data base");
    System.out.println("7 - Write animals to data base");
		System.out.print("Your command: ... ");
	}

  public static void NewAnimalType() {
    System.out.println("Select the type of animal: ");
    System.out.println("1 - Waterfowl");
    System.out.println("2 - Feathered");
    System.out.println("3 - Ungulates");
    System.out.println("4 - Cold blooded");
    System.out.print("Your command: ... ");
  }

  public static void NewAnimalWeight() {
    System.out.print("Weight of the animal: ... ");
  }

  public static void NewAnimalAge() {
    System.out.print("Age of the animal: ... ");
  }

  public static void InputError() {
    System.out.println("Input error. Try again");
    Continue();
  }
}
