import java.util.ArrayList;	
import java.util.LinkedList;
import java.util.Random;
import java.io.*;

public class Main {
	public static void WriteToFile(String value, String fileName, Boolean append) throws IOException{
		FileWriter writer = new FileWriter(fileName, append);
		writer.write(value);
		writer.close();
	}

	public static Integer GetRandomInt() {
		return new Random().nextInt(89) + 10;
	}

	public static Waterfowl CreateRandomWaterfowlAnimal() {
		return new Waterfowl(GetRandomInt(), GetRandomInt());
	}

	public static ColdBlooded CreateRandomColdBloodedAnimal() {
		return new ColdBlooded(GetRandomInt(), GetRandomInt());
	}

	public static Feathered CreateRandomFeatheredAnimal() {
		return new Feathered(GetRandomInt(), GetRandomInt());
	}

	public static Ungulates CreateRandomUngulatesAnimal() {
		return new Ungulates(GetRandomInt(), GetRandomInt());
	}

	public static Animal CreateRandomAnimal() {
		Integer animalType = new Random().nextInt(4);

		switch(animalType) {
			case 0:
				return CreateRandomWaterfowlAnimal();
			case 1:
				return CreateRandomColdBloodedAnimal();
			case 2:
				return CreateRandomFeatheredAnimal();
			case 3:
				return CreateRandomUngulatesAnimal();

			default:
				return CreateRandomWaterfowlAnimal();
		}
	}
 
	public static ArrayList<Animal> CreateAviaryList(Integer size, Config config) {
		ArrayList<Animal> animals = new ArrayList<>();

		Long startTime = System.nanoTime();

		for(int i = 0; i < size; i++) {
			Long startOperationTime = System.nanoTime();

			Animal newAnimal = CreateRandomAnimal();
			animals.add(CreateRandomAnimal());

			Long endOperationTime = System.nanoTime();

			String opeartionTime = Long.toString(endOperationTime - startOperationTime);
			String logValue = "Add, ID: " + Integer.toString(newAnimal.GetId()) + ", Time: " + opeartionTime;
			config.Log(logValue, "lab4/logs.txt");
		}
		
		Long endTime = System.nanoTime();

		String totalCountLog = "Total count: " + Integer.toString(size);
		String totalTimeLog = "Total time: " + Long.toString(endTime - startTime);
		String mediumTimeLog = "Medium time: " + Long.toString((endTime - startTime) / size) + "\n";

		config.Log(totalCountLog, "lab4/logs.txt");
		config.Log(totalTimeLog, "lab4/logs.txt");
		config.Log(mediumTimeLog, "lab4/logs.txt");

		config.Log("ArrayList Add\n", "lab4/results.txt");
		config.Log(totalCountLog, "lab4/results.txt");
		config.Log(totalTimeLog, "lab4/results.txt");
		config.Log(mediumTimeLog, "lab4/results.txt");

		return animals;
	}

	public static LinkedList<Animal> CreateAnimalLinkedList(Integer size, Config config) {
		LinkedList<Animal> animals = new LinkedList<>();

		Long startTime = System.nanoTime();

		for(int i = 0; i < size; i++) {
			Long startOperationTime = System.nanoTime();

			Animal newAnimal = CreateRandomAnimal();
			animals.add(CreateRandomAnimal());

			Long endOperationTime = System.nanoTime();

			String opeartionTime = Long.toString(endOperationTime - startOperationTime);
			String logValue = "Add, ID: " + Integer.toString(newAnimal.GetId()) + ", Time: " + opeartionTime;
			config.Log(logValue, "lab4/logs.txt");
		}
		
		Long endTime = System.nanoTime();

		String totalCountLog = "Total count: " + Integer.toString(size);
		String totalTimeLog = "Total time: " + Long.toString(endTime - startTime);
		String mediumTimeLog = "Medium time: " + Long.toString((endTime - startTime) / size) + "\n";

		config.Log(totalCountLog, "lab4/logs.txt");
		config.Log(totalTimeLog, "lab4/logs.txt");
		config.Log(mediumTimeLog, "lab4/logs.txt");

		config.Log("LinkedList Add\n", "lab4/results.txt");
		config.Log(totalCountLog, "lab4/results.txt");
		config.Log(totalTimeLog, "lab4/results.txt");
		config.Log(mediumTimeLog, "lab4/results.txt");

		return animals;
	}

