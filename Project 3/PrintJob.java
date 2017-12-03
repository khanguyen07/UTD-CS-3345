package DataStructures;

public class PrintJob implements Comparable {

private String userName;

private UserPriority userPriority;

private int pageCount;

public String getUserName() {

return userName;

}

public void setUserName(String userName) {

this.userName = userName;

}

public UserPriority getUserPriority() {

return userPriority;

}

public void setUserPriority(UserPriority userPriority) {

this.userPriority = userPriority;

}

public int getPageCount() {

return pageCount;

}

public void setPageCount(int pageCount) {

this.pageCount = pageCount;

}

public PrintJob(String userName, UserPriority userPriority, int pageCount) {

this.userName = userName;

this.userPriority = userPriority;

this.pageCount = pageCount;

}

public int getJobPriority() {

return (userPriority.getValue() * pageCount);

}

@Override

public int compareTo(Comparable rhs) {

if (rhs instanceof PrintJob) {

int rhsPriority = ((PrintJob) rhs).getJobPriority();

int lhsPriority = getJobPriority();

if (lhsPriority > rhsPriority) {

return 1;

} else if (lhsPriority < rhsPriority) {

return -1;

}

}

return 0;

}

}