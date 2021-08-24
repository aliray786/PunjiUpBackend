package in.timesinternet.punjiup.controller.Manager;
import in.timesinternet.punjiup.dto.*;
import in.timesinternet.punjiup.entity.FundDetails;
import in.timesinternet.punjiup.entity.FundManager;
import in.timesinternet.punjiup.entity.Transaction;
import in.timesinternet.punjiup.entity.enumaration.FundType;
import in.timesinternet.punjiup.entity.enumaration.TransactionStatus;
import in.timesinternet.punjiup.repository.FundDetailsRepository;
import in.timesinternet.punjiup.repository.FundManagerRepository;
import in.timesinternet.punjiup.repository.TransactionRepository;
import in.timesinternet.punjiup.service.impl.FundManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/fundmanager")
public class ManagerController {
    @Autowired
    FundDetailsRepository fundDetailsRepository;
    @Autowired
    FundManagerRepository fundManagerRepository;
    @Autowired
    FundManagerServiceImpl fundManagerServiceImpl;
    @Autowired
    TransactionRepository transactionRepository;
    @PostMapping("/login")
    Object loginManager(@RequestBody LoginDto loginDto)
    {
        return null;
    }
    @PostMapping("/signup")
    ResponseEntity<FundManager> createManager(@RequestBody  FundmanagerDto fundmanagerDto)
    {
        //Manager Service Call;
        FundManager fundManager=fundManagerServiceImpl.createFundManagerAccount(fundmanagerDto);
        return ResponseEntity.ok(fundManager);
    }
    @GetMapping("/allfundmanager")
    ResponseEntity<List<FundManager> >fundManagerList()
    {
        return ResponseEntity.ok(fundManagerServiceImpl.getAllFundManager());
    }

    @PutMapping("/update")
    FundManager updateManager(@RequestBody FundManagerUpdateDTO fundManagerUpdateDTO)
    {
        //update Mangaer Service Call
        FundManager fundManager=fundManagerServiceImpl.UpdateManager(fundManagerUpdateDTO);
        return  fundManager;
    }
    @PostMapping("/addfund")
    ResponseEntity<FundDetails >addFund(@RequestBody FundDto fundDto)
    {
        //update Mangaer Service Call
       FundDetails fundDetails=fundManagerServiceImpl.addFund(fundDto);
      return ResponseEntity.ok(fundDetails);
    }
    @PutMapping("/fundUpdate")
    ResponseEntity<FundDetails >fundUpdate(@RequestBody FundUpdateDto fundUpdateDto)
    {
        //update Mangaer Service Call
        FundDetails fundDetails=fundManagerServiceImpl.fundUpdate(fundUpdateDto);
        return ResponseEntity.ok(fundDetails);
    }
    @GetMapping("/{managerId}/funds")
   ResponseEntity< List<FundDetails> >getAllFund(@PathVariable Integer managerId)
    {
        //Get All funds for perticular manager
         List<FundDetails>fundDetailsList=fundManagerServiceImpl.getAllFund(managerId);
        return ResponseEntity.ok(fundDetailsList);
    }
    @GetMapping("/{mgrId}/fund/{fundId}")
   ResponseEntity<FundDetails >getFund(@PathVariable Integer fundId, @PathVariable Integer mgrId)
    {
        //update Mangaer Service Call
        FundDetails fundDetails=fundManagerServiceImpl.getFund(fundId,mgrId);
        return ResponseEntity.ok(fundDetails);
    }
    @GetMapping("/{mgrId}/funds/{fundType}")
   ResponseEntity<List<FundDetails >>getAllTypeFund(@PathVariable FundType fundType, @PathVariable Integer mgrId)
    {
        //get all open or close end fund;
        List<FundDetails> fundDetailsList=fundManagerServiceImpl.getAllTypeFund(fundType,mgrId);
        return ResponseEntity.ok(fundDetailsList);
    }
  // To update Transaction Status
    @PutMapping("/fund/updatetransaction")
    Transaction updateTransactinStatus(@RequestBody TransactionStatusUpdateDto transactionDto)
    {
        return fundManagerServiceImpl.updateTransactionStatus(transactionDto);
    }


   // To get all Unapproved Transaction
    @GetMapping("/{managerId}/fund/transactions/{transactionStatus}")
   ResponseEntity< List<Transaction> >getTransaction(@PathVariable Integer managerId,@PathVariable TransactionStatus transactionStatus)
    {
        FundManager fundManager=fundManagerRepository.getById(managerId);
        return ResponseEntity.ok(transactionRepository.findTransactionByTransactionStatusAndFundDetailsFundManager(transactionStatus,fundManager));
    }
    @GetMapping("{managerId}/fund/transactions")
   ResponseEntity< List<Transaction>>getAllTransaction(@PathVariable Integer managerId)
    {
        return ResponseEntity.ok(fundManagerServiceImpl.getAllTransaction(managerId));
    }


}
