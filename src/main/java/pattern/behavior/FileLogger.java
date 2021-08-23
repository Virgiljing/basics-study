package pattern.behavior;

/**
 * FileLogger
 *
 * @author virgilin
 * @date 2019/3/15
 */
public class FileLogger extends AbstractLogger {

    public FileLogger(int level){
        this.level = level;
    }
    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}
