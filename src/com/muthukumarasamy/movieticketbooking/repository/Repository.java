package com.muthukumarasamy.movieticketbooking.repository;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.muthukumarasamy.movieticketbooking.dto.Booking;
import com.muthukumarasamy.movieticketbooking.dto.Movie;

public class Repository {
	private static final String MOVIE = "C:\\Users\\ramki\\git\\ConsoleProjects\\src\\com\\muthukumarasamy\\movieticketbooking\\files\\MOVIE.json";
	private static final String BOOKING = "C:\\Users\\ramki\\git\\ConsoleProjects\\src\\com\\muthukumarasamy\\movieticketbooking\\files\\BOOKING.json";
	private static List<Movie> movies = new ArrayList<>();
	private static List<Booking> bookings = new ArrayList<>();
	private static Repository repository = null;

	public Repository() {
		loadMoviesFromJson();
		loadBookingsFromJson();

	}

	public static Repository getInstance() {
		if (repository == null)
			repository = new Repository();
		return repository;
	}

	public void loadMoviesFromJson() {
		try {
			String content = new String(Files.readAllBytes(Paths.get(MOVIE)));
			JSONArray jsonArray = new JSONArray(content);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				String title = jsonObject.getString("title");
				List<List<String>> seats = new ArrayList<>();

				JSONArray seatsArray = jsonObject.getJSONArray("seats");
				for (int j = 0; j < seatsArray.length(); j++) {
					JSONArray seatGroupArray = seatsArray.getJSONArray(j);

					List<String> seatGroup = new ArrayList<>();
					for (int k = 0; k < seatGroupArray.length(); k++) {
						seatGroup.add(seatGroupArray.getString(k));
					}

					seats.add(seatGroup);
				}

				movies.add(new Movie(title, seats));
			}

		} catch (IOException e) {
			System.out.println("Error loading movies from JSON: " + e.getMessage());
		}
	}

	public static void loadBookingsFromJson() {
		try {
			String content = new String(Files.readAllBytes(Paths.get(BOOKING)));
			JSONArray jsonArray = new JSONArray(content);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				String userName = jsonObject.getString("userName");
				String movieTitle = jsonObject.getString("movieTitle");
				String bookedSeat = jsonObject.getString("bookedSeat");

				bookings.add(new Booking(userName, movieTitle, bookedSeat));
			}

		} catch (IOException e) {
			System.out.println("Error loading bookings from JSON: " + e.getMessage());
		}
	}

	public void saveDataToJson() {
		JSONArray moviesArray = new JSONArray();

		for (Movie movie : movies) {
			JSONObject movieObject = new JSONObject();
			movieObject.put("title", movie.getTitle());

			JSONArray seatsArray = new JSONArray();

			for (List<String> seatGroup : movie.getSeats()) {
				JSONArray groupArray = new JSONArray();
				for (String seat : seatGroup) {
					groupArray.put(seat);
				}
				seatsArray.put(groupArray);
			}

			movieObject.put("seats", seatsArray);
			moviesArray.put(movieObject);
		}

		try (FileWriter fileWriter = new FileWriter(MOVIE)) {
			fileWriter.write(moviesArray.toString());
		} catch (IOException e) {
			System.out.println("Error saving data to JSON: " + e.getMessage());
		}
	}

	public static void saveBookingsToJson() {
		JSONArray jsonArray = new JSONArray();

		for (Booking booking : bookings) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("userName", booking.getUserName());
			jsonObject.put("movieTitle", booking.getMovieTitle());
			jsonObject.put("bookedSeat", booking.getBookedSeat());

			jsonArray.put(jsonObject);
		}
		try {
			Path filePath = Paths.get(BOOKING);
			Files.write(filePath, jsonArray.toString().getBytes());
		} catch (IOException e) {
			System.out.println("Error saving bookings to JSON: " + e.getMessage());
		}
	}

	public List<Movie> getMovies() {

		return movies;
	}

	public List<Booking> getBookings() {

		return bookings;
	}

	public void addBooking(Booking booking2) {
		bookings.add(booking2);
		saveBookingsToJson();
	}

	public void removeBooking(int index) {
		bookings.remove(index);
		saveBookingsToJson();
	}
	public void replaceMovie(List<Movie> data) {

		movies = data;
		saveDataToJson();
	}

}
