package com.axelor.dashboard.web;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.axelor.dashboard.service.HomeDashboardService;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;

public class HomeDashboardController{
	
	public void getHomeDashboardData(ActionRequest request, ActionResponse response) {
		
		Map<String, Object> obj =(Map<String, Object>)request.getRawContext().get("customer");
		Long customerId = Long.valueOf(obj.get("id").toString());
		System.out.println(customerId);
		
//		Context context = request.getContext();
//	    Long obj = (long)(context.get("customer"));
//	   	System.out.println(obj);
//	   LocalDateTime currentDateTime =(LocalDateTime) context.get("invoiceDate");
	   
				
	   	Beans.get(HomeDashboardService.class).calculateAmountInvoice(null, null);
//	   System.out.println(invoiceDateTime);
//	   System.out.println(contactId);
	   
	}
}
