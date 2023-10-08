public class Feathered extends Animal {
  public Feathered(Integer weight, Integer age) {
    super(weight, age, Aviary.typesOfAviaries.meshCoveredAviaries);
  }

  public void Move(Aviary aviary) {
    if(aviary.GetType() == Aviary.typesOfAviaries.meshCoveredAviaries) {
      aviary.AddAnimal(this);
    }
    else {
      System.out.println("This animal cant move to this aviary");
    }
  }
}
