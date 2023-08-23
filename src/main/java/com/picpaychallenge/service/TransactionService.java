package com.picpaychallenge.service;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpaychallenge.domain.transaction.Transaction;
import com.picpaychallenge.domain.user.User;
import com.picpaychallenge.dto.TransactionDTO;
import com.picpaychallenge.repositories.TransactionRepository;

@Service
public class TransactionService {
  @Autowired
  private TransactionRepository transactionRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private RestTemplate restTemplate;

  public void createTransaction(TransactionDTO transaction) throws Exception {
    User sender = this.userService.findUserById(transaction.senderId());

    User receiver = this.userService.findUserById(transaction.receiverId());

    userService.validateSender(sender, transaction.value());

    if(!this.authorizeTransaction()) {
      throw new Exception("Unauthorized transaction");
    }

    Transaction newTransaction = new Transaction();

    newTransaction.setAmount(transaction.value());
    newTransaction.setSender(sender);
    newTransaction.setReceiver(receiver);
    newTransaction.setTimestamp(LocalDateTime.now());

    sender.setBalance(sender.getBalance().subtract(transaction.value()));
    receiver.setBalance(receiver.getBalance().add(transaction.value()));

    this.transactionRepository.save(newTransaction);
    this.userService.createUser(sender);
    this.userService.createUser(receiver);
  }

  public boolean authorizeTransaction() {
    ResponseEntity<Map> authResponse = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);

    if(authResponse.getStatusCode() == HttpStatus.OK){
      String message = (String)authResponse.getBody().get("message");

      return "Autorizado".equalsIgnoreCase(message);
    } else {
      return false;
    }
  }
}
