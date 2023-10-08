public class Aviary {
  public enum typesOfAviaries {
    undefined,
    aquarium,
    meshCoveredAviaries, 
    openAirAviaries, 
    aviariesWithInfraredLighting
  };

  private Animal[] animals;
  private typesOfAviaries type;

  public Aviary(typesOfAviaries type) {
    this.animals = new Animal[0];
    this.type = type;
  }

  public Animal[] GetAnimals() {
    return this.animals;
  }
  
  public typesOfAviaries GetType() {
    return this.type;
  }

  public void AddAnimal(Animal animal) {
    Animal[] newAnimals = new Animal[this.animals.length + 1];

    for (int i = 0; i < this.animals.length; i++) {
      newAnimals[i] = this.animals[i];
    }

    newAnimals[this.animals.length] = animal;
    this.animals = newAnimals;
  }

  public void PrintAnimals() {
    if(this.animals.length == 0) {
      System.out.println("There are no animals :(");
    }
    
    for (int i = 0; i < this.animals.length; i++) {
      System.out.print("Animal №" + Integer.toString(i + 1) + ": ");
			System.out.println(animals[i]);
		}
  }
}
