package com.registration.course.clientapp.controllers.admin.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.registration.course.clientapp.models.Transaction;
import com.registration.course.clientapp.models.dto.request.TransactionStatusAndIsRegisteredRequest;
import com.registration.course.clientapp.models.dto.response.ResponseData;
import com.registration.course.clientapp.services.TransactionService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("admin/dasboard/transaction/rest/transaction")
@PreAuthorize("hasRole('ADMIN')")
public class RestTransactionDC {

  private TransactionService transactionService;

  @PutMapping("/{id}")
  public ResponseData<Transaction> updateStatusTransaction(@PathVariable Integer id,
      @RequestBody TransactionStatusAndIsRegisteredRequest transactionStatusAndIsRegisteredRequest) {
    return transactionService.updateStatus(id, transactionStatusAndIsRegisteredRequest);

  }

}
