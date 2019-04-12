import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
	private static ArrayList<Director> Directors = new ArrayList<Director>();

	public static void main(String[] args) {
		Director director = new Director("Snyder", "01/01/1990");

		Directors.add(director);

		director.insertMovie("Liga da Justiça", "04/2018");

		try (Scanner scanner = new Scanner(System.in)) {
			final int mainMenuSelection = askUserForNumberInput(scanner, 3, true);
			cases(mainMenuSelection, scanner, null);
		}
	}

	private static int askUserForNumberInput(Scanner scanner, int maxValue, boolean start) {
		if (start) {
			System.out.println("/*********************************************/");
			System.out.println("  Sistema de cadastro de Diretores e Filmes");
			System.out.println("/*********************************************/");

			System.out.println("\n\n\n\nPor favor selecione uma das opções abaixo:");
			System.out.println("1 - Cadastrar Diretor");
			System.out.println("2 - Cadastrar Filme");
			System.out.println("3 - Verificar Diretor e Filme no Google");
		}

		int value = scanner.nextInt();
		while (value < 1 || value > maxValue) {
			System.out.println("Menu inválido, poor favor, tente novamente.");
			// java.util.InputMismatchException should also be caught
			// to intercept non-numeric input
			value = scanner.nextInt();
		}
		return value;
	}

	private static void cases(int mainMenuSelection, Scanner scanner, Director director) {
		switch (mainMenuSelection) {
		case 1: {
			// clearConsole();
			director = insertDirector(scanner);

			menu(1, director, 3);
			break;
		}
		case 2: {
			Movie movie = insertMovie(scanner, director);

			menu(2, director, 2);
			break;
		}
		case 3: {
			director = selectDirector(scanner);

			int movie = selectMovie(scanner, director);

			System.out.println("Diretor: " + director.getName());
			System.out.println("Filme: " + director.getMovies().get(movie).getName());

			try {
				writeFile(director, movie);
			} catch (IOException e) {
				e.printStackTrace();
			}

			Thread thread;
			thread = new Thread(new StartMaven());

			thread.start();
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			menu(3, director, 2);
			break;
		}
		case 4:
			try (Scanner scanner_out = new Scanner(System.in)) {
				final int mainMenuSelection_out = askUserForNumberInput(scanner_out, 3, true);
				cases(mainMenuSelection_out, scanner_out, null);
			}
		}
	}

	private static Director insertDirector(Scanner scanner) {
		scanner.nextLine();
		String name;
		System.out.println("Por favor o nome do Diretor:");
		name = scanner.nextLine();

		String birth;
		System.out.println("Por favor a data de nascimento do Diretor:");
		birth = scanner.nextLine();

		Director newDirector = new Director(name, birth);

		Directors.add(newDirector);

		System.out.println("Novo Diretor inserido:");
		System.out.println("Nome: " + newDirector.getName());
		System.out.println("Nascimento: " + newDirector.getBirth());

		System.out.println("\n------------------------------;");

		return newDirector;
	}

	private static Movie insertMovie(Scanner scanner, Director director) {
		if (director == null) {
			director = selectDirector(scanner);
		}

		scanner.nextLine();
		String name;
		System.out.println("Por favor o nome do Filme:");
		name = scanner.nextLine();

		String release_date;
		System.out.println("Por favor a data de lançamento do Filme:");
		release_date = scanner.nextLine();

		Movie newMovie = director.insertMovie(name, release_date);

		System.out.println("Novo Filme inserido:");
		System.out.println("Nome: " + newMovie.getName());
		System.out.println("Lançamento: " + newMovie.getreleaseDate());

		System.out.println("\n------------------------------;");

		return newMovie;
	}

	private static Director selectDirector(Scanner scanner) {
		System.out.println("Selecione um Diretor:");

		Director director;

		if (Directors.size() > 0) {
			for (int i = 0; i < Directors.size(); i++) {
				System.out.println((i + 1) + " - " + Directors.get(i).getName());
			}
			scanner.nextLine();
			int directorNumber;
			directorNumber = scanner.nextInt();

			director = Directors.get(directorNumber - 1);
		} else {
			System.out.println("Não há um Diretor, adicione um:");
			director = insertDirector(scanner);
		}

		return director;
	}

	private static int selectMovie(Scanner scanner, Director director) {
		System.out.println("Selecione um Diretor:");

		if (director.getMovies().size() > 0) {
			for (int i = 0; i < director.getMovies().size(); i++) {
				System.out.println((i + 1) + " - " + director.getMovies().get(i).getName());
			}
		} else {

		}
		scanner.nextLine();
		int directorNumber;
		directorNumber = scanner.nextInt();

		return (directorNumber - 1);
	}

	private static void menu(int choosed, Director director, int maxValue) {
		switch (choosed) {
		case 1:
			// clearConsole();
			System.out.println("Digite 1 caso queira adicionar um novo Diretor");
			System.out.println("Digite 2 caso queira adicionar um Filme a esse Diretor");
			System.out.println("Digite 3 caso queira sair");

			try (Scanner scanner = new Scanner(System.in)) {
				int mainMenuSelection = askUserForNumberInput(scanner, 3, false);

				if (mainMenuSelection == 3) {
					mainMenuSelection = 4;
				}
				cases(mainMenuSelection, scanner, director);
			}
			break;
		case 2:
			System.out.println("Digite 1 caso queira adicionar um novo Filme a esse Diretor");
			System.out.println("Digite 2 caso queira sair");
			try (Scanner scanner = new Scanner(System.in)) {
				int mainMenuSelection = askUserForNumberInput(scanner, 2, false);

				if (mainMenuSelection == 1) {
					mainMenuSelection = 2;
				} else if (mainMenuSelection == 2) {
					mainMenuSelection = 4;
				}

				cases(mainMenuSelection, scanner, director);
			}
			break;
		case 3:
			System.out.println("Digite 1 caso queira pesquisar outro Diretor e Filme");
			System.out.println("Digite 2 caso queira sair");

			try (Scanner scanner = new Scanner(System.in)) {
				int mainMenuSelection = askUserForNumberInput(scanner, 2, false);

				if (mainMenuSelection == 1) {
					mainMenuSelection = 3;
				} else if (mainMenuSelection == 2) {
					mainMenuSelection = 4;
				}

				cases(mainMenuSelection, scanner, director);
			}
		}
	}

	public static void writeFile(Director director, int movie) throws IOException {
		System.out.println("teste");
		String PATH = "../cucumber-java-skeleton/src/test/resources/io/cucumber/skeleton";
		String directoryName = PATH;
		String fileName = "search.feature";

		File directory = new File(directoryName);
		if (!directory.exists()) {
			directory.mkdir();
			// If you require it to make the entire directory path including parents,
			// use directory.mkdirs(); here instead.
		}

		File file = new File(directoryName + "/" + fileName);
		Files.deleteIfExists(file.toPath()); // surround it in try catch block

		file = new File(directoryName + "/" + fileName);
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("Feature: Alelo");
			bw.write("\n\nScenario: Search Director and Movie");
			bw.write("\n    Given I access google");
			bw.write("\n    When I search about \"" + director.getName() + "\" and your movie \""
					+ director.getMovies().get(movie).getName() + "\"");
			bw.write("\n    Then I recover the approximate amout of results");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public final static void clearConsole() {
		try {
			final String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				Runtime.getRuntime().exec("cls");
			} else {
				Runtime.getRuntime().exec("clear");
			}
		} catch (final Exception e) {
			// Handle any exceptions.
			e.printStackTrace();
		}
	}
}
