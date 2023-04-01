package ibf2022.batch2.paf.serverstub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.batch2.paf.serverstub.payload.TransferRequest;
import ibf2022.batch2.paf.serverstub.repository.BankRepository;

@Service
public class BankService {

    @Autowired
    BankRepository bankRepo; 

    //use boolean so that you can return true or false, if not you will not know whether transaction has taken place or not because you are using RestController and Advanced Rest Client app
    public Boolean transferFunds(Integer fromId, Integer toId, Float amount){
        return bankRepo.transferFunds(fromId, toId, amount);
    }

    public void insertTransfer(Integer accountFrom, Integer accountTo, Float amount) {
		bankRepo.insertTransfer(accountFrom, accountTo, amount);
	}
}
