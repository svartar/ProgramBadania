package application;

public class BloodPressure {
	
	private int high;
	private int low;
	private String date;
	
	public BloodPressure(int high, int low, String date) {
		
		if (high > 0 && high < 300 && low > 0 && low < 300 && date != null) {
			this.high = high;
			this.low = low;
			this.setDate(date);
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public int getHigh() {
		return high;
	}
	
	public void setHigh(int high) {
		if (high > 0 && high < 300) {
			this.high = high;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public int getLow() {
		return low;
	}
	
	public void setLow(int low) {
		if (low > 0 && low < 300) {
			this.low = low;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	

}
