package com.skilldistillery.filmquery.app;

import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;

public class FilmQueryApp {

	Scanner sc = new Scanner(System.in);
	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		app.launch();
	}

//	private void test() {
//		Film film = db.findFilmById(1);
//		System.out.println(film);
//	}

	private void launch() {

		startUserInterface(sc);

		sc.close();
	}

	private void startUserInterface(Scanner input) {
		// menu

		boolean b = true;
		int choice = 0;

		while (b) {
			System.out.println(
					"Please select a number:\n1. Look up a film by its id.\n2. Look up a film by keyword.\n3. Quit");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Please enter an id number-->");
				int filmId = sc.nextInt();
				sc.nextLine();
				if(db.findFilmById(filmId) != null) {
				System.out.println(db.findFilmById(filmId));
				} else {
					System.out.println("No such film id, please try again.");
				}
				break;
			case 2:
				System.out.println("Please enter an keyword-->");
				String keyword = sc.next();
				sc.nextLine();
				if(db.findFilmByKeyword(keyword).isEmpty()) {
					System.out.println("No match was found, please try again.");
				}else {
					System.out.println(db.findFilmByKeyword(keyword));
				}
				break;
			case 3:
				System.exit(0);
				break;

			default:
				break;
			}

		}
		sc.close();
	}

}
