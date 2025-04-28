package spring.data.pattern.filtering.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {


    private final String accountType;

    public IndexController(@Value("${account.type}") String accountType) {
        this.accountType = accountType;
    }

    @RequestMapping("/")
    public String homePage(Model model)
    {
        model.addAttribute("accountType",accountType);
        return "index";
    }
}
