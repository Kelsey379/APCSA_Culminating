import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import java.util.List;
class FaceDetectionSample {
   private static Mat frameGray = new Mat();
    private static MatOfRect faces = new MatOfRect();
    private static Mat frame = new Mat();
    private static int numFaces = 0;

    public void detectAndDisplay(Mat frame, CascadeClassifier faceCascade) {
        Imgproc.cvtColor(frame, frameGray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.equalizeHist(frameGray, frameGray);
        // -- Detect faces
        faceCascade.detectMultiScale(frameGray, faces);
        List<Rect> listOfFaces = faces.toList();
        for (Rect face : listOfFaces) {
            Point center = new Point(face.x + face.width / 2, face.y + face.height / 2);
            Imgproc.ellipse(frame, center, new Size(face.width / 2, face.height / 2), 0, 0, 360,
                    new Scalar(255, 0, 255));
            Mat faceROI = frameGray.submat(face);
            numFaces++;
        }
        //-- Show what you got
        HighGui.imshow("Capture - Face detection", frame );
        //count faces and end program: FIX THIS CODE!!
//        System.out.println("There are " + numFaces + " faces in the frame.");
//        System.exit(0);
    }
    public void run(String[] args) {
        String filenameFaceCascade = args.length > 2 ? args[0] : "C:\\Users\\kelse\\cs culm test\\src\\main\\haarcascade_frontalface_default.xml";
        int cameraDevice = args.length > 2 ? Integer.parseInt(args[2]) : 0;
        CascadeClassifier faceCascade = new CascadeClassifier();
        if (!faceCascade.load(filenameFaceCascade)) {
            System.err.println("--(!)Error loading face cascade: " + filenameFaceCascade);
            System.exit(0);
        }
        VideoCapture capture = new VideoCapture(cameraDevice);
        if (!capture.isOpened()) {
            System.err.println("--(!)Error opening video capture");
            System.exit(0);
        }
        while (capture.read(frame)) {
            if (frame.empty()) {
                System.err.println("--(!) No captured frame -- Break!");
                break;
            }
            //-- 3. Apply the classifier to the frame
            detectAndDisplay(frame, faceCascade);
            if (HighGui.waitKey(1) == 27) {
                break;// escape
            }
        }
        System.out.println("There are " + numFaces + " faces in the frame.");
        System.exit(0);
    }
}