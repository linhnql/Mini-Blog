package com.miniblog.poc.service.search;

import com.miniblog.poc.service.entity.BlogEntity;
import com.miniblog.poc.service.entity.ContactEntity;
import lombok.AllArgsConstructor;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
@AllArgsConstructor
public class BlogSearch {
    private static final String TITLE = "title";
    private static final String CATEGORY = "category";
    private static final String CREATED_AT = "time";
    private static final String DETAIL = "detail";

    private EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;

    public List<BlogEntity> getBlogs(Integer page, Integer pageSize) {

        entityManager = entityManagerFactory.createEntityManager();

        FullTextEntityManager fullTextEntityManager = initializedFullTextEntityManager();

        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(BlogEntity.class).get();

        BooleanJunction<BooleanJunction> booleanJunction = qb.bool();

        booleanJunction.should(qb.all().createQuery());

        return getBlogEntities(fullTextEntityManager, qb, booleanJunction, page, pageSize);
    }

    private List<BlogEntity> getBlogEntities(FullTextEntityManager fullTextEntityManager, QueryBuilder qb, BooleanJunction<BooleanJunction> booleanJunction, Integer page, Integer pageSize) {

        FullTextQuery query = fullTextEntityManager.createFullTextQuery(booleanJunction.createQuery(), BlogEntity.class);

        handlePagination(query, page, pageSize);

        List<BlogEntity> BlogEntities = query.getResultList();

        commitTransaction();

        return BlogEntities;
    }

    private void handlePagination(FullTextQuery query, Integer page, Integer pageSize) {
        if (pageSize != null) {
            query.setMaxResults(pageSize);

            if (page != null) {
                query.setFirstResult((page - 1) * pageSize);
            }
        } else {
            if (page != null) {
                pageSize = 5;

                query.setFirstResult((page - 1) * pageSize);
                query.setMaxResults(pageSize);
            }
        }
    }

    private FullTextEntityManager initializedFullTextEntityManager() {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        entityManager.getTransaction().begin();

        return fullTextEntityManager;
    }

    private void commitTransaction() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
