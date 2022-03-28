select *
from brand
inner join company on brand.id = company.brand_id
inner join business_account business on business.company_id = company.id
inner join opening_closing_channels channel on channel.business_account_id = business.id
inner join transaction_methods transaction on transaction.business_account_id = business.id
inner join terms_conditions terms on terms.business_account_id = business.id
inner join minimum_balance balance on balance.terms_conditions_id = terms.id
inner join income_rate income on income.business_account_id = business.id