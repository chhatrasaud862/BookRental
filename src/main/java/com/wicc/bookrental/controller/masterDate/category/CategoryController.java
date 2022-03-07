package com.wicc.bookrental.controller.masterDate.category;

import com.wicc.bookrental.dto.CategoryDto;
import com.wicc.bookrental.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
@GetMapping("/home")
public String openCategoryHome(Model model)
{
    model.addAttribute("categoryDto",new CategoryDto());
    model.addAttribute("categoryList",categoryService.findAll());
    return "masterData/category/categoryHome";
}
@GetMapping("/page")
public String openCategoryPage(Model model)
{
    if(model.getAttribute("categoryDto")==null)
    model.addAttribute("categoryDto",new CategoryDto());
    return "masterData/category/categoryPage";
}
@PostMapping("/create")
public String createCategory(@ModelAttribute CategoryDto categoryDto,
                             RedirectAttributes redirectAttributes)
{
    try {
        categoryDto = categoryService.saveCategory(categoryDto);
    }catch (Exception e)
    {
        e.printStackTrace();
        return "redirect:/category/home";
}
    return "redirect:/category/home";
}
@GetMapping("/edit/{id}")
        public String editCategory(@PathVariable("id") Integer categoryId,
                                   RedirectAttributes redirectAttributes)
{
    CategoryDto categoryDto=categoryService.findById(categoryId);
    if(categoryDto!=null)
       redirectAttributes.addFlashAttribute("categoryDto",categoryDto);
     return "redirect:/category/page";
}
@GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer categoryId,
                                 RedirectAttributes redirectAttributes)
{
    categoryService.deleteCategoryId(categoryId);
    return "redirect:/category/home";
}
}