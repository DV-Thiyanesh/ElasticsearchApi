

<!DOCTYPE>
<html lang="en">

<head>
<title>LOG DATA</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--=======================================================================================================================-->
<!--jquery-->
<!--=======================================================================================================================-->
<script src="https://code.jquery.com/jquery-3.3.1.js"
	integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
	crossorigin="anonymous"></script>


<!--Model Dialog-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<!--=======================================================================================================================-->
<!--datatables-->
<!--=======================================================================================================================-->
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" />
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="http://localhost:8080/css/spin.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<style>
.button {
	background-color: #4CAF50;
	border: none;
	color: white;
	padding: 15px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
}
</style>


<script>
	$(document).ready(function() {
		$("#btnsubmit").click(function(event) {
			$('.spinner').attr('hidden', false);
		//	alert("click");
			var req_data = $("#loginform").serialize();
			//alert(req_data)
			console.log("SUCCESS");
			var ts = new Date("2019-03-27");
			var ts1 = new Date();
			//console.log(ts);
			//console.log(ts1);
			// console.log(ts.toJSON());
			//   console.log(ts.toISOString()); 
			//console.log(ts.toDateString());
			event.preventDefault();
			$.ajax({
				url : "http://localhost:8080/restt/user/datesearch2",
				data : req_data,
				dataType : "JSON",
				success : function(responsedate) {
					//alert("success")
					console.log(responsedate)
					$('#example').DataTable({
						data : responsedate,
						"columns" : [ {
							"data" : "type"
						}, {
							"data" : "computer_name"
						}, {
							"data" : "message"
						}, {
							"data" : "event_time"
						}

						]
					});
					$('.spinner').attr('hidden', true);
				},
				error : function(e) {
					//alert("Error");
					$('.spinner').attr('hidden', true);
				}
			});

		});

	});
</script>
</head>
<body>
	<div class="spinner mx-auto" hidden>
		<img
			src="http://localhost:8080/assets/images/Spinner-1s-200px.gif"
			class="loader">
	</div>
	<div id="main">
		<header style="background-color: white;">
			<h1
				style="color: blue; font-family: Heletica Neue, Arial; font-size: 50px; align-content: center">LOG
				DATA</h1>
		</header>
		<form id="loginform">
			ENTER THE FROM DATE: <input type="date" name="fromdate" /> ENTER THE
			TO DATE: <input type="date" name="todate" />
			<!-- <input type="submit" class="button" value="submit" id="btnsubmit"> -->
			<input type="submit" class="btn btn-info" value="Submit"
				id="btnsubmit">
		</form>
		CHART FORMAT <a href="http://localhost:8080/restt/user/displays"
			class="btn btn-success" role="button">CHART</a>
		<table id="example" class="display" style="width: 100%; color:green; background-color:black;">
			<thead>
				<tr>
					<th>Type</th>
					<th>Computer Name</th>
					<th>Message</th>
					<th>Date</th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th>Type</th>
					<th>Computer Name</th>
					<th>Message</th>
					<th>Date</th>

				</tr>
			</tfoot>
		</table>
	</div>
</body>

</html>