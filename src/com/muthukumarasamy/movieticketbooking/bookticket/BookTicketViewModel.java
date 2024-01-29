package com.muthukumarasamy.movieticketbooking.bookticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.muthukumarasamy.movieticketbooking.dto.Booking;
import com.muthukumarasamy.movieticketbooking.dto.Movie;
import com.muthukumarasamy.movieticketbooking.repository.Repository;

class BookTicketViewModel {

	private BookTicketView bookticketview;
	private static List<Movie> movies = Repository.getInstance().getMovies();

	private static List<List<String>> data = new ArrayList<>();
	private static Movie movie;

	public void displayMovies() {
		System.out.println("\t+---+=======================+");
		System.out.println("\t|   |  Available movies:    |");
		System.out.println("\t+---+-----------------------+");
		int index = 0;
		for (Movie movie : movies) {
			System.out.printf("\t| %-2s| %-21s |\n", ++index, movie.getTitle());
		}
		System.out.println("\t+---+=======================+");

	}

	public String getMovieByTitle(int movieTitle) {
		Movie moviee = movies.get(movieTitle - 1);
		data = moviee.getSeats();
		movie = moviee;

		return movie.getTitle();
	}

	public void getSeats() {

		System.out.println("\t+-----------------------------+");
		for (List<String> details : data) {
			System.out.print("\t| ");

			int halfSize = details.size() / 2;

			for (int i = 0; i < details.size(); i++) {
				System.out.printf("%-3s", details.get(i) + " ");
				if (i == halfSize - 1) {
					System.out.print("|  |");
				}

			}
			System.out.print("|\n");
		}
		System.out.println("\t+-----------------------------+");

	}

	public void removeSeats(String bookedSeat) {
		List<String> seats = Arrays.asList(bookedSeat.split(","));

		for (List<String> row : data) {
			for (int i = 0; i < row.size(); i++) {
				if (seats.contains(row.get(i))) {
					row.set(i, "0");
				}
			}
		}

		movie.setSeats(data);
		Repository.getInstance().saveDataToJson();

	}

	public void addBookings(Booking booking) {
		Repository.getInstance().addBooking(booking);
	}

}
