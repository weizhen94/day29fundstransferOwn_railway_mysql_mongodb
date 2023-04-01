package ibf2022.batch2.paf.serverstub.repository;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import ibf2022.batch2.paf.serverstub.payload.TransferRequest;

@Repository
public class BankRepository {

    @Autowired
    JdbcTemplate jdbcTemplate; 

    //works with or without the transactional, so what is it for? 
    // @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    //use boolean so that you can return true or false, if not you will not know whether transaction has taken place or not because you are using RestController and Advanced Rest Client app
    public Boolean transferFunds(Integer fromId, Integer toId, Float amount){
        //check the id to transfer from has the balance
        final String BalanceSQL = "select balance from bankaccount where id = ?"; 
        Float balance = jdbcTemplate.queryForObject(BalanceSQL, Float.class, fromId);

        if(balance >= amount){
        //perform the withdrawal
        final String withdrawSQL = "update bankaccount set balance = balance - ? where id = ?";
        jdbcTemplate.update(withdrawSQL, amount, fromId); 

        //perform the deposit
        final String depositSQL = "update bankaccount set balance = balance + ? where id = ?"; 
        jdbcTemplate.update(depositSQL, amount, toId); 

        return true; 
        } else {
        return false; 
        }
    }

    @Autowired
	private MongoTemplate template;

    public void insertTransfer(Integer accountFrom, Integer accountTo, Float amount) {
        Document doc = new Document();
		doc.put("accountFrom", accountFrom);
		doc.put("accountTo", accountTo);
		doc.put("amount", amount);

		Document result = template.insert(doc, "transfer");

        ObjectId id = doc.getObjectId("_id"); 
    }
}
