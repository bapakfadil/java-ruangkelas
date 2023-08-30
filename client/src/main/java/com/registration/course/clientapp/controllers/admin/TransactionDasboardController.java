package com.registration.course.clientapp.controllers.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.registration.course.clientapp.models.dto.request.TransactionStatusAndIsRegisteredRequest;
import com.registration.course.clientapp.services.TransactionService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/dasboard/transaction")
@PreAuthorize("hasRole('ADMIN')")
public class TransactionDasboardController {

  private TransactionService transactionService;

  @GetMapping
  public String getAllTransactions(Model model,
      TransactionStatusAndIsRegisteredRequest transactionStatusAndIsRegisteredRequest) {
    model.addAttribute("transactions", transactionService.getAll().getPayload());

    return "admin/transaction/transaction";
  }

  @GetMapping("/{id}")
  public String getTransactionById(@PathVariable Integer id, Model model) {
    model.addAttribute("transaction", transactionService.getTransactionById(id).getPayload().get(0));

    return "admin/transaction/update-transaction";
  }

}
