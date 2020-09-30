package S.kento.attendancemanagementsystem.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import S.kento.attendancemanagementsystem.entity.mst.Users;
import S.kento.attendancemanagementsystem.repository.AdminUserRepository;
import S.kento.attendancemanagementsystem.request.AdminUserRequest;

@Service
@Transactional(rollbackOn = Exception.class)
public class AdminUserService {
	@Autowired
	AdminUserRepository adminUserRepository;

	/**
	 * 管理者情報登録処理
	 * @param users
	 * @throws ParseException
	 * @todo setのユーザIDはログイン機能実装後、セッション情報から持ってくる。
	 */
	public void add(AdminUserRequest adminUserRequest) throws ParseException {
		Users adminUser = new Users();
		Date date = new Date();
		TimeZone timezone = TimeZone.getTimeZone("Asia/Tokyo");
		SimpleDateFormat fmt = new SimpleDateFormat();
		fmt.setTimeZone(timezone);
		Date now = fmt.parse(fmt.format(date));
		adminUser.setId(adminUserRequest.getId());
		adminUser.setEmail(adminUserRequest.getEmail());
		adminUser.setName(adminUserRequest.getName());
		adminUser.setPassword(adminUserRequest.getPassword());
		adminUser.setCreatedAt(now);
		adminUser.setCreatedAtUser(1);//Todo
		adminUser.setUpdatedAt(now);
		adminUser.setUpdatedAtUser(1);//Todo
		adminUserRepository.save(adminUser);
	}

	/**
	 * 管理者一覧表示機能
	 * @return 全検索結果
	 */
	public List<Users> allFind() {
		return adminUserRepository.findAll();
	}

	/**
	 * 管理者検索機能
	 * @param name
	 * @param email
	 * @return 検索結果（あいまい検索）
	 */
	public List<Users> findByNameAndLoginId(String name, String email) {
		return adminUserRepository.findByNameContainingAndEmailContainingOrderById(name, email);
	}

	/**
	 * 一覧画面から編集画面へ遷移するとき対象の管理者idを検索
	 * @param id
	 * @return  検索結果（id）
	 */
	public Users findById(Integer id) {
		return adminUserRepository.findById(id).get();
	}

	/**
	 * 管理者更新機能
	 * @param adminUserRequest
	 * @throws ParseException
	 */
	public void adminUserUpdate(AdminUserRequest adminUserRequest) throws ParseException {
		Date date = new Date();
		TimeZone timezone = TimeZone.getTimeZone("Asia/Tokyo");
		SimpleDateFormat fmt = new SimpleDateFormat();
		fmt.setTimeZone(timezone);
		Date now = fmt.parse(fmt.format(date));
		Users adminUser = findById(adminUserRequest.getId());
		adminUser.setEmail(adminUserRequest.getEmail());
		adminUser.setName(adminUserRequest.getName());
		adminUser.setPassword(adminUserRequest.getPassword());
		adminUser.setUpdatedAt(now);
		adminUser.setUpdatedAtUser(1);//Todo
		adminUserRepository.save(adminUser);
	}

	/**
	 * 管理者削除機能
	 * @throws ParseException
	 * @param adminUserRequest
	 */
	public void adminUserDelete(AdminUserRequest adminUserRequest) throws ParseException {
		Date date = new Date();
		TimeZone timezone = TimeZone.getTimeZone("Asia/Tokyo");
		SimpleDateFormat fmt = new SimpleDateFormat();
		fmt.setTimeZone(timezone);
		Date now = fmt.parse(fmt.format(date));
		Users adminUser = findById(adminUserRequest.getId());
		adminUser.setUpdatedAt(now);
		adminUser.setUpdatedAtUser(1);//Todo
		adminUser.setDeletedAt(now);
		adminUser.setDeletedAtUser(1);//Todo
		adminUserRepository.save(adminUser);
	}
}
