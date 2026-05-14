package com.kedu_spring_data_jpa.service;

import com.kedu_spring_data_jpa.entity.AutorInfo;
import com.kedu_spring_data_jpa.repository.AutorInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AutorInfoService {

    @Autowired
    private AutorInfoRepository repository;

    public AutorInfo findById(Long id){
        var info = new AutorInfo();
        info.setId(id);
        return repository.findOne(Example.of(info)).orElseGet(AutorInfo::new);
    }

    public List<AutorInfo> findAllContainsCargo(String cargo){
        var info = new AutorInfo();
        info.setCargo(cargo);

        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("cargo", ExampleMatcher.GenericPropertyMatchers.contains());

        return repository.findAll(Example.of(info, matcher));

    }

    public List<AutorInfo> findAllCargoAndEmpresa(String cargo, String empresa){
        var info = new AutorInfo();
        info.setCargo(cargo);
        info.setEmpresa(empresa);

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("cargo", ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withMatcher("empresa", ExampleMatcher.GenericPropertyMatchers.contains());

        return repository.findAll(Example.of(info, matcher));

    }

    public AutorInfo findFromBio(String bio){
        var info = new AutorInfo();
        info.setBio(bio);

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("bio", ExampleMatcher.GenericPropertyMatchers.contains());


        return repository.findBy(
                Example.of(info, matcher),
                query -> query.sortBy(Sort.by("cargo").descending()).first()
        ).orElseGet(AutorInfo::new);

    }

}
