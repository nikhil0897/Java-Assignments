import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class RequestProcessor {
        String command;
        Process processRequest;
        RequestProcessor(String webAddress){
            command = "ping -c 5 " + webAddress;
        }
        void pingTheAddress() {
            try{
                System.out.println("Pinging 5 times...");
                processRequest = Runtime.getRuntime().exec(command);
                BufferedReader readAddress = new BufferedReader(new InputStreamReader(processRequest.getInputStream()));
                String checkAddress;
                List<Float> arrayList = new ArrayList<>();
                int counter = 0;
                while((checkAddress = readAddress.readLine()) != null) {
                    System.out.println(checkAddress);
                    String[] tmp = checkAddress.split(" ");
                    if(counter > 0 && counter < 6){
                        arrayList.add(Float.valueOf(tmp[7].substring(5)));
                    }
                    counter++;    //keeping the counter in order to ping the entered address 5 times only
                }
                processRequest.waitFor();
                processRequest.destroy();
                System.out.println();
                System.out.println("Median time taken to ping: " + medianTime(arrayList) + " ms");
            }catch(Exception addressNotFound){
                addressNotFound.printStackTrace();    //print the exception if encountered
            }
        }

        float medianTime(List<Float> list) {
            Collections.sort(list);
            return list.get(list.size()/2);
        }
    }

    public class PingHost {
        public static void main(String[] args) throws Exception {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter web address: ");  //www.google.com might sometime show an error, if so try any other web address
            String webAddress = input.next();
            RequestProcessor requestCheck = new RequestProcessor(webAddress);
            requestCheck.pingTheAddress();
        }
}
