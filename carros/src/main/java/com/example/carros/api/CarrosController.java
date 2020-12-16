package com.example.carros.api;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
    @Autowired
    private CarroService carroService;

    @GetMapping()
    public Iterable<Carro> get() { return carroService.getCarros();}

    @GetMapping("/{id}")
    public Optional<Carro> get(@PathVariable("id") long id){ return carroService.getCarroById(id);} //Optional, apenas um elemento...


    @GetMapping("/tipo/{tipo}")
    public Iterable<Carro> get(@PathVariable("tipo") String tipo){ return carroService.getCarroByTipo(tipo);}

    @PostMapping
    public String post(@RequestBody Carro carro){
        Carro c = carroService.save(carro);
        return "Carro Salvo com sucesso: " + c.getId();
    }

    @PutMapping("/{id}")
    public String put(@PathVariable("id") Long id, @RequestBody Carro carro){
        Carro c = carroService.update(carro, id);
        return "Carro atualizado com sucesso e de id: " + c.getId();
    }
    @DeleteMapping("/{id}")
    public  String delete(@PathVariable("id") Long id){
        carroService.delete(id);
        return "Carro deletado com sucesso";
    }

}
