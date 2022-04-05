select *
from brand
inner join company on brand.id = company.brand_id
inner join business_loan business on business.company_id = company.id
inner join fees_loans fees on fees.business_loan_id = business.id
inner join priority_service priority on priority.fees_loan_id = fees.id
inner join price on price.priority_service_id = priority.id
inner join customers on customers.price_id = price.id
inner join minimum on minimum.priority_service_id = priority.id
inner join maximum on maximum.priority_service_id = priority.id