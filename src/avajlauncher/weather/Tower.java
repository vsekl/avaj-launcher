package avajlauncher.weather;

import avajlauncher.aircrafts.Flyable;
import avajlauncher.utils.OutputWriter;

import java.io.IOException;
import java.util.ArrayList;

public class Tower {
    private ArrayList<Flyable> observers = new ArrayList<>();
    public void register(Flyable flyable) { observers.add(flyable); }

    public void unregister(Flyable flyable) { observers.remove(flyable); }

    protected void conditionsChanged() throws IOException {
        for (int i=0; i<observers.size(); i++) {
            int sizeBefore = observers.size();
            observers.get(i).updateConditions();
            if (sizeBefore != observers.size())
                i--;
        }
    }
}
