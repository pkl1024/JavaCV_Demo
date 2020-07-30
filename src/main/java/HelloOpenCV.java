import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
//
// Detects faces in an image, draws boxes around them, and writes the results
// to "faceDetection.png".
//
class DetectFaceDemo {
    public void run() {
        System.out.println("\n正在运行人脸检测Demo");
        // 从参考资料中的级联文件创建人脸检测器
        // 文件目录.
        CascadeClassifier faceDetector = new CascadeClassifier("D:\\JAVAweb\\JavaCV_Demo\\src\\main\\resources\\lbpcascade_frontalface.xml");
        Mat image = Imgcodecs.imread("D:\\JAVAweb\\JavaCV_Demo\\src\\main\\resources\\1.jpg");
        // 在图像中检测人脸。
        // MatOfRect是Rect的一个特殊容器类。
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);
        System.out.println(String.format("检测到 %s 张脸", faceDetections.toArray().length));
        // 在每个是别到的脸周围画一个边界框。
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0,255,0));
        }
        // 保存可视化检测。
        String filename = "faceDetection.png";
        System.out.println(String.format("检测结果图片存为： %s", filename));
        Imgcodecs.imwrite(filename, image);
    }
}
public class HelloOpenCV {
    public static void main(String[] args) {
        System.out.println("Hello, OpenCV");
        // Load the native library.
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        new DetectFaceDemo().run();
    }
}