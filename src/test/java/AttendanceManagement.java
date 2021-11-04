/*
import java.util.Scanner;
import java.util.ArrayList;

class Attendance{
    private static int day;
    private static String month;
    private static int year;
    //private static int faces;
    ArrayList<String> datesList = new ArrayList<String>();
    ArrayList<Integer> faceList = new ArrayList<Integer>();

    public Attendance(String dateInput){
        numFaces = 5; //set this to however many faces there are detected in the image/video input
        datesList.add(dateInput);
        faceList.add(numFaces);
        for (int i = 0; i < datesList.size(); i++) {
            System.out.println("On "+ datesList.get(i) + " there were " + faceList.get(i) + " people in class.");
        }
        // day = dateInput.substring(0, 2);
        // month = dateInput.substring(0, 2);
        // year = dateInput.substring(0, 2);

        System.out.println(dateInput);
        Scanner wantToSearch = new Scanner(System.in);
        System.out.println("Would you like to check the attendance for a specific date?");
        String answer = wantToSearch.nextLine();
        if (answer.toUpperCase().equals("YES")){
            Scanner searchValue = new Scanner(System.in);
            System.out.println("Okay, what date would you like to check? (Please enter your date in DD/MM/YYYY format)");
            String searchDate = searchValue.nextLine();

            for (int i = 0; i < datesList.size(); i++) {
                if(datesList.get(i).contains(searchDate)){
                    System.out.println("On "+ datesList.get(i) + " there were " + faceList.get(i) + " people in class.");
                }else{
                    System.out.println("Sorry, that date couldn't be found.");
                }
            }
        }else if (answer.toUpperCase().equals("NO")){
            System.out.println("Okay, attendance for today has been recorded.");
        }
    }
}
*/
