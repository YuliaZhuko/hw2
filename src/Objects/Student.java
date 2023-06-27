package Objects;

import java.util.HashMap;
import java.util.Map;

public class Student {
   public Map<String, String> valuesStudents = new HashMap<String, String>() {
        {
        }};
    public Student(Integer id,String fio, String sex, Integer id_group){

     this.valuesStudents.put("id", "" + id + "");
     this.valuesStudents.put("fio", "\"" + fio  + "\"" );
     this.valuesStudents.put("sex", "\"" + sex + "\"");
     this.valuesStudents.put("id_group", "" + id_group + "");

    }

}
