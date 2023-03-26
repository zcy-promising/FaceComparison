package config;

//各项路径配置
public class path {
    //openCV自带的人脸模型数据
    public static final String haarcascades=".\\static\\haarcascade_frontalface_default.xml";
    //人脸存放位置
    public static final String faceSave=".\\static\\saveFace\\";
    //数据库名
    public static final String userName="root";
    //数据库密码
    public static final String password="123456";
    //数据库连接地址
    public static final String url="jdbc:mysql://localhost:3306/face_manage";

}
