package com.registration.course.clientapp.controllers.user.dasboard;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.registration.course.clientapp.services.HistoryService;
import com.registration.course.clientapp.services.TransactionService;
import com.registration.course.clientapp.services.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/user/dasboard")
@PreAuthorize("hasRole('USER')")
public class MemberDasboardPage {

  private TransactionService transactionService;
  private UserService userService;
  private HistoryService historyService;

  @GetMapping
  public String homeDasboardUser(Model model, Authentication authentication) {
    if (authentication != null && authentication.isAuthenticated()) {
      model.addAttribute("auth", true);
      model.addAttribute("authentication", authentication);
      model.addAttribute("isROLE",
          authentication.getAuthorities().stream().findFirst().orElse(null).getAuthority());
    } else {
      model.addAttribute("auth", false);
    }
    return "user/dasboard/index";
  }

  @GetMapping("/transaction")
  public String renderAllTransactionByMemberId(Model model, Authentication authentication) {
    if (authentication != null && authentication.isAuthenticated()) {
      model.addAttribute("auth", true);
      model.addAttribute("authentication", authentication);
      model.addAttribute("isROLE", authentication.getAuthorities().stream().findFirst().orElse(null).getAuthority());
      Integer memberId = userService.getByUsername(authentication.getPrincipal().toString()).getPayload().get(0)
          .getId();
      model.addAttribute("transactions",
          transactionService.getTransactionByMemberId(memberId).getPayload());
    } else {
      model.addAttribute("auth", false);
    }
    return "user/dasboard/transaction";
  }

  @GetMapping("/course")
  public String renderAllCourseIsRegisteredByTransaction(Model model, Authentication authentication) {
    if (authentication != null && authentication.isAuthenticated()) {
      model.addAttribute("auth", true);
      model.addAttribute("authentication", authentication);
      model.addAttribute("isROLE", authentication.getAuthorities().stream().findFirst().orElse(null).getAuthority());
      model.addAttribute("transactions", transactionService.getTransactionByMemberIdSessionIsRegistered().getPayload());
    } else {
      model.addAttribute("auth", false);
    }
    return "user/dasboard/course";
  }

  @GetMapping("/history")
  public String renderAllHistoryTransactions(Model model, Authentication authentication) {
    if (authentication != null && authentication.isAuthenticated()) {
      model.addAttribute("auth", true);
      model.addAttribute("authentication", authentication);
      model.addAttribute("isROLE", authentication.getAuthorities().stream().findFirst().orElse(null).getAuthority());
      model.addAttribute("histories", historyService.getAllHistoryByMemberIdSession().getPayload());
    } else {
      model.addAttribute("auth", false);
    }
    return "user/dasboard/history";
  }
}
