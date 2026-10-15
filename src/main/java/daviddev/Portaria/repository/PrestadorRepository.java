package daviddev.Portaria.repository;

import daviddev.Portaria.model.Prestador;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PrestadorRepository extends JpaRepository<Prestador, Long> {
    List<Prestador> findByDataSaidaIsNull();
    List<Prestador> findByNomeContainingIgnoreCase(String nome);
}
