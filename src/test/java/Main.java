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
 */
import org.opencv.core.Core;
public class Main {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        FaceDetectionSample.loadImage();
//        FaceDetectionSample.detectFace();
//        FaceDetectionSample.saveImage();
         new FaceDetectionSample().run(args);
         FaceDetectionSample.Attendance();
    }
}