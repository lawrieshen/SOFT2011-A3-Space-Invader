package invaders.utils;

import java.io.*;

public class DeepCopy {
//    public static void deepCopy(){
//        ByteArrayOutputStream bos = null;
//        ObjectOutputStream oos = null;
//        ByteArrayInputStream bis = null;
//        ObjectInputStream ois = null;
//
//        try{
//            //Serialization
//            bos = new ByteArrayOutputStream();
//            oos = new ObjectOutputStream(bos);
//            oos.writeObject(this);
//            oos.flush();
//            oos.close();
//
//            //Deserialization
//            bis = new ByteArrayInputStream(bos.toByteArray());
//            ois = new ObjectInputStream(bis);
//            Ball copyObj = (Ball) ois.readObject();
//
//            return copyObj;
//
//        } catch (IOException e) {
//            // Handle I/O-related exceptions, such as file I/O errors.
//            e.printStackTrace();
//            return null;
//        } catch (ClassNotFoundException e) {
//            // Handle the case where the class of the object being deserialized is not found.
//            e.printStackTrace();
//            return null;
//        }
//    }
}
