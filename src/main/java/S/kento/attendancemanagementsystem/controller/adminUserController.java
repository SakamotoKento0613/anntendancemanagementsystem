package S.kento.attendancemanagementsystem.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import S.kento.attendancemanagementsystem.entity.mst.Users;
import S.kento.attendancemanagementsystem.request.AdminUserRequest;
import S.kento.attendancemanagementsystem.service.AdminUserService;


@Controller
public class adminUserController {
	@Autowired
	AdminUserService adminUserService;

	/**
	 * 管理者登録画面表示
	 * @return 管理者登録画面
	 */
	@GetMapping(value = "/add")
	public String adminUserAddDisplay(Model model) {
		model.addAttribute("adminUsers", new AdminUserRequest());
		return "add";
	}

	/**
	 * 管理者登録機能
	 * @param adminUserRequest
	 * @param model Model
	 * @return add
	 * @throws ParseException
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String adminUserAddFunction(@Validated @ModelAttribute("adminUsers") AdminUserRequest adminUserRequest,
			BindingResult result, Model model, RedirectAttributes redirectAttributes) throws ParseException{
		//バリデーションエラー
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("errorMsg", errorList);
			return "add";
		}
		//登録機能
		adminUserService.add(adminUserRequest);
		return "redirect:add";
	}

	/**
	 * 管理者一覧検索画面表示
	 * @return 管理者一覧検索画面
	 */
	@GetMapping(value = "/list")
	public String adminUserListDisplay(Model model) {
		List<Users> adminUsers = new ArrayList<Users>();
		adminUsers = adminUserService.allFind();
		model.addAttribute("keyWord", new AdminUserRequest());
		model.addAttribute("adminUsers", adminUsers);
		return "list";
	}

	/**
	 *管理者検索機能
	 *@return 管理者一覧検索画面
	 */
	@GetMapping("/list/search")
	public String adminUserFindFunction(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "email", required = false) String email, Model model) {
		List<Users> result = adminUserService.findByNameAndLoginId(name,email);
		model.addAttribute("adminUsers", result);
		AdminUserRequest adminUserRequest = new AdminUserRequest();
		adminUserRequest.setName(name);
		adminUserRequest.setEmail(email);
		model.addAttribute("keyWord", adminUserRequest);
		return "list";
	}

	/**
	 * 管理者編集画面表示
	 * @param id
	 * @param model Model
	 * return edit
	 */
	@GetMapping(value = "/edit/{id}")
	public String adminUserEditDisplay(@PathVariable Integer id, Model model) {
		Users adminUsers = adminUserService.findById(id);
		AdminUserRequest adminUserRequest = new AdminUserRequest();
		adminUserRequest.setId(adminUsers.getId());
		adminUserRequest.setEmail(adminUsers.getEmail());
		adminUserRequest.setName(adminUsers.getName());
		model.addAttribute("adminUserRequest", adminUserRequest);
		return "/edit";
	}

	/**
	 * 更新機能
	 * @param AdminUserRequest
	 * @param model Model
	 * @return 管理者編集画面
	 * @throws ParseException
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String adminUserUpdateFunction(@Validated @ModelAttribute("adminUserRequest") AdminUserRequest adminUserRequest,
			BindingResult result, Model model) throws ParseException {
		//バリデーションエラー
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("errorMsg", errorList);
			return "edit";
		}
		adminUserService.adminUserUpdate(adminUserRequest);
		return "/edit";
	}

	/**
	 * 削除機能
	 * @param model
	 * @return 管理者一覧検索画面
	 * @throws ParseException
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String adminUsetDeleteFunction(@ModelAttribute AdminUserRequest adminUserRequest,
			RedirectAttributes redirectAttributes) throws ParseException {
		adminUserService.adminUserDelete(adminUserRequest);
		return "redirect:list";
	}
}