	public static ArrayList<Animal> RemoveItemFromArrayList(ArrayList<Animal> arr, Config config) {
		Long startTime = System.nanoTime();

		Integer startSize = arr.size();
		for(int i = 0; i < startSize * 0.1; i++) {
			Integer index = new Random().nextInt(arr.size());
			Integer id = arr.get(index).GetId();

			Long startOperationTime = System.nanoTime();
			arr.remove(index);
			Long endOperationTime = System.nanoTime();

			String opeartionTime = Long.toString(endOperationTime - startOperationTime);
			String logValue = "Remove, ID: " + Integer.toString(id) + ", Time: " + opeartionTime;
			config.Log(logValue, "lab4/logs.txt");
		}
		
		Long endTime = System.nanoTime();

		String totalCountLog = "Total count: " + Double.toString(startSize * 0.1);
		String totalTimeLog = "Total time: " + Long.toString(endTime - startTime);
		String mediumTimeLog = "Medium time: " + Long.toString((endTime - startTime) / (int)(startSize * 0.1)) + "\n";

		config.Log(totalCountLog, "lab4/logs.txt");
		config.Log(totalTimeLog, "lab4/logs.txt");
		config.Log(mediumTimeLog, "lab4/logs.txt");

		config.Log("ArrayList Remove\n", "lab4/results.txt");
		config.Log(totalCountLog, "lab4/results.txt");
		config.Log(totalTimeLog, "lab4/results.txt");
		config.Log(mediumTimeLog, "lab4/results.txt");

		return arr;
	}

	public static LinkedList<Animal> RemoveItemFromLinkedList(LinkedList<Animal> list, Config config) {
		Long startTime = System.nanoTime();

		Integer startSize = list.size();

		for(int i = 0; i < startSize * 0.1; i++) {
			Integer index = new Random().nextInt(list.size());
			Integer id = list.get(index).GetId();

			Long startOperationTime = System.nanoTime();
			list.remove(index);
			Long endOperationTime = System.nanoTime();

			String opeartionTime = Long.toString(endOperationTime - startOperationTime);
			String logValue = "Remove, ID: " + Integer.toString(id) + ", Time: " + opeartionTime;
			config.Log(logValue, "lab4/logs.txt");
		}
		
		Long endTime = System.nanoTime();

		String totalCountLog = "Total count: " + Double.toString(startSize * 0.1);
		String totalTimeLog = "Total time: " + Long.toString(endTime - startTime);
		String mediumTimeLog = "Medium time: " + Long.toString((endTime - startTime) / (int)(startSize * 0.1)) + "\n";

		config.Log(totalCountLog, "lab4/logs.txt");
		config.Log(totalTimeLog, "lab4/logs.txt");
		config.Log(mediumTimeLog, "lab4/logs.txt");

		config.Log("LinkedList Remove\n", "lab4/results.txt");
		config.Log(totalCountLog, "lab4/results.txt");
		config.Log(totalTimeLog, "lab4/results.txt");
		config.Log(mediumTimeLog, "lab4/results.txt");

		return list;
	}

	public static void main(String[] args) {
		Integer countOfErrors = 0;
		Config config = new Config("config.ini");

		try {
			Integer[] sizes = {10, 100, 1000, 10000, 100000};

			for(Integer size : sizes) {
				ArrayList<Animal> animalsArray1 = CreateAviaryList(size, config);
				LinkedList<Animal> animalsLinkedList = CreateAnimalLinkedList(size, config);

				animalsArray1 = RemoveItemFromArrayList(animalsArray1, config);
				animalsLinkedList = RemoveItemFromLinkedList(animalsLinkedList, config);
			}
		} catch (Exception e) {
			config.ErrorLog(Integer.toString(countOfErrors) + e.getMessage(), "lab4/results.txt");
			countOfErrors += 1;
		}

		System.out.print("Count of errors: ");
		System.out.print(countOfErrors);
	}
}