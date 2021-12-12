import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.*;
public class cache {
    private static final int CACHESIZE = 5;
    private Map<String, String> map = new HashMap<String, String>();

    private PriorityQueue<main> pq = new PriorityQueue<main>(CACHESIZE, new Comparator(){
        @Override
        public int compare(Object arg0, Object arg1) {
            if (! (arg0 instanceof main) || !(arg1 instanceof main))
                return 0;
            main element1 = (main) arg0;
            main element2 = (main) arg1;
            return element1.getTimestamp().compareTo(element2.getTimestamp());
        }
    });

    private void update(String recent) {
        Iterator<main> pqIterator = pq.iterator();
        while(pqIterator.hasNext()) {
            main e = pqIterator.next();
            if(e.getValue().equals(recent)) {
                pqIterator.remove();
                break;
            }
        }
        main mostRecent = new main();
        mostRecent.setTimestamp(new Date());
        mostRecent.setValue(recent);
        queue(mostRecent);
    }
    private void queue(main e) {
        System.out.println(" main in queue: "+e.getValue());
        pq.offer(e);
    }


    private String remove() {
        main toRemove= pq.poll();
        if (toRemove!= null) {
            System.out.println(toRemove.getValue() + "Has been removed at " +toRemove.getTimestamp() );
            return toRemove.getValue();
        }
        return " ";
    }

    public String get(String key) {
        String value = map.get(key);
        return value;
    }

    public void put(String key, String value) {
        System.out.println("map size: "+map.size());
        if (map.containsKey(key)) {
            update(key);
            map.put(key, value);
        } else {
            if(map.size() >= CACHESIZE) {
                String leastUsedKey = remove();
                map.remove(leastUsedKey);
            }
            System.out.println(key + " Not present");
            main e = new main();
            e.setValue(key);
            e.setTimestamp(new Date());
            queue(e);
            map.put(key,value);
        }

    }

}
