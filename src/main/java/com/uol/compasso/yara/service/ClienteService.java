package com.uol.compasso.yara.service;

import com.uol.compasso.yara.exceptions.BusinessException;
import com.uol.compasso.yara.interfaces.IService;
import com.uol.compasso.yara.model.Cliente;
import com.uol.compasso.yara.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService implements IService<Cliente, Integer> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Cliente create(Cliente entity) {
        return this.clienteRepository.save(entity);
    }

    @Override
    public List<Cliente> read() {
        return this.clienteRepository.findAll();
    }

    @Override
    public Cliente read(Integer id) {
        return this.clienteRepository.findById(id).orElseThrow(()->new BusinessException("Não foi possível encontrar o registro solicitado"));
    }
    public Page<Cliente> read(String nome, Pageable pageable) {
        return this.clienteRepository.page(nome,  pageable);
    }
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void update(Cliente entity) {
        this.clienteRepository.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void deleteById(Integer id) {
        this.clienteRepository.deleteById(id);
    }
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void delete(Cliente entity) {
        this.clienteRepository.delete(entity);
    }
}
