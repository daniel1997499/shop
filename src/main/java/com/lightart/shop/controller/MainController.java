package com.lightart.shop.controller;

import com.lightart.shop.model.OrderItem;
import com.lightart.shop.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/", "/index", "/home"})
public class MainController {
    private final Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHome(Model model, @ModelAttribute OrderItem orderItem) {
//        model.addAttribute("newOrder", new OrderItem()); //this can be used instead of @ModelAttribute
        model.addAttribute("orders", orderService.getAllOrders());
        return "index";
    }

    @RequestMapping(value = "/createorder", method = RequestMethod.GET)
    public String showCreateNewOrderPage(Model model, @ModelAttribute OrderItem orderItem) {
        return "createOrderPage";
    }

    @RequestMapping(value = "/createorder", method = RequestMethod.POST)
    public String createNewOrder(OrderItem orderItem) {
        try {
            orderService.createNewOrder(orderItem);
        }
        catch (NullPointerException ne) {
            log.error(String.valueOf(ne));
        }
        return "redirect:/";
    }
}
