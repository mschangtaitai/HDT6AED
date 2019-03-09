package Factory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.Map;

public class MapFactory<Key,Value> {

    public Map<Key,Value> createMap(int op){
        if (op == 1){
            return new HashMap<Key,Value>();

        } else if (op == 2){
            return new TreeMap<Key,Value>();

        } else {
            return new LinkedHashMap<Key,Value>();
        }

    }

    public Map<String,Integer> createCantMap(int op){
        if (op == 1) {
            return new HashMap<String, Integer>();
        }else if (op == 2){
            return new TreeMap<String, Integer>();
        }else {
            return new LinkedHashMap<String,Integer>();
        }
    }
}
