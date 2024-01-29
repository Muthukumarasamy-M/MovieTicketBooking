package com.muthukumarasamy.movieticketbooking.canceticket;

import java.util.Arrays;
import java.util.List;

import com.muthukumarasamy.movieticketbooking.dto.Booking;
import com.muthukumarasamy.movieticketbooking.dto.Movie;
import com.muthukumarasamy.movieticketbooking.repository.Repository;

class CancelTicketViewModel {


	private static List<Booking> booking = Repository.getInstance().getBookings();
	private static List<Movie> data = Repository.getInstance().getMovies();

	private static Booking books;

	public int cancelTicket(String name, String movietitle) {
		int index = -1;
		for (Booking book : booking) {
			if (book.getMovieTitle().equals(movietitle) && book.getUserName().equals(name)) {
				index = booking.indexOf(book);
				books = book;
			}
		}
		if (index == -1) {
			System.out.println("NO such bookings");
		} else {
			Repository.getInstance().removeBooking(index);
		}
		return index;

	}

	public void getSeats() {
		for (Movie movie : data) {
			if (movie.getTitle().equals(books.getMovieTitle())) {
				movie.setSeats(removeTicktet(movie.getSeats()));
			}
		}
		Repository.getInstance().replaceMovie(data);

	}

	private List<List<String>> removeTicktet(List<List<String>> seats) {
		List<String> selected = Arrays.asList(books.getBookedSeat().split(","));
		for (String string : selected) {
			int row = string.charAt(0) - 'a';
			int col = string.charAt(1) - '1';

			List<String> inner = seats.get(row);
			inner.set(col, string);
			seats.set(row, inner);
		}
		System.out.println("  " + selected + " seats have been restocked");

		return seats;
	}
}