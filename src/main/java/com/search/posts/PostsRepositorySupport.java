package com.search.posts;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PostsRepositorySupport {

    @PersistenceContext
    private EntityManager em;

    public List<Posts> findByKeywords(List<String> keyword, List<String> censored) {
        StringBuilder query = new StringBuilder("select p from Posts p where ");
        int size = keyword.size();
        for (String s : censored) {
            query.append("p.content not like '%").append(s).append("%'");
            query.append(" and ");
        }
        for(int i = 0; i < size; i++) {
            query.append("p.content like '%").append(keyword.get(i)).append("%'");
            if(i != size-1) {
                query.append(" or ");
            }
        }
        String sql = query.toString();

        return em.createQuery(sql, Posts.class)
                .getResultList();
    }
}
