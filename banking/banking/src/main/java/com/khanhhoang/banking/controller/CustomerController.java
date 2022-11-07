package com.khanhhoang.banking.controller;


import com.khanhhoang.banking.model.Customer;
import com.khanhhoang.banking.model.Deposit;
import com.khanhhoang.banking.model.Withdraw;
import com.khanhhoang.banking.service.customer.ICustomerService;
import com.khanhhoang.banking.service.deposit.IDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping({"/customers",""})
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IDepositService depositService;

    @GetMapping
    public ModelAndView showListPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/list");

        List<Customer> customers = customerService.findAllByDeletedIsFalse();

        modelAndView.addObject("customers", customers);

        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView showSearchPage(@RequestParam String keySearch) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/list");
        List<Customer> customer = customerService.findAllByDeletedIsFalse();
        keySearch = "%" + keySearch + "%";

        List<Customer> customers = customerService.findAllByFullNameLikeOrEmailLikeAndDeletedIsFalse(keySearch);

        modelAndView.addObject("customers", customers);
        modelAndView.addObject("customer", new Customer());

        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreatePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/create");

        modelAndView.addObject("customer", new Customer());

        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@Validated @ModelAttribute Customer customer,
                               BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/create");

        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("error", true);
            return modelAndView;
        }

        try {
            customer.setId(0L);
            customer.setBalance(new BigDecimal(0L));
            customerService.save(customer);

            modelAndView.addObject("customer", new Customer());
            modelAndView.addObject("success", "Thêm mới khách hàng thành công!");
        } catch (Exception e) {
            modelAndView.addObject("error", "Thao tác không thành công, vui lòng liên hệ Administrator");
        }

        return modelAndView;
    }

    @GetMapping("/update/{cid}")
    public ModelAndView showUpdatePage(@PathVariable Long cid) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/update");

        Optional<Customer> customerOptional = customerService.findById(cid);

        if (!customerOptional.isPresent()) {
            modelAndView.addObject("customer", new Customer());
            modelAndView.addObject("error", "ID khách hàng không hợp lệ");
            return modelAndView;
        }

        modelAndView.addObject("customer", customerOptional.get());
        return modelAndView;
    }

    @PostMapping("/update/{cid}")
    public ModelAndView update(@ModelAttribute("customer") Customer customer, @PathVariable Long cid) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/update");
        try {
            customerService.save(customer);
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("success", "Cập nhật thông tin khách hàng thành công!");
        } catch (Exception e) {
            modelAndView.addObject("error", "Thao tác không thành công, vui lòng thử lại");
        }

        return modelAndView;
    }

    @GetMapping("/delete/{customerId}")
    public ModelAndView showDeletepage(@PathVariable Long customerId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/delete");

        Optional<Customer> customerOptional = customerService.findById(customerId);

        if (!customerOptional.isPresent()) {
            modelAndView.addObject("customer", new Customer());
            modelAndView.addObject("error", "ID khách hàng không hợp lệ");
            return modelAndView;
        }

        modelAndView.addObject("customer", customerOptional.get());


        return modelAndView;
    }

    @PostMapping("/delete/{customerId}")
    public ModelAndView delete(@PathVariable Long customerId, RedirectAttributesModelMap redirectAttributesModelMap) {
        ModelAndView modelAndView = new ModelAndView();

        Optional<Customer> customerOptional = customerService.findById(customerId);

        if (!customerOptional.isPresent()) {
            modelAndView.addObject("customer", new Customer());
            modelAndView.addObject("error", "ID khách hàng không hợp lệ");
            modelAndView.setViewName("customer/delete");
            return modelAndView;
        }

        Customer customer = customerOptional.get();
        customer.setDeleted(true);
        customerService.save(customer);

        redirectAttributesModelMap.addFlashAttribute("success", "Xóa thành công");
        modelAndView.setViewName("redirect:" + "/customers");

        return modelAndView;
    }


    @GetMapping("/deposit/{cid}")
    public ModelAndView showDepositPage(@PathVariable Long cid) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/deposit");

        modelAndView.addObject("deposit", new Deposit());
        Optional<Customer> customerOptional = customerService.findById(cid);

        if (!customerOptional.isPresent()) {
            modelAndView.addObject("customer", new Customer());
            modelAndView.addObject("error", "ID khách hàng không hợp lệ");
            return modelAndView;
        }

        modelAndView.addObject("customer", customerOptional.get());

        return modelAndView;
    }


    @PostMapping("/deposit/{cid}")
    public ModelAndView deposit(@ModelAttribute Deposit deposit, @PathVariable Long cid) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/deposit");

        modelAndView.addObject("deposit", new Deposit());
        Optional<Customer> customerOptional = customerService.findById(cid);

        if (!customerOptional.isPresent()) {
            modelAndView.addObject("error", "ID khách hàng không hợp lệ");
            return modelAndView;
        }

        Customer customer = customerOptional.get();

        try {
            customerService.deposit(deposit, customer);

            modelAndView.addObject("deposit", new Deposit());
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("success", "Gửi tiền thành công");
        } catch (Exception e) {
            modelAndView.addObject("deposit", new Deposit());
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("error", "Thao tác không thành công, vui lòng liên hệ Administrator");
        }

        return modelAndView;
    }

    @GetMapping("/withdraw/{cid}")
    public ModelAndView showWithdrawPage(@PathVariable long cid) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/withdraw");

        Optional<Customer> customerOptional = customerService.findById(cid);

        if (!customerOptional.isPresent()) {
            modelAndView.addObject("error", "Id khách hàng không hợp lệ");
        } else {
            Withdraw withdraw = new Withdraw();
            modelAndView.addObject("withdraw", withdraw);
            modelAndView.addObject("customer", customerOptional.get());
        }
        return modelAndView;
    }

    @PostMapping("/withdraw/{cid}")
    public ModelAndView withdraw(@Validated @ModelAttribute Withdraw withdraw,
                                 BindingResult bindingResult,
                                 @PathVariable Long cid) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/withdraw");

        Optional<Customer> customerOptional = customerService.findById(cid);

        if (!customerOptional.isPresent()) {
            modelAndView.addObject("error", "ID khách hàng không hợp lệ");
            return modelAndView;
        }

        Customer customer = customerOptional.get();

        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("error", true);
            return modelAndView;
        }
        try {
            if (customerService.withdraw(withdraw, customer)) {
                modelAndView.addObject("customer", customer);
                modelAndView.addObject("withdraw", new Withdraw());
                modelAndView.addObject("success", "Rút tiền thành công");
            } else {
                modelAndView.addObject("customer", customer);
                modelAndView.addObject("withdraw", new Withdraw());
                modelAndView.addObject("errorAction", "Thao tác không thành công, vui lòng liên hệ Administrator");
            }

        } catch (Exception e) {
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("withdraw", new Withdraw());
            modelAndView.addObject("errorAction", "Thao tác không thành công, vui lòng liên hệ Administrator");
        }

        return modelAndView;
    }


}
