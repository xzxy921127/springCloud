package com.xzxy.model;

/**
 * 此类废弃,已改用EBean框架来做持久化
 * @author XZXY
 *
 */
@Deprecated
public interface UserRepository /* extends PagingAndSortingRepository<User, Long>,JpaSpecificationExecutor<User>*/ {
/*	
    @Query("from User u where u.id=:id")
    User find(@Param("id") Long id);
    
    @Query("from User")
    List<User> findAll();
    
    @Query("from User u where u.name=:name")
    User findUserByName(@Param("name") String name);
    
    @Query("from User u where u.age=:age")
    List<User> findUserByAge(@Param("age") Integer age);
    
    @Modifying
    @Transactional
    @Query("update User u set u.name = :name, u.age = :age where u.id = :id")
    void update(@Param("name")String name,@Param("age")Integer age, @Param("id")Long id);
    
    @Modifying
    @Transactional
    @Query("delete from User u where u.id = :id")
    void delete(@Param("id")Long id);

    @Query("from User u where u.id=:id")
	User getUserById(@Param("id")Long id);
*/
}