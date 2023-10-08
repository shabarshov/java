public abstract class Animal {
  protected Integer weight;
  protected Integer age;
  protected Aviary.typesOfAviaries type;

  public Animal(Integer weight, Integer age, Aviary.typesOfAviaries type) {
    this.weight = weight;
    this.age = age;
    this.type = type;
  }

  public abstract void Move(Aviary place);

  public Integer GetAge() {
    return this.age;
  }

  public Integer GetWeight() {
    return this.weight;
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
}
