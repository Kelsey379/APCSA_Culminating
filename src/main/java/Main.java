/*
Name; Kelsey Rough
Date: November 9 2021
Assignment: APCSA Culminating Assignment
Description: This program will use OpenCV to detect faces based on input from a webcam.
The program can be used to record attendance, check the capacity of a room, and ensure that the maximum capacity is not exceeded.
It also allows users to check past attendance records in order to keep track of how many people were present over multiple days.
 */

import org.opencv.core.Core;
public class Main {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            FaceDetectionSample.run();
    }
}