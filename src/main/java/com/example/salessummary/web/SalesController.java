package com.example.salessummary.web;

import com.example.salessummary.SalesSummaryApplication;

import com.example.salessummary.entities.Sales;
import com.example.salessummary.repositories.SalesRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
@SessionAttributes({"a", "e"})
@Controller
@AllArgsConstructor
public class SalesController {
    @Autowired
    private SalesRepository salesRepository;
    static int num=0;


    @GetMapping(path = "/index")
    public String sales(Model model, @RequestParam(name = "keyword", defaultValue = "")String keyword) {
        List<Sales> sales;
        if(keyword.isEmpty()){
            sales=salesRepository.findAll();
        }else {
            long key = Long.parseLong(keyword);
            sales=salesRepository.findSalesById(key);
        }
        model.addAttribute("listSales",sales);

        return "sales";
    }
    @GetMapping("/delete")
    public String delete(Long id){
        salesRepository.deleteById(id);
        return "redirect:/index";
    }
    @GetMapping("/formSales")
    public String formSales(Model model){
        model.addAttribute("sales", new Sales()); return "formSales";
    }
    @PostMapping(path="/save")
    public String save(Model model, Sales sales, BindingResult bindingResult, ModelMap mm, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "formSales";
        } else {
            salesRepository.save(sales);
            if (num == 2) {
                mm.put("e", 2);
                mm.put("a", 0);
            } else {
                mm.put("a", 1);
                mm.put("e", 0);
            }
            return "redirect:index";

        }
    }
    @GetMapping("/editSales")
    public String editSales(Model model, Long id, HttpSession session){
        num = 2;
        session.setAttribute("info", 0);
        Sales sales = salesRepository.findById(id).orElse(null); if(sales==null) throw new RuntimeException("Patient does not exist"); model.addAttribute("sales", sales);
        return "editSales";
    }
    @GetMapping(path = "/")
    public String sales2(Model model, ModelMap mm, @RequestParam(name="keyword",defaultValue = "") String keyword,HttpSession session){
        List<Sales> sales; if (keyword.isEmpty()) {
            sales = salesRepository.findAll();
        } else {
            mm.put("e", 0);
            mm.put("a", 0);
            long key = Long.parseLong(keyword);
            sales = salesRepository.findSalesById(key);
        }
        model.addAttribute("listSales", sales);
        return "sales";
    }

}