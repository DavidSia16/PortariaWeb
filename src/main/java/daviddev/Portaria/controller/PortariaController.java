package daviddev.Portaria.controller;

import daviddev.Portaria.model.Encomenda;
import daviddev.Portaria.model.Prestador;
import daviddev.Portaria.repository.EncomendaRepository;
import daviddev.Portaria.repository.PrestadorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/")
public class PortariaController {

    private final PrestadorRepository prestadorRepository;
    private final EncomendaRepository encomendaRepository;

    public PortariaController(PrestadorRepository prestadorRepository, EncomendaRepository encomendaRepository) {
        this.prestadorRepository = prestadorRepository;
        this.encomendaRepository = encomendaRepository;
    }

    // Rota Principal (Carrega a página e processa as buscas da mesma tela)


    @GetMapping
    public String index(
            @RequestParam(required = false) String buscaPrestador,
            @RequestParam(required = false) String buscaEncomenda,
            Model model) {

        // Listas ativas (o que está no prédio / pendente)
        model.addAttribute("prestadores", prestadorRepository.findByDataSaidaIsNull());
        model.addAttribute("encomendas", encomendaRepository.findByDataEntregaMoradorIsNull());

        // Objetos para os formulários de cadastro
        model.addAttribute("novoPrestador", new Prestador());
        model.addAttribute("novaEncomenda", new Encomenda());

        // Histórico de Prestadores (com ou sem filtro de busca)
        if (buscaPrestador != null && !buscaPrestador.isEmpty()) {
            model.addAttribute("historicoPrestadores", prestadorRepository.findByNomeContainingIgnoreCase(buscaPrestador));
        } else {
            model.addAttribute("historicoPrestadores", prestadorRepository.findAll());
        }

        return "portaria";
    }

    // Rota pagina de historicos

    @GetMapping("/historico")
    public String verHistorico(
            @RequestParam(required = false) String buscaPrestador,
            @RequestParam(required = false) String buscaEncomenda,
            Model model) {

        // Histórico de Encomendas (com ou sem filtro de busca)
        if (buscaEncomenda != null && !buscaEncomenda.isEmpty()) {
            model.addAttribute("historicoEncomendas", encomendaRepository.findByDestinatarioContainingIgnoreCase(buscaEncomenda));
        } else {
            model.addAttribute("historicoEncomendas", encomendaRepository.findAll());
        }

        model.addAttribute("buscaPrestador", buscaPrestador);
        model.addAttribute("buscaEncomenda", buscaEncomenda);

        return "historico";
    }

    // CADASTRO E SAÍDA DE PRESTADORES
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

    // CADASTRO E ENTREGA DE ENCOMENDAS
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