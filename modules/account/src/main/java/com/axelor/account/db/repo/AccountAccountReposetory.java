package com.axelor.account.db.repo;

import com.axelor.account.db.Account;

public class AccountAccountReposetory extends AccountRepository{
	@Override
	public Account save(Account entity) {
        entity.setFullName(entity.getCode() + " - " + entity.getName());
        return super.save(entity);
	}
}
