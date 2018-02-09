package com.form3.hometest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.form3.hometest.domain.AccountVO;
import com.form3.hometest.entity.Account;
import com.form3.hometest.mapper.AccountMapper;
import com.form3.hometest.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {
    
    private final Logger logger = LoggerFactory.getLogger(AccountController.class);
    
    private final AccountService accountService;
    private final AccountMapper accountMapper;
    private final ObjectMapper objectMapper; 
    
    @Autowired
    public AccountController(AccountService accountService, AccountMapper accountMapper, ObjectMapper objectMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
        this.objectMapper = objectMapper;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAllAccounts(@RequestParam(value = "name", required = false) String name) {
        logger.info("All accounts queried with parameters: name {}", name);
        List<AccountVO> accountVOS = accountMapper.mapFromAccount(accountService.getAccountsByName(name));
        return new ResponseEntity<>(accountVOS, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addAccount(@RequestBody String accountVoStr) {
        logger.info("Add account request with following body: {}", accountVoStr);

        AccountVO accountVO = null;
        try {
            accountVO = objectMapper.readValue(accountVoStr, AccountVO.class);
        } catch (IOException e) {
            logger.error("Invalid POST request for adding accounts, can not map to accountVO, input is {}", accountVoStr);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Account account = accountMapper.mapToAccount(accountVO);
        
        try {
            accountService.save(account);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST); // This could be detailed further
        }
    }

    @RequestMapping(value = "{accountId}", method = RequestMethod.PUT)
    public ResponseEntity updateAccount(@PathVariable("accountId") String accountId, @RequestBody String accountVoStr) {
        logger.info("Update account request with following body: {}", accountVoStr);

        AccountVO accountVO = null;
        try {
            accountVO = objectMapper.readValue(accountVoStr, AccountVO.class);
        } catch (IOException e) {
            logger.error("Invalid POST request for adding accounts, can not map to accountVO, input is {}", accountVoStr);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Account account = accountMapper.mapToAccount(accountVO);

        if (!accountId.equals(account.getAccountNumber())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        
        boolean updated = accountService.update(account);
        
        if (updated) {
            return new ResponseEntity(HttpStatus.CREATED);
        } else { 
            return new ResponseEntity(HttpStatus.BAD_REQUEST); // This could be detailed further
        }
    }
    
    @RequestMapping(value = "{accountId}", method = RequestMethod.GET)
    public ResponseEntity getAccountDetails(@PathVariable("accountId") String accountId) {
        logger.info("Account with id {} requested through API", accountId);
        AccountVO accountVO = accountMapper.mapFromAccount(accountService.findById(accountId));
        return new ResponseEntity<>(accountVO, HttpStatus.OK);
    }

    @RequestMapping(value = "{accountId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAccount(@PathVariable("accountId") String accountId) {
        logger.info("Delete account with id {} requested through API", accountId);
        boolean done = accountService.deleteAccount(accountId);
        if (done) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
}
