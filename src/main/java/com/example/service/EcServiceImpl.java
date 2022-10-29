package com.example.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.binding.ApplicantDisplay;
import com.example.entity.ApplicantDetails;
import com.example.entity.CitizenApp;
import com.example.entity.Co_Triggers;
import com.example.entity.Dc_Cases;
import com.example.entity.Dc_Children;
import com.example.entity.Dc_Education;
import com.example.entity.Dc_Income;
import com.example.repo.ApplicantDetailsRepo;
import com.example.repo.CitizenAppRepo;
import com.example.repo.Co_TriggerRepo;
import com.example.repo.Dc_CasesRepo;
import com.example.repo.Dc_EducationRepo;
import com.example.repo.Dc_childrenRepo;
import com.example.repo.Dc_incomeRepo;
@Service
public class EcServiceImpl implements EcService {
	@Autowired
	private CitizenAppRepo carepo;
	
	
	@Autowired
	private Dc_CasesRepo drepo;
    
    @Autowired
	private Dc_childrenRepo crepo;
    
    @Autowired
	private Dc_EducationRepo erepo;
    
    @Autowired
	private Dc_incomeRepo irepo;
    
    @Autowired
    ApplicantDetailsRepo aprepo;
    
    @Autowired
    Co_TriggerRepo trepo; 
	
    public ApplicantDisplay searchapplicant(Integer casenum) {
		ApplicantDetails apdetails=new ApplicantDetails();
		Optional<Dc_Cases> dccase=drepo.findById(casenum);
		 ApplicantDisplay apdisplay=new  ApplicantDisplay();
		
		Dc_Cases cases=dccase.get();
		Integer apid=cases.getApp_id();
		Integer planid=cases.getPlan_id();
		Optional<CitizenApp> capp=carepo.findById(apid);
		CitizenApp citiapp=capp.get();
		
		
		if(drepo.existsById(casenum)) {
			
		     apdisplay=setapplicant(casenum,planid,citiapp);
             apdetails.setCasenum(casenum);
		     BeanUtils.copyProperties(apdisplay, apdetails);
		     apdetails.setCasenum(casenum);
		     aprepo.save(apdetails);
		     Co_Triggers trigger=new Co_Triggers();
		     trigger.setCase_no(casenum);
		     trigger.setTrg_status("pending");
		     trigger.setNotice(null);
		     trigger.setEmailid(citiapp.getEmailId());
		     trepo.save(trigger);
			
			return apdisplay;
		}
	
	   else
		  
	      return apdisplay;
		}
		private ApplicantDisplay setapplicant(Integer casenum,Integer planid,CitizenApp citiapp)	{
			ApplicantDisplay apdisplay=new ApplicantDisplay();
			
			apdisplay.setApplicantName(citiapp.getFullName());
			apdisplay.setApplicantSsn(citiapp.getSsn());
			LocalDate now=LocalDate.now();
			if(planid==1)
			{
				apdisplay.setPlanname("SNAP");
			 Dc_Income dcinc=irepo.findByCaseno(casenum);
			 if(dcinc.getIncome()<=300) {
				 apdisplay.setPlanstatus("Approved");
				 apdisplay.setBenefitAmt(150);
			 }else {
				 apdisplay.setPlanstatus("Denied");
				 apdisplay.setDenialreason("Income is more");
			}}
			else if(planid==2) {
				apdisplay.setPlanname("CCAP");
				Dc_Income dcinc=irepo.findByCaseno(casenum);
				Dc_Children child=crepo.findByCaseno(casenum);
				if(dcinc.getIncome()<=300 && child.getChildid()!=null && child.getAge()<=16) {
					apdisplay.setPlanstatus("Approved");
					apdisplay.setBenefitAmt(250);
					 
				 }else {
					 apdisplay.setPlanstatus("Denied");
					 apdisplay.setDenialreason("conditions doesnot satisfy");
				}	
			}
			else if(planid==3) {
				Dc_Income dcinc=irepo.findByCaseno(casenum);
				if(dcinc.getIncome()<=300 && dcinc.getProperty_income()==0) {
					apdisplay.setPlanstatus("Approved");
					apdisplay.setBenefitAmt(150);
				 }else {
					 apdisplay.setPlanstatus("Denied");
					 apdisplay.setDenialreason("conditions doesnot satisfy");
				}	
			}
			else if(planid==4) {
				LocalDate dob=citiapp.getDob();
				
				Integer age=Period.between(dob, now).getYears();
				if(age>=65) {
					apdisplay.setPlanstatus("Approved");
					apdisplay.setBenefitAmt(200);
				 }else {
					 apdisplay.setPlanstatus("Denied");
					 apdisplay.setDenialreason("conditions doesnot satisfy");
				}		
			}
			else if(planid==5) {
				apdisplay.setPlanname("NJW");
				Dc_Income dcinc=irepo.findByCaseno(casenum);
				Dc_Education edu=erepo.findByCaseno(casenum);
				if(dcinc.getIncome()<=0 && "Graduation".equals(edu.getHighest_qualifcation())&& edu.getYearof_completion()<2022) {
					apdisplay.setPlanstatus("Approved");
					apdisplay.setBenefitAmt(150);
					 
				 }else {
					 apdisplay.setPlanstatus("Denied");
					 apdisplay.setDenialreason("conditions doesnot satisfy");
				         
				   }
			   }
			apdisplay.setStartDate(now);
			apdisplay.setEndDate(now.plusMonths(6));
			return apdisplay;	
		
	}

}
