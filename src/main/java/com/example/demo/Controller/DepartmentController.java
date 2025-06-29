package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Department;
import com.example.demo.form.DepartmentForm;
import com.example.demo.form.ValidationOrder;
import com.example.demo.service.DepartmentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DepartmentController {
	
	private final DepartmentService departmentService;

	@GetMapping("/department/index")
	public String index() {
		return "departments/index";
	}
	
	@GetMapping("/department/create")
	public String create(Model model) {
		model.addAttribute("departmentForm", new DepartmentForm());
		return "departments/create";
	}
	
	@PostMapping("/department/store")
	public String store(
			@Validated(ValidationOrder.class) @ModelAttribute("departmentForm") DepartmentForm form, 
			BindingResult result, RedirectAttributes ra) {
		
		Department NameJp = departmentService.findByNameJp(form.getNameJp());
			if(NameJp != null) {
				result.rejectValue("NameJp","duplicate.department","部署名は既に存在しています。");
			}
			
			Department NameEn = departmentService.findByNameEn(form.getNameEn());
			if(NameEn != null) {
				result.rejectValue("NameEn","duplicate.department","部署名（英語）は既に存在しています。");
			}
			
			System.out.println("errors: " + result.getAllErrors());

			if(result.hasErrors()) {
				ra.addFlashAttribute("org.springframework.validation.BindingResult.departmentForm", result);
				ra.addFlashAttribute("departmentForm", form);
				return "/departments/create";
			}
		
			Department department = new Department();
			department.setNameJp(form.getNameJp());
			department.setNameEn(form.getNameEn());
			departmentService.save(department);
			
		return "redirect:/department/index";
	}
	
}
