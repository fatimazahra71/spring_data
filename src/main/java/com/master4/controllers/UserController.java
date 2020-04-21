package com.master4.controllers;

import com.master4.entities.Article;
import com.master4.entities.User;
import com.master4.exceptions.ResourceNotFoundException;
import com.master4.services.ArticleService;
import com.master4.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = {"","/user"})
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd"); //yyyy-MM-dd'T'HH:mm:ssZ example
        date.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(date, false));
    }

    @GetMapping(value = {"/","/page/{id}"})
    public String home (@PathVariable(name="id", required = false) Optional<Integer> id, ModelMap model)
    {
        Page<User> pages = userService.getAllUsers(id, 1,"id");
        model.addAttribute("pageable",pages);
        return "users/home";
    }
    @GetMapping("/add")
    public String add(ModelMap model , User user){
        model.addAttribute("articles", articleService.getAllArticles());
        model.addAttribute("user", user);
        return "users/add";
    }
    @GetMapping("/add/{id}")
    public String edit(@PathVariable("id") long id, ModelMap model) throws ResourceNotFoundException {
        User user=userService.findByIdWithArticles(id);
        List<Article> articles=articleService.getAllArticles();

        articles.forEach(a->{
            user.getArticlesList().forEach(t->{
                if(a.getId() ==t.getId()){
                    boolean used = true;
                }
            });
        });

        model.addAttribute("articles", articles);


        model.addAttribute("user", userService.findByIdWithArticles(id));
        return "users/add";
    }
    @PostMapping("/save")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult result, ModelMap model) throws ResourceNotFoundException{
        if(result.hasErrors()){
            model.addAttribute("article", articleService.getAllArticles());
            model.addAttribute("user", user);
            return  "users/add";
        }
        userService.save(user);
        return "redirect:/user/";
    }
    @GetMapping("/delete/{page}/{id}")
    public String delete(@PathVariable("page") long page, @PathVariable("id") long id, ModelMap model) throws ResourceNotFoundException{
        userService.deleteById(id);
        return "redirect:/user/";
    }
}
