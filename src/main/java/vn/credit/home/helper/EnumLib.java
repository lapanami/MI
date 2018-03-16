/**
 * 
 */
package vn.credit.home.helper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author an.nd
 *
 */
public class EnumLib {

	
	public static enum Period {
		DAILY(0), WEEK(7), MONTH(1);
		private int val;

		private Period(int i) {
			this.val = i;
		}

		public int getVal() {
			return this.val;
		}
	}
	
	
	public static enum RunStatus {
		FAILED(0), RUNNING(7), FINISHED(1);
		private int val;

		private RunStatus(int i) {
			this.val = i;
		}

		public int getVal() {
			return this.val;
		}
	}
	
	public static enum ReportStatus {
		INACTIVE(0), ACTIVE(1);
		private int val;

		private ReportStatus(int i) {
			this.val = i;
		}

		public int getVal() {
			return this.val;
		}
	}
	
	public static enum ReportSource {
		NEWHDWVN(0), RCHS2(1),DMS(2);
		private int val;

		private ReportSource(int i) {
			this.val = i;
		}

		public int getVal() {
			return this.val;
		}
	}


	/**
	 * @author an.nd
	 * @Desciption 
	 * @Date 19.08.2017
	 */
	public static List<String> getListPeriod() {
		List<String> liStatusModels = new ArrayList<String>();
		liStatusModels.add(Period.DAILY.toString());
		liStatusModels.add(Period.WEEK.toString());
		liStatusModels.add(Period.MONTH.toString());
		return liStatusModels;
	}
	
	public static List<String> getRunStatus() {
		List<String> liStatusModels = new ArrayList<String>();
		liStatusModels.add(RunStatus.FAILED.toString());
		liStatusModels.add(RunStatus.RUNNING.toString());
		liStatusModels.add(RunStatus.FINISHED.toString());
		return liStatusModels;
	}
	
	public static List<String> getListReportStatus() {
		List<String> liStatusModels = new ArrayList<String>();
		liStatusModels.add(ReportStatus.INACTIVE.toString());
		liStatusModels.add(ReportStatus.ACTIVE.toString());
		return liStatusModels;
	}
	
	public static List<String> getListReportSource() {
		List<String> liStatusModels = new ArrayList<String>();
		liStatusModels.add(ReportSource.NEWHDWVN.toString());
		liStatusModels.add(ReportSource.RCHS2.toString());
		liStatusModels.add(ReportSource.DMS.toString());
		return liStatusModels;
	}
}
