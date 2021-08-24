package threaddemo.threadpool.execption;

public class RunnableDenyPolicyExecption extends RuntimeException {
    public RunnableDenyPolicyExecption(String message) {
        super(message);
    }
}
