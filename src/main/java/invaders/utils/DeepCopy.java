package invaders.utils;

import java.io.*;

public class DeepCopy {
    static ByteArrayOutputStream bos = null;
    static ObjectOutputStream oos = null;
    static ByteArrayInputStream bis = null;
    static ObjectInputStream ois = null;

    public static <T> T deepCopy(T original, Class<T> type){
        try{
            //Serialize the original object
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(original);
            oos.close();

            //Deserialize
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois  = new ObjectInputStream(bis);
            T copy = (T) ois.readObject();
            ois.close();

            return copy;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Deep copy failed for IOException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Deep copy failed for ClassNotFoundException: " + e.getMessage());
        }
    }

}
