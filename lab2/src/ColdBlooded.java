package src;
public class ColdBlooded extends Animal {
  public ColdBlooded(Integer weight, Integer age) {
    super(weight, age, Aviary.typesOfAviaries.aviariesWithInfraredLighting);
  }

  public void Move(Aviary aviary) {
    if(aviary.GetType() == Aviary.typesOfAviaries.aviariesWithInfraredLighting) {
      aviary.AddAnimal(this);
    }
    else {
      System.out.println("This animal cant move to this aviary");
    }
  }
}
