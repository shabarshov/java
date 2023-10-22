public class Waterfowl extends Animal {
  public Waterfowl(Integer weight, Integer age, Integer id) {
    super(weight, age, Aviary.typesOfAviaries.aquarium, id);
  }

  public Waterfowl(Integer weight, Integer age) {
    super(weight, age, Aviary.typesOfAviaries.aquarium);
  }

  public void Move(Aviary aviary) {
    if(aviary.GetType() == Aviary.typesOfAviaries.aquarium) {
      aviary.AddAnimal(this);
    }
    else {
      System.out.println("This animal cant move to this aviary");
    }
  }
}
