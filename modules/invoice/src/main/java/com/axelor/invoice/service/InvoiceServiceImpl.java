package com.axelor.invoice.service;

import java.util.ArrayList;
import java.util.List;

import com.axelor.inject.Beans;
import com.axelor.invoice.db.Invoice;
import com.axelor.invoice.db.repo.InvoiceRepository;
import com.axelor.rpc.Response;
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
				return;
			}
		}
		
			
	}
	
	
}
