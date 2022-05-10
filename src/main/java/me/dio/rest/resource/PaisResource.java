package me.dio.rest.resource;

import me.dio.rest.repository.PaisRepository;
import java.util.List;
import me.dio.rest.entity.Pais;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/paises")
public class PaisResource {

  private final PaisRepository repository;

  public PaisResource(final PaisRepository repository) {
    this.repository = repository;
  }

  @GetMapping
  public List<Pais> paises() {
    return repository.findAll();
  }
}
