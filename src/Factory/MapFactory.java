package Factory;

public class MapFactory {

    public Map getMap(String mapType){
        if (mapType == null){
            return null;
        }
        if (mapType.equalsIgnoreCase("Hashmap")){
            return new Hashmap();

        } else if (mapType.equalsIgnoreCase("Treemap")){
            return new Treemap();

        } else if (mapType.equalsIgnoreCase("LinkedHashmap")){
            return new LinkedHashmap();

        }
        return null;

    }
}
