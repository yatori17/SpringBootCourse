package com.example.carros.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    public CarroRepository carroRepository;

    public Iterable<Carro> getCarros(){
        return carroRepository.findAll();

    }
    public List<Carro> getCarrosFake(){
        List<Carro> carros = new ArrayList<>();
        carros.add(new Carro( 1L, "Fusca"));
        carros.add(new Carro( 2L, "Chevette"));
        carros.add(new Carro( 3L, "NaoseinomedoCarro"));

        return carros;
    }

    public Optional<Carro> getCarroById(long id) {
        return carroRepository.findById(id);
    }

    public Iterable<Carro> getCarroByTipo(String tipo) {
        return carroRepository.findByTipo(tipo);
    }

    public Carro save(Carro carro) {
        return carroRepository.save((carro));
    }

    public Carro update(Carro carro, Long id) {
        Assert.notNull(id, "Não foi possível atualizar o registro");
        Optional<Carro> optional = getCarroById(id);
        if (optional.isPresent()) {
            Carro c1 = optional.get(); //retorna o objeto dentro de optional
            c1.setNome(carro.getNome());
            c1.setTipo(carro.getTipo());
            System.out.println("Carro de id:" + c1.getId());

            carroRepository.save(c1);
            return c1;
        } else {
            throw new RuntimeException("Não foi possível atualizar o registro!");
        }
    }

    public void delete(Long id) {
        Assert.notNull(id, "Não foi possível excluir o registro");
        Optional<Carro> optional = getCarroById(id);
        if(optional.isPresent()){
            carroRepository.deleteById(id);
        }
    }
}