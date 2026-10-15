package daviddev.Portaria.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table (name = "prestadores")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prestador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String documento;
    private String empresa;
    private String apartamentoDestino;

    private LocalDateTime dataEntrada = LocalDateTime.now();
    private LocalDateTime dataSaida;




}