package com.registration.course.serverapp.api.transaction.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.registration.course.serverapp.api.authentication.AppUserDetail;
import com.registration.course.serverapp.api.dto.response.ResponseData;
import com.registration.course.serverapp.api.user.User;
import com.registration.course.serverapp.api.user.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/history")
public class HistoryController {

  @Autowired
  private HistoryService historyService;

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<ResponseData<History>> getAllTransactions() {
    ResponseData<History> responseData = new ResponseData<>();
    responseData.setStatus(true);
    responseData.getMessages().add("success");
    responseData.setPayload(historyService.getAll());
    return ResponseEntity.ok(responseData);
  }

  @GetMapping("/member")
  public ResponseEntity<ResponseData<History>> getAllHistoriesByMemberId() {
    ResponseData<History> responseData = new ResponseData<>();
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null) {
      Object principal = authentication.getPrincipal();

      if (principal instanceof AppUserDetail) {
        AppUserDetail appUserDetail = (AppUserDetail) principal;
        String username = appUserDetail.getUsername();
        // Melakukan pemrosesan lebih lanjut dengan informasi pengguna
        User user = userService.getByUsername(username);
        responseData.setPayload(historyService.getAllHistoriesByMemberId(user.getId()));
      }
    }
    responseData.setStatus(true);
    responseData.getMessages().add("berhasil ditemukan");
    return ResponseEntity.ok(responseData);
  }

}
