//require Elasticsearch Library 
const elasticsearch = require('elasticsearch');
// instantiate Elasticsearch client 
const client = new elasticsearch.Client({
    host: [ 'http://localhost:9200']
});

// File to base index on
const fileName = 'tx-01';

// ping the client to check if Elasticsearch is up
client.ping({
    requestTimeout: 30000,
}, function(error) {
    // At this point, Elasticsearch is down, check status
    if (error) {
        console.error('Elasticsearch cluster is down!');
    } else {
        console.log('Everything is okay!');
    }
});

//creating a new index, if indicated index is already created, function fails safely
client.indices.create({
    index: fileName;
}, function(error, response, status) {
    if (error) {
        console.log("Error occured, Index already created!");
    } else {
        console.log("created a new index", response);
    }
});

// Require the array of records that was downloaded
const records = require('./' + fileName + '.json');
// declare an empty array called bulk
var bulk = [];
//loop through each city and create and push two objects into the array in each loop
//first object sends the index and type you will be saving the data as
//second object is the data you want to index

records.forEach(record =>{
   bulk.push({index:{ 
                 _index:fileName, 
                 _type:"record_list",
             }          
         })
  bulk.push(record)
})
//perform bulk indexing of the data passed
client.bulk({body:bulk}, function( err, response  ){ 
         if( err ){ 
             console.log("Failed Bulk operation".red, err) 
         } else { 
             console.log("Sucess");
             console.log("Successfully imported"); 
         } 
}); 