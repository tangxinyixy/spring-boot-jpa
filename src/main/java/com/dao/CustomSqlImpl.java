package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.entity.DepaEntity;

/**
 * 自定义sql  实现类的名字要和接口名一样  然后在加上Impl后缀  后缀可以改
 * 
 * 不知道sql怎么就去看HibernateMain项目
 * @author TXY 
 *
 * 2019年9月10日下午5:16:53
 */
public class CustomSqlImpl implements CustomSql{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<DepaEntity> depaList() {
		Query query = (Query)entityManager.createQuery("from DepaEntity");
		List<DepaEntity> list = query.getResultList();
		return list;
	}

}
