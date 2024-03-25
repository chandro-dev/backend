package ara.main.Service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CRUDInterface<T> {
    ResponseEntity<String> register(T service);
    ResponseEntity<String> modify(T service);
    ResponseEntity<List<T>> getAll();
}
