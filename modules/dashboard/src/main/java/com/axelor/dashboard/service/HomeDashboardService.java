package com.axelor.dashboard.service;

import java.time.LocalDateTime;

import com.axelor.contact.db.Contact;

public interface HomeDashboardService {
	public void calculateAmountInvoice(LocalDateTime invoiceDate , Long customerId);
}
