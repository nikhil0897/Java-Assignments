import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author Nikhil Sharma
 */

public class FileSearch {
    /**
     * @param fileName string for matching to the name of the file prints the
     *                 absolute path of all matching files
     */
    private static void printFilePath(String fileName) {
        String pathToSearch = System.getProperty("user.home"); // assigned the directory
        File locationToBeSearched = new File(pathToSearch);
        final Pattern patternFileName = Pattern.compile(fileName); // changed filename into a pattern to be searched for
        File[] listOfFiles = locationToBeSearched.listFiles(new FileFilter() {
            @Override
            public boolean accept(File record) {
                return patternFileName.matcher(record.getName()).matches();
            }
        });
        if (locationToBeSearched.isDirectory()) { // checking the directory access
            if (listOfFiles.length < 1) {
                System.out.println("There is no file with such name in your home directory.");
            } else {
                System.out.println("File found.");
                for (File fileHit : listOfFiles) {
                    if (!fileHit.isDirectory()) {
                        try {
                            System.out.println("Absolute Path: " + fileHit.getAbsolutePath().toString()); // printing the path of the file
                        } catch (Exception noFileFound) {
                            noFileFound.printStackTrace();
                        }
                    }
                }
            }
        } else {
            System.out.println("There is nothing to search inside the provided directory:" + pathToSearch);
        }
    }

    public static void main(String[] args) throws IOException {
        String haltExecution;
        do {
            System.out.println("Enter the filename to be searched inside home directory: ");
            Scanner input = new Scanner(System.in);
            String fileName = input.next();
            printFilePath(fileName);
            System.out.println("Want to continue? Press Y/N");
            haltExecution = input.next();
        } while (haltExecution.toLowerCase().equals("y")); // to make the program run till the user want
    }
}

/** Test cases:
 * 1. imagePerforatedBy2.cpp
 * File found.
 * Absolute Path: /home/zadmin/imagePerforatedBy2.cpp
 *
 * 2. imagePerforatedBy4.cpp
 * There is no file with such name in your home directory.
 *
 * 3. Username
 * File found.
 * Absolute Path: /home/zadmin/Username
 *
 * 4. ^Ab.*
 * File found.
 * Absolute Path: /home/zadmin/AbsoluteValue.java
 */
