package com.uol.compasso.yara.service;

import com.uol.compasso.yara.enuns.EnumEstado;
import com.uol.compasso.yara.exceptions.BusinessException;
import com.uol.compasso.yara.interfaces.IService;
import com.uol.compasso.yara.model.Cidade;
import com.uol.compasso.yara.repository.CidadeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
public class CidadeService implements IService<Cidade, Integer> {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Cidade create(Cidade entity) {
        return this.cidadeRepository.save(entity);
    }

    @Override
    public List<Cidade> read() {
        return this.cidadeRepository.findAll();
    }

    @Override
    public Cidade read(Integer id) {
        return this.cidadeRepository.findById(id).orElseThrow(()->new BusinessException("Não foi possível encontrar o registro solicitado"));
    }

    public Page<Cidade> read(String nome, Pageable pageable) {
        return this.cidadeRepository.page(nome, pageable);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void update(Cidade entity) {
        this.cidadeRepository.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void deleteById(Integer id) {
        this.cidadeRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void delete(Cidade entity) {
        this.cidadeRepository.delete(entity);
    }
}
