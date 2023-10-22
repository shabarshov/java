import java.util.Random;

public abstract class Animal {
  protected Integer weight;
  protected Integer age;
  protected Aviary.typesOfAviaries type;
  protected Integer id;

  public Animal(Integer weight, Integer age, Aviary.typesOfAviaries type, Integer id) {
    this.weight = weight;
    this.age = age;
    this.type = type;
    this.id = id;
  }

  public Animal(Integer weight, Integer age, Aviary.typesOfAviaries type) {
    this.weight = weight;
    this.age = age;
    this.type = type;
    this.id = new Random().nextInt(10000) + 1000;
  }

  public Animal () {
    this.weight = null;
    this.age = null;
    this.type = null;
    this.id = null;
  }

  public abstract void Move(Aviary place);

  public Integer GetAge() {
    return this.age;
  }

  public Integer GetWeight() {
    return this.weight;
  }

  public Integer GetId() {
    return this.id;
  }

  public Aviary.typesOfAviaries GetType() {
    return this.type;
  }

  public void SetType(Aviary.typesOfAviaries type) {
    this.type = type;
  }

  public void SetAge(Integer age) {
    this.age = age;
  }

  public void SetWeght(Integer weight) {
    this.weight = weight;
  }

  @Override
  public String toString() {
      return Integer.toString(this.id) + " " + type.name() + " " + Integer.toString(this.weight) + " " + Integer.toString(this.age);
  }
}
