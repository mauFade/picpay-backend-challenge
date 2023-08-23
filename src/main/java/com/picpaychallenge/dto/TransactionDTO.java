package com.picpaychallenge.dto;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, String senderId, String receiverId) {
  
}
