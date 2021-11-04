/*
Name; Kelsey Rough
Date: November 9 2021
Assignment: APCSA Culminating Assignment
Description:
 */

import org.opencv.core.Core;
public class Main {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
         new FaceDetectionSample().run(args);

/*
        FaceDetection.detectFace();
        FaceDetection.drawBoxes();
        FaceDetection.saveOutput();
        //ask user for input about attendance from past dates
        Scanner getDate = new Scanner(System.in);
        System.out.println("Please enter today's date in the format DD/MM/YYYY");
        String dateInput = getDate.nextLine();
        Attendance week1 = new Attendance(dateInput);
*/

    }
}