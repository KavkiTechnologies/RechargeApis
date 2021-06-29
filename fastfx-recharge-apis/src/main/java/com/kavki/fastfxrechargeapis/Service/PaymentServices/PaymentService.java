package com.kavki.fastfxrechargeapis.Service.PaymentServices;
import java.math.*;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;

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
    private RSAEncryptorDecryptor rsaUtil;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TransactionIdGenerator trans;
    @Autowired
    private ClientListRepo cListRepo;

    private static DecimalFormat df = new DecimalFormat("0.00");
    
    public String UpiPayments(IciciCredentials iciciCreds) {
    
        String clientId = iciciCreds.getUserId();
        ClientEntity client = cListRepo.findById(clientId).orElse(null);

        String payerVa = env.getProperty("icici.payerVa");
        String amount =  df.format(iciciCreds.getAmount());
        String note = env.getProperty("icici.note");
        String collectByDate = trans.getCurrentDateAndTime();
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

        try{
            String encrypted = rsaUtil.encrypt(toBeEncrypted);
            System.out.println("\n"+encrypted);
            String baseUrl = "https://apibankingsandbox.icicibank.com/api/MerchantAPI/UPI/v0/CollectPay2/401579";
            
            HttpHeaders headers = new HttpHeaders();
            //headers.setContentType(MediaType.TEXT_PLAIN);
            headers.setContentType(new MediaType("text", "plain", StandardCharsets.UTF_8));
            // headers.add(HttpHeaders.CACHE_CONTROL, "no-cache");
            // headers.add(HttpHeaders.ACCEPT_ENCODING,"*");
            // headers.add(HttpHeaders.ACCEPT_LANGUAGE,"en-US,en;q=0.8,hi;q=0.6");
            headers.add("apikey", "xNXlrrJksceuh44RfBKrPxWvca9hXL9T");

            HttpEntity<String> request = new HttpEntity<String>(encrypted,headers);
            
            ResponseEntity<String> response = restTemplate.exchange(baseUrl ,
                    HttpMethod.POST,
                    request,
                    String.class);
            String jsonStr = response.getBody();
            System.out.println("\nResponse: "+jsonStr);

            String responseText = rsaUtil.decrypt(jsonStr);
            System.out.println("\nres json:"+responseText);
            
            return responseText;
        }
        catch(Exception e)
        {
           return null;
        }

    }
    
}
