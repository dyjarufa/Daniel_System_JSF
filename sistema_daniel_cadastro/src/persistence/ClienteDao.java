package persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Cliente;

public class ClienteDao {

	Session s;
	Transaction t;
	/*
	 * Query query; Criteria criteria;
	 */

	// public void create(Cliente c) throws Exception{
	// session = HibernateUtil.getSessionFactory().openSession();
	// transaction = session.beginTransaction();
	// transaction.commit();
	// session.close();
	// }
	//
	//
	// public List<Cliente> findAll() {
	// session = HibernateUtil.getSessionFactory().openSession();
	// List<Cliente> lista= session.createQuery("from Cliente").list();
	// //Hibernate chama a classe ele lista a Tabela ...
	// session.close();
	// return lista;
	// }
	//

	public void inserir(Cliente c) throws Exception {
		s = HibernateUtil.getSessionFactory().openSession();
		t = s.beginTransaction();
		s.save(c);
		t.commit();
		s.close();

	}

	public void alterar(Cliente c) throws Exception {
		s = HibernateUtil.getSessionFactory().openSession();
		t = s.beginTransaction();
		s.update(c);
		t.commit();
		s.close();

	}

	public void excluir(Cliente c) throws Exception {
		s = HibernateUtil.getSessionFactory().openSession();
		t = s.beginTransaction();
		s.delete(c);
		t.commit();
		s.close();
	}

	public List<Cliente> pesquisar() {
		s = HibernateUtil.getSessionFactory().openSession();
		List<Cliente> lista = s.createQuery("FROM Cliente").list();
		s.close();
		return lista;

	}
	
	public Cliente pesquisar(String cpf) {
		s = HibernateUtil.getSessionFactory().openSession();
		Query q = s.createQuery("FROM Cliente c WHERE c.cpf = :param");
		q.setParameter("param", cpf);
		Cliente c = (Cliente) q.uniqueResult();
		s.close();
		return c;

		}
	
	public Cliente pesquisar(Integer cod) {
		s = HibernateUtil.getSessionFactory().openSession();
		Cliente c = (Cliente) s.get(Cliente.class, cod);
		s.close();
		return c;

		}

}
