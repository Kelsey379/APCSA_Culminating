import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

class FaceDetectionSample {
   private static Mat grayscaleImage = new Mat();
    private static MatOfRect facesInFrame = new MatOfRect();
    private static Mat imageView = new Mat();
    private static int numFaces = 0;
    private static CascadeClassifier faceCascade = new CascadeClassifier();

    Scanner inputType = new Scanner (System.in);
    private static int day;
    private static String month;
    private static int year;
    //private static int faces;
    private static  ArrayList<String> datesList = new ArrayList<String>();
    private static ArrayList<Integer> faceList = new ArrayList<Integer>();

    public void detectAndDisplay() {
        Imgproc.cvtColor(imageView, grayscaleImage, Imgproc.COLOR_BGR2GRAY);
       // Imgproc.equalizeHist(frameGray, frameGray);
        // -- Detect faces
        faceCascade.detectMultiScale(grayscaleImage, facesInFrame);
        List<Rect> listOfFaces = facesInFrame.toList();
        numFaces = 0;
        for (Rect face : listOfFaces) {
            Point center = new Point(face.x + face.width / 2, face.y + face.height / 2);
            Imgproc.ellipse(imageView, center, new Size(face.width / 2, face.height / 2), 0, 0, 360,
                    new Scalar(255, 0, 255), 10);
//            Imgproc.rectangle(imageView,
////                    new Point(face.x, face.y),        //p1
////                    new Point(face.x+face.width/2, face.y+face.height/2),       //p2
//                    new Point(150, 30),
//                    new Point (300, 150),
//                    new Scalar(255, 0, 255), 10);
            Mat faceROI = grayscaleImage.submat(face);
            numFaces++;
        }
        //diaplay results
        HighGui.imshow("Capture - Face detection", imageView);
        //count faces and end program: FIX THIS CODE!!
//        System.out.println("There are " + numFaces + " faces in the frame.");
//        System.exit(0);
    }
    public void run(String[] args) {
        String filenameFaceCascade = args.length > 2 ? args[0] : "C:\\Users\\kelse\\cs culm test\\src\\main\\haarcascade_frontalface_default.xml";
        int cameraDevice = args.length > 2 ? Integer.parseInt(args[2]) : 0;
        if (!faceCascade.load(filenameFaceCascade)) {
            System.err.println("--(!)Error loading face cascade: " + filenameFaceCascade);
            System.exit(0);
        }
        VideoCapture capture = new VideoCapture(cameraDevice);
        if (!capture.isOpened()) {
            System.err.println("--(!)Error opening video capture");
            System.exit(0);
        }
        while (capture.read(imageView)) {
            if (imageView.empty()) {
                System.err.println("--(!) No captured frame -- Break!");
                break;
            }
            //-- 3. Apply the classifier to the frame
            detectAndDisplay();
            if (HighGui.waitKey(0) == 27) {
                break;// escape
            }
        }
        System.out.println("There are " + numFaces + " faces in the frame.");

        Attendance();
        System.exit(0);
    }
        public static void Attendance(){
            Scanner getDate = new Scanner(System.in);
            System.out.println("Please enter today's date in the format DD/MM/YYYY");
            String dateInput = getDate.nextLine();
            //FaceDetectionSample week1 = new FaceDetectionSample(dateInput);

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



//import org.opencv.core.Core;
//import org.opencv.core.Mat;
//import org.opencv.core.MatOfRect;
//import org.opencv.core.Point;
//import org.opencv.core.Rect;
//import org.opencv.core.Scalar;
//
//import org.opencv.imgcodecs.Imgcodecs;
//import org.opencv.imgproc.Imgproc;
//import org.opencv.objdetect.CascadeClassifier;
//
//public class FaceDetectionSample {
//    public static void detectFaces(){
//        // Loading the OpenCV core library
//        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
//
//        // Reading the Image from the file and storing it in to a Matrix object
//        String file ="C:\\Users\\kelse\\cs culm test\\src\\main\\people.png";
//        Mat src = Imgcodecs.imread(file);
//
//        // Instantiating the CascadeClassifier
//        //String xmlFile = "E:/OpenCV/facedetect/lbpcascade_frontalface.xml";
//        CascadeClassifier classifier = new CascadeClassifier("haarcascade_frontalface_default.xml");
//
//        // Detecting the face in the snap
//        MatOfRect faceDetections = new MatOfRect();
//        classifier.detectMultiScale(Imgcodecs.imread(file), faceDetections);
//        System.out.println(String.format("Detected %s faces",
//                faceDetections.toArray().length));
//
//        // Drawing boxes
//        for (Rect rect : faceDetections.toArray()) {
//            Imgproc.rectangle(
//                    src,                                               // where to draw the box
//                    new Point(rect.x, rect.y),                            // bottom left
//                    new Point(rect.x + rect.width, rect.y + rect.height), // top right
//                    new Scalar(0, 0, 255),
//                    3                                                     // RGB colour
//            );
//        }
//
//        // Writing the image
//        Imgcodecs.imwrite("C:\\Users\\kelse\\cs culm test\\src\\main\\results.png", src);
//
//        System.out.println("Image Processed");
//    }
//}
}

/*
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;
import javafx.scene.image.Image;
import java.io.ByteArrayInputStream;

public class FaceDetectionSample {
public static String imagePath = "C:\\Users\\kelse\\cs culm test\\src\\main\\people.png";
    public static String sourceImagePath = "C:\\Users\\kelse\\cs culm test\\src\\main\\people.png";
    public static String targetPath = "C:\\Users\\kelse\\cs culm test\\src\\main\\results.png";

    public static Mat loadImage() {
        Imgcodecs imageCodecs = new Imgcodecs();
        return imageCodecs.imread(imagePath);
    }

    public static void saveImage(Mat imageMatrix) {
        Imgcodecs imgcodecs = new Imgcodecs();
        imgcodecs.imwrite(targetPath, imageMatrix);
    }

    public static void detectFace() {
        Mat loadedImage = loadImage(imagePath);
        MatOfRect facesDetected = new MatOfRect();
        CascadeClassifier cascadeClassifier = new CascadeClassifier();
        int minFaceSize = Math.round(loadedImage.rows() * 0.1f);
        cascadeClassifier.load("src\\main\\haarcascade_frontalface_default.xml");
        cascadeClassifier.detectMultiScale(loadedImage,
                facesDetected,
                1.1,
                3,
                Objdetect.CASCADE_SCALE_IMAGE,
                new Size(minFaceSize, minFaceSize),
                new Size()
        );
        Rect[] facesArray =  facesDetected.toArray();
        for(Rect face : facesArray) {
            Imgproc.rectangle(loadedImage, face.tl(), face.br(), new Scalar(0, 0, 255), 3 );
        }
        saveImage(loadedImage, targetImagePath);
    }

    public Image mat2Img(Mat mat) {
        MatOfByte bytes = new MatOfByte();
        Imgcodecs.imencode("img", mat, bytes);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes.toArray());
        Image img = new Image(inputStream); return img;
    }

}
 */