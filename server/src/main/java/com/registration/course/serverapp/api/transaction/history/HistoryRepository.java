package com.registration.course.serverapp.api.transaction.history;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {
  List<History> findAllByTransactionMemberId(Integer memberId);
}
