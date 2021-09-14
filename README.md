# microservice003-crud-student-profile
CRUD operation of Student Profile

## API End Points
### GET :: /api/master-data/
	boards :: List all boards - to populate dropdowns in the frontend
	levels :: List all levels - to populate dropdowns in the frontend

### GET :: api/address/
	countries :: List all countries - to populate dropdowns in the frontend
	states(BigInt country_id) :: List all states of given country - to populate dropdowns in the frontend
	towns(BigInt state_id) :: List all towns of given state - to populate dropdowns in the frontend
	suburbs(BigInt town_id) :: List all suburbs of given town - to populate dropdowns in the frontend
