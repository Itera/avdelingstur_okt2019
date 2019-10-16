import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.AllArgsConstructor;
import org.jdbi.v3.core.Jdbi;

@AllArgsConstructor
public class JdbiFactory {
    private Config config;

    public JdbiFactory() {
        config = ConfigFactory.load();
    }

    public Jdbi getNewInstance() {
        return Jdbi.create(
                "jdbc:postgresql://" + getDBHostname() + ":" + getDBPort() + "/postgres",
                getDBUser(),
                getDBPassword()
        );
    }

    private String getDBHostname() {
        return config.getString("db.hostname");
    }
    private Integer getDBPort() {
        return config.getInt("db.portnumber");
    }
    private String getDBUser() {
        return config.getString("db.username");
    }
    private String getDBPassword() {
        return config.getString("db.password");
    }
}
