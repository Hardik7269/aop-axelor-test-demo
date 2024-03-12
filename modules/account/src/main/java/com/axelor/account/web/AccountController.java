package com.axelor.account.web;

import com.axelor.account.service.AccountService;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.axelor.rpc.Context;
import com.axelor.sale.db.SaleOrder;
import com.axelor.sale.db.repo.SaleOrderRepository;

public class AccountController {
	
	public void generateInvoiceFromSaleOrder(ActionRequest request , ActionResponse response) {
		Context context = request.getContext();
		SaleOrder saleOrder = context.asType(SaleOrder.class);
		saleOrder=Beans.get(SaleOrderRepository.class).find(saleOrder.getId());
		Beans.get(AccountService.class).generateInvoiceFromSaleOrders(saleOrder);
	}
}
