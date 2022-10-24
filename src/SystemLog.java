package src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class SystemLog {

	private String ret;

	private SystemLog() {
		ret = "";
	}

	private static class SystemLogHolder {
		private final static SystemLog SystemLog = new SystemLog();
	}

	public static SystemLog getWriteLog() {
		return SystemLogHolder.SystemLog;
	}

	public void addLog(String str) {
		ret += str + "\n";
	}

	public void writeLog() throws MyFileException {
		try {
			File file = new File("simulation.txt");
			file.delete();
			BufferedWriter fd = new BufferedWriter(new FileWriter(file, true));
			fd.write(this.ret);
			fd.close();
		} catch (Exception e) {
			throw new MyFileException("File creation failed.");
		}
	}
}
