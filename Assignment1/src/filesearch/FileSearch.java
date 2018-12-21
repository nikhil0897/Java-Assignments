package filesearch;

import java.util.*;
import java.io.*;

public class FileSearch {

    public void findFile( String name, File file ) {
        File[] list = file.listFiles();
        for( File file1 : list )
        {
            if ( file1.isDirectory() )
            {
                findFile( name,file1 );
            }
            else if ( name.equalsIgnoreCase(file1.getName())) {
                System.out.println(file1.getParentFile());
            }
        }
    }
    public static void main(String[] args) {
        FileSearch F = new FileSearch();
        Scanner scan = new Scanner( System.in );
        System.out.println( "Enter the name of the file to be searched: " );
        while (scan.hasNext()) {
            String name = scan.next();
            System.out.println( "Enter the directory where you want to search: ");
            String directory = scan.next();
            F.findFile( name,new File(directory));
        }
    }
}
