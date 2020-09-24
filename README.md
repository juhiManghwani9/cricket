# cricket-service

To Set up,you need to create two index on fields <b>"emailId"</b> and <b>"phoneNumber"</b> in MongoDB.</br>
db.user_details.createIndex({emailId:1},{unique:true})</br>
db.user_details.createIndex({phoneNumber:1},{unique:true})
