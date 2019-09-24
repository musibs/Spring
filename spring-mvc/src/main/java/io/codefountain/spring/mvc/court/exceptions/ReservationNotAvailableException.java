package io.codefountain.spring.mvc.court.exceptions;

public class ReservationNotAvailableException extends RuntimeException {
	
	private static final long serialVersionUID = -2371373633107171506L;
	
	private String courtName;
	
	public ReservationNotAvailableException(String cortName) {
		this.courtName = cortName;
	}

	public String getCourtName() {
		return courtName;
	}

}
