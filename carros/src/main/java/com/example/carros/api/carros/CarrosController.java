package com.example.carros.api.carros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
    @Autowired
    private CarroService carroService;

    @GetMapping() //R
    public ResponseEntity<List<CarroDTO>> get(@RequestParam (value = "page", defaultValue = "0") Integer page,
                                              @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResponseEntity.ok(carroService.getCarros(PageRequest.of(page,size)));
        //return new ResponseEntity<>(carroService.getCarros(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") long id){
        CarroDTO carro = carroService.getCarroById(id);
        return ResponseEntity.ok(carro);

       // return carro.map(ResponseEntity::ok)
       //         .orElse(ResponseEntity.notFound().build());

    } //Optional, apenas um elemento...


    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<CarroDTO>> getCarByType(@PathVariable("tipo") String tipo,
                                                       @RequestParam (value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "10") Integer size){
        List<CarroDTO> carros = carroService.getCarroByTipo(tipo , PageRequest.of(page,size));
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity post(@RequestBody Carro carro) {

            CarroDTO c = carroService.save(carro);
            URI location = getUri(c.getId());
            return ResponseEntity.created(location).build();

    }
    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Carro carro){
        CarroDTO c = carroService.update(carro, id);
        return c!= null?ResponseEntity.ok(c):
                ResponseEntity.notFound().build();

    }
    @DeleteMapping("/{id}")
    public  ResponseEntity delete(@PathVariable("id") Long id){
        carroService.delete(id);
        return ResponseEntity.ok().build();
    }

}
