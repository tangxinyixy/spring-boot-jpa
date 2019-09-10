package com.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.DepaRepsotory;
import com.entity.DepaEntity;

@Service
public class DepaService {
	
	@Autowired
	private DepaRepsotory depaRepsotory;
	
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<DepaEntity> findAll()
	{
		return depaRepsotory.findAll();
	}
	
	/**
	 * 查询单条
	 * @param id
	 * @return
	 */
	public Object getOne(Integer id)
	{
//		DepaEntity depaEntity = depaRepsotory.getOne(id);
		Object object = depaRepsotory.findById(id);
		return object;
	}
	
	
	/**
	 * 添加
	 * @param depaEntity
	 */
	public void save(DepaEntity depaEntity)
	{
		depaRepsotory.save(depaEntity);
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Integer id) 
	{
		depaRepsotory.deleteById(id);
	}
	
	/**
	 * 修改
	 * @param depaEntity
	 */
	public void update(DepaEntity depaEntity)
	{
		depaRepsotory.save(depaEntity);
	}
	
	/**
	 * JPQL
	 */
	/**
	 * 根据名字查询
	 * @param name
	 */
	public DepaEntity getByName(String name)
	{
		return depaRepsotory.getByName(name);
	}
	
	/**
	 * and关键字
	 * @param name
	 * @param id
	 * @return
	 */
	public DepaEntity getBynameAndId(String name,Integer id)
	{
		return depaRepsotory.getBynameAndId(name, id);
	}
	
	
	/**
	 * OR关键字
	 */
	public List<DepaEntity> getByNameOrId(String name,Integer id)
	{
		return depaRepsotory.getByNameOrId(name, id);
	}
	
	/**
	 * Between
	 */
	public List<DepaEntity> getByIdBetween(Integer str,Integer end)
	{
		return depaRepsotory.getByIdBetween(str, end);
	}
	/**
	 * //LessThan 小于
	 */
	public List<DepaEntity> getByAgeLessThan(Integer age)
	{
		return depaRepsotory.getByAgeLessThan(age);
	}
	
	/**
	 * //GreaterThan大于
	 */
	public List<DepaEntity> getByAgeGreaterThan(Integer age)
	{
		return depaRepsotory.getByAgeGreaterThan(age);
	}
	
	/**
	 * //After 指定时间后  Before 指定时间之前
	 */
	public List<DepaEntity> getByDateAfter(Date date)
	{
		return depaRepsotory.getByDateAfter(date);
	}
	
	/**
	 * sNull为null的   idNotNull/NotNull不为null的
	 * @return
	 */
	public List<DepaEntity> getByNameIsNull()
	{
		return depaRepsotory.getByNameIsNull();
	}
	
	//Like
	public List<DepaEntity> getByNameLike(String name)
	{
		return depaRepsotory.getByNameLike(name);
	}
	
	
	
	
	
	
	
	/**
	 * 分页
	 */
	public Page<DepaEntity> testPagingAndSortingRespository(PageRequest pageRequest)
	{
		return depaRepsotory.findAll(pageRequest);
	}
	
	
	
	
	/**
	 * @Query注解
	 */
	
	/**
	 * 命名参数
	 * @param age
	 * @param name
	 * @return
	 */
	public List<DepaEntity> getByNameOrAgeLessThen(Integer age ,String name)
	{
		return depaRepsotory.getName(age, name);
	}
	
	/**
	 * 索引参数
	 * @param name
	 * @return
	 */
	public List<DepaEntity> getLike(String name)
	{
		return depaRepsotory.getLike(name);
	}
	
	/**
	 * 本地sql
	 * @param name
	 * @return
	 */
	public List<DepaEntity> getAll(String name)
	{
		return depaRepsotory.getAll(name);
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@Transactional  //这里一定加事物 不然无法执行
	public void delete(Integer id) 
	{
		depaRepsotory.delete(id);
	}
	
	/**
	 * 修改
	 * @param age
	 * @param name
	 * @param date
	 */
	@Transactional
	public void update(String name,Integer id)
	{
		depaRepsotory.update(name,id);
	}
	
	/**
	 * 自定义sql
	 * 查询所有
	 * @return
	 */
	public List<DepaEntity> entityManagerAll()
	{
		return depaRepsotory.depaList();
	}
	
}
