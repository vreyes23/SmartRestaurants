<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
echo <<<_END
<html lang="en">
<head>
<title>Smart Restaurant</title>

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

<div class="container">
<section class="menu">
		<div class="wrapper">
			<div class="menu_title">
				<h2>The Menu</h2>
			</div>
			
			<div class="mean_menu">
				<!--  left menu row  -->
				<article class="lmenu">
					<ul>
						<li>
							<div class="item_info">
								<h3 class="item_name">Double-Double.</h3>
								<p class="item_desc">Double meat and double cheese.</p>
							</div>
							<h4 class="price">$2.95</h4>
							<span class="separator"></span>
						</li>
						<li>
							<div class="item_info">
								<h3 class="item_name">Hamburger.</h3>
								<p class="item_desc">Chicken, mozzarella cheese, onions.</p>
							</div>
							<h4 class="price">$1.65</h4>
							<span class="separator"></span>
						</li>
						<li>
							<div class="item_info">
								<h3 class="item_name">Cheese-Burger.</h3>
								<p class="item_desc">Sweetcorn, Cheese.</p>
							</div>
							<h4 class="price">$1.95</h4>
							<span class="separator"></span>
						</li>
						<li>
							<div class="item_info">
								<h3 class="item_name">French Fries.</h3>
								<p class="item_desc">Deep fried Potato.</p>
							</div>
							<h4 class="price">$1.19</h4>
							<span class="separator"></span>
						</li>
					</ul>
				</article>

				<!--  right menu row  -->
				<article class="rmenu">
					<ul>
						<li>
							<div class="item_info">
								<h3 class="item_name">Sweetcorn-cheese.</h3>
								<p class="item_desc">Tuna, Sweetcorn, Cheese.</p>
							</div>
							<h4 class="price">$4</h4>
							<span class="separator"></span>
						</li>
						<li>
							<div class="item_info">
								<h3 class="item_name">Coke.</h3>
								<p class="item_desc">Small, Medium, Large.</p>
							</div>
							<h4 class="price">$1.15(S), $1.25(M), 1.45(L)</h4>
							<span class="separator"></span>
						</li>
						<li>
							<div class="item_info">
								<h3 class="item_name">Root Beer.</h3>
								<p class="item_desc">Small, Medium, Large.</p>
							</div>
							<h4 class="price">$1.15(S), $1.25(M), 1.45(L)</h4>
							<span class="separator"></span>
						</li>
						<li>
							<div class="item_info">
								<h3 class="item_name">Seven Up</h3>
								<p class="item_desc">Small, Medium, Large.</p>
							</div>
							<h4 class="price">$1.15(S), $1.25(M), 1.45(L)</h4>
							<span class="separator"></span>
						</li>
					</ul>
				</article>
			</div>

			<!--  hidden menu items  -->
			
			<div class="load-more">
				<a href="#hidden_items" id="more_items">
					show more
					<hr/>
					<span class="bottom_arrow"></span>
				</a>
			</div>
		</div>
</section>




</body>
</html>
_END;
?>
	

