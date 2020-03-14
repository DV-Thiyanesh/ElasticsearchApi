<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>

<head>
    <title>LOGIN</title>
    <!--Quick access-->
    <!--bootstrap css ..............................................................................................-->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.0/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-PDle/QlgIONtM1aqA2Qemk5gPOE7wFq8+Em+G/hmo5Iq0CCmYZLv3fVRDJ4MMwEA" crossorigin="anonymous">
    <!--fontawesome 5.7.1 ..........................................................................................-->
   <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css"
        integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <!--formvalidation css..........................................................................................-->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/theme-default.min.css"
        rel="stylesheet" type="text/css">
    <!--toast css..........................................................................................-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.css"
        integrity="sha256-zlmAH+Y2JhZ5QfYMC6ZcoVeYkeo0VEPoUnKeBd83Ldc=" crossorigin="anonymous" />
    <!--bootstrap css ..............................................................................................-->
    <!--link href="webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">-->
    <!--fontawesome 5.7.1 ..........................................................................................-->
    <!--<link href="webjars/font-awesome/5.7.1/all/all.css" rel="stylesheet">-->
    <link href="http://localhost:8080/css/style.css" rel="stylesheet">
    <link href="http://localhost:8080/css/login.css" rel="stylesheet">
</head>

<body>
    <div id="loader-wrapper">
        <div id="loader"></div>
        <div class="loader-section"></div>
    </div>
    <!------------------------------------------------------------------------------------------------------------------------------------------------------>
    <!--div #LoginSection------------------------------------------------------------------------------------------------------------------------------->
    <!------------------------------------------------------------------------------------------------------------------------------------------------------>
    <div clas="w-100 p-3" id="content" style="padding-top: 10px; padding-bottom:50px; height:100%; background-color:darkslategray">
        <center>
            <!------------------------------------------------------------------------------------------------------------------------------------------------------>
            <!--Card ----------------------------------------------------------------------------------------------------------------------------------------------->
            <!------------------------------------------------------------------------------------------------------------------------------------------------------>
           <div class="card border-dark mb-3" style="max-width: 28rem; box-shadow: 5px 5px 10px #999;">
                <!------------------------------------------------------------------------------------------------------------------------------------------------------>
                <!--Card header----------------------------------------------------------------------------------------------------------------------------------------->
                <!------------------------------------------------------------------------------------------------------------------------------------------------------>
                <div class="card-header">
                    <!-- <img src="../assets/images/logo-icon-boi-trans-s.png"> -->
                    <h1>
  <span style='color: #DB41BB'>E</span>
  <span style='color: #C2BD37'>L</span>
  <span style='color: #DBB012'>A</span>
  <span style='color: #A9D932'>S</span>
  <span style='color: #32D99D'>T</span>
  <span style='color: #3630CF'>I</span>
  <span style='color: #4ECF30'>C</span>
  <span style='color: #2C2B2E'>L</span>
  <span style='color: #2C2B2E'>O</span>
  <span style='color: #2C2B2E'>G</span>
  <span style='color: #2C2B2E'>I</span>
  <span style='color: #2C2B2E'>N</span>
