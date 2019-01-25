import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class DateRangeForKYC {

    private static void calculateDateRange(String signUpDate, String currentDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);

        Calendar registrationDate = Calendar.getInstance();
        registrationDate.setTime(dateFormat.parse(signUpDate));

        Calendar presentDate = Calendar.getInstance();
        presentDate.setTime(dateFormat.parse(currentDate));

        Calendar registeredKYCDate = Calendar.getInstance();
        registeredKYCDate.setTime(registrationDate.getTime());

        registeredKYCDate.add(Calendar.YEAR, 1);
        registeredKYCDate.add(Calendar.DATE, -30);

        if(presentDate.compareTo(registeredKYCDate)<0) {
            System.out.println("No range");
            System.out.println();
            return;
        }

        int presentYear = presentDate.get(Calendar.YEAR);
        registrationDate.set(Calendar.YEAR, presentYear);

        Calendar startingRangeFormSubmission = Calendar.getInstance();
        startingRangeFormSubmission.setTime(registrationDate.getTime());
        startingRangeFormSubmission.add(Calendar.DATE, -30);

        Calendar endDateFromSubmission = Calendar.getInstance();
        endDateFromSubmission.setTime(registrationDate.getTime());
        endDateFromSubmission.add(Calendar.DATE, 30);

        if(presentDate.compareTo(startingRangeFormSubmission)<0) {
            startingRangeFormSubmission.add(Calendar.YEAR, -1);
            endDateFromSubmission.add(Calendar.YEAR, -1);
        }

        if(presentDate.compareTo(endDateFromSubmission)<0) {
            endDateFromSubmission = presentDate;
        }
        System.out.print("Range: ");
        System.out.println(dateFormat.format(startingRangeFormSubmission.getTime()) + " " + dateFormat.format(endDateFromSubmission.getTime()));
        System.out.println();
    }

    public static void main(String[] args) throws ParseException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of instances you want to check: ");
        int numberOfInstance = input.nextInt();
        while(numberOfInstance>0) {
            System.out.println("Enter sign up and present date: ");
            String signUpDate = input.next();
            String presentDate = input.next();
            try{
                calculateDateRange(signUpDate, presentDate);
            }catch (ParseException invalidDate){
                System.out.println("Please enter a valid date.");
                System.out.println();
            }
            numberOfInstance--;
        }
        input.close();
    }
}

/*
 * Test cases:
 *  1. Enter sign up and present date:
 *  21-08-2010 22-09-2100
 *  Range: 22-07-2100 20-09-2100
 *
 *  2. Enter sign up and present date:
 *  05-01-1997 21-02-2018
 *  Range: 06-12-2017 04-02-2018
 *
 *  3. Enter sign up and present date:
 *  31-02-2010 31-01-2018
 *  Please enter a valid date.
 */
