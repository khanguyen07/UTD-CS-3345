package DataStructures;

public class OutsidePrintJob extends PrintJob {

private static final double COST_PER_PAGE = 10.0;

public OutsidePrintJob(String userName, UserPriority userPriority, int pageCount) {

super(userName, userPriority, pageCount);

}

public double calculatePrintCost() {

return (COST_PER_PAGE * this.getPageCount());

}

}