package com.muthukumarasamy.movieticketbooking.dto;

public class Booking {
	
	private String userName;
    private String movieTitle;
    private String bookedSeat;

    public Booking(String userName, String movieTitle, String bookedSeat) {
        this.userName = userName;
        this.movieTitle = movieTitle;
        this.bookedSeat = bookedSeat;
    }

    public String getUserName() {
        return userName;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getBookedSeat() {
        return bookedSeat;
    }
}
