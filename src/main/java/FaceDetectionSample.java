//import all of the required modules for the program
//These will be used to process the image, take input, and display the results
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
//List, Scanner, and ArrayList are used in the attendanceManagement method to keep track of results
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//start by creating the FaceDetection class
class FaceDetectionSample {
    //Images or video input will need to be converted to a matrix in order to be processed by the cascade classifier
    private static Mat grayscaleImage = new Mat();
    private static MatOfRect facesInFrame = new MatOfRect();
    private static Mat imageView = new Mat();
    private static int numFaces = 0;
    private static CascadeClassifier faceCascade = new CascadeClassifier();
    private static boolean found = false;
    private static int foundIndex;
    //create new scanners and variables to manage the date for the attendanceManagement method
    Scanner inputType = new Scanner(System.in);
    private static int maxCapacity;
    private static ArrayList<String> datesList = new ArrayList<String>();
    private static ArrayList<Integer> faceList = new ArrayList<Integer>();

    public static void detectAndDisplay() {
        Imgproc.cvtColor(imageView, grayscaleImage, Imgproc.COLOR_BGR2GRAY);
        //convert the image/video input to grayscale:
        //takes parameters (input, result, conversion code)
        //in this case it changes from colour to black and white
        faceCascade.detectMultiScale(grayscaleImage, facesInFrame);
        //detectMultiScale uses the face cascade to find faces. the input parameters are (input, result)

        //create a new list and add each face in the frame to it.
        List<Rect> listOfFaces;
        listOfFaces = facesInFrame.toList();
        numFaces = 0;

        //draw a rectangle around each face in the frame
        for (Rect face : listOfFaces) {
            Imgproc.rectangle(imageView,
                    new Point(face.x, face.y),//select the main starting goordinates
                    new Point(face.x+face.width, face.y+face.height),//select ending coordinates
                    new Scalar(0, 255, 0), 1);//select colour and thickness
            Mat faceROI = grayscaleImage.submat(face);
            numFaces++;
        }
        //display the results
        HighGui.imshow("Detecting Faces...", imageView);
    }

    //create the attendance method
    public static void Attendance() {
        System.out.println("Face Detection complete:");
        //print how many faces are in the frame
        if (numFaces > 1) {
            System.out.println("There are " + numFaces + " faces in the frame.");
        } else if (numFaces == 1) {
            System.out.println("There is " + numFaces + " face in the frame.");
        } else {
            System.out.println("There are no faces in the frame.");
        }

        //ask the user for the current date
        Scanner getDate = new Scanner(System.in);
        System.out.println("Please enter today's date in the format DD/MM/YYYY");
        String dateInput = getDate.nextLine();
        //FaceDetectionSample week1 = new FaceDetectionSample(dateInput);

        //add pre-made values (for reference and to show how to search the arrayList:
        datesList.add("06/11/2021");
        datesList.add("07/11/2021");
        datesList.add("08/11/2021");
        faceList.add(18);
        faceList.add(7);
        faceList.add(3);

        //add the current date and the number of faces to their respective lists
        datesList.add(dateInput);
        faceList.add(numFaces);

        //check to make sure the room is below or meets the maximum quantity. If it is over, alert the user
        if (numFaces>maxCapacity){
            System.out.println("NOTICE: This room is beyond its' maximum capacity!");
            if(numFaces-maxCapacity == 1){
                System.out.println("There is " + (numFaces-maxCapacity) +" person beyond the limit.\n");
            }else{
                System.out.println("There are " + (numFaces-maxCapacity) +" people beyond the limit.\n");
            }
        }

        //iterate through the datesList list and print how many faces there were on each day
        //if the limit is exceeded, let the user know
        System.out.println("Past attendance records:");
        for (int i = 0; i < datesList.size(); i++) {
            if (faceList.get(i) > 1) {
                System.out.println("On " + datesList.get(i) + " there were " + faceList.get(i) + " people in class.");
                if (faceList.get(i)>maxCapacity){
                    System.out.print("WARNING: Capacity exceeded ");
                    if(faceList.get(i)-maxCapacity == 1){
                        System.out.println("There is " + (faceList.get(i)-maxCapacity) +" person beyond the limit.");
                    }else{
                        System.out.println("There are " + (faceList.get(i)-maxCapacity) +" people beyond the limit.");
                    }
                }
            } else if (faceList.get(i) == 1) {
                System.out.println("On " + datesList.get(i) + " there was " + faceList.get(i) + " person in class.");
                if (faceList.get(i)>maxCapacity){
                    System.out.print("WARNING: Capacity exceeded! ");
                    if(faceList.get(i)-maxCapacity == 1){
                        System.out.println("There is " + (faceList.get(i)-maxCapacity) +" person beyond the limit.");
                    }else{
                        System.out.println("There are " + (faceList.get(i)-maxCapacity) +" people beyond the limit.");
                    }
                }
            } else {
                System.out.println("On " + datesList.get(i) + " there was nobody in class.");
            }
        }

        //ask the user if they want to search for a specific date to check attendance
        Scanner wantToSearch = new Scanner(System.in);
        System.out.println("Would you like to check the attendance for a specific date?");
        String answer = wantToSearch.nextLine();

        if (answer.toUpperCase().equals("YES")) {
            Scanner searchValue = new Scanner(System.in);
            System.out.println("Okay, what date would you like to check? (Please enter your date in DD/MM/YYYY format)");
            String searchDate = searchValue.nextLine();

            //iterate through the list to find the correct date and print the result
            for (int i = 0; i < datesList.size(); i++) {
                if (datesList.get(i).equals(searchDate)) {
                    found = true;
                    foundIndex = i;
                }else{
                    found = false;
                }
            }
            //if the value has been found, print the found message
            //otherwise let the user know that their search value could not be found
            if (found = true) {
                System.out.println("On " + datesList.get(foundIndex) + " there were " + faceList.get(foundIndex) + " people in class.");
            } else {
                //if the date can't be found display an error message
                System.out.println("Sorry, that date couldn't be found.");
            }

            //if the user chooses not to search for a date complete the attendance.
        } else if (answer.toUpperCase().equals("NO")) {
            System.out.println("Okay, attendance for today has been recorded.");
        }else {
            System.out.println("Sorry, that was not a valid option.\nAttendance has been recorded and saved.");
        }
    }

    //create the run method to run the rest of the code
    public static void run() {
        //ask the user for the maximum capacity of the room that they're in
        //this will help manage the number of people that are in a room at one given time
        Scanner capacity = new Scanner(System.in);
        System.out.println("What is the maximum capacity of the room?");
        maxCapacity = capacity.nextInt();

        //set the cascade file and select the device camera
        String filenameFaceCascade = "C:\\Users\\kelse\\cs culm test\\src\\main\\haarcascade_frontalface_default.xml";
        int cameraDevice = 1;//change to 0 to use device camera, use a value of 1 for external camera
        VideoCapture capture = new VideoCapture(cameraDevice);

        //error message in case the cascade does not work as intended
        if (!faceCascade.load(filenameFaceCascade)) {
            System.err.println("ERROR: FaceCascade not loaded!" + filenameFaceCascade);
            System.exit(0);
        }
        //Repeat the code below for as long as the program reads the webcam input
        while (capture.read(imageView)) {
            //call the detectAndDisplay method
            detectAndDisplay();
            //if the escape key is pressed, release the video capture, call the attendance method, and exit the program
            if (HighGui.waitKey(0) == 27) {
                 capture.release();//release the camera to stop taking input
                 break;// escape the while loop
            }
        }
        //call the attendance method and end the program once complete
        Attendance();
        System.exit(0);
    }
}