</h1>
                   <!--  <h1 style="text-align:center">LOGIN</h1> -->
                </div>
                <!------------------------------------------------------------------------------------------------------------------------------------------------------>
                <!--Card body------------------------------------------------------------------------------------------------------------------------------------------->
                <!------------------------------------------------------------------------------------------------------------------------------------------------------>
                <div class="card-body">
                    <p class="card-text">
                        <!------------------------------------------------------------------------------------------------------------------------------------------------------>
                        <!--form #login ---------------------------------------------------------------------------------------------------------------------------------------->
                        <!----------------------------------------------------------------------------------------------------------------------------------------------------->
                        <form:form modelAttribute="Login" id="formLogin" method="post" action="/elastic/index">
                            <!--=======================================================================================================================-->
                            <!--alert Login Success-->
                            <!--=======================================================================================================================-->
                            <div class="alert alert-success container" id="alertLoginSuccess" hidden>
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <strong>Login Success!</strong> Login successfully !!!
                            </div>  
                            <!--==================================================================-->
                            <!--alertError-->
                            <!--==================================================================-->
                            <div class="alert alert-danger container-danger" id="alertError" hidden>
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <strong>Error!</strong> Unexpected Error occurred. Please try again !!!
                            </div>
                            <!--==================================================================-->
                            <!--alert Login Failed-->
                            <!--==================================================================-->
                            <div class="alert alert-danger container-danger" id="alertLoginFailed" hidden>
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <strong>Error!</strong> Invalid Credentials !!!
                                <div id="OrderList"></div>
                            </div>
                            <!--==================================================================-->
                            <!--message-->
                            <!--==================================================================-->
                            <div id="Successmessage" name="Successmessage" class="hidden-message" path="SuccessMsg" hidden>
								${SuccessMsg}
                            </div>
                            <div id="Errormessage" name="Errormessage" class="hidden-message" path="ErrorMsg" hidden>
								${ErrorMsg}
                            </div>
                            <!------------------------------------------------------------------------------------------------------------------------------------------------------>
                            <!--UserName -------------------------------------------------------------------------------------------------------------------------------------------->
                            <!------------------------------------------------------------------------------------------------------------------------------------------------------>
                            <div class="form-row">
                                <b>
                                    <label for="userName">Username</label>
                                </b>
                                <div class="form-group input-group mb-2 mr-sm-2 mb-sm-0">
                                    <div class="col-auto">
                                        <label class="sr-only" for="userName">Username</label>
                                        <div class="input-group mb-2">
                                            <div class="input-group-prepend">
                                                <div class="input-group-text"><i class="fas fa-user-alt"></i></div>
                                            </div>
                                            <label class="sr-only" for="userName">Username</label>
                                            <input type="text" class="form-control" id="userName" name="uname"
                                                style="width:300px;" data-validation="required"
                                                data-validation-error-msg="Username is required"/>
                                            <small>Enter the username</small>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!------------------------------------------------------------------------------------------------------------------------------------------------------>
                            <!--Password ------------------------------------------------------------------------------------------------------------------------------------------->
                            <!------------------------------------------------------------------------------------------------------------------------------------------------------>
                            <div class="form-row">
                                <b>
                                    <label for="password">Password</label>
                                </b>
                                <div class="form-group input-group mb-2 mr-sm-2 mb-sm-0">
                                    <div class="col-auto">
                                        <div class="input-group mb-2">

                                            <div class="input-group-prepend">
                                                <div class="input-group-text"><i class="fas fa-lock"></i></div>
                                            </div>
                                            <input type="password" class="form-control" id="password" name="pwd" 
                                                data-validation="required" autocomplete="off" style="width:300px;"
                                                data-validation-error-msg="Password is required"/><br>
                                            <form:hidden class="form-control" id="passwordslt" path="randCode"/>
                                            <input type="text" class="form-control" id="passwordslt"
                                                placeholder="Password" hidden>
                                            <!--hide-->
                                            <small>Enter the Password</small>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <br>
                            <!------------------------------------------------------------------------------------------------------------------------------------------------------>
                            <!--captcha ----------------pp---------------------------------------------------------------------------------------------------------------------------->
                            <!------------------------------------------------------------------------------------------------------------------------------------------------------>
                            <div class="form-row" id="captcha-row" style="max-width: 22rem;">
                                <div class="input-group mb-2">
                                    <div style="border-style: solid;border-width: 1px; margin: auto; ">
                                        <img id="captcha" alt="captcha" style="width:190px;height:40px;" align="middle">
                                        <button id="refresh" style="height:53px;"><i
                                                class="fas fa-sync-alt"></i></button>
                                        <input type="text" hidden class="form-control" id="captchaslt">
                                        <!--hide-->
                                        <form:hidden class="form-control" id="captchaslt" path="captcha" />
                                    </div>
                                    <div style="margin: auto;">
                                        <b>
                                            <label for="password" style="font-size:12px">Type the characters you
                                                see in the image below</label>
                                        </b>
                                    </div>
                                    <div style="margin: auto;">
                                        <div class="input-group mb-2">
                                            <input type="text" class="form-control" id="captchatxt" name="captchaVal"
                                                placeholder="type the image here" data-validation="required"
                                                data-validation-error-msg="Captcha is required">
                                            <br>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!------------------------------------------------------------------------------------------------------------------------------------------------------>
                            <!--btn of the form ----------------------------------------------------------------------------------------------------------------------------------->
                            <!------------------------------------------------------------------------------------------------------------------------------------------------------>
                            <div class="form-row" id="formbtn">
                                <div class="form-group input-group mb-2 mr-sm-2 mb-sm-0" id="divloginbtn">
                                    <input type="submit" class="loginbtn btn btn-primary" id="loginbtn" value="Login"
                                        style="margin-right: 25px; margin-left: 150px"/>
                                    <input type="reset" class="btn btn-secondary">
                                </div>
                            </div>
                        </form:form>
                        <!--close form-->
                        <!------------------------------------------------------------------------------------------------------------------------------------------------------>
                        <!--Closing the form ----------------------------------------------------------------------------------------------------------------------------------->
                        <!------------------------------------------------------------------------------------------------------------------------------------------------------>
                    </p>
                </div>
                <!------------------------------------------------------------------------------------------------------------------------------------------------------>
                <!--Closing the card body ------------------------------------------------------------------------------------------------------------------------------>
                <!------------------------------------------------------------------------------------------------------------------------------------------------------>
            </div>
            <!------------------------------------------------------------------------------------------------------------------------------------------------------>
            <!--Closing the card------------------------------------------------------------------------------------------------------------------------------------>
            <!------------------------------------------------------------------------------------------------------------------------------------------------------>
        </center>

    </div>
    <!------------------------------------------------------------------------------------------------------------------------------------------------------>
    <!--Closing the div -------------------------------------------------------------------------------------------------------------------------------->
    <!------------------------------------------------------------------------------------------------------------------------------------------------------>
</body>
<!--Quick access-->
<!--Bootstrap .........................................................................................................................-->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.0/js/bootstrap.min.js"
    integrity="sha384-7aThvCh9TypR7fIc2HV4O/nFMVCBwyIUKL8XCtKE+8xgCgl/PQGuFsvShjr74PBp" crossorigin="anonymous">
</script>
<!--crypto js .........................................................................................................................-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/sha512.js"></script>
<script src="http://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/sha512.js"></script>
<!--Bootstrap .........................................................................................................................-->
<script src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"
    integrity="sha384-7aThvCh9TypR7fIc2HV4O/nFMVCBwyIUKL8XCtKE+8xgCgl/PQGuFsvShjr74PBp" crossorigin="anonymous">
</script>
<!--Jquery .............................................................................................................................-->
<script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
    crossorigin="anonymous"></script>
<!--form validation...........................................................................................................................-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.2.1/jquery.form-validator.min.js"></script>
<script src="http://localhost:8080/js/login.js"></script>

</html>