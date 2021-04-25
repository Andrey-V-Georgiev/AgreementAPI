# AgreementAPI

## Description

This is Spring Boot REST backend application.
Use file system as DB and store Agreement documents.
Agreement document has "products" property wich is collection of Products.
Every Product has "products" property as well. 

## Requirements

Windows OS with partition (C:)

## Details 

The API works on port 8080 and can be hitted by client such Postman (or similar).
Application assume that the OS is Windows and there is (C:) disk available.
On run will create folder on (C:) to store the data "C:\AgreementAPI_DB".
There are three routes as follow:
- POST /agreement/store  /* Store the given JSON with the Agreement and nested products */
- GET /agreement/references-all /* Gets all Agreement records folder names, helps using next route bellow */
- GET /agreement/agreement-folder-name /* Gets Agreement record by folder name */


## Input JSON example

{
	"name": "Pesho",
	"signBy": "Rumen",
	"products": [
		{
			"name": "Product 1", 
			"price": "1",
			"products": [
				{
					"name": "Nested Product 1", 
					"price": "11",
					"products": [
						{
							"name": "Nested Nested Product 1", 
							"price": "111",
							"products": []
						} ,
									{
							"name": "Nested Nested Product 2", 
							"price": "222",
							"products": []
						} 
					]
				} 
			]
		},
		{
			"name": "Product 2", 
			"price": "2",
			"products": [
				{
					"name": "Nested Product 2", 
					"price": "22",
					"products": [
						{
							"name": "Nested Nested Product 3", 
							"price": "111",
							"products": []
						} ,
									{
							"name": "Nested Nested Product 4", 
							"price": "222",
							"products": []
						} 
					]
				} 
			]
		},
				{
			"name": "Product 3", 
			"price": "3",
			"products": [
				{
					"name": "Nested Product 3", 
					"price": "33",
					"products": [
						{
							"name": "Nested Nested Product 5", 
							"price": "111",
							"products": []
						} ,
									{
							"name": "Nested Nested Product 6", 
							"price": "222",
							"products": []
						} 
					]
				} 
			]
		}
	]
}

