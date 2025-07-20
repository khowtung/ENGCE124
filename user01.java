// import java.net.IDN;
import java.util.Scanner;

    public class user01 {
        public static void main(String[] args) {
            Restaurants system = new Restaurants();
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("Enter command (add / cancel / check / exit):");
                String command = sc.nextLine();

                if (command.equalsIgnoreCase("add")) {
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Table No.: ");
                    int tableNo = Integer.parseInt(sc.nextLine());
                    System.out.print("Date: ");
                    String date = sc.nextLine();
                    System.out.print("Time: ");
                    String time = sc.nextLine();

                    

                    if (system.isDuplicateBooking(tableNo, name ,date ,time)) {
                        System.out.println("Duplicate booking");
                    } else if (system.isDoubleBooking(tableNo, name, date, time)) {
                        System.out.println("Double booking");
                    } else {
                        String id = system.generateID(); // สร้าง ID อัตโนมัติ
                        Record booking = new Record(name, tableNo, date, time, id);

                        if (system.addBooking(booking)) {
                            System.out.println("Booking Added. ID: #" + id);
                        } else {
                            System.out.println("Booking Failed. Duplicate ID.");
                        }
                        
                        System.out.println("tableNo :" + tableNo);
                        System.out.println("date :" + date);
                        System.out.println("time :" + time);

                    } // <-- ปิด else ของ add ถูกที่นี่
                    

                } else if (command.equalsIgnoreCase("cancel")) {
                    // ส่วน cancel ...
                    System.out.print("ID to Cancel: ");
                    String id = sc.nextLine();
                    if (system.cancelBooking(id)) {
                        System.out.println("ID: #" + id + " Canceled.");
                    } else {
                        System.out.println("Booking ID not found.");
                    }

                } else if (command.equalsIgnoreCase("check")) {
                    // ส่วน check ...
                    System.out.print("Table No.: ");
                    int tableNo = Integer.parseInt(sc.nextLine());

                    String result = system.check(tableNo);
                    System.out.println(result);

                } else if (command.equalsIgnoreCase("exit")) {
                    System.out.println("System Exit.");
                    break;

                } else {
                    System.out.println("Invalid command.");
                }

            }

            sc.close();
        }
    }
