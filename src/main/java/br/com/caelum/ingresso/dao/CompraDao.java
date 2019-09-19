package br.com.caelum.ingresso.dao;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Compra;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by nando on 03/03/17.
 */
@Repository
public class CompraDao {

    @PersistenceContext
    private EntityManager manager;

    public Compra findOne(Integer id) {

        return manager.find(Compra.class, id);
    }

    public void save(Compra Compra) {
        manager.persist(Compra);
    }
    
    public List<Compra> BuscaSessoesDaSala(Sala sala) {
        return manager.createQuery("select s from Compra s where s.sala = :sala", Compra.class).setParameter("sala",sala).getResultList();
    }
    
    public List<Compra> BuscaSessoesPorFilme(Filme filme) {
        return manager.createQuery("select s from Compra s where s.filme = :filme", Compra.class).setParameter("filme",filme).getResultList();
    }

    public List<Compra> findAll() {
        return manager.createQuery("select s from Compra s", Compra.class).getResultList();
    }

    public void delete(Integer id) {
        manager.remove(findOne(id));
    }
}
