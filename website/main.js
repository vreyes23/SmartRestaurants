
//alert(httpGet("https://833c1344-ce44-4b24-b461-98b8faec0b65-bluemix.cloudant.com/smartrestaurants/_all_docs"));

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
	  	dataLoaded(data);
	  
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