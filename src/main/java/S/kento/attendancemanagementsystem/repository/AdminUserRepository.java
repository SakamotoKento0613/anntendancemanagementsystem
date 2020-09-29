package S.kento.attendancemanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import S.kento.attendancemanagementsystem.entity.mst.Users;

@Repository
public interface AdminUserRepository extends JpaRepository<Users, Integer> {
	//管理者検索処理
	List<Users> findByNameContainingAndEmailContainingOrderById(String name, String email);

	//emailの重複確認
	@Query(value = "select t from Users t where t.email = ?1")
	Users findByOneEmail(@Param("email") Object requestEmail);

	@Query(value = "select t.id from Users t where t.email =:email")
	String getId(@Param("email") Object requestEmail);
}
