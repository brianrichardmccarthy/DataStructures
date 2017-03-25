import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {

	private Heap<DictionaryItem> dictionary;
	private Scanner input;

	public Driver() {
		dictionary = new Heap<>();
		input = new Scanner(System.in);
		init();
		menu();
	}

	private void init() {
		String file = "./src/SpanishWords.txt";
		Scanner fileReader = null;
		String delim = "\\t";
		try {
			fileReader = new Scanner(new File(file));
			while (fileReader.hasNextLine()) {
				String[] newLine = fileReader.nextLine().split(delim);
				if (newLine.length == 2) {
					dictionary.add(new DictionaryItem((newLine[0].trim()).substring(0, newLine[0].length() - 1),
							newLine[1].trim()));
				}
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Size: " + dictionary.getSize());
	}

	private void menu() {
		int choice = 4;
		do {
			System.out.println("Spanish to English dicitonary");
			System.out.println("-----------------------------");
			System.out.println("1. To print all spanish and english words");
			System.out.println("2. To add a new spanish to english word");
			System.out.println("3. To search for a english translation");
			System.out.println("4. To quit");
			System.out.print("Choice: ");

			choice = input.nextInt();

			switch (choice) {
			case 1:
				print();
				break;
			case 2:
				addWord();
				break;
			case 3:
				searchWord();
				break;
			case 4:
				break;
			}

		} while (choice != 4);

	}

	private void searchWord() {
		input.nextLine();
		System.out.print("Enter spanish word: ");
		String spanish = input.nextLine();
		try {
			System.out.println("English word: "
					+ dictionary.breadthFirstSearch(new DictionaryItem(spanish, null)).getEnglishWord());
		} catch (NullPointerException e) {
			System.out.println("Sorry the spanish word for '" + spanish + "' is not in the dictionary");
		}
	}

	private void print() {
		System.out.println(dictionary);
	}

	private void addWord() {
		input.nextLine();
		System.out.print("Enter spanish word: ");
		String spanish = input.nextLine();
		System.out.print("Enter english word: ");
		String english = input.nextLine();
		if (!spanish.equals(null) && !english.equals(null)) {
			dictionary.add(new DictionaryItem(spanish, english));
		}
	}

	public static void main(String args[]) {
		new Driver();
	}

}
