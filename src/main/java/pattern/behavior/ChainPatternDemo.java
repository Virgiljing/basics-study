package pattern.behavior;

/**
 * ChainPatternDemo
 *
 * @author virgilin
 * @date 2019/3/15
 */
public class ChainPatternDemo {
   private static AbstractLogger getChainOfLoggers(){
       ErrorLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
       FileLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
       ConsoleLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);
       errorLogger.setNextLogger(fileLogger);
       fileLogger.setNextLogger(consoleLogger);
       return errorLogger;
   }

    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainOfLoggers();
        loggerChain.logMessage(AbstractLogger.INFO,"This is an information.");
        loggerChain.logMessage(AbstractLogger.DEBUG,"This is a debug level information.");
        loggerChain.logMessage(AbstractLogger.ERROR,"This is an error  information.");
    }
}
