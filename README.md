# QueenAndDragon REST API

It is an API using which user can do the following tasks:

-- POST request to Register the new rules .

-- POST request to Register the new dragon .

-- GET request to view all the defined rules.

-- DELETE request to delete the rules by RuleID.

-- GET request to know whether the dragon can kill animal or not.


How to run this API.

- Import the project in Intellij or Eclipse.

- Run the GOT Application.

Use Postman or Browser to run the Rest http://localhost:8080/

1) POST request to Register the new rules 

   REST API : http://localhost:8080/rules
   
   Input :
   
    {
		  "time" : "5",
		  "noOfAnimals" : "2"
    }


  Output : 
  
    ruleId : Rule3

2) POST request to Register the new dragon 

   REST API : http://localhost:8080/dragons
   
   Input :
   
	 {
		"Name" : "Dragonn" 	
	}
   
   Output : 
   
     dragonId : Drogon1
     
3) GET request to view all the defined rules.

   REST API : http://localhost:8080/rules
   
   Output:
   
   [
    {
        "id": "Rule1",
        "time": 1,
        "noOfAnimals": 3
    },
    {
        "id": "Rule2",
        "time": 3,
        "noOfAnimals": 7
    }
  ]
 
 
4) DELETE request to delete the rules by RuleID.

  REST API : http://localhost:8080/rules/Rule1
  
  
5) GET request to know whether the dragon can kill animal or not.

   REST API : http://localhost:8080/dragons/Dragon1
   
   Input : 
   
   {
	    "date" : "2020-01-01-12:00",
	    "animalKilled" : "5"
   }
   
   Output :
   
        didKill : True(satisfies Rule1 & Rule2 ) / didKill : False (break Rule1 )   
        
