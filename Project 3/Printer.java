package DataStructures;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Printer {

private BinaryHeap queue;

public Printer(File jobFile) {

queue = new BinaryHeap();
BufferedReader brd = null;

try {

brd = new BufferedReader(new FileReader(jobFile));
String line;

while ((line = brd.readLine()) != null) {
line = line.trim();

if (!line.isEmpty()) {
String[] fields = line.split("\\t");
UserPriority priority;

if (fields[1].equals("1")) {
priority = UserPriority.HIGH;

} else if (fields[1].equals("2")) {

priority = UserPriority.MEDIUM;

} else {

priority = UserPriority.LOW;

}

Comparable cmp;

if (fields[3].equals("I")) {

cmp = new PrintJob(fields[0], priority, Integer.parseInt(fields[2]));

} else {

cmp = new OutsidePrintJob(fields[0], priority, Integer.parseInt(fields[2]));

}

queue.insert(cmp);

}

}

} catch (IOException e) {

e.printStackTrace();

} catch (Overflow e) {

e.printStackTrace();

} finally {

if (brd != null) {

try {

brd.close();

} catch (IOException e) {

e.printStackTrace();

}

}

}

}

public void processPrintQueue() {

while (!queue.isEmpty()) {

// show minimum info

Comparable cmp = queue.findMin();

System.out.println("---------------------------------");

if (cmp instanceof OutsidePrintJob) {

OutsidePrintJob job = (OutsidePrintJob) cmp;

System.out.println("User Name: " + job.getUserName());

System.out.println("User Priority: " + job.getUserPriority().getValue());

System.out.println("Page Count: " + job.getPageCount());

System.out.println("Print Cost: " + job.calculatePrintCost() + " cents");

} else {

PrintJob job = (PrintJob) cmp;

System.out.println("User Name: " + job.getUserName());

System.out.println("User Priority: " + job.getUserPriority().getValue());

System.out.println("Page Count: " + job.getPageCount());

}

// delete minimum

queue.deleteMin();

}

}

public static void main(String[] args) {

File file = new File("C:\\myfiles\\print_jobs.txt");

Printer printer = new Printer(file);

// process print queue

printer.processPrintQueue();

}

}

