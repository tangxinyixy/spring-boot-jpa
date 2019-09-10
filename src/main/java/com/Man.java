package com;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.dao.DepaRepsotory;
import com.entity.DepaEntity;
import com.service.DepaService;

public class Man {
	
	@Test
	public void test() {
		System.out.println("=============");
	}
	
	private ApplicationContext ctx = null;
	private DepaRepsotory depaRepsotory = null;
	private DepaService depaService = null;
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		depaRepsotory = ctx.getBean(DepaRepsotory.class);
		depaService = ctx.getBean(DepaService.class);
	}
	
//	@Test
//	public void testCommonCustomRepositoryMethod(){
//		ApplicationContext ctx2 = new ClassPathXmlApplicationContext("classpath:/applicationContext2.xml");
//		AddressRepository addressRepository = ctx2.getBean(AddressRepository.class);
//		addressRepository.method();
//	}
	
	/**
	 * 添加
	 */
	@Test
	public void seva() {
		
		DepaEntity depaEntity = new DepaEntity();
		depaEntity.setAge(20);
		depaEntity.setName("TXY");
		depaService.save(depaEntity);
		
	}
	
	/**
	 * 删除
	 */
	@Test
	public void deleteById() {
		
		depaService.deleteById(1);
		
	}
	
	
	/**
	 * 查询所有
	 */
	@Test
	public void findAll() {
		
		List<DepaEntity> list = depaService.findAll();
		for (DepaEntity depaEntity : list) {
			System.out.println(depaEntity);
		}
		
	}
	
	/**
	 * 查询单条
	 * @param id
	 * @return
	 */
	@Test
	public void findOne()
	{
		Object object = depaService.getOne(3);
		System.out.println(object);
	}
	
	/**
	 * 修改
	 */
	@Test
	public void update()
	{
		DepaEntity depaEntity = new DepaEntity();
		depaEntity.setId(3);
		depaEntity.setAge(18);
		depaEntity.setName("TYY");
		depaService.update(depaEntity);
	}
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");         
	Date date = null; 
	/**
	 * sql的条件结果由方法名决定
	 * 
	 */
	@Test
	public void getByName()
	{
		//根据名字查询
//		DepaEntity depaEntity = depaService.getByName("TYY");
		
		//And
//		DepaEntity depaEntity = depaService.getBynameAndId("TYY",3);
		
		//Or
//		List<DepaEntity> list = depaService.getByNameOrId("TXY",3);
		
		//Between
//		List<DepaEntity> list = depaService.getByIdBetween(1,2);
		
		//LessThan 小于
//		List<DepaEntity> list = depaService.getByAgeLessThan(20);
		
		//大于
//		List<DepaEntity> list = depaService.getByAgeGreaterThan(18);
		
//		try {
//			date = format.parse("2019-09-06");
			//时间类型转换https://www.cnblogs.com/sharpest/p/7879377.html
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		//指定时间后
//		List<DepaEntity> list = depaService.getByDateAfter(date);
		
		//sNull为null的   idNotNull/NotNull不为null的
//		List<DepaEntity> list = depaService.getByNameIsNull();
		
		//Like
		List<DepaEntity> list = depaService.getByNameLike("T");
		
//		System.out.println(depaEntity);
		for (DepaEntity depaEntity2 : list) {
			System.out.println(depaEntity2);
		}
	}
	
	
	
	/**
	 * 分页
	 */
	@Test
	public void testPagingAndSortingRespository(){
		//pageNo 从 0 开始. 
		int pageNo = 0;
		int pageSize = 2;
		//Pageable 接口通常使用的其 PageRequest 实现类. 其中封装了需要分页的信息
		//排序相关的. Sort 封装了排序的信息
		//DESC降序  asc升序
		
		//只排序
		Sort sort = new Sort(Sort.Direction.DESC, "id");
        List<DepaEntity> list = depaRepsotory.findAll(sort);
        for(DepaEntity depaEntity : list) {
            System.out.println(depaEntity);
        }
		//分页和排序
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.ASC,"age","id");
		//只分页
//		PageRequest pageable = PageRequest.of(pageNo, pageSize);
		Page<DepaEntity> page = depaService.testPagingAndSortingRespository(pageable);
		System.out.println("当前页面的 List: " + page.getContent());
	}
	
	
	/**
	 * Query注解
	 */
	
	//命名参数
	@Test
	public void getByNameOrAgeLessThen()
	{
		List<DepaEntity> list = depaService.getByNameOrAgeLessThen(18,"TYY");
		for (DepaEntity depaEntity : list) {
			System.out.println(depaEntity);
		}
	}
	
	//索引参数
	@Test
	public void getLike() 
	{
		List<DepaEntity> list = depaService.getLike("T");
		for (DepaEntity depaEntity : list) {
			System.out.println(depaEntity);
		}
	}
	
	//本地sql
	@Test
	public void getAll()
	{
		List<DepaEntity> list = depaService.getAll("");
		for (DepaEntity depaEntity : list) {
			System.out.println(depaEntity);
		}
	}
	
	/**
	 * 删除
	 */
	@Test
	public void delete()
	{
		depaService.delete(4);
	}
	
	/**
	 * 修改
	 */
	@Test
	public void update1()
	{
		depaService.update("TXX",3);
	}
	
	
	//分页和模糊查询
	/**
	 * 目标: 实现带查询条件的分页. id > 5 的条件
	 * 继承这个之后  JpaSpecificationExecutor
	 * 调用 JpaSpecificationExecutor 的 Page<T> findAll(Specification<T> spec, Pageable pageable);
	 * Specification: 封装了 JPA Criteria 查询的查询条件
	 * Pageable: 封装了请求分页的信息: 例如 pageNo, pageSize, Sort
	 */
	@Test
	public void testJpaSpecificationExecutor(){
		int pageNo = 0;
		int pageSize = 2;
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC,"age","id");
		Specification<DepaEntity> specification = new Specification<DepaEntity>() {
			/**
			 * @param *root: 代表查询的实体类. 
			 * @param query: 可以从中可到 Root 对象, 即告知 JPA Criteria 查询要查询哪一个实体类. 还可以
			 * 来添加查询条件, 还可以结合 EntityManager 对象得到最终查询的 TypedQuery 对象. 
			 * @param *cb: CriteriaBuilder 对象. 用于创建 Criteria 相关对象的工厂. 当然可以从中获取到 Predicate 对象
			 * @return: *Predicate 类型, 代表一个查询条件. 
			 */
			@Override
			public Predicate toPredicate(Root<DepaEntity> root, CriteriaQuery<?> arg1, CriteriaBuilder cb) {
				
				//第一个条件
				Path<Integer> path = root.get("id");
				Predicate predicate = cb.gt(path,2);
				//第二个条件
				Path<String> path1 = root.get("name");
				Predicate predicate1 = cb.like(path1,"%T%");
				
//				cb.or(predicate,cb.and(predicate1))  //返回多个条件
				return cb.and(predicate,predicate1);  //返回多个条件
			}
		};
		
		Page<DepaEntity> page = depaRepsotory.findAll(specification, pageable);
		System.out.println("当前页面的 List: " + page.getContent());
	}
	
	/**
	 * 自定义sql
	 */
	@Test
	public void entityManagerAll()
	{
		List<DepaEntity> list = depaService.entityManagerAll();
		for (DepaEntity depaEntity : list) {
			System.out.println(depaEntity);
		}
	}
	
	
}
