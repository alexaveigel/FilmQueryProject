package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static String url = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	String user = "student";
	String pwd = "student";

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;

		String sql = "SELECT film.id, film.title, film.description, film.release_year, film.language_id, \n"
				+ "film.rental_duration, film.rental_rate, film.length, film.replacement_cost, film.rating, \n"
				+ "film.special_features, language.name "
				+ "FROM film JOIN language ON film.language_id = language.id \n" + "WHERE film.id = ?;";
		try {
			Connection conn = DriverManager.getConnection(url, user, pwd);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, filmId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				film = new Film(rs.getInt("film.id"), rs.getString("film.title"), rs.getString("film.description"),
						rs.getInt("film.release_year"), rs.getInt("film.language_id"),
						rs.getInt("film.rental_duration"), rs.getDouble("film.rental_rate"), rs.getInt("film.length"),
						rs.getDouble("film.replacement_cost"), rs.getString("film.rating"),
						rs.getString("film.special_features"), rs.getString("language.name"),
						findActorsByFilmId(rs.getInt("id")));
			}
			conn.close();
			if (film == null) {
				System.out.println("No such film id, please try again.");
				return film;
			}
			return film;

		} catch (SQLException e) {
			System.out.println(e);

			return new Film("");
		}

	}

	@Override
	public List<Film> findFilmByKeyword(String keyword) {
		List<Film> films = new ArrayList<Film>();

		String sql = "SELECT film.id, film.title, film.description, film.release_year, film.language_id, film.rental_duration, \n"
				+ "film.rental_rate, film.length, film.replacement_cost, film.rating, film.special_features, language.name "
				+ "FROM film JOIN language \n"
				+ "ON film.language_id = language.id "
				+ "WHERE film.title like ? or film.description like ?;";
		try {
			Connection conn = DriverManager.getConnection(url, user, pwd);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			ps.setString(2, "%" + keyword + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Film film = new Film(rs.getInt("film.id"), rs.getString("film.title"), rs.getString("film.description"),
						rs.getInt("film.release_year"), rs.getInt("film.language_id"),
						rs.getInt("film.rental_duration"), rs.getDouble("film.rental_rate"), rs.getInt("film.length"),
						rs.getDouble("film.replacement_cost"), rs.getString("film.rating"),
						rs.getString("film.special_features"), rs.getString("language.name"),
		 findActorsByFilmId(rs.getInt("film.id")));

				films.add(film);

			}
			conn.close();
			if (films.size() > 0) {
				return films;
			} else {
				System.out.println("No match was found, please try again.");
				return films;
			}

		} catch (SQLException e) {
			System.out.println(e);
			return films;
		}

	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pwd);
			String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setfName(rs.getString("first_name"));
				actor.setlName(rs.getString("last_name"));
				actor.setFilms(findFilmsByActorId(actorId)); // An Actor has Films
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("No such actor id, please try again.");
			return null;
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pwd);
			String sql = "SELECT actor.id, actor.first_name, actor.last_name \n" + "FROM actor JOIN film_actor \n"
					+ "ON actor.id = film_actor.actor_id\n" + "JOIN film \n" + "ON film_actor.film_id = film.id\n"
					+ "WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (actors == null) {
					actors = new ArrayList<>();
				}

				Actor actor = new Actor();

				actor.setId(rs.getInt("id"));
				actor.setfName(rs.getString("first_name"));
				actor.setlName(rs.getString("last_name"));
				actors.add(actor);

			}
			rs.close();
			stmt.close();
			conn.close();
			if (actors == null) {
				System.out.println("No such film id, please try again.");
			}
		} catch (SQLException e) {
			return null;
		}
		return actors;
	}

	public List<Film> findFilmsByActorId(int actorId) {
		List<Film> films = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pwd);
			String sql = "SELECT id, title, description, release_year, language_id, rental_duration\n"
					+ "rental_rate, length, replacement_cost, rating, special_features \n" + "FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (films == null) {
					films = new ArrayList<>();
				}

				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				String releaseYear = rs.getString("release_year");
				String langId = rs.getString("language_id");
				String rentDur = rs.getString("rental_duration");
				String rate = rs.getString("rental_rate");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");
//				List<Actor> cast = findActorsByFilmId(filmId);
//				
//				Film film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
//						features, cast);
//				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
			if (films == null) {
				System.out.println("No such actor id, please try again.");
			}
		} catch (SQLException e) {
		}
		return films;
	}

}
