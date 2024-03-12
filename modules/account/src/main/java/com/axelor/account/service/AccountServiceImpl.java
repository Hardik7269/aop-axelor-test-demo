package com.axelor.account.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.axelor.inject.Beans;
import com.axelor.invoice.db.Invoice;
import com.axelor.invoice.db.InvoiceLine;
import com.axelor.invoice.db.repo.InvoiceRepository;
import com.axelor.sale.db.SaleOrder;
import com.axelor.sale.db.SaleOrderLine;
import com.google.inject.persist.Transactional;

public class AccountServiceImpl implements AccountService {
	
	
	
	@Transactional
	@Override
	public void generateInvoiceFromSaleOrders(SaleOrder saleOrderObj) {
		Invoice invoice = new Invoice();
		invoice.setSaleOrder(saleOrderObj);
		invoice.setStatusSelect(0);
		invoice.setCustomer(saleOrderObj.getCustomer());
		invoice.setInTaxTotal(saleOrderObj.getInTaxTotal());
		LocalDate estDate = saleOrderObj.getEstimatedInvoiceDate();
		invoice.setInvoiceDateT(estDate.atStartOfDay());
		
		List<SaleOrderLine> saleOrderLineObj = saleOrderObj.getSaleOrderLineList();
		List<InvoiceLine> invoiceLineListObj = new ArrayList<InvoiceLine>();
		
		for(int i = 0 ; i < saleOrderLineObj.size() ; i++) {
			SaleOrderLine sol = saleOrderLineObj.get(i);
			InvoiceLine invoiceLine = new InvoiceLine();
			invoiceLine.setProduct(sol.getProduct());
			invoiceLine.setDescription(sol.getDescription());
			invoiceLine.setUnitPriceUntaxed(sol.getUnitPriceUntaxed());
			invoiceLine.setExTaxTotal(sol.getExTaxTotal());
			invoiceLine.setQuantity(sol.getQuantity());
			invoiceLine.setTaxRate(sol.getTaxRate());
			invoiceLine.setInvoice(invoice);
			BigDecimal inTaxCalculate = sol.getExTaxTotal().multiply(BigDecimal.ONE.add(sol.getTaxRate())); 
			
			invoiceLine.setInTaxTotal(inTaxCalculate);
			invoiceLineListObj.add(invoiceLine);
		}
		
		invoice.setInvoiceLineList(invoiceLineListObj);
		
		Beans.get(InvoiceRepository.class).save(invoice);
	}

}
