import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.*;

public class DateManipulation {

    private static void calculateDate(String signup, String current) throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Calendar registrationDate = Calendar.getInstance();
        registrationDate.setTime(dateFormat.parse(signup));

        Calendar today = Calendar.getInstance();
        today.setTime(dateFormat.parse(current));

        Calendar kycStart = Calendar.getInstance();
        kycStart.setTime(registrationDate.getTime());

        kycStart.add(Calendar.YEAR, 1);
        kycStart.add(Calendar.DATE, -30);

        if(today.compareTo(kycStart)<0) {
            System.out.println("No range");
            return;
        }

        int year = today.get(Calendar.YEAR);
        registrationDate.set(Calendar.YEAR, year);


        Calendar start = Calendar.getInstance();
        start.setTime(registrationDate.getTime());
        start.add(Calendar.DATE, -30);

        Calendar end = Calendar.getInstance();
        end.setTime(registrationDate.getTime());
        end.add(Calendar.DATE, 30);

        if(today.compareTo(start)<0) {
            start.add(Calendar.YEAR, -1);
            end.add(Calendar.YEAR, -1);
        }

        if(today.compareTo(end)<0) {
            end = today;
        }
        System.out.print("Range: ");
        System.out.println(dateFormat.format(start.getTime()) + " " + dateFormat.format(end.getTime()));

    }

    public static void main(String[] args) throws ParseException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of instances you want to check: ");
        int n = in.nextInt();
        while(n>0) {
            System.out.println("Enter sign up date: ");
            String signup = in.next();
            System.out.println("Enter current date: ");
            String current = in.next();
            calculateDate(signup, current);
            n--;
        }

        in.close();
    }
}
