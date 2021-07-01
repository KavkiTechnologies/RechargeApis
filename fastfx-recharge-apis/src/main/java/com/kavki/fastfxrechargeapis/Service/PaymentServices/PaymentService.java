package com.kavki.fastfxrechargeapis.Service.PaymentServices;
import java.math.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.kavki.fastfxrechargeapis.DAO.AdminRepositories.ClientListRepo;
import com.kavki.fastfxrechargeapis.DTO.RSAEncryptorDecryptor;
import com.kavki.fastfxrechargeapis.Entity.Admin.ClientEntity;
import com.kavki.fastfxrechargeapis.Entity.Payments.IciciCredentials;
import com.kavki.fastfxrechargeapis.Entity.Recharge.TransactionIdGenerator;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {

    @Autowired
	private Environment env;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TransactionIdGenerator trans;
    @Autowired
    private ClientListRepo cListRepo;

    private static DecimalFormat df = new DecimalFormat("0.00");
    
    public String ApiRequestPacket(String baseUrl, String toBeEncrypted) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException{
        // encrypting packet request
        String encrypted = RSAEncryptorDecryptor.encrypt(toBeEncrypted);
        // custome header for the api request 
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text", "plain", StandardCharsets.UTF_8));
        headers.add("apikey", "xNXlrrJksceuh44RfBKrPxWvca9hXL9T");
        // entity consisting of header & ecrypted packet
        HttpEntity<String> request = new HttpEntity<String>(encrypted,headers);
        // consuming external Api
        ResponseEntity<String> response = restTemplate.exchange(baseUrl ,
                HttpMethod.POST,
                request,
                String.class);
        // getting response for the api request
        String encryptedResponse = response.getBody();
        // decrypting the api response to plain text
        String responseText = RSAEncryptorDecryptor.decrypt(encryptedResponse);
        return responseText;
    }
    
    public String UpiPayments(IciciCredentials iciciCreds) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException {
    
        String clientId = iciciCreds.getUserId();
        ClientEntity client = cListRepo.findById(clientId).orElse(null);

        String payerVa = env.getProperty("icici.payerVa");
        String amount =  df.format(iciciCreds.getAmount());
        String note = env.getProperty("icici.note");
        String collectByDate = iciciCreds.getCollectByDate();
        //String collectByDate = trans.getCurrentDateAndTime();
        String merchantId = env.getProperty("icici.merchantId");
        String merchantName = env.getProperty("icici.merchantName");
        String subMerchantId = env.getProperty("icici.subMerchantId");
        String subMerchantName = client.getName();
        String terminalId = env.getProperty("icici.terminalId");
        String merchantTranId = trans.generateTransId(clientId);
        String billNumber = clientId;

        String toBeEncrypted = "{\"payerVa\" : \""+payerVa+"\"," +
                                "\"amount\" : \""+amount+"\"," +
                                "\"note\" : \""+note+"\","+
                                "\"collectByDate\" : \""+collectByDate+"\","+
                                "\"merchantId\" : \""+merchantId+"\","+
                                "\"merchantName\" : \""+merchantName+"\","+
                                "\"subMerchantId\" : \""+subMerchantId+"\","+
                                "\"subMerchantName\" : \""+subMerchantName+"\","+
                                "\"terminalId\" : \""+terminalId+"\","+
                                "\"merchantTranId\" : \""+merchantTranId+"\","+
                                "\"billNumber\" : \""+billNumber+"\"}"  ;

        System.out.println("\n"+toBeEncrypted);

        String baseUrl = "https://apibankingsandbox.icicibank.com/api/MerchantAPI/UPI/v0/CollectPay2/401579";
        String responseText = ApiRequestPacket(baseUrl, toBeEncrypted);
        System.out.println("\n"+responseText);

        return responseText;  
           
    }

    public String UpiPaymentsRefund(IciciCredentials iciciCreds) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException {
       
       String clientId = iciciCreds.getUserId();
       ClientEntity client = cListRepo.findById(clientId).orElse(null);

       String merchantId = env.getProperty("icici.merchantId");
       String subMerchantId = env.getProperty("icici.subMerchantId");
       String terminalId = env.getProperty("icici.terminalId");
       String originalBankRRN = iciciCreds.getOriginalBankRRN();
       String merchantTranId = trans.generateTransId(clientId);
       
       String originalmerchantTranId = iciciCreds.getOriginalmerchantTranId();
       String payerVa = env.getProperty("icici.payerVa");
       String refundAmount = iciciCreds.getRefundAmount();
       String onlineRefund = env.getProperty("icici.onlineValue");
       String note = env.getProperty("icici.note");
       

       String toBeEncrypted = "{\"merchantId\" : \""+merchantId+"\"," +
                               "\"subMerchantId\" : \""+subMerchantId+"\"," +
                               "\"terminalId\" : \""+terminalId+"\","+
                               "\"originalBankRRN\" : \""+originalBankRRN+"\","+
                               "\"merchantTranId\" : \""+merchantTranId+"\","+
                               "\"originalmerchantTranId\" : \""+originalmerchantTranId+"\","+
                               "\"payeeVA\" : \""+payerVa+"\","+
                               "\"refundAmount\" : \""+refundAmount+"\","+
                               "\"note\" : \""+note+"\","+
                               "\"onlineRefund\" : \""+onlineRefund+"\"}" ;

       System.out.println("\n"+toBeEncrypted);

       String baseUrl = "https://apibankingsandbox.icicibank.com/api/MerchantAPI/UPI/v0/Refund/401579";   
       String responseText = ApiRequestPacket(baseUrl, toBeEncrypted);
       System.out.println("\n"+responseText);

       return responseText;  
     
    }

    public String UpiPaymentsStatus(IciciCredentials iciciCreds) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException {
        
        String merchantId = env.getProperty("icici.merchantId");
        String merchantTranId = iciciCreds.getMerchantTranId();
        String subMerchantId = env.getProperty("icici.subMerchantId");
        String terminalId = env.getProperty("icici.terminalId");
         
        String toBeEncrypted = "{\"merchantId\" : \""+merchantId+"\"," +
                                "\"merchantTranId\" : \""+merchantTranId+"\"," +
                                "\"subMerchantId\" : \""+subMerchantId+"\","+
                                "\"terminalId\" : \""+terminalId+"\"}";

        System.out.println("\n"+toBeEncrypted);

        String baseUrl = "https://apibankingsandbox.icicibank.com/api/MerchantAPI/UPI/v0/TransactionStatus1/401579 "; 
        String responseText = ApiRequestPacket(baseUrl, toBeEncrypted);
        System.out.println("\n"+responseText);

        return responseText;  

    }
    
}
