/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//alert(httpGet("https://833c1344-ce44-4b24-b461-98b8faec0b65-bluemix.cloudant.com/smartrestaurants/_all_docs"));

//readDB("https://833c1344-ce44-4b24-b461-98b8faec0b65-bluemix.cloudant.com/smartrestaurants/_all_docs", "thennegencelfinglygodycr","787e88b978422e10f4e8b25318ec8b4c067bb5d3");

function readFromDB()
{
	$.ajax
	({
	  type: "GET",
	  url: "85d7a97e-6a4a-4710-bf33-116d883d77bb-bluemix",
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
	  	ticketLoaded(data);
	  
    },
            error: function(data) {
                alert("An error occurred: " + data);

            }
        
	});
}
function dataLoaded(data)
{
	
	console.log(data);
	divElement = document.getElementById("someDiv");
	divElement.innerHTML = JSON.stringify( data);

	for (var i = 0; i < data.rows.length; i++)
	{
		alert(data.rows[i].id);
	}

}

function ticketLoaded(data)
{
	
	
        
        JSONObject object = new JSONObject("");
        JSONObject getObject = object.getJSONObject("test");
        JSONArray getArray1 = getObject.getJSONObject("nameValuePairs");
        JSONArray getArray2 = getObject.getJSONObject("customers name");
        JSONArray getArray3 = getObject.getJSONObject("ticket number");
        JSONArray getArray4 = getObject.getJSONObject("food items");

            for(int i = 0; i < getArray1.size(); i++)
            {
                  JSONObject objects = getArray1.getJSONArray(i);
                  //Iterate through the elements of the array i.
                  //Get thier value.
                  //Get the value for the first element and the value for the last element.
            }
            for(int i = 0; i < getArray2.size(); i++)
            {
                  JSONObject objects = getArray2.getJSONArray(i);
                  //Iterate through the elements of the array i.
                  //Get thier value.
                  //Get the value for the first element and the value for the last element.
            }
            for(int i = 0; i < getArray3.size(); i++)
            {
                  JSONObject objects = getArray3.getJSONArray(i);
                  //Iterate through the elements of the array i.
                  //Get thier value.
                  //Get the value for the first element and the value for the last element.
            }
            for(int i = 0; i < getArray4.size(); i++)
            {
                  JSONObject objects = getArray4.getJSONArray(i);
                  //Iterate through the elements of the array i.
                  //Get thier value.
                  //Get the value for the first element and the value for the last element.
            }
            
            
}
