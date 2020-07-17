# On Demand Service

> The company provides various products e.g shirts, hoodies, mugs, cushions, etc. The suppliers are spread
  around the world and therefore the delivery of products depends on the region
  where they are shipped to.

> This project is to implement a solution that gives the number of days for delivery and
  the amounts of shipments and their details based on the list of items in the basket
  and the region where those items are supposed to be delivered. There might be
  more shipments as not all suppliers provide all products.


## Table of Contents (Optional)

> If your `README` has a lot of info, section headers might be nice.

- [Overview Of The Solution](#Overview Of The Solution)
- [Usage](#Usage)
- [Control Flow](#Control Flow)
- [Package Structure](#Package Structure)
- [Tests](#Tests)


---

## Overview Of The Solution

- It's a `Spring Boot` `MVC` `REST` solution
- This solution also involves `JPA`, `Hibernate`
- The backend DB is `H2` embedded database
---
## Usage
---
> POST request to http://localhost:8080/order with a json payload
````
{
    “region”: “us”,
    “basket”: {
         “items”: [
            {
                “product”: “black_mug”,
                “count”: 1
            },
            {
                “product”: “pink_t-shirt”,
                “count”: 2
            },
          ]
     }
}
````

## Control Flow
---
```
<--Jeson--> |Contol Layer| <--DTOs--> |Service Layer| <--Entities--> |Repocitory Layer| <--> DB
```

## Package Structure
```
controllers   --> controller front end with the Request mappings
dto
    -request  --> DTOs used for capturing request payload (basket informaton)
    -response --> contains DTOs used for transering the response (delivery informaton)
    -common   --> DTOs shaired between request and response ex: ItemDto
model      --> houses entity clases used to persist data
repository --> houses interfaces used to wire JPA, Hibernate
runner     --> holds the data initializer class
services   --> holsds classes with the business logic
test       --> holds the unit test classes
```


## Tests

- Test Scenario 1: 
      Having a list of items containing product A with delivery time 3 days and product B
      with delivery time 1 day, then the delivery time is 3.*/

      @Test
      void placeOrderScenario1()
- Test Scenario 2: 
      Having a product A from two suppliers A and B. When supplier A delivers product A
      in 3 days and supplier B delivers product A in 2 days, then delivery time for that product is 2 days.*/
 
      @Test
      void placeOrderScenario2() {
- Test Scenario 3: 
       Having a t-shirt and hoodie in the basket. When t-shirt can be shipped from
       supplier A and B and hoodie can be shipped from supplier B and C, then deliver the
       t-shirt and hoodie from supplier B

       @Test
       void placeOrderScenario3()  {
- Scenario 4
      Having a 10 t-shirt in the basket and two suppliers A and B. When there are only 6
      T-shirts from supplier A and 7 t-shirts from supplier B on stock, then there would
      be two shipments. One from supplier A with 6 t-shirts and second from supplier B
      with 4 t-shirts.

      @Test
      void placeOrderScenario4()  {
---

