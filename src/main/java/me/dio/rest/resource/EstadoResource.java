package me.dio.rest.resource;

import me.dio.rest.repository.EstadoRepository;
import java.util.List;
import me.dio.rest.entity.Estado;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/estados")
public class EstadoResource {

  private final EstadoRepository repository;

  public EstadoResource(final EstadoRepository repository) {
    this.repository = repository;
  }

  @GetMapping
  public List<Estado> estados() {
    return repository.findAll();
  }
}
