
      
//alert(httpGet("https://833c1344-ce44-4b24-b461-98b8faec0b65-bluemix.cloudant.com/smartrestaurants/_all_docs"));  167a3b37da7d4a1bae284264d504e904

//readDB("https://833c1344-ce44-4b24-b461-98b8faec0b65-bluemix.cloudant.com/smartrestaurants/_all_docs", "thennegencelfinglygodycr","787e88b978422e10f4e8b25318ec8b4c067bb5d3");
function readFromDB()
{
	$.ajax
	({
	  type: "GET",
	  url: "https://85d7a97e-6a4a-4710-bf33-116d883d77bb-bluemix.cloudant.com/test/_all_docs",
	  dataType: 'json',
	  async: false,
	   
	  headers: {
	  //	"Access-Control-Allow-Origin: ": "*",
      //"Access-Control-Allow-Methods: ": "GET",
      //"Access-Control-Allow-Headers: ": "Authorization",
	    "Authorization": "Basic " + btoa("rryoupperarnotedgenerste" + ":" + "c91a8e3515d96e5eeb3319f80d7ef35733413516")
	  },
	  //data: '{ "comment" }',
	  success: function(data) { 
	  
 
	  	readFromDoc(data);
	  
    },
    error: function(data) {
        alert("An error occurred: " + data);
        
    }
	});
}


function readFromDoc(data)
{
	for (var i = 0; i < data.rows.length; i++)
	{

		var obj=data.rows[i].id;
	 $.ajax
	({
	  type: "GET",
	  url: "https://85d7a97e-6a4a-4710-bf33-116d883d77bb-bluemix.cloudant.com/test/"+obj,
	  dataType: 'json',
	  async: false,
	   
	  headers: {
	  //	"Access-Control-Allow-Origin: ": "*",
      //"Access-Control-Allow-Methods: ": "GET",
      //"Access-Control-Allow-Headers: ": "Authorization",
	    "Authorization": "Basic " + btoa("rryoupperarnotedgenerste" + ":" + "c91a8e3515d96e5eeb3319f80d7ef35733413516")
	  },
	  //data: '{ "comment" }',
	  success: function(data) { 
	  
 
	  	dataLoaded(data);
	  
    },
    error: function(data) {
        alert("An error occurred: " + data);
        
    }
	});
  }
}


function dataLoaded(data)
{
	
	console.log(data);
	var table = document.getElementById("resto");

// Create an empty <tr> element and add it to the 1st position of the table:
	var row = table.insertRow(1);

// Insert new cells (<td> elements) at the 1st and 2nd position of the "new" <tr> element:
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	var cell4 = row.insertCell(3);
// Add some text to the new cells:
	cell1.innerHTML = JSON.stringify(data.nameValuePairs["Ticket number"]);
	cell2.innerHTML = JSON.stringify(data.nameValuePairs["Customers name"]);
	cell3.innerHTML = JSON.stringify(data.nameValuePairs["Food list"]);
	
	cell4.innerHTML = '<button type="button" class="btn btn-primarybtn-sm" onclick="buttonClicked('+data.nameValuePairs["Ticket number"]+')">Done</button><button type="button" class="btn btn-primarybtn-sm">Cancel</button>';

	
}

function buttonClicked(param)
{

	$.ajax
	({
	  type: "GET",
	  url: "https://ramyapushnotification.mybluemix.net/sendPushNotification",
	  dataType: 'json',
	  async: false,
	   
	  headers: {
	  //	"Access-Control-Allow-Origin: ": "*",
      //"Access-Control-Allow-Methods: ": "GET",
      //"Access-Control-Allow-Headers: ": "Authorization",
	    //"Authorization": "Basic " + btoa("rryoupperarnotedgenerste" + ":" + "c91a8e3515d96e5eeb3319f80d7ef35733413516")
	  },
	  //data: '{ "comment" }',
	  success: function(data) { 
	  
 
	  	alert("Push notification sent for order #" +param);
	  
    },
    error: function(data) {
        alert("An error occurred: " + data);
        
    }
	});
}



