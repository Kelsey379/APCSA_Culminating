//import necessary modules from the OpenCV library
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

//create a faceDetection class
public class FaceDetection {
    //Save the image as a mat so that the program can identify elements in it
    public static Mat image = Imgcodecs.imread("src\\main\\people.png");
    public static MatOfRect faceDetections = new MatOfRect();
    //create a method to detect faces in an image
    public static void detectFace() {
        //load the library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //set up the cascade so that it can be used to detect faces
        //For this project I used a pre-made Haar cascade
        CascadeClassifier faceDetector = new CascadeClassifier();
        faceDetector.load("haarcascade_frontalface_default.xml");
        //use detectMultiScale to detect any faces in the image
        faceDetector.detectMultiScale(image, faceDetections);
    }
    //create a method to draw boxes around any faces identified
    public static void drawBoxes() {
        // Creating a rectangular box showing faces detected
        //repeat this line of code for each face that is detected
        for (Rect rect : faceDetections.toArray()) {
            //draw a rectangle around any faces using X and Y-coordinates and the width/height
            //Imgproc.ellipse(frame, center, new Size(face.width / 2, face.height / 2), 0, 0, 360, new Scalar(255, 0, 255));
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(255, 0, 0));
        }
    }
    //create a new method to save the results image so that users can look at it again after
    public static void saveOutput(){
        // Saving the output image
        Imgcodecs.imwrite("C:\\Users\\kelse\\cs culm test\\src\\main\\programOutput.png", image);
    }
}
