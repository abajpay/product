# alok-repository
Alok's repository

1. Replace ip 192.168.1.3 from project with actual ip of local machine before building docker image.
2. To buld docker image run ./gradlew build docker from <REPO>/product directory.
  
  
  GraphQL Queries:
  
  1.Get Product by Id

{ 
  getProduct(id:3){
    id
    name
    price
  }
}

2.Get products by name

{ 
  getProductsByName(name:”Petrol"){
    id
    name
    price
  }
}

3. Get all products


{
  allProducts{
    name
    price
  }
}


4.Create Product 

 mutation {
  createProduct(name: "Kerosin", price: 31.12) {
    id
    name
    price
  }
}

5.Update price

 mutation {
  updateProduct(id: 2, price: 131.12) {
    id
    name
    price
  }
}

6. Rename Product

 mutation {
  renameProduct(id: 2, name: “Mobil Oil") {
    id
    name
    price
  }
}

Delete product

mutation {
  deleteProduct(id:4)
}
