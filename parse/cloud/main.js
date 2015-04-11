
// Use Parse.Cloud.define to define as many cloud functions as you want.
// For example

Parse.Cloud.define("dinner", function(request, response) {
  console.log("length -> " + request.params.diningHalls.length);
  var matches = [];
    var query = new Parse.Query("Student");
     query.find({
      success : function(results) { 
        for (var j = 0; j < results.length; j++) {
          for (var i = 0; i < request.params.diningHalls.length; i++) {
            if (results[j].get("DinnerHalls") != undefined) {
               if (results[j].get("DinnerHalls").indexOf(request.params.diningHalls[i]) > -1 ) {
                 if (matches.indexOf(results[j]) == -1) {
                   matches.push(results[j]);}}
              
                }
            }
          }
        response.success(matches);
        } ,
        error: function(error) {
        alert("Error: " + error.code + " " + error.message); }
     });});
