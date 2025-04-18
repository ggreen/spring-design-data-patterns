package spring.data.pattern.filtering.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {


    @RequestMapping("/")
    public String homePage(Model model)
    {
       // model.addAttribute(customerIdAttribId,customerId);
        return "index";
    }
}
