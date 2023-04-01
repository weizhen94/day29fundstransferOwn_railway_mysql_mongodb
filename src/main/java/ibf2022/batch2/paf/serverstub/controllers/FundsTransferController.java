package ibf2022.batch2.paf.serverstub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

import ibf2022.batch2.paf.serverstub.service.BankService;

@RestController
@RequestMapping
public class FundsTransferController {

	@Autowired
    BankService bankSvc; 

    @PostMapping("/submit")
	public ResponseEntity<String> postTransfer(@RequestParam Integer accountFrom, @RequestParam Integer accountTo, @RequestParam Float amount) {

		Boolean transfer = false; 
		transfer = bankSvc.transferFunds(accountFrom, accountTo, amount); 

		System.out.println("{accountFrom:" + accountFrom + "}" + "{accountTo:" + accountTo + "}" + "{amount:" + amount + "}");

		bankSvc.insertTransfer(accountFrom, accountTo, amount);

		// Transfer successful return the following JSON object
		// { "transactionId", "aTransactionId" }
		
		// Transfer failed return the following JSON object
		// { "message", "Error message" }

		if(transfer){
			String aTransactionId = UUID.randomUUID().toString(); 
			return ResponseEntity.ok(("transactionId: " + aTransactionId));
        } else {
			return ResponseEntity.badRequest().body("message: " + "Error message");
        }
	}
}
