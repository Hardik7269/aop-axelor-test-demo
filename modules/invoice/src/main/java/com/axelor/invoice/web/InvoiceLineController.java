package com.axelor.invoice.web;

import com.axelor.invoice.db.Invoice;
import com.axelor.invoice.db.InvoiceLine;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.axelor.rpc.Context;
import com.axelor.sale.db.Product;

public class InvoiceLineController {
	public void setTaxRate(ActionRequest request , ActionResponse response) {
		Context context = request.getContext();
		
		InvoiceLine invoiceLine = context.asType(InvoiceLine.class);
		Invoice invoice = invoiceLine.getInvoice();
		response.setValue("taxRate", 0.2);
		
		Boolean isSubjectToTax = invoice.getCustomer().getIsSubjectToTaxes();
		
		if(isSubjectToTax) {
			response.setHidden("taxRate", false);
		}else {		
			response.setHidden("taxRate", true);
		}
		
	}
	
	public void setDiscription(ActionRequest request , ActionResponse response) {
		Context context = request.getContext();
		Product product = context.asType(Product.class);
		
		response.setValue("description", product.getFullName());
	}
	public void setUnitPriceUntaxed(ActionRequest request , ActionResponse response) {
		Context context = request.getContext();
		InvoiceLine invoiceLine = context.asType(InvoiceLine.class);
		
	}
}
