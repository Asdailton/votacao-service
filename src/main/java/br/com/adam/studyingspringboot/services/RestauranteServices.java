package br.com.adam.studyingspringboot.services;

import br.com.adam.studyingspringboot.controllers.RestauranteController;
import br.com.adam.studyingspringboot.dtos.PersonDTO;
import br.com.adam.studyingspringboot.dtos.RestauranteDTO;
import br.com.adam.studyingspringboot.infra.exceptions.PersonException;
import br.com.adam.studyingspringboot.infra.exceptions.RestauranteException;
import br.com.adam.studyingspringboot.model.Person;
import br.com.adam.studyingspringboot.model.RestauranteModel;
import br.com.adam.studyingspringboot.repositories.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.Link;
import java.util.List;
import java.util.Optional;

@Service
public class RestauranteServices {
    @Autowired
    RestauranteRepository restauranteRepository;

    public List<RestauranteModel> findAll(){
        List<RestauranteModel> restauranteList = restauranteRepository.findAll();
        if(!restauranteList.isEmpty()){
            for(RestauranteModel restaurante : restauranteList){
                restaurante.add(linkTo(RestauranteController.class).slash(restaurante.getId()).withSelfRel());
            }
        }
        return restauranteList;
    }
    public RestauranteModel findById(long id){
        Optional<RestauranteModel> entity = restauranteRepository.findById(id);

        if(entity.isEmpty()){
            throw new RestauranteException();
        }
        entity.get().add(linkTo(RestauranteController.class).withRel("Restaurante List"));
        BeanUtils.copyProperties(entity, RestauranteDTO.class);
        return entity.get();
    }
    public RestauranteDTO create(RestauranteDTO restaurante){
        var entity = new RestauranteModel();
        BeanUtils.copyProperties(restaurante, entity);
        restauranteRepository.save(entity);
        return restaurante;

    }
    public RestauranteDTO update(RestauranteDTO restauranteDTO, long id){
        Optional<RestauranteModel> entity = restauranteRepository.findById(id);
        if(entity.isEmpty()){
            throw new RestauranteException();
        }
        var restaurante = entity.get();
        BeanUtils.copyProperties(restauranteDTO, restaurante );
        restauranteRepository.save(restaurante);
        return restauranteDTO;
    }
    public boolean delete(long id) {
        Optional<RestauranteModel> entity = restauranteRepository.findById(id);
        if (entity.isEmpty()) {
            throw new RestauranteException("Restaurante não encontrado.");
        }

        try {
            restauranteRepository.delete(entity.get());
            return true; // Exclusão bem-sucedida
        } catch (Exception e) {
            throw new RestauranteException();
        }
    }
}
