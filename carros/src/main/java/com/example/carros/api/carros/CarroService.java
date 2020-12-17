package com.example.carros.api.carros;


import com.example.carros.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    public CarroRepository carroRepository;

    public List<CarroDTO> getCarros(){
        List<Carro> carros = carroRepository.findAll();
        List<CarroDTO> list = new ArrayList<>();

        for(Carro c: carros){
            list.add(CarroDTO.create(c));
        }
        return list;
        //List<CarroDTO> list =carros.stream().map(c -> new CarroDTO(c)).collect((Collectors.toList());
    }
   // public List<Carro> getCarrosFake(){
   //     List<Carro> carros = new ArrayList<>();
   //     carros.add(new Carro( 1L, "Fusca"));
   //     carros.add(new Carro( 2L, "Chevette"));
   //     carros.add(new Carro( 3L, "NaoseinomedoCarro"));

    //    return carros;
    //}

    public CarroDTO getCarroById(long id) {
        Optional<Carro> carro = carroRepository.findById(id);
        return carro.map(CarroDTO::create).orElseThrow( () -> new ObjectNotFoundException("Carro não encontrado"));

    }

    public List<CarroDTO> getCarroByTipo(String tipo) {

        return carroRepository.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public CarroDTO save(Carro carro) {
       // Assert.isNull(carro.getId(), "Nao foi possivel inserir o registro" );

        return CarroDTO.create(carroRepository.save(carro));
    }

    public CarroDTO update(Carro carro, Long id) {
        Assert.notNull(id, "Não foi possível atualizar o registro");
        Optional<Carro> optional = carroRepository.findById(id);
        if (optional.isPresent()) {
            Carro c1 = optional.get(); //retorna o objeto dentro de optional
            c1.setNome(carro.getNome());
            c1.setTipo(carro.getTipo());
            System.out.println("Carro de id:" + c1.getId());

            carroRepository.save(c1);
            return CarroDTO.create(c1);
        } else {
            return  null;
        }
    }

    public void delete(Long id) {
            carroRepository.deleteById(id);
        }
}