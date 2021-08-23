package pattern.behavior;

/**
 * ConsoleLogger
 *
 * @author virgilin
 * @date 2019/3/15
 */
public class ConsoleLogger extends AbstractLogger {
    public ConsoleLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}
