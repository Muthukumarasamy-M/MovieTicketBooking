package com.muthukumarasamy.movieticketbooking.bookticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.muthukumarasamy.movieticketbooking.dto.Booking;
import com.muthukumarasamy.movieticketbooking.dto.Movie;

public class BookTicketView {
	private static List<Movie> movies = new ArrayList<>();
	private static List<Booking> bookings = new ArrayList<>();
	private BookTicketViewModel bookticketviemodel;

	public BookTicketView() {
		bookticketviemodel = new BookTicketViewModel();

	}

	public void bookTicket() {
		System.out.println("--------------------------------\n");
		Scanner mc = new Scanner(System.in);
		System.out.print("Enter your UserName:  ");
		String userName = mc.nextLine();

		bookticketviemodel.displayMovies();
		System.out.print("Enter number of the movie :  ");
		int movieindex  = mc.nextInt();

		String movieTitle =  bookticketviemodel.getMovieByTitle(movieindex);
		System.out.println("--------------------------------");
		System.out.println("\nAvailable seats for " + movieTitle + ": ");
		bookticketviemodel.getSeats();

		System.out.print("Enter number of seats to book  : ");
		int num = mc.nextInt();
		mc.nextLine();
		String bookedSeat = "";
		System.out.println("Enter the seats no :  ");
		if (num > 1) {
			for (int i = 0; i < num; i++) {
				String cur = mc.nextLine();
				bookedSeat += cur + ",";
			}
		} else {
			bookedSeat = mc.nextLine();
		}
		bookticketviemodel.removeSeats(bookedSeat);
		Booking booking = new Booking(userName, movieTitle, bookedSeat);
		bookticketviemodel.addBookings(booking);

		System.out.println("Ticket booked successfully!");
	}

}