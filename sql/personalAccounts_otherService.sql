select *
from brand
inner join company on brand.id = company.brand_id
inner join personal_account personal on personal.company_id = company.id
inner join fees_personal_accounts fees on fees.personal_account_id = personal.id
inner join other_service other on other.fees_personal_accounts_id = fees.id
inner join price on price.other_service_id = other.id
inner join customers on customers.price_id = price.id
inner join minimum on minimum.other_service_id = other.id
inner join maximum on maximum.other_service_id = other.id