select *
from brand
inner join company on brand.id = company.brand_id
inner join personal_account personal on personal.company_id = company.id
inner join opening_closing_channels channel on channel.personal_account_id = personal.id
inner join transaction_methods transaction on transaction.personal_account_id = personal.id
inner join terms_conditions terms on terms.personal_account_id = personal.id
inner join minimum_balance balance on balance.terms_conditions_id = terms.id
inner join income_rate income on income.personal_account_id = personal.id