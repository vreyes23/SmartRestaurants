<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<?php
echo <<<_END
<html>
    <head>
        <meta charset="UTF-8">
        <title>Smart Restaurants</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<link rel="stylesheet" type="text/css" href="reset.css">
	<link rel="stylesheet" type="text/css" href="main1.css">
    </head>
    <body>
        <header>
		<div class="wrapper">
			<div class="logo">
				<a href=""><img src="Images\logo.PNG" alt="Res" title=""/></a>
			</div>

			<nav>
				<ul>
					
					<li><a href="index.php">Home</a></li>
					<li><a href="Menu.php">Menu</a></li>
					<li><a href="Order.php">Order</a></li>
				</ul>
			</nav>
		</div>
	</header>
<section class="hero">
		<div class="caption">
			<h3>Welcome to Smart Restaurant</h3>
			<h4>
				<span class="rsep"></span>
				This is the homepage of smart restaurant
				<span class="lsep"></span>
			</h4>
			
		</div>
	</section>
        
        
    </body>
</html>
_END;
?>