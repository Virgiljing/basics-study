package pattern.behavior;

/**
 * ErrorLogger
 *
 * @author virgilin
 * @date 2019/3/15
 */
public class ErrorLogger extends AbstractLogger {
    public ErrorLogger(int level){
        this.level = level;
    }
    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}
