import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Camera {
    /**
     * 打开摄像头并且抓取
     * @return VideoCapture
     */
    public static VideoCapture open(){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);//加载openCV
        System.setProperty("java.awt.headless","false");//关闭headless模式
        VideoCapture capture=new VideoCapture();
        capture.set(7,120);//设置帧率
        capture.open(0,700);//打开本机摄像头，并一直抓取

        return capture;
    }

    /**
     * 检测人脸坐标
     * @param src
     * @return faceDetections
     */
    public static MatOfRect faceDetections(Mat src){
        Mat dtc=new Mat();
         //输出帧
         //openCV自带的人脸模型数据
         //人脸特征提取器
        CascadeClassifier classifier=new CascadeClassifier(path.haarcascades);
         //人脸坐标的集合
        MatOfRect faceDetections=new MatOfRect();
        Imgproc.cvtColor(src,dtc,Imgproc.COLOR_BGR2GRAY);
        classifier.detectMultiScale(dtc,faceDetections);

        return faceDetections;

    }

    /**
     * 根据坐标绘画人脸框
     * @param src
     * @param faceDetections
     * @return Mat
     */
    public static Mat showFace(Mat src,MatOfRect faceDetections){
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(src, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),new Scalar(0, 0, 255), 2);
        }

        return src;
    }

    /**
     * 画面展示
     * @param src
     * @return 按下按键ASCII码
     */
     public static int show(Mat src){
         HighGui gui=new HighGui();
         Core.flip(src,src,1);//镜像翻转
         gui.imshow("人脸识别",src);
         return gui.waitKey(1);//返回按下按键ASCII码
    }

    /**
     * 相关系数大于0.72则判定是同一个人
     * @param src
     * @return boolean
     */
    public static boolean compareFace(Mat src,String name){
         Mat compare= Imgcodecs.imread(path.faceSave+name+".jpg");
         Mat hist1=new Mat();
         Mat hist2=new Mat();

         MatOfFloat ranges=new MatOfFloat(0f,256f);
         MatOfInt histSize =new MatOfInt(10000000);
         Imgproc.calcHist(Arrays.asList(src),new MatOfInt(0),new Mat(),hist1,histSize,ranges);
         Imgproc.calcHist(Arrays.asList(compare),new MatOfInt(0),new Mat(),hist2,histSize,ranges);

         double res=Imgproc.compareHist(hist1,hist2,Imgproc.CV_COMP_CORREL);

        System.out.println(res);
        if (res>0.72){
            System.out.println("是同一个人");
        }else {
            System.out.println("不是用一个人");
        }
         return Imgproc.compareHist(hist1,hist2,Imgproc.CV_COMP_CORREL)>0.72;
    }

    /**
     * 裁剪图片并保存
     * @param src
     * @param rect
     * @return
     */
    public static void imageCut(Mat src, Rect rect,String name){
        //截取图片
        Mat dtc=src.submat(rect);
        Size size=new Size(rect.width,rect.height);
        //图片的缩放
        Imgproc.resize(dtc,dtc,size);
        //存放
        Imgcodecs.imwrite(path.faceSave+name+".jpg",dtc);
    }

}
