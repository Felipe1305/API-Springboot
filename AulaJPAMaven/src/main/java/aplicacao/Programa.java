package aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Pessoa;

public class Programa {

	public static void main(String[] args) {
//		Pessoa p1 = new Pessoa(null, "Felipe Silva", "felipe@gmail.com");
//				Pessoa p2 = new Pessoa(null, "Maria Silva", "maria@gmail.com");
//				Pessoa p3 = new Pessoa(null, "José Silva", "jose@gmail.com");
//								
//				EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
//				EntityManager em = emf.createEntityManager();
//				em.getTransaction().begin();
//				
//				em.persist(p1);
//				em.persist(p2);
//				em.persist(p3);
//				
//				em.getTransaction().commit();
//				
//		Os objetos acimas já foram instaciados na base de dados!! Seguindo com o exercicio.
				System.out.println("Pronto!");
				
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
				EntityManager em = emf.createEntityManager();
				
				Pessoa p = em.find(Pessoa.class, 2);
				
				System.out.println(p);
				
				
				Pessoa p1 = em.find(Pessoa.class, 3);
				//Apagando uma pessoa do banco de dados. JPA só pode remover entidade que esteja monitorada.
				//Entidade monitarada é aquela para poder sofrer alterações tem que acabar de ser inseridade ou ser recuperada do banco imediatamente.
				em.getTransaction().begin();
				em.remove(3);
				em.getTransaction().commit();
				System.out.println("Pessoa excluída com sucesso!");
				
				
				
				em.close();
				emf.close();
				
	}

}
