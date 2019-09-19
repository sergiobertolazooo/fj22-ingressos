package br.com.caelum.ingresso.dao;

import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;

import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by nando on 03/03/17.
 */
@Repository
public class LugarDao {

    @PersistenceContext
    private EntityManager manager;

    public void save(Lugar lugar) {
        manager.persist(lugar);
    }
    
    public Lugar findOne(Integer id) {

        return manager.find(Lugar.class, id);
    }
 
    public List<Lugar> findAll() {
        return manager.createQuery("select s from Lugar s", Lugar.class).getResultList();
    }
}
