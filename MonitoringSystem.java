import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class MonitoringSystem {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		while (true) {
			
			System.out.println("Welcome to Zoo Monitoring System");
			System.out.println("1.Monitor animal");
			System.out.println("2.Monitor habitat");
			System.out.println("3.Exit");
			System.out.println("Enter your choice: ");
			int option = Integer.parseInt(input.nextLine());

			switch (option) {
			case 1: // Animal
				System.out.println("1.Animal - Lion");
				System.out.println("2.Animal - Tiger");
				System.out.println("3.Animal - Bear");
				System.out.println("4.Animal - Giraffe");

				System.out.println("Select Animal: ");
				int choice = Integer.parseInt(input.nextLine());
				String animal = "";
				if (choice == 1)
					animal = "Animal - Lion";
				else if (choice == 2)
					animal = "Animal - Tiger";
				else if (choice == 3)
					animal = "Animal - Bear";
				else if (choice == 4)
					animal = "Animal - Giraffe";

				try {
					//opening animals.txt file
					// Change this link to the link on your computer that contains this file
					Scanner infile = new Scanner(new File("/Users/fjyfly/eclipse-workspace/04173/src/animal.txt"));
					//search the file to locate animal
					while (infile.hasNextLine()) {
						if (animal.equals(infile.nextLine()))
							break;
					}
					//read the remaining details
					String name = infile.nextLine();
					String age = infile.nextLine();
					String health = infile.nextLine();
					String feed = infile.nextLine();
					infile.close();
					//issue warnings if any problem arises
					if (health.contains("*****")) 
						JOptionPane.showMessageDialog(null, health.replace("*", ""), "Warning : " + animal,
								JOptionPane.INFORMATION_MESSAGE);
					else if (feed.contains("*****")) 
						JOptionPane.showMessageDialog(null, feed.replace("*", ""), "Warning : " + animal,
								JOptionPane.INFORMATION_MESSAGE);
				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 2: // habitats
				System.out.println("1.Habitat - Penguin ");
				System.out.println("2.Habitat - Bird");
				System.out.println("3.Habitat - Aquarium");
				System.out.println("Select Habitat: ");
				choice = Integer.parseInt(input.nextLine());
				String habitat = "";
				if (choice == 1)
					habitat = "Habitat - Penguin";
				else if (choice == 2)
					habitat = "Habitat - Bird";
				else if (choice == 3)
					habitat = "Habitat - Aquarium";

				//read the file
				try {
					// Change this link to the link on your computer that contains this file
					Scanner infile = new Scanner(new File("/Users/fjyfly/eclipse-workspace/04173/src/habitat.txt"));
					//search the file to locate the habitat
					while (infile.hasNextLine()) {
						if (habitat.equals(infile.nextLine()))
							break;
					}
					//read remaining details of habitat
					String temperature = infile.nextLine();
					String food = infile.nextLine();
					String cleanliness = infile.nextLine();

					//Checking if temperature, food or cleaniness contains any concerns
					if (temperature.contains("*****"))
						JOptionPane.showMessageDialog(null, temperature.replace("*", ""), "Warning : " + habitat,
								JOptionPane.INFORMATION_MESSAGE);
					else if (food.contains("*****"))
						JOptionPane.showMessageDialog(null, food.replace("*", ""), "Warning : " + habitat,
								JOptionPane.INFORMATION_MESSAGE);
					else if (cleanliness.contains("*****"))
						JOptionPane.showMessageDialog(null, cleanliness.replace("*", ""), "Warning : " + habitat,
								JOptionPane.INFORMATION_MESSAGE);
					;
				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.exit(0); // quit
			}
		}
	}
}