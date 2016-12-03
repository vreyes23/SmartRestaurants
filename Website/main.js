
      
//alert(httpGet("https://833c1344-ce44-4b24-b461-98b8faec0b65-bluemix.cloudant.com/smartrestaurants/_all_docs"));  167a3b37da7d4a1bae284264d504e904
var i=0;
//readDB("https://833c1344-ce44-4b24-b461-98b8faec0b65-bluemix.cloudant.com/smartrestaurants/_all_docs", "thennegencelfinglygodycr","787e88b978422e10f4e8b25318ec8b4c067bb5d3");
function readFromDB()
{   //e.preventDefault();
	$.ajax
	({
	  type: "GET",
	  url: "https://85d7a97e-6a4a-4710-bf33-116d883d77bb-bluemix.cloudant.com/test/_all_docs",
	  dataType: 'jsonp',
	  async: true,
	   
	  headers: {
         //"Access-Control-Allow-Origin:": "*",
         //"Access-Control-Allow-Methods: ": "GET",
         //"Access-Control-Allow-Headers: ": "Authorization",
	    "Authorization": "Basic" + btoa("rryoupperarnotedgenerste" + ":" + "c91a8e3515d96e5eeb3319f80d7ef35733413516")
	  },
	  //data: '{ "comment" }',
	  success: function(data) { 
              //alert("hi An error occurred: " );
	  	readFromDoc(data);
	  
    },
    error: function(data) {
        alert("hi An error occurred: " + JSON.stringify(data));
        
    }
	});
}


function readFromDoc(data)
{
    
	for (var j=i; j < data.rows.length; j++)
	{
		var obj=data.rows[j].id;
                 i++;
	 $.ajax
	({
	  type: "GET",
	  url: "https://85d7a97e-6a4a-4710-bf33-116d883d77bb-bluemix.cloudant.com/test/"+obj,
	  dataType: 'jsonp',
	  async: true,
	   
	  headers: {
    //"Access-Control-Allow-Origin: ": "*",
      //"Access-Control-Allow-Methods: ": "GET",
      //"Access-Control-Allow-Headers: ": "Authorization",
	    "Authorization": "Basic " + btoa("rryoupperarnotedgenerste" + ":" + "c91a8e3515d96e5eeb3319f80d7ef35733413516")
	  },
	  //data: '{ "comment" }',
	  success: function(data) { 
	  	dataLoaded(data);
                //alert(JSON.stringify(data) );
	  
    },
    error: function(data) {
        alert("An error occurred: " + JSON.stringify(data));
        
    }
	});
  }
}


function dataLoaded(data)
{
	
	console.log(data);
	
        var row = $("<tr>");
    var d1=JSON.stringify(data.nameValuePairs["Ticket number"]);
    var d2=JSON.stringify(data.nameValuePairs["Customers name"]);
    var d3=JSON.stringify(data.nameValuePairs["Food list"]);
    row.append($("<td>"+d1+"</td>"))
       .append($("<td>"+d2+"</td>"))
       .append($("<td>"+d3+"</td>"))
       .append($("<td><button type=\"button\" class=\"btn btn-primary btn-sm\">Done</button></td>"))
       ;
  $("#resto tbody").append(row);
        /*
        
        var table = document.getElementById("resto");

// Create an empty <tr> element and add it to the 1st position of the table:
	var row = table.insertRow(1);

// Insert new cells (<td> elements) at the 1st and 2nd position of the "new" <tr> element:
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
        
// Add some text to the new cells:
	cell1.innerHTML = JSON.stringify(data.nameValuePairs["Ticket number"]);
	cell2.innerHTML = JSON.stringify(data.nameValuePairs["Customers name"]);
	cell3.innerHTML = JSON.stringify(data.nameValuePairs["Food list"]);
	*/
}
