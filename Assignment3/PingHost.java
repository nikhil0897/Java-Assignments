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

/* 1. Enter web address:
 *    google.co.in
 *    Pinging 5 times...
 *    PING google.co.in (172.217.163.131) 56(84) bytes of data.
 *    64 bytes from maa05s04-in-f3.1e100.net (172.217.163.131): icmp_seq=1 ttl=52 time=47.9 ms
 *    64 bytes from maa05s04-in-f3.1e100.net (172.217.163.131): icmp_seq=2 ttl=52 time=48.3 ms
 *    64 bytes from maa05s04-in-f3.1e100.net (172.217.163.131): icmp_seq=3 ttl=52 time=48.3 ms
 *    64 bytes from maa05s04-in-f3.1e100.net (172.217.163.131): icmp_seq=4 ttl=52 time=53.7 ms
 *    64 bytes from maa05s04-in-f3.1e100.net (172.217.163.131): icmp_seq=5 ttl=52 time=48.2 ms
 *
 *    --- google.co.in ping statistics ---
 *    5 packets transmitted, 5 received, 0% packet loss, time 4005ms
 *    rtt min/avg/max/mdev = 47.907/49.338/53.775/2.237 ms
 *
 *    Median time taken to ping: 48.3 ms
 *
 * 2. Enter web address:
 *    archive.org
 *    Pinging 5 times...
 *    PING archive.org (207.241.224.2) 56(84) bytes of data.
 *    64 bytes from www.archive.org (207.241.224.2): icmp_seq=1 ttl=47 time=374 ms
 *    64 bytes from www.archive.org (207.241.224.2): icmp_seq=2 ttl=47 time=294 ms
 *    64 bytes from www.archive.org (207.241.224.2): icmp_seq=3 ttl=47 time=317 ms
 *    64 bytes from www.archive.org (207.241.224.2): icmp_seq=4 ttl=47 time=340 ms
 *    64 bytes from www.archive.org (207.241.224.2): icmp_seq=5 ttl=47 time=362 ms
 *
 *    --- archive.org ping statistics ---
 *    5 packets transmitted, 5 received, 0% packet loss, time 4005ms
 *    rtt min/avg/max/mdev = 294.700/337.925/374.730/29.179 ms
 *
 *    Median time taken to ping: 340.0 ms
 *
 * 3. Enter web address: 
 *    github.com
 *    Pinging 5 times...
 *    PING github.com (192.30.253.113) 56(84) bytes of data.
 *    64 bytes from lb-192-30-253-113-iad.github.com (192.30.253.113): icmp_seq=1 ttl=51 time=280 ms
 *    64 bytes from lb-192-30-253-113-iad.github.com (192.30.253.113): icmp_seq=2 ttl=51 time=305 ms
 *    64 bytes from lb-192-30-253-113-iad.github.com (192.30.253.113): icmp_seq=3 ttl=51 time=226 ms
 *    64 bytes from lb-192-30-253-113-iad.github.com (192.30.253.113): icmp_seq=4 ttl=51 time=250 ms
 *    64 bytes from lb-192-30-253-113-iad.github.com (192.30.253.113): icmp_seq=5 ttl=51 time=272 ms
 *   
 *    --- github.com ping statistics --- 
 *    5 packets transmitted, 5 received, 0% packet loss, time 4001ms
 *    rtt min/avg/max/mdev = 226.570/267.220/305.135/26.800 ms
 *    
 *    Median time taken to ping: 272.0 ms 
 */
