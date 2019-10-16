import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

class MyDataMapper implements RowMapper<MyData> {
    @Override
    public MyData map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new MyData(rs.getString("name"), rs.getInt("age"));
    }
}
