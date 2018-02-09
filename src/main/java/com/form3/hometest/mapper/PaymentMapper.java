package com.form3.hometest.mapper;

import com.form3.hometest.domain.ChargeVO;
import com.form3.hometest.domain.PaymentVO;
import com.form3.hometest.entity.Charge;
import com.form3.hometest.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PaymentMapper {

    @Mappings({
            @Mapping(target = "type"),
            @Mapping(target = "id"),
            @Mapping(target = "version"),
            @Mapping(target = "organisationId"),

            @Mapping(source = "attributes.beneficiaryParty", target = "receiverAccount"),
            @Mapping(source = "attributes.debtorParty", target = "senderAccount"),
            @Mapping(source = "attributes.schemePaymentType", target = "schemePaymentType"),
            @Mapping(source = "attributes.schemePaymentSubType", target = "schemePaymentSubType"),
            @Mapping(source = "attributes.reference", target = "reference"),
            @Mapping(source = "attributes.paymentScheme", target = "paymentScheme"),
            @Mapping(source = "attributes.paymentPurpose", target = "paymentPurpose"),
            @Mapping(source = "attributes.paymentId", target = "paymentId"),
            @Mapping(source = "attributes.numericReference", target = "numericReference"),
            @Mapping(source = "attributes.currency", target = "currency"),
            @Mapping(source = "attributes.paymentType", target = "paymentType"),
            @Mapping(source = "attributes.amount", target = "amount"),
            @Mapping(source = "attributes.endToEndReference", target = "endToEndReference"),
            @Mapping(source = "attributes.chargesInformation.bearerCode", target = "bearerCode"),
            @Mapping(source = "attributes.fx.contractReference", target = "fxContractReference"),
            @Mapping(source = "attributes.fx.exchangeRate", target = "fxExchangeRate"),
            @Mapping(source = "attributes.fx.originalAmount", target = "fxOriginalAmount"),
            @Mapping(source = "attributes.fx.originalCurrency", target = "fxOriginalCurrency"),
            @Mapping(source = "attributes.processingDate", target = "processingDate"),
            @Mapping(source = "attributes.chargesInformation.senderCharges", target = "senderCharges"),
            @Mapping(source = "attributes.chargesInformation.receiverCharge", target = "receiverCharge"),
            @Mapping(source = "attributes.sponsorParty", target = "sponsorAccount")
            
    })
    
    Payment mapToPayment(PaymentVO paymentVO);
    
    List<Payment> mapToPayment(List<PaymentVO> paymentVOs);
    
    default List<Charge> mapToCharges(List<ChargeVO> chargeVOS) {
        return chargeVOS.stream()
                .map(chargeVo -> new Charge(null, chargeVo.getAmount(), chargeVo.getCurrency()))
                .collect(Collectors.toList());
    }

    default Charge mapToCharge(ChargeVO chargeVo) {
        return new Charge(null, chargeVo.getAmount(), chargeVo.getCurrency());
    }

}
