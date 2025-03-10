package br.com.caelum.ingresso.dao;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by nando on 03/03/17.
 */
@Repository
public class SessaoDao {

    @PersistenceContext
    private EntityManager manager;

    public Sessao findOne(Integer id) {

        return manager.find(Sessao.class, id);
    }

    public void save(Sessao sessao) {
        manager.persist(sessao);
    }
    
    public List<Sessao> BuscaSessoesDaSala(Sala sala) {
        return manager.createQuery("select s from Sessao s where s.sala = :sala", Sessao.class).setParameter("sala",sala).getResultList();
    }
    
    public List<Sessao> BuscaSessoesPorFilme(Filme filme) {
        return manager.createQuery("select s from Sessao s where s.filme = :filme", Sessao.class).setParameter("filme",filme).getResultList();
    }

    public List<Sessao> findAll() {
        return manager.createQuery("select s from Sessao s", Sessao.class).getResultList();
    }

    public void delete(Integer id) {
        manager.remove(findOne(id));
    }
}
