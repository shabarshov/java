package src;

public class Main
{
	public static void main(String[] args) {
		Waterfowl waterAnimal1 = new Waterfowl(15, 3);
		Waterfowl waterAnimal2 = new Waterfowl(12, 2);

		ColdBlooded coldBloodedAnimal = new ColdBlooded(12, 2);
		
		Aviary aquarium = new Aviary(Aviary.typesOfAviaries.aquarium);
		
		waterAnimal1.Move(aquarium);
		waterAnimal2.Move(aquarium);
		coldBloodedAnimal.Move(aquarium);
		
		aquarium.PrintAnimals();
	}
}