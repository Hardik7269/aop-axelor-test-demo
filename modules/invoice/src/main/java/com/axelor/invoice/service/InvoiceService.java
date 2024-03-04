package com.axelor.invoice.service;

import java.util.*;

public interface InvoiceService {
	public void setStatusCancelBtn(List<Integer> list);
	public void invoiceLineMergeBtn(List<Integer> idList);
}
