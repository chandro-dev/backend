package ara.main.Repositories.InventoryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InventarioRepositorio {
    private final JdbcTemplate jdbcTemplate;


    public List<String> getBrandTableColumns() {
        String sql = "SELECT * FROM brand";
        return jdbcTemplate.queryForList(sql, String.class);
    }
}
