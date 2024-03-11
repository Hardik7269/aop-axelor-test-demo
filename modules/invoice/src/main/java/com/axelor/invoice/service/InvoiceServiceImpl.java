package com.axelor.invoice.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.axelor.contact.db.Contact;
import com.axelor.inject.Beans;
import com.axelor.invoice.db.Invoice;
import com.axelor.invoice.db.InvoiceLine;
import com.axelor.invoice.db.repo.InvoiceRepository;
import com.axelor.rpc.Response;
import com.axelor.sale.db.Product;
import com.google.inject.persist.Transactional;

public class InvoiceServiceImpl implements InvoiceService{

	@Transactional(rollbackOn = Exception.class)
	@Override
	public void setStatusCancelBtn(List<Integer> list) {
		
		for(Integer listItem : list) {
			Long id = (long) listItem;
			Invoice invoice = Beans.get(InvoiceRepository.class).find(id);
			if(invoice.getStatusSelect()!=2) {				
				invoice.setStatusSelect(3);
			}
			Beans.get(InvoiceRepository.class).save(invoice);
		}
	}
	
	@Transactional(rollbackOn = Exception.class)
	@Override
	public void invoiceLineMergeBtn(List<Integer> idList) {
		List<Invoice> invoiceList = new ArrayList<Invoice>();		
				
		for(Integer id : idList) {
			Invoice invoice = Beans.get(InvoiceRepository.class).find((long)id);	
			invoiceList.add(invoice);
		}
		String customerName = invoiceList.get(0).getCustomer().getFullName().toString();
		for(Invoice inv : invoiceList) {
			if(!(customerName.equals(inv.getCustomer().getFullName().toString()))){
				
//				 invoiceList.forEach(e -> e.getCustomer().setArchived(true));
				return;
			}else {
				Invoice newInvoice = generateNewInvoice(inv.getCustomer());
				invoiceList.forEach(e -> e.getCustomer().setArchived(true));
			}
		}	
	}
	
	@Transactional(rollbackOn = Exception.class)
	public Invoice generateNewInvoice(Contact customer ) {
		Invoice newInvoice = new Invoice();
		newInvoice.setCustomer(customer);
		newInvoice.setInvoiceDateT(LocalDateTime.now());
		newInvoice.setStatusSelect(0);
		return newInvoice;
	}

}
