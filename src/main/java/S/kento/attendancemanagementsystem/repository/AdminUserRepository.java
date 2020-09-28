package S.kento.attendancemanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import S.kento.attendancemanagementsystem.entity.mst.Users;

@Repository
public interface AdminUserRepository extends JpaRepository<Users, Integer> {
	//管理者検索処理
	List<Users> findByNameContainingAndEmailContainingOrderById(String name, String email);
}
