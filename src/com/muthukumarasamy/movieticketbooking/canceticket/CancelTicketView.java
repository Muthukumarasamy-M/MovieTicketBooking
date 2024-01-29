package com.muthukumarasamy.movieticketbooking.canceticket;

import java.util.Scanner;

public class CancelTicketView {
	private CancelTicketViewModel cancelticketviewmodel;

	public CancelTicketView() {
		cancelticketviewmodel = new CancelTicketViewModel();
	}

	public void cancelTicket() {
		Scanner mc = new Scanner(System.in);
		System.out.print("\nEnter the userName    : ");
		String name = mc.nextLine();
		System.out.print("Enter the movie title : ");
		String movietitle = mc.nextLine();
		int num = cancelticketviewmodel.cancelTicket(name, movietitle);
		if (num != -1)
			cancelticketviewmodel.getSeats();
	}

}
