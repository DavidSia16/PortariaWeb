package daviddev.Portaria.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Encomenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destinatario;
    private String apartamento;
    private String codigoRastreio;
    private String empresaEntrega;

    private LocalDateTime dataChegada = LocalDateTime.now();
    private LocalDateTime dataEntregaMorador;

   }
