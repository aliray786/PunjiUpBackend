package in.timesinternet.punjiup.service.impl;
import in.timesinternet.punjiup.dto.*;
import in.timesinternet.punjiup.entity.Customer;
import in.timesinternet.punjiup.entity.FundDetails;
import in.timesinternet.punjiup.entity.FundManager;
import in.timesinternet.punjiup.entity.Transaction;
import in.timesinternet.punjiup.entity.enumaration.FundType;
import in.timesinternet.punjiup.entity.enumaration.IsActive;
import in.timesinternet.punjiup.exception.NotFoundException;
import in.timesinternet.punjiup.exception.UserAlreadyExistException;
import in.timesinternet.punjiup.repository.CustomerRepository;
import in.timesinternet.punjiup.repository.FundDetailsRepository;
import in.timesinternet.punjiup.repository.FundManagerRepository;
import in.timesinternet.punjiup.repository.TransactionRepository;
import in.timesinternet.punjiup.service.FundManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class FundManagerServiceImpl implements FundManagerService {
    @Autowired
    FundManagerRepository fundManagerRepository;
    @Autowired
    FundDetailsRepository fundDetailsRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public FundManager createFundManagerAccount(FundmanagerDto fundmanagerDto) {
        FundManager fundManager=new FundManager();
        fundManager.setEmail(fundmanagerDto.getEmail());
        fundManager.setCompanyName(fundmanagerDto.getCompanyName());
        fundManager.setEducationQualification(fundmanagerDto.getEducationQualification());
        fundManager.setExperience(fundmanagerDto.getExperience());
        fundManager.setMgrPassword(fundmanagerDto.getMgrPassword());
        fundManager.setFirstName(fundmanagerDto.getFirstName());
        fundManager.setLastName(fundmanagerDto.getLastName());
        try
        {
             fundManager= fundManagerRepository.save(fundManager);
             return  fundManager;
        }
     catch (
    DataIntegrityViolationException dataIntegrityViolationException) {
        throw new UserAlreadyExistException("Fundmanager with given details :- " +fundManager.getEmail()+ " already exits");
    }
    }

    @Override
    public FundManager UpdateManager(FundManagerUpdateDTO fundManagerUpdateDTO) {
        Optional<FundManager >fundManagerOptional=fundManagerRepository.findById(fundManagerUpdateDTO.getMgrId());
        if(fundManagerOptional.isPresent())
        {
            FundManager fundManager=fundManagerOptional.get();
            fundManager.setCompanyName(fundManagerUpdateDTO.getCompanyName());
            fundManager.setEducationQualification(fundManagerUpdateDTO.getEducationQualification());
            fundManager.setExperience(fundManagerUpdateDTO.getExperience());
            fundManager.setFirstName(fundManagerUpdateDTO.getFirstName());
            fundManager.setLastName(fundManagerUpdateDTO.getLastName());
            fundManager.setMgrPassword(fundManagerUpdateDTO.getMgrPassword());
            return fundManagerRepository.save(fundManager);
        }
        else
        {
            throw new NotFoundException("Manager is not found with given Id :- " + fundManagerUpdateDTO.getMgrId());

        }


    }

    @Override
    public FundDetails addFund(FundDto fundDto) {
        FundDetails fundDetails = new FundDetails();
        fundDetails.setFundName(fundDto.getFundName());
        fundDetails.setFundType(fundDto.getFundType());
        fundDetails.setExitLoad(fundDto.getExitLoad());
        fundDetails.setNav(fundDto.getNav());
        fundDetails.setExitLoad(fundDto.getExitLoad());
        fundDetails.setExpenseRatio(fundDto.getExpenseRatio());
        fundDetails.setPreference(fundDto.getPreference());
        fundDetails.setSymbol(fundDto.getSymbol());
        fundDetails.setCloseEndFund(fundDto.getCloseEndFund());
        FundManager fundManager = fundManagerRepository.getById(fundDto.getMgrId());
        fundDetails.setFundManager(fundManager);
        fundDetails.setTotalValue(fundDto.getTotalValue());
        IsActive isActive = IsActive.Yes;
        fundDetails.setIsActive(isActive);

        try {
            fundDetails = fundDetailsRepository.save(fundDetails);
            return fundDetails;
        } catch (
                DataIntegrityViolationException dataIntegrityViolationException) {
            throw new UserAlreadyExistException("Fund with given details :- " + fundDetails.getFundName() + " already exits");
        }
    }

    @Override
    public FundDetails fundUpdate(FundUpdateDto fundUpdateDto) {
       Optional< FundDetails >optionalFundDetails=fundDetailsRepository.findById(fundUpdateDto.getFundId());
        if(optionalFundDetails.isPresent())
        {
            FundDetails fundDetails=optionalFundDetails.get();
            fundDetails.setFundType(fundUpdateDto.getFundType());
            fundDetails.setExitLoad(fundUpdateDto.getExitLoad());
            fundDetails.setNav(fundUpdateDto.getNav());
            fundDetails.setExitLoad(fundUpdateDto.getExitLoad());
            fundDetails.setExpenseRatio(fundUpdateDto.getExpenseRatio());
            fundDetails.setPreference(fundUpdateDto.getPreference());
            fundDetails.setCloseEndFund(fundUpdateDto.getCloseEndFund());
            fundDetails.setIsActive(fundUpdateDto.getIsActive());
            fundDetails.setTotalValue(fundUpdateDto.getTotalValue());
            return fundDetailsRepository.save(fundDetails);
        }
        else
        {
            throw new NotFoundException("Fund is not found with given Id :- " + fundUpdateDto.getFundId());

        }
    }

    @Override
    public List<FundDetails> getAllFund(int managerId) {
       Optional<FundManager> fundManagerOptional=fundManagerRepository.findById(managerId);
        if(fundManagerOptional.isPresent())
        {
            FundManager fundManager=fundManagerOptional.get();
            List<FundDetails> fundDetailsList=fundDetailsRepository.findAllByFundManagerOrderByPreferenceAsc(fundManager);
            return fundDetailsList;
        }
        else
        {
            throw new NotFoundException("Manager is not found with given Id :- " + managerId);

        }

    }

    @Override
    public FundDetails getFund(int fundId,int mgrId) {
        FundManager fundManager=fundManagerRepository.getById(mgrId);
       FundDetails fundDetails=fundDetailsRepository.findByFundIdAndFundManager(fundId,fundManager);
       return fundDetails;
    }

    @Override
    public List<FundDetails> getAllTypeFund(FundType fundType, Integer managerId) {
        Optional<FundManager>fundManagerOptional=fundManagerRepository.findById(managerId);
        if(fundManagerOptional.isPresent())
        {
            FundManager fundManager=fundManagerOptional.get();
            List<FundDetails> fundDetailsList =fundDetailsRepository.findAllByFundTypeAndFundManagerOrderByPreferenceAsc(fundType,fundManager);
            return fundDetailsList;
        }else
        {
            throw new NotFoundException("Manager is not found with given Id :- " + managerId);
        }

    }

    @Override
    public Transaction updateTransactionStatus(TransactionStatusUpdateDto transactionStatusUpdateDto) {
        Transaction transaction=transactionRepository.getById(transactionStatusUpdateDto.getTransactionId());
        transaction.setTransactionStatus(transactionStatusUpdateDto.getTransactionStatus());
        FundDetails fundDetails=fundDetailsRepository.getById(transactionStatusUpdateDto.getFundId());
        transaction.setFundDetails(fundDetails);
        transaction.setAmount(transactionStatusUpdateDto.getAmount());
        transaction.setNav(transactionStatusUpdateDto.getAmount());
        Customer customer=customerRepository.getById(transactionStatusUpdateDto.getCustomerId());
        transaction.setCustomer(customer);
       return transactionRepository.save(transaction);
    }

    @Override
    public List<FundManager> getAllFundManager() {
        return fundManagerRepository.findAll();
    }

  @Override
   public List<Transaction> getAllTransaction(Integer mgrId)
    {
          FundManager fundManager=fundManagerRepository.getById(mgrId);
          return transactionRepository.findAllByFundDetailsFundManager(fundManager);
    }

    @Override
    public ResponseEntity login(String email, String password) {
        return null;
    }




}
