package com.form3.hometest.mapper;

import com.form3.hometest.domain.ChargeVO;
import com.form3.hometest.domain.PaymentVO;
import com.form3.hometest.entity.Charge;
import com.form3.hometest.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PaymentVOMapper {

    @Mappings({
            @Mapping(target = "type"),
            @Mapping(target = "id"),
            @Mapping(target = "version"),
            @Mapping(target = "organisationId"),
            
            @Mapping(target = "attributes.beneficiaryParty", source = "receiverAccount"),
            @Mapping(target = "attributes.debtorParty", source = "senderAccount"),
            @Mapping(target = "attributes.schemePaymentType", source = "schemePaymentType"),
            @Mapping(target = "attributes.schemePaymentSubType", source = "schemePaymentSubType"),
            @Mapping(target = "attributes.reference", source = "reference"),
            @Mapping(target = "attributes.paymentScheme", source = "paymentScheme"),
            @Mapping(target = "attributes.paymentPurpose", source = "paymentPurpose"),
            @Mapping(target = "attributes.paymentId", source = "paymentId"),
            @Mapping(target = "attributes.paymentType", source = "paymentType"),
            @Mapping(target = "attributes.numericReference", source = "numericReference"),
            @Mapping(target = "attributes.currency", source = "currency"),
            @Mapping(target = "attributes.endToEndReference", source = "endToEndReference"),
            @Mapping(target = "attributes.amount", source = "amount"),
            @Mapping(target = "attributes.chargesInformation.bearerCode", source = "bearerCode"),
            @Mapping(target = "attributes.fx.contractReference", source = "fxContractReference"),
            @Mapping(target = "attributes.fx.exchangeRate", source = "fxExchangeRate"),
            @Mapping(target = "attributes.fx.originalAmount", source = "fxOriginalAmount"),
            @Mapping(target = "attributes.fx.originalCurrency", source = "fxOriginalCurrency"),
            @Mapping(target = "attributes.processingDate", source = "processingDate"),
            @Mapping(target = "attributes.chargesInformation.senderCharges", source = "senderCharges"),
            @Mapping(target = "attributes.chargesInformation.receiverCharge", source = "receiverCharge"),
            @Mapping(target = "attributes.sponsorParty", source = "sponsorAccount")
            
    })
    
    PaymentVO mapFromPayment(Payment payment);
    
    List<PaymentVO> mapFromPayment(List<Payment> payments);

    default List<ChargeVO> mapToChargeVOs(List<Charge> charges) {
        return charges.stream()
                .map(charge -> new ChargeVO(charge.getAmount(), charge.getCurrency()))
                .collect(Collectors.toList());
    }

    default ChargeVO mapToChargeVO(Charge charge) {
        return new ChargeVO(charge.getAmount(), charge.getCurrency());
    }

}
