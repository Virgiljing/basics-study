package pattern2.chainOfResp.cainiao;

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
        AbstractLogger chainOfLoggers = getChainOfLoggers();
        chainOfLoggers.logMessage(AbstractLogger.INFO,"This is an information.");
        chainOfLoggers.logMessage(AbstractLogger.DEBUG,"This is an debug level information");
        chainOfLoggers.logMessage(AbstractLogger.ERROR,"this is an error information");
    }
}
