package com.picpaychallenge.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpaychallenge.domain.user.User;
import com.picpaychallenge.domain.user.UserType;
import com.picpaychallenge.repositories.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public void validateSender(User sender, BigDecimal amount) throws Exception {
    if (sender.getType() == UserType.MERCHANT) {
      throw new Exception("Merchants are not authorized to send money");
    }

    BigDecimal userAmount = sender.getBalance();

    if(userAmount.compareTo(amount) < 0) {
      throw new Exception("Not enough cash, stranger");
    }
  }

  public User findUserById(String id) throws Exception {
    return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("User not found with this id"));
  }

  public void createUser(User user) {
    this.userRepository.save(user);
  }
}
