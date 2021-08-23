package pattern.observerpattern;

import java.util.ArrayList;
import java.util.List;


/**
 * Subject
 *
 * @author virgilin
 * @date 2019/4/9
 */
public class Subject {
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    private void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

