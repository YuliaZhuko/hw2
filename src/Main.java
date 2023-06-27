import Objects.Curator;
import Objects.Groupa;
import Objects.Student;
import db.DBConnector;
import db.IDBConnector;
import tables.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws SQLException {
        IDBConnector idbConnector = new DBConnector();
        Map<String, String> columns = new HashMap<String, String>() {
            {
                put("id", "integer primary key");
                put("fio", "varchar(55)");
                put("sex", "char");
                put("id_group", "integer");
            }
        };
        AbsTable studentTable = new StudentTable();
        try {
            studentTable.delete(columns);
            studentTable.create(columns);
        } finally {
            studentTable.close();
        }

        Student student1 = new Student(5, "Trebuhova Lidia Petrovna", "w", 301);
        studentTable.insert(student1.valuesStudents);
        Student student2 = new Student(1, "Ivanova Maria Aleksandrovna", "w", 302);
        studentTable.insert(student2.valuesStudents);
        Student student3 = new Student(7, "Svetashov Sergey Maksimovich", "m", 303);
        studentTable.insert(student3.valuesStudents);
        Student student4 = new Student(4, "Budanov Oleg Mirzoevich", "m", 301);
        studentTable.insert(student4.valuesStudents);
        Student student5 = new Student(2, "Evstratov Nadim Kalimovich", "m", 301);
        studentTable.insert(student5.valuesStudents);
        Student student6 = new Student(8, "Andaluskina Maya Ioanovna", "w", 303);
        studentTable.insert(student6.valuesStudents);
        Student student7 = new Student(11, "Puhov Artem Evgenievich", "m", 302);
        studentTable.insert(student7.valuesStudents);
        Student student8 = new Student(92, "Malinovskaya Svetlana Uhvatovna", "w", 302);
        studentTable.insert(student8.valuesStudents);
        Student student9 = new Student(21, "Strelkov Egor Ivanovich", "m", 301);
        studentTable.insert(student9.valuesStudents);
        Student student10 = new Student(19, "Savinov Pavel Hafizovich", "m", 301);
        studentTable.insert(student10.valuesStudents);
        Student student11 = new Student(16, "Alaya Olga Andreevna", "w", 303);
        studentTable.insert(student11.valuesStudents);
        Student student12 = new Student(29, "Ivanova Siria Palisandrovna", "w", 303);
        studentTable.insert(student12.valuesStudents);
        Student student13 = new Student(6, "Dontsova Olga Petrovna", "w", 303);
        studentTable.insert(student13.valuesStudents);
        Student student14 = new Student(12, "Batonov Baton Klimovich", "m", 301);
        studentTable.insert(student14.valuesStudents);
        Student student15 = new Student(89, "Elephantov Senen Maksimovich", "m", 301);
        studentTable.insert(student15.valuesStudents);

        Map<String, String> columnsCurator = new HashMap<String, String>() {
            {
                put("id", "integer primary key");
                put("fio", "varchar(55)");
            }
        };
        AbsTable curatorTable = new CuratorTable();
        try {
            curatorTable.delete(columnsCurator);
            curatorTable.create(columnsCurator);
        } finally {

            curatorTable.close();
        }
        Curator curator1 = new Curator(53, "Platonov Vagran Aloisovich");
        curatorTable.insert(curator1.valuesCurators);
        Curator curator2 = new Curator(17, "Oliferenko Anna Pavlovna");
        curatorTable.insert(curator2.valuesCurators);
        Curator curator3 = new Curator(8, "Lukashov Stepan Sargasovich");
        curatorTable.insert(curator3.valuesCurators);
        Curator curator4 = new Curator(9, "Pochkina Alla Osipovna");
        curatorTable.insert(curator4.valuesCurators);


        Map<String, String> columnsGroupa = new HashMap<>() {
            {
                put("id", "integer");
                put("name", "varchar(55)");
                put("id_curator", "integer primary key");
            }
        };
        AbsTable groupaTable = new GroupTable();
        try {
            groupaTable.delete(columnsGroupa);
            groupaTable.create(columnsGroupa);
        } finally {
            groupaTable.close();
        }
        Groupa groupa1 = new Groupa(301, "rocket science", 9);
        groupaTable.insert(groupa1.valuesGroupa);
        Groupa groupa2 = new Groupa(302, "soil science", 17);
        groupaTable.insert(groupa2.valuesGroupa);
        Groupa groupa3 = new Groupa(303, "solid fuel engines", 8);
        groupaTable.insert(groupa3.valuesGroupa);
        Groupa groupa4 = new Groupa(303, "astronautics", 53);
        groupaTable.insert(groupa4.valuesGroupa);

        String sqlRequest = "select s.id as student_id, s.sex, s.id_group, s.fio, g.name as group_name, c.fio as curator_name  from student as s \n" +
                "inner join groupa as g on s.id_group=g.id \n" +
                "inner join curator c on g.id_curator=c.id;";
       ResultSet resultSet = groupaTable.getData(sqlRequest);
       groupaTable.print(resultSet);

       String studentsCount = "select count(id) from student;";
        ResultSet resultSetQuantity = groupaTable.getData(studentsCount);
        groupaTable.print(resultSetQuantity);

        String womenStudents = "select * from student where sex = 'w';";
        ResultSet resultSetWomen = groupaTable.getData(womenStudents);
        groupaTable.print(resultSetWomen);


        String updateCurator = "update curator as c set c.id = 42 and c.fio ='Matveeva Anna Stepanovna' where c.id=53;";
        String updateGroup = "update groupa as g set g.id_curator=42 where g.id_curator=53;";
        groupaTable.update(updateCurator);
        groupaTable.update(updateGroup);

        String groupaCurators = "select DISTINCT s.id_group,g.name as specialisation,  c.fio as curator from student as s\n" +
                "join groupa as g on s.id_group=g.id\n" +
                "join curator as c on g.id_curator=c.id;";
        ResultSet listCurators = groupaTable.getData(groupaCurators);
        groupaTable.print(listCurators);

        String studentListRequest = "select id_group, fio from student\n" +
                "where id_group =(select id from groupa \n" +
                "where name='soil science');";
        ResultSet listStudents = groupaTable.getData(studentListRequest);
        groupaTable.print(listStudents);

            }
    }








