package src;
public class Ungulates extends Animal {
  public Ungulates(Integer weight, Integer age) {
    super(weight, age, Aviary.typesOfAviaries.openAirAviaries);
  }

  public void Move(Aviary aviary) {
    if(aviary.GetType() == Aviary.typesOfAviaries.openAirAviaries) {
      aviary.AddAnimal(this);
    }
    else {
      System.out.println("This animal cant move to this aviary");
    }
  }
}
