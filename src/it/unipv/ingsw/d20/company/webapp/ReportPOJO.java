package it.unipv.ingsw.d20.company.webapp;

/**
 * Conenitore di informazioni relative a un report.
 *
 */
public class ReportPOJO {
	private String vendingID;
	private String problem;
	private String details;
	private String operatorName;

	public ReportPOJO(String vendingID, String problem, String details, String operatorName) {
		this.vendingID=vendingID;
		this.problem = problem;
		this.details = details;
		this.operatorName = operatorName;
	}

	public String getVendingID() {
		return vendingID;
	}

	public void setVendingID(String vendingID) {
		this.vendingID = vendingID;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getDetails() {
		return "["+details+"]";
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	} 	
}

