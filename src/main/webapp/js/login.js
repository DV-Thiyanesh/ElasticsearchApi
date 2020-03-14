$(document).ready(function () {
	'use strict';

	setTimeout(function () {
		$('body').addClass('loaded');
	}, 2000);
	
	
	var url ='http://localhost:8080/elastic/refreshCaptcha';
	var method = 'POST';
	var dataType = 'json';
	

	
	$.ajax({
		type: method,
		url: url,
		dataType: dataType,
		success: function (responseData) {
		
			var returnedData = JSON.parse(JSON.stringify(responseData));
			var captcha = returnedData.captcha;
			var imagesrc = 'data:image/png;base64,' + captcha;
			var enc = returnedData.randCode;
			$('#captcha').attr('src', imagesrc);
			$('#passwordslt').attr('value', enc);
		},
		error: function (xh) {
			alert('status ' + xh.statusText);
		}
	});
	
	if ($("script[src*='https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/crypto-js.js']").length < 1) {
		$.getScript('https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/crypto-js.js');
	}
	//=================================================================imageSrc================
	var c_src = $('#captchaslt').val();
	var imagesrc = 'data:image/png;base64,' + c_src;
	$('#captcha').attr('src', imagesrc);
	//=================================================================validation================
	//$('#loginbtn').submit(function()
	//{
	$.validate({
		validateOnBlur: true,
		form: '#formLogin',
		onSuccess: function()
		{
			//alert("login");
			var saltbase = $('#passwordslt').val();
			var salt = window.atob(saltbase);
			var password = $('#password').val(); 
			var hash = hashing(salt, password);
			//alert(hash);
			$('#password').val(hash);
			function hashing(salt, password) 
			{
				var hashsalt = CryptoJS.SHA512(salt);
				var hashPassword = CryptoJS.SHA512(password);
				var PasswordSalted = hashsalt + hashPassword;
				var hashed = CryptoJS.SHA512(PasswordSalted);
				return hashed;
			}			
		}
	});	

	//==================================================================refresh=================
	$('#refresh').click(function (event) {
		event.preventDefault();

		//var url = 'http:/LoginJson/captcha';
		var url ='http://localhost:8080/elastic/refreshCaptcha';
		var method = 'POST';
		var dataType = 'json';

		ajaxrefresh(method, url, dataType);

		//==============================================================ajaxRefresh===============
		function ajaxrefresh(method, url, dataType) {
			//mm var getenc = "";
			$.ajax({
				type: method,
				url: url,
				dataType: dataType,
				success: function (responseData) {
					var returnedData = JSON.parse(JSON.stringify(responseData));
					var captcha = returnedData.captcha;
					var imagesrc = 'data:image/png;base64,' + captcha;
					var enc = returnedData.randCode;

					$('#captcha').attr('src', imagesrc);
					$('#passwordslt').attr('value', enc);
				},
				error: function () {
					alert('404!!! Server Not Found');
				}
			});
			
		}
	});
});