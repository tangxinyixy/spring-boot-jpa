package com.dao;

import java.util.List;

import com.entity.DepaEntity;

/**
 * 自定义sql 要使用的接口  在Repsotory中继承这个接口就可以使用里面的方法了 ,不需要在Repsotory中定义(这就变成了所谓的自定义sql了
 * @author TXY 
 *
 * 2019年9月10日下午5:32:02
 */
public interface CustomSql {
	List<DepaEntity> depaList();
}
