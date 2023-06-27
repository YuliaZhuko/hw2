package Objects;

import java.util.HashMap;
import java.util.Map;

public class Groupa {
    public Map<String, String> valuesGroupa = new HashMap<String, String>() {
        {
        }};
    public Groupa(Integer id,String name, Integer id_curator){

        this.valuesGroupa.put("id", "" + id + "");
        this.valuesGroupa.put("name", "\"" + name  + "\"" );
        this.valuesGroupa.put("id_curator", "" + id_curator + "");

    }
}
