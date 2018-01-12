package littleJ.hardware.dto;

public enum ItemStatus {
	ON(1), OFF(0), TOGGLE(2);
	int statusCode;

	private ItemStatus(int statusCode) {
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getStatusString() {
		switch (statusCode) {
		case 1:
			return "On";
		case 0:
			return "Off";
		case 2:
			return "Toggle";
		}
		
		return "Off";
	}

}
