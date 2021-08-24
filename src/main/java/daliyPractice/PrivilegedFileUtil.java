package daliyPractice;

import java.io.File;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class PrivilegedFileUtil {
    public static boolean canRead(String fileName){
        try {
            File file = new File(fileName);
            return file.canRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void makeFile(String fileName){
        try {
            File file = new File(fileName);
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doPrivilegedAction(final String fileName){
        AccessController.doPrivileged(new PrivilegedAction<String>() {
            @Override
            public String run() {
                makeFile(fileName);
                return null;
            }
        });
    }


}
