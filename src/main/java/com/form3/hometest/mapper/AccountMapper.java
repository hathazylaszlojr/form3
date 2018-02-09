package com.form3.hometest.mapper;

import com.form3.hometest.domain.AccountVO;
import com.form3.hometest.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AccountMapper {

    @Mappings({
            @Mapping(target = "accountNumber"),
            @Mapping(target = "accountName"),
            @Mapping(target = "accountNumberCode"),
            @Mapping(target = "accountType"),
            @Mapping(target = "address"),
            @Mapping(target = "bankId"),
            @Mapping(target = "bankIdCode"),
            @Mapping(target = "name")
    })

    AccountVO mapFromAccount(Account account);
    
    List<AccountVO> mapFromAccount(List<Account> account);

    Account mapToAccount(AccountVO accountVO);
    
    List<Account> mapToAccount(List<AccountVO> accountVO);
    
}
