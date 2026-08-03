package daviddev.Portaria.repository;


import daviddev.Portaria.model.Encomenda;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EncomendaRepository extends JpaRepository<Encomenda, Long> {
    List<Encomenda> findByDataEntregaMoradorIsNull();
}
