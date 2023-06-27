package Objects;

import java.util.HashMap;
import java.util.Map;

public class Curator {
    public Map<String, String> valuesCurators = new HashMap<String, String>() {
        {
        }};
    public Curator(Integer id,String fio){

        this.valuesCurators.put("id", "" + id + "");
        this.valuesCurators.put("fio", "\"" + fio  + "\"" );

    }

}
