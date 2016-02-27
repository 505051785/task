package controller.cenum;

public enum ExcuteStatusEnum {

	Excuting("执行中"), End("完成");
	private final String status;

	private ExcuteStatusEnum(String day) {
		this.status = day;
	}

	public static String resStatus(int i) {
		String result="";
		switch (i) {
		case 0:
			result = ExcuteStatusEnum.Excuting.getStatus();
			break;
		case 1:
			result = ExcuteStatusEnum.End.getStatus();
			break;
		}
		return result;
	}

	public String getStatus() {
		return status;
	}
}
