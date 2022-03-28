select *
from brand
inner join company on brand.id = company.brand_id
inner join personal_account personal on personal.company_id = company.id
inner join fees_personal_accounts fees on fees.personal_account_id = personal.id
inner join priority_service priority on priority.fees_personal_accounts_id = fees.id
inner join price on price.priority_service_id = priority.id
inner join customers on customers.price_id = price.id
inner join minimum on minimum.priority_service_id = priority.id
inner join maximum on maximum.priority_service_id = priority.id