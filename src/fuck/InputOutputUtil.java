package fuck;

import robocode.RobocodeFileOutputStream;

import java.io.*;

/**
 * Created by user50 on 19.10.2014.
 */
public class InputOutputUtil {

    public static void save(Serializable obj, File file)
    {
        try (
                OutputStream fileOutputStream = new RobocodeFileOutputStream(file);
                OutputStream buffer = new BufferedOutputStream(fileOutputStream);
                ObjectOutput output = new ObjectOutputStream(buffer)
        ){
            output.writeObject(obj);
        }
        catch(IOException ex){
            System.out.println("Error during saving object");
            ex.printStackTrace();
        }
    }

    public static Object load(String fileName) throws IOException, ClassNotFoundException {
        try(
                InputStream file = new FileInputStream(fileName);
                InputStream buffer = new BufferedInputStream(file);
                ObjectInput input = new ObjectInputStream (buffer)
        ){
            return input.readObject();
        }
    }
}
