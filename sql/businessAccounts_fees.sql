select *
from brand
inner join company on brand.id = company.brand_id
inner join business_account business on business.company_id = company.id
inner join fees_business_accounts fees on fees.business_account_id = business.id
inner join service_business_accounts service on service.fees_business_accounts_id = fees.id
inner join price on price.service_business_accounts_id = service.id
inner join customers on customers.price_id = price.id
inner join minimum on minimum.service_business_accounts_id = service.id
inner join maximum on maximum.service_business_accounts_id = service.id