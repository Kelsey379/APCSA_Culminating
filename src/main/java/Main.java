/*
Name; Kelsey Rough
Date: November 9 2021
Assignment: APCSA Culminating Assignment
Description:
 */

/*
To check:
split run method into smaller methods
change elipsis to rectangle
what is args?
save past recordings?

line 85 not being reached?
 */
import org.opencv.core.Core;
public class Main {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

//        boolean runProgram = true;
//        FaceDetectionSample.loadImage();
//        FaceDetectionSample.detectFace();
//        FaceDetectionSample.saveImage();
//        while(runProgram) {
            FaceDetectionSample.run();
//        }
        //FaceDetectionSample.Attendance();

//        Scanner repeat = new Scanner(System.in);
//        System.out.println("Would you like to take attendance again?");
//        String repeatAnswer = repeat.nextLine();
//        if (repeatAnswer.toUpperCase().equals("YES")) {
//            runProgram= true;
//            System.out.println("Taking attendance again...");
//            FaceDetectionSample.run();
//        }else {
//            runProgram = false;
//            System.out.println("Ending Program...");
//        }
    }
}