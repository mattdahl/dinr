
// Use Parse.Cloud.define to define as many cloud functions as you want.
// For example:

/** 
 *
 * All the code is the same because I can't query for objects that have an array that contains [x];
 * Query for all students, loop through, and get those who have a match on the array.  Return;
 *
 *
 *
 *
 *
 *
 */
Parse.Cloud.define("dinner", function(request, response) {
  console.log("length -> " + request.params.diningHalls.length);
  var matches = [];
    var query = new Parse.Query("Student");
     query.find({
      success : function(results) { 
        for (var j = 0; j < results.length; j++) {
          var student = results[j];
          for (var i = 0; i < request.params.diningHalls.length; i++) { 
            if (results[j].get("DinnerHalls").indexOf(request.params.diningHalls[i]) > -1 ) {
              matches.push(results[j]);
              }
            }
          }
        response.success(matches);
        } ,
         error: function(error) {
        alert("Error: " + error.code + " " + error.message); }
     });});




Parse.Cloud.define("lunch", function(request, response) {
  console.log("length -> " + request.params.diningHalls.length);
  var matches = [];
    var query = new Parse.Query("Student");
     query.find({
      success : function(results) { 
        for (var j = 0; j < results.length; j++) {
          var student = results[j];
          for (var i = 0; i < request.params.diningHalls.length; i++) { 
            if (results[j].get("LunchHalls").indexOf(request.params.diningHalls[i]) > -1 ) {
              matches.push(results[j]);
              }
            }
          }
        response.success(matches);
        } ,
         error: function(error) {
        alert("Error: " + error.code + " " + error.message); }
     });});

Parse.Cloud.define("dinner", function(request, response) {
  console.log("length -> " + request.params.diningHalls.length);
  var matches = [];
    var query = new Parse.Query("Student");
     query.find({
      success : function(results) { 
        for (var j = 0; j < results.length; j++) {
          var student = results[j];
          for (var i = 0; i < request.params.diningHalls.length; i++) { 
            if (results[j].get("LunchHalls").indexOf(request.params.diningHalls[i]) > -1 ) {
              matches.push(results[j]);
              }
            }
          }
        response.success(matches);
        } ,
         error: function(error) {
        alert("Error: " + error.code + " " + error.message); }
     });});
