select *
from brand
inner join company on brand.id = company.brand_id
inner join business_account business on business.company_id = company.id
inner join service_bundle service on service.business_account_id = business.id
inner join service_from_service_bundle bundle on bundle.service_bundle_id = service.id
inner join price on price.service_bundle_id = service.id
inner join customers on customers.price_id = price.id
inner join minimum on minimum.service_bundle_id = service.id
inner join maximum on maximum.service_bundle_id = service.id