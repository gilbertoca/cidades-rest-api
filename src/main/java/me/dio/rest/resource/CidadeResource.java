package me.dio.rest.resource;

import me.dio.rest.repository.CidadeRepository;
import me.dio.rest.entity.Cidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {

    private final CidadeRepository repository;

    public CidadeResource(final CidadeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Page<Cidade> cidades(final Pageable page) {
        return repository.findAll(page);
    }
}
