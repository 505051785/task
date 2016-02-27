package controller.cenum;

public enum TaskTypeEnum {

	Need("需求"), Bug("bug");
	private final String status;

	private TaskTypeEnum(String day) {
		this.status = day;
	}

	public static String resStatus(int i) {
		String result="";
		switch (i) {
		case 0:
			result = TaskTypeEnum.Need.getStatus();
			break;
		case 1:
			result = TaskTypeEnum.Bug.getStatus();
			break;
		}
		return result;
	}

	public String getStatus() {
		return status;
	}
}
