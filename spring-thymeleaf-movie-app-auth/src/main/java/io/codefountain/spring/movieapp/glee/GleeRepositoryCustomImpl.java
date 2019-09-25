package io.codefountain.spring.movieapp.glee;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class GleeRepositoryCustomImpl implements GleeRepositoryCustom{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Page<Glee> filter(LocalDate fromDate, LocalDate toDate, LocalTime fromTime, LocalTime toTime, String text,Double value, Long userId, Pageable p) {

		String query = "from Glee where 1=1";
		String countQuery = "select count(1) from Glee where 1=1";
		Map<String, Object> params = new HashMap<>();
		
		if(Objects.nonNull(fromDate)) {
			String f = " and date >= :fromDate";
			query += f;
			countQuery += f;
			params.put("fromDate", fromDate);
		}
		if(Objects.nonNull(toDate)) {
			String f = " and date <= :toDate";
			query += f;
			countQuery += f;
			params.put("toDate", toDate);
		}
		if(Objects.nonNull(fromTime)) {
			String f = " and time >= :fromTime";
			query += f;
			countQuery += f;
			params.put("fromTime", fromTime);
		}
		if(Objects.nonNull(toTime)) {
			String f = " and time <= :toTime";
			query += f;
			countQuery += f;
			params.put("toTime", toTime);
		}
		if(Objects.nonNull(userId)) {
			String f = " and user.id = :userId";
			query += f;
			countQuery += f;
			params.put("userId", userId);
		}
		
		if(p.getSort().isSorted()) {
			query += " order by "+p.getSort().get().map(o -> o.getProperty()+" "+o.getDirection()).collect(Collectors.joining(","));
		}
		
		TypedQuery<Glee> q = em.createQuery(query, Glee.class);
		q.setMaxResults(p.getPageSize());
		q.setFirstResult((int) p.getOffset());
		
		Query c = em.createQuery(countQuery);
		params.forEach((k,v) -> {
			q.setParameter(k,v);
			c.setParameter(k, v);
		});
		
		List<Glee> list = q.getResultList();
		Long count = (Long) c.getSingleResult();	
		return new PageImpl<>(list, p, count);
	}
}
