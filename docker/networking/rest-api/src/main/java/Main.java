import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Option;
import io.vavr.control.Try;
import io.vavr.jackson.datatype.VavrModule;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import spark.Request;

import java.util.List;

import static spark.Spark.*;

public class Main {
    private static Jdbi jdbi = null;

    public static void main(String[] args) {
        var mapper = new ObjectMapper();
        mapper.registerModule(new VavrModule());
        get("/hello", (req, res) -> {
            if (jdbi == null) {
                jdbi = new JdbiFactory().getNewInstance();
            }
            createTable(jdbi);
            var name = getName(req);
            var age = getAge(req);
            Handle handle = jdbi.open();
            insertData(name, age, handle);
            var dataList = getDataList(handle);
            handle.close();
            res.type("application/json");
            return mapper.writeValueAsString(dataList);
        });
    }

    private static List<MyData> getDataList(Handle handle) {
        return handle
                .createQuery("select * from mydata")
                .map(new MyDataMapper())
                .list();
    }

    private static int insertData(String name, Integer age, Handle handle) {
        return handle.createUpdate(
                "insert into mydata(name, age) values('" + name + "', " + age + ")"
        ).execute();
    }

    private static String getName(Request req) {
        return Option.of(req.queryParams("name"))
                .getOrElse("Auto");
    }

    private static Integer getAge(Request req) {
        return Option.of(req.queryParams("age"))
                .map(Integer::parseInt)
                .getOrElse(100);
    }

    private static void createTable(Jdbi jdbi) {
        Handle handle = jdbi.open();
        Try.of(() -> handle.createUpdate(
                "create table mydata( id serial primary key, name varchar not null, age int not null)"
        ).execute());
        handle.close();
    }
}
