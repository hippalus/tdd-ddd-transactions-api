package contracts.api

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
                        Save transaction and sign as approved 
                        ```
                        given:
                            transaction
                        when:
                            saved
                        then:
                            we will return transaction id and whether approved or not
                           
                        ```
                        
                        """)
    request {
        method POST()
        url "/transactions"
        headers {
            contentType applicationJson()
        }
        body("""
            {
        "firstName": "john",
        "lastName": "doe",
        "email": "john.doe@gmail.com",
        "productName": "USB Disc",
        "price": 20.0,
        "transactionCode": "TR023"}
                
                """
        )
    }
    response {
        status OK()
        headers {
            contentType applicationJson()

        }
        body("""
                {
                "id": 1,
                "state": "APPROVED",
                }
               
                """
        )
    }

}
