package br.com.adam.studyingspringboot.services;

import br.com.adam.studyingspringboot.dtos.ComentDTO;
import br.com.adam.studyingspringboot.model.ComentModel;
import br.com.adam.studyingspringboot.model.Opcao;
import br.com.adam.studyingspringboot.repositories.ComentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentServices {
    @Autowired
    private ComentRepository comentRepository;

    public List<ComentModel> findAllByWebSite() {
        // Retorna todos os comentários com a opção WEBSITE
        return comentRepository.findAll().stream()
                .filter(coment -> coment.getOpcao() == Opcao.WEBSITE)
                .collect(Collectors.toList());
    }

    public List<ComentModel> findAllByRestaurante() {
        // Retorna todos os comentários com a opção RESTAURANTE
        return comentRepository.findAll().stream()
                .filter(coment -> coment.getOpcao() == Opcao.RESTAURANTE)
                .collect(Collectors.toList());
    }

    public ComentDTO createComent(ComentDTO comentDTO) {
        ComentModel entity = new ComentModel();

        // Mapear manualmente todos os campos
        entity.setNome(comentDTO.nome());
        entity.setOpcao(comentDTO.opcao());

        entity.setComentario(comentDTO.comentario());
        entity.setTimestampp(comentDTO.timestampp());
        entity.setEstrela(comentDTO.estrela());

        // Salvar a entidade no repositório
        ComentModel savedEntity = comentRepository.save(entity);

        // Retornar o DTO com os dados salvos
        return new ComentDTO(
                savedEntity.getNome(),
                savedEntity.getOpcao(),

                savedEntity.getComentario(),
                savedEntity.getTimestampp(),
                savedEntity.getEstrela()
        );
    }


}