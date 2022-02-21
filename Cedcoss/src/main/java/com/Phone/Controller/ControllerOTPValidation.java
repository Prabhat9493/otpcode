package com.Phone.Controller;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Phone.helper.EmailTemplate;
import com.Phone.service.EmailService;
import com.Phone.service.OTPService;


@RestController
public class ControllerOTPValidation {
	

	
	@Autowired
	public OTPService otpService;
	
	@Autowired
	public EmailService emailService;
	
	@GetMapping("/generateOtp")
	public String generateOTP() throws MessagingException{
		
			Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
			String username = auth.getName();
			int otp = otpService.generateOTP(username);
			//Generate The Template to send OTP 
			EmailTemplate template = new EmailTemplate("SendOtp.html");
			Map replacements = new HashMap();
			replacements.put("user", username);
			replacements.put("otpnum", String.valueOf(otp));
			String message = template.getTemplate((HashMap<String, String>) replacements);
	
			emailService.sendOtpMessage("nayanajain854@gmail.com", "OTP -SpringBoot", message);
			
	return "otppage";
	}
	
	@RequestMapping(value ="/validateOtp", method = RequestMethod.GET)
	public @ResponseBody String validateOtp(@RequestParam("otpnum") int otpnum){
		
			final String SUCCESS = "Entered Otp is valid";
			final String FAIL = "Entered Otp is NOT valid. Please Retry!";
			Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
			String username = auth.getName();
			//Validate the Otp 
			if(otpnum >= 0){
				
			  int serverOtp = otpService.getOtp(username);
				if(serverOtp > 0){
				  if(otpnum == serverOtp){
					  otpService.clearOTP(username);
			
					  return ("Entered Otp is valid");
					} 
					else {
						return FAIL;
					   }
				   }else {
				  return FAIL;
				   }
				 }else {
					return FAIL;
			 }
		  }

	}

