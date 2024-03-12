package com.axelor.account.module;

import com.axelor.account.service.AccountService;
import com.axelor.account.service.AccountServiceImpl;
import com.axelor.app.AxelorModule;

public class AccountModule extends AxelorModule{
	@Override
	protected void configure() {
		bind(AccountService.class).to(AccountServiceImpl.class);
	}
}
