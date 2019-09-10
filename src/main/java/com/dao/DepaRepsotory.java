package com.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entity.DepaEntity;

/**
 * 1. Repository 是一个空接口. 即是一个标记接口
 * 2. 若我们定义的接口继承了 Repository, 则该接口会被 IOC 容器识别为一个 Repository Bean.
 * 纳入到 IOC 容器中. 进而可以在该接口中定义满足一定规范的方法. 
 * 
 * 3. 实际上, 也可以通过 @RepositoryDefinition 注解来替代继承 Repository 接口
 */
/**
 * 在 Repository 子接口中声明方法
 * 1. 不是随便声明的. 而需要符合一定的规范
 * 2. 查询方法以 find | read | get 开头
 * 3. 涉及条件查询时，条件的属性用条件关键字连接
 * 4. 要注意的是：条件属性以首字母大写。
 * 5. 支持属性的级联查询. 若当前类有符合条件的属性, 则优先使用, 而不使用级联属性. 
 * 若需要使用级联属性, 则属性之间使用 _ 进行连接. 
 *
 */
//@RepositoryDefinition(domainClass=Person.class,idClass=Integer.class)
public interface DepaRepsotory extends JpaRepository<DepaEntity, Integer>,JpaSpecificationExecutor<DepaEntity>,CustomSql{
	
	/**
	 * 关键字
	 * sql的条件结果由方法名决定
	 * @param name
	 * @return
	 */
	//根据名字查询
	DepaEntity getByName(String name);
	
	//and
	DepaEntity getBynameAndId(String name,Integer id);
	
	//OR
	List<DepaEntity> getByNameOrId(String name,Integer id);
	
	//Between
	List<DepaEntity> getByIdBetween(Integer str,Integer end);
	
	//LessThan 小于
	List<DepaEntity> getByAgeLessThan(Integer age);
	
	//GreaterThan大于
	List<DepaEntity> getByAgeGreaterThan(Integer age);
	
	//After 指定时间后  Before 指定时间之前
	List<DepaEntity> getByDateAfter(Date date);
	
	//isNull为null的   idNotNull/NotNull不为null的
	List<DepaEntity> getByNameIsNull();
	
	 /**
	  * Like 不加%
	  * NotLike
	  * StanrtingWith  在前面加%
	  * EndingWith  在后面加
	  * @param name
	  * @return
	  */
	List<DepaEntity> getByNameLike(String name);
	
	
	
	
	
	
	
	/**
	 * @Query注解
	 * 使用查询不受方法关键字的约束
	 */
	//命名参数  不受参数顺序的限制
	@Query("select d from DepaEntity d where d.name=:name or d.age < :age")
	List<DepaEntity> getName(@Param("age") Integer age,@Param("name") String name);
	
	//索引参数
	@Query("select d from DepaEntity d where d.name like %?1%")
	List<DepaEntity> getLike(String name);
	
	//本地sql
	@Query(value = "select * from spring_boot_jpa where name like %?%",nativeQuery = true)
	List<DepaEntity> getAll(String name);
	
	//删除
	@Modifying
	@Query("delete from DepaEntity where id = :id")
	void delete(@Param("id")Integer id);
	
	//修改
	@Modifying
	@Query("update DepaEntity set name= :name where id= :id")
	void update(@Param("name")String name,@Param("id")Integer id);
	
	
	
	
}
 