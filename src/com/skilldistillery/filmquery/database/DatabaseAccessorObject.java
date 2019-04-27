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

		return null;
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
		// TODO Auto-generated method stub
		List<Actor> actors = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pwd);
			String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
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
				System.out.println("No such actor id, please try again.");
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
			String sql = "SELECT id, title, description, release_year, language_id, rental_duration, ";
			sql += " rental_rate, length, replacement_cost, rating, special_features "
					+ " FROM film JOIN film_actor ON film.id = film_actor.film_id " + " WHERE actor_id = ?";
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
				Film film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features);
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
			if (films == null) {
				System.out.println("No such film id, please try again.");
			}
		} catch (SQLException e) {
		}
		return films;
	}

}
