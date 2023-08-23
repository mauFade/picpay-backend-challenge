package com.picpaychallenge.domain.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.picpaychallenge.domain.user.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private BigDecimal amount;

  @ManyToOne
  @JoinColumn(name = "sender_id")
  private User sender;

  @ManyToOne
  @JoinColumn(name = "receiver_id")
  private User receiver;

  private LocalDateTime timestamp;
}
