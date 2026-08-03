package daviddev.Portaria.controller;

import daviddev.Portaria.model.Encomenda;
import daviddev.Portaria.model.Prestador;
import daviddev.Portaria.repository.EncomendaRepository;
import daviddev.Portaria.repository.PrestadorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
public class PortariaController {

    private final PrestadorRepository prestadorRepository;
    private final EncomendaRepository encomendaRepository;

    public PortariaController(PrestadorRepository prestadorRepository, EncomendaRepository encomendaRepository) {
        this.prestadorRepository = prestadorRepository;
        this.encomendaRepository = encomendaRepository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("prestadores", prestadorRepository.findByDataSaidaIsNull());
        model.addAttribute("encomendas", encomendaRepository.findByDataEntregaMoradorIsNull());

        model.addAttribute("novoPrestador", new Prestador());
        model.addAttribute("novaEncomenda", new Encomenda());
        return "portaria";
    }


    @PostMapping("/prestadores/cadastrar")
    public String cadastrarPrestador(@ModelAttribute Prestador prestador) {
        prestadorRepository.save(prestador);
        return "redirect:/";
    }

    @PostMapping("/prestadores/saida/{id}")
    public String registrarSaidaPrestador(@PathVariable Long id) {
        Prestador p = prestadorRepository.findById(id).orElseThrow();
        p.setDataSaida(LocalDateTime.now());
        prestadorRepository.save(p);
        return "redirect:/";
    }

    //  ENCOMENDAS
    @PostMapping("/encomendas/cadastrar")
    public String cadastrarEncomenda(@ModelAttribute Encomenda encomenda) {
        encomendaRepository.save(encomenda);
        return "redirect:/";
    }

    @PostMapping("/encomendas/entregar/{id}")
    public String registrarEntregaEncomenda(@PathVariable Long id) {
        Encomenda e = encomendaRepository.findById(id).orElseThrow();
        e.setDataEntregaMorador(LocalDateTime.now());
        encomendaRepository.save(e);
        return "redirect:/";
    }
}
